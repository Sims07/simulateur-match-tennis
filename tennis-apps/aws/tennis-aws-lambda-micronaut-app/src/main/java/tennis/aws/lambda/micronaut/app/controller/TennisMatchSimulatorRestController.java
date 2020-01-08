package tennis.aws.lambda.micronaut.app.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.util.List;

@Controller("/")
public class TennisMatchSimulatorRestController {

    private final SimulateTennisMatchUseCase simulateTennisMatchUseCase;

    public TennisMatchSimulatorRestController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        this.simulateTennisMatchUseCase = simulateTennisMatchUseCase;
    }

    @Get(uri = "/tennisMatchSimulation", produces = MediaType.APPLICATION_JSON)
    public List<TennisScoreModel> tennisMatchSimulation() {
        return simulateTennisMatchUseCase.playRandomMatch(2);
    }
}
