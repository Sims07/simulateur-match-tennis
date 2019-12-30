package simon.chareyron.tennis.controller.impl;

import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

public class TennisMatchSimulatorControllerImpl implements TennisMatchSimulatorController {

    private final SimulateTennisMatchUseCase tennisMatchUseCase;

    public TennisMatchSimulatorControllerImpl(SimulateTennisMatchUseCase tennisMatchUseCase) {
        this.tennisMatchUseCase = tennisMatchUseCase;
    }

    @Override
    public Flux<TennisScoreModel> simulateTennisMatch(int nbWinningSet) {
        return Flux.fromIterable(tennisMatchUseCase.playRandomMatch(nbWinningSet));
    }
}
