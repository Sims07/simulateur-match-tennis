package simon.chareyron.tennis.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
