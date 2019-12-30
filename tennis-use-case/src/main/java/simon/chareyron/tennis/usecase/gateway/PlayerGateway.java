package simon.chareyron.tennis.usecase.gateway;

import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

public interface PlayerGateway {
    List<PlayerModel> loadRandomPlayers(int nbPlayersToLoad);
}
