package simon.chareyron.tennis.controller.impl;

import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;

public class TennisMatchSimulatorControllerImpl<T> implements TennisMatchSimulatorController {

    private final SimulateTennisMatchUseCase<T> tennisMatchUseCase;

    public TennisMatchSimulatorControllerImpl(SimulateTennisMatchUseCase tennisMatchUseCase) {
        this.tennisMatchUseCase = tennisMatchUseCase;
    }

    @Override
    public T simulateTennisMatch(int nbWinningSet) {
        return tennisMatchUseCase.playRandomMatch(nbWinningSet);
    }
}
