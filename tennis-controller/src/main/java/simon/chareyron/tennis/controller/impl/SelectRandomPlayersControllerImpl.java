package simon.chareyron.tennis.controller.impl;

import simon.chareyron.tennis.controller.SelectRandomPlayersController;
import simon.chareyron.tennis.usecase.SelectRandomPlayersUseCase;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

public class SelectRandomPlayersControllerImpl implements SelectRandomPlayersController {

    private final SelectRandomPlayersUseCase selectRandomPlayersUseCase;

    public SelectRandomPlayersControllerImpl(SelectRandomPlayersUseCase selectRandomPlayersUseCase) {
        this.selectRandomPlayersUseCase = selectRandomPlayersUseCase;
    }

    @Override
    public List<PlayerModel> selectRandomPlayers(int nbPlayers) {
        return selectRandomPlayersUseCase.selectRandomPlayers(nbPlayers);
    }
}
