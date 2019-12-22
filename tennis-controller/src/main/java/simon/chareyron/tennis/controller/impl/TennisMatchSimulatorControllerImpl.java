package simon.chareyron.tennis.controller.impl;

import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;

public class TennisMatchSimulatorControllerImpl implements TennisMatchSimulatorController {

    private final SimulateTennisMatchUseCase tennisMatchUseCase;

    public TennisMatchSimulatorControllerImpl(SimulateTennisMatchUseCase tennisMatchUseCase) {
        this.tennisMatchUseCase = tennisMatchUseCase;
    }

    @Override
    public void simulateTennisMatch(int nbWinningSet) {
        tennisMatchUseCase.playRandomMatch(nbWinningSet);
    }
}
