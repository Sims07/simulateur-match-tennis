package tennis.api.gateway.aws.lamda.micronaut.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.util.ArrayList;
import java.util.List;

@Controller("/tennisMatchSimulation")
public class TennisMatchSimulatorRestController {

    private final TennisMatchSimulatorController tennisMatchSimulatorController;

    public TennisMatchSimulatorRestController(TennisMatchSimulatorController tennisMatchSimulatorController) {
        this.tennisMatchSimulatorController = tennisMatchSimulatorController;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<TennisScoreModel> tennisMatchSimulation() {
        List<TennisScoreModel> scoresMatch = new ArrayList<>();
        tennisMatchSimulatorController
                .simulateTennisMatch(2).doOnNext(scoresMatch::add).blockLast();
        return scoresMatch;
    }

}
