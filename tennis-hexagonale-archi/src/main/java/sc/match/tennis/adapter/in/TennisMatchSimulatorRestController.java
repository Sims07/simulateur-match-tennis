package sc.match.tennis.adapter.in;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import sc.match.tennis.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.application.port.in.model.TennisScoreModel;


@RestController
@CrossOrigin
@RequestMapping("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final PlayRandomMatchUseCase tennisMatchUseCase;

    public TennisMatchSimulatorRestController(PlayRandomMatchUseCase tennisMatchUseCase) {
        this.tennisMatchUseCase = tennisMatchUseCase;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TennisScoreModel> tennisMatchSimulation() {
        return Flux.fromIterable(tennisMatchUseCase.playRandomMatch(2))
                .delayElements(Duration.of(100, ChronoUnit.MILLIS));
    }

}
