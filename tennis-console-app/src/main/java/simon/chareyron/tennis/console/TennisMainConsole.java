package simon.chareyron.tennis.console;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenterImpl;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapperImpl;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author djz4712
 */
public class TennisMainConsole {

    public static void main(String[] args) {
        Flux<TennisScoreModel> objectFlux = Flux.<TennisScoreModel>create(fluxSink -> {
            ReactiveSimulateMatchPresenterImpl simulateTennisMatchOutput = new ReactiveSimulateMatchPresenterImpl();
            simulateTennisMatchOutput.setCurrentFluxSink(fluxSink);
            new TennisMatchSimulatorControllerImpl
                    (new SimulateTennisMatchUseCaseImpl(
                            new TennisScoreMapperImpl(),
                            simulateTennisMatchOutput
                    )).simulateTennisMatch(2);
        }).delayElements(Duration.of(1, ChronoUnit.MILLIS));
        objectFlux.subscribe(System.out::println);
        objectFlux.blockLast();
    }
}
