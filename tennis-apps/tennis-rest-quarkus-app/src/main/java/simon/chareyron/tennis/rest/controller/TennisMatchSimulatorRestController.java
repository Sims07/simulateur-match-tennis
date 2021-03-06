package simon.chareyron.tennis.rest.controller;

import io.reactivex.Flowable;
import reactor.adapter.rxjava.RxJava2Adapter;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Path("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @GET
    @Produces("text/event-stream")
    public Flowable<TennisScoreModel> simulateMatch() {
        return RxJava2Adapter.fluxToFlowable(tennisMatchSimulatorController.simulateTennisMatch(2).delayElements(Duration.of(100, ChronoUnit.MILLIS)));
    }
}