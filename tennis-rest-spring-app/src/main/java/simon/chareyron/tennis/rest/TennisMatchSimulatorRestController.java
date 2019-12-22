package simon.chareyron.tennis.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenter;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;
    private final ReactiveSimulateMatchPresenter reactiveSimulateMatchPresenter;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController, ReactiveSimulateMatchPresenter reactiveSimulateMatchPresenter) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
        this.reactiveSimulateMatchPresenter = reactiveSimulateMatchPresenter;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TennisScoreModel> tennisMatchSimulation() {
        return Flux.<TennisScoreModel>create(fluxSink -> {
            reactiveSimulateMatchPresenter.setCurrentFluxSink(fluxSink);
            tennisMatchSimulatorController.simulateTennisMatch(2);
        }).delayElements(Duration.of(100, ChronoUnit.MILLIS));
    }

}
