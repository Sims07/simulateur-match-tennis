package simon.chareyron.tennis.console;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
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
        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            new SimulateTennisMatchUseCaseImpl(new TennisScoreMapperImpl(), new SimulateTennisMatchOutput() {
                @Override
                public void onScoreChanged(int i, TennisScoreModel tennisScoreModel) {
                    fluxSink.next(tennisScoreModel.toString());
                }

                @Override
                public void onPlayerWinTheMatch(int i, TennisScoreModel tennisScoreModel) {
                    fluxSink.next("Player:" + i + " win the match");
                }
            }).playRandomMatch(2);
        }).delayElements(Duration.of(100, ChronoUnit.MILLIS));
        objectFlux.subscribe(System.out::println);
        objectFlux.blockLast();
    }
}
