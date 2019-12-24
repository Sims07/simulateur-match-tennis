package simon.chareyron.tennis.rest;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController<Flux<TennisScoreModel>> tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TennisScoreModel> simulateMatch() {
        List<TennisScoreModel> tennisScoreModels = new ArrayList<>();
        tennisMatchSimulatorController.simulateTennisMatch(2).doOnNext(tennisScoreModels::add).blockLast();
        return tennisScoreModels;
    }
}