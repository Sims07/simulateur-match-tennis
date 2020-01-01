package simon.chareyron.tennis.console;

import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.usecase.impl.SimulateTennisMatchUseCaseImpl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author djz4712
 */
public class TennisMainConsole {

    public static void main(String[] args) {
        new TennisMatchSimulatorControllerImpl
                (new SimulateTennisMatchUseCaseImpl(
                        new TennisScoreMapStrutMapperImpl()
                )).simulateTennisMatch(2)
                .delayElements(Duration.of(100, ChronoUnit.MILLIS))
                .doOnNext(System.out::println)
                .blockLast();
    }
}
