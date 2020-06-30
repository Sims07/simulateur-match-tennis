package sc.match.tennis.match.simulator.adapter.in.web;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sc.match.tennis.match.simulator.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.match.simulator.application.port.in.model.TennisScoreModel;


@RestController
@RequestMapping("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final PlayRandomMatchUseCase playRandomMatchUseCase;

    public TennisMatchSimulatorRestController(PlayRandomMatchUseCase playRandomMatchUseCase) {
        this.playRandomMatchUseCase = playRandomMatchUseCase;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TennisScoreModel> tennisMatchSimulation() {
        return Flux.fromIterable(playRandomMatchUseCase.playRandomMatch(2))
                .delayElements(Duration.of(100, ChronoUnit.MILLIS));
    }

}
