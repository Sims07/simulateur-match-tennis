package simon.chareyron.tennis.usecase.impl;

import simon.chareyron.tennis.usecase.SelectRandomPlayersUseCase;
import simon.chareyron.tennis.usecase.gateway.PlayerGateway;
import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

public class SelectRandomPlayersUseCaseImpl implements SelectRandomPlayersUseCase {

    private final PlayerGateway playerGateway;

    public SelectRandomPlayersUseCaseImpl(PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
    }

    @Override
    public List<PlayerModel> selectRandomPlayers(int nbPlayers) {
        return playerGateway.loadRandomPlayers(nbPlayers);
    }
}
