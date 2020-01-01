package simon.chareyron.tennis.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RestController
@CrossOrigin
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
