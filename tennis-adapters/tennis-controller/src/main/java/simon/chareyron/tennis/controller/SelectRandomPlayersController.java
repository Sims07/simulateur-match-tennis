package simon.chareyron.tennis.controller;

import simon.chareyron.tennis.usecase.model.actors.PlayerModel;

import java.util.List;

public interface SelectRandomPlayersController {

    List<PlayerModel> selectRandomPlayers(int nbPlayers);
}
