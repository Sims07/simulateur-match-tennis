# simulateur-match-tennis 

[![Build Status](https://travis-ci.com/Sims07/simulateur-match-tennis.svg?branch=master)](https://travis-ci.com/Sims07/simulateur-match-tennis)

### Goals

The goals of this project is to be able to change of microservice frameworks without impacting the core of the application.
To do that we will use an adaptation of the clean architecture.

### Realisation

We put all microservice frameworks's codes and dependencies in the outermost layer. 
The apps in this outer layer contain a "main" method and so have access to all the modules to make the glue code.

To ensure dependency rules inward and prevent access from adapter layer to domain model we will use the modules of java 9.

We use clean architecture boundary to protect the core of our application of framework.

To simplify the architecture and stay focused only on the isolation of microservice framework we decide 
- to remove the tennis presenter
- to use a no mapping strategy for adapter layer and directly return the use-case's model in the controller.

We use bi-directional binding between use case's model and domain's model.
The mapper framework is put on the upper layer to let the business rules framework independent. 

![Component Diagram](CleanArchitectureTennis.svg)

## 1. Microservice Frameworks

### 1.1 App Configuration

The glue code used to start the application is different for each of the frameworks.
We want a non intrusive configuration and isolate it in the outer most layer (no annotation on the code in inward layers).

### 1.1.1 App Configuration with Spring

With spring we can use the non intrusive java based configuration.

```java
@Configuration
public class AppConfig {

    @Bean
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }
    ...
}
```

### 1.1.2 App Configuration with Quarkus

We use a quarkus adapter for spring DI which enable the use of the same configuration file.

### 1.1.3 App Configuration with Micronaut

Micronaut uses its own annotation to create factories.

```java
@Factory
public class AppConfig {

    @Singleton
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }

    ...
}
```

## 1.2 Rest Service

All microsevices framework have their own way to create a rest service with different annotations.
Spring MVC and Micronaut use their own annotation and Quarkus is based on JAX-RS.
Furthemore they rely on different implementations of reactive library (RxJava for Quarkus, Reactor for Spring and both for Micronaut).

That's why we choose to put the REST implementation in the outermost layer and not in the adapter layer.
The controller in the adapter layer only delegates the call to the use case.

### 1.2.1 Rest Service with Quarkus
```java
@Path("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @GET
    @Produces("text/event-stream")
    public Flowable<TennisScoreModel> simulateMatch() {
        return RxJava2Adapter.fluxToFlowable(tennisMatchSimulatorController
                                                .simulateTennisMatch(2)
                                                .delayElements(Duration.of(100, ChronoUnit.MILLIS)));
    }
}
```
### 1.2.2 Rest Service with Micronaut:
```java
@Controller("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @Get(produces = MediaType.TEXT_EVENT_STREAM)
    public Flux<TennisScoreModel> tennisMatchSimulation() {
        return tennisMatchSimulatorController
                .simulateTennisMatch(2)
                .delayElements(Duration.of(100, ChronoUnit.MILLIS));
    }

}
```
### 1.2.3 Rest Service with Spring :
```java
@RestController
@RequestMapping("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TennisScoreModel> tennisMatchSimulation() {
        return tennisMatchSimulatorController
                .simulateTennisMatch(2)
                .delayElements(Duration.of(100, ChronoUnit.MILLIS));
    }

}
```
## 2. AWS Lambda

We want now to test our application by exposing it via an AWS lambda.
We will develop the lambda with only amazon api and then use the micronaut aws features with and without the graal native-image. 

### 2.1 Aws lambda with java 11 runtime

It's simple to wrap our controller through an AWS stream handler.
Note : we have to remove the delay element of the flux because a lambda is not made for streaming.
 
```java
public class LambdaRequestStreamHandler implements RequestStreamHandler {

    private static final TennisMatchSimulatorController tennisMatchSimulatorController;
    private static final Gson gson;

    static {
        tennisMatchSimulatorController = new TennisMatchSimulatorFactoryImpl().build();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream, Context context) {
        tennisMatchSimulatorController.simulateTennisMatch(2)
                .doOnNext(score -> {
                    try {
                        outputStream.write(gson.toJson(score).getBytes(Charset.forName("UTF-8")));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                })
                .blockLast();
    }
}

```

On cold start we obtain the following trace:
```
REPORT RequestId: a9cebc7e-f761-4d81-855c-099def95efd4	Duration: 1113.04 ms	Billed Duration: 1200 ms	Memory Size: 512 MB	Max Memory Used: 101 MB	Init Duration: 284.49 ms
```
and for the calls after:

```
REPORT RequestId: cf990d79-f86c-452d-81c3-bc4c6227ffa9	Duration: 136.07 ms	Billed Duration: 200 ms	Memory Size: 512 MB	Max Memory Used: 102 MB
```
### 2.2 Aws lambda with micronaut and java 11 runtime

Micronaut offers a feature for aws lambda exposed via api gateway.
Thanks to a "dispatcher" based on aws proxy request Micronaut is able to call the right Rest service.
Therefore we don't have to develop a new service we just have to add the following class:

```java
public class StreamLambdaHandler implements RequestStreamHandler {
    private static MicronautLambdaContainerHandler handler; 
    ...
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}
```

We could call our tennis simulation with the following data test :  
```json
{
  "path": "/tennisMatchSimulation",
  "httpMethod": "GET"
}
```
On cold start we obtain the following trace:
```
REPORT RequestId: c4a5f4ab-27c1-4da9-94f5-009bec07d2f0	Duration: 1814.64 ms	Billed Duration: 1900 ms	Memory Size: 512 MB	Max Memory Used: 179 MB	Init Duration: 3387.89 ms
```
and for the calls after:

```
REPORT RequestId: 0abd9e0e-f34e-4a5f-9f83-c4d896b4b06e	Duration: 336.92 ms	Billed Duration: 400 ms	Memory Size: 512 MB	Max Memory Used: 180 MB
```

### 2.3 Aws lambda with micronaut Graal Native Image and java 11 runtime

First of all we have to upgrade the Graalvm version to 19.3.0 provided by Micronaut in the docker file to be able to use java 11.
For simplicity of integration with Graalvm we update our rest controller to remove dependencies with Reactor which causes some problems of compilation.

```java
@Controller("/")
public class TennisMatchSimulatorRestController {

    private final SimulateTennisMatchUseCase simulateTennisMatchUseCase;

    public TennisMatchSimulatorRestController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        this.simulateTennisMatchUseCase = simulateTennisMatchUseCase;
    }

    @Get(uri = "/tennisMatchSimulation", produces = MediaType.APPLICATION_JSON)
    public List<TennisScoreModel> tennisMatchSimulation() {
        return simulateTennisMatchUseCase.playRandomMatch(2);
    }
}
```
If we let the Micronaut configuration as is the project doesn't build because jackson use reflection to serialize a list or a map of object.
To fix these issues we have to add -H:ReflectionConfigurationFiles=reflectconfig to the native image command in Micronaut docker file and add a reflectconfig json file with the following content:
```json
[
  {
    "name": "simon.chareyron.tennis.usecase.model.score.TennisScoreModel",
    "allDeclaredFields": true,
    "allPublicFields": true,
    "allDeclaredMethods": true,
    "allPublicMethods": true
  },
  {
    "name": "simon.chareyron.tennis.usecase.model.score.SetScoreModel",
    "allDeclaredFields": true,
    "allPublicFields": true,
    "allDeclaredMethods": true,
    "allPublicMethods": true
  },
  {
    "name": "simon.chareyron.tennis.usecase.model.score.ScoreModel",
    "allDeclaredFields": true,
    "allPublicFields": true,
    "allDeclaredMethods": true,
    "allPublicMethods": true
  },
  {
    "name": "org.slf4j.impl.StaticLoggerBinder",
    "allDeclaredFields": true,
    "allPublicFields": true,
    "allDeclaredMethods": true,
    "allPublicMethods": true
  },
  {
    "name": "com.fasterxml.jackson.databind.ObjectMapper",
    "allDeclaredFields": true,
    "allPublicFields": true,
    "allDeclaredMethods": true,
    "allPublicMethods": true
  }
]
```

On cold start we obtain the following trace:
```
REPORT RequestId: e28cf9b7-8f43-4cd7-bdad-39076ca7baf4	Duration: 460.60 ms	Billed Duration: 1000 ms	Memory Size: 256 MB	Max Memory Used: 158 MB	Init Duration: 452.13 ms
```
and for the calls after:



```
REPORT RequestId: a4e199f0-3d4a-4d00-9df8-798a669be003	Duration: 13.39 ms	Billed Duration: 100 ms	Memory Size: 256 MB	Max Memory Used: 177 MB	
```

### 2.4 Results summary


|   Lambda Type	|   Duration (ms)	|  Billed Duration	|   Memory Size (MB)	|  Max Memory Used 	| Init Duration |
|---	|---	|---	|---	|---	|---	|
|  AWS API Cold Start 	|   1113.04	|   1200	|   512	|  101 	|  284.49 |
|  AWS API Start 	|   136.07	|  200 	|  512 	|   102	|   |
|  Micronaut Cold Start 	|  1814.64 	|  1900 	|  512 	|  179 	| 3387.89  |
|  Micronaut Start 	|  336.92 	|  400 	|   512	|  180 	|   |
|  Micronaut GraalVm Cold Start 	|  460.60 	|  1000 	|  256 	|  158 	| 452.13  |
|  Micronaut GraalVm Start 	|   13.39	|  100 	|   256	|   177	|   |
