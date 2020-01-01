package simon.chareyron.tennis.controller;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

public interface TennisMatchSimulatorController {
    Flux<TennisScoreModel> simulateTennisMatch(int nbWinningSet);
}
