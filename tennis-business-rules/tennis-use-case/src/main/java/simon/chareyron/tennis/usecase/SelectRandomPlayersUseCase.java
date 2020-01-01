package simon.chareyron.tennis.usecase;

import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

public interface SelectRandomPlayersUseCase {

    List<PlayerModel> selectRandomPlayers(int nbPlayers);
}
