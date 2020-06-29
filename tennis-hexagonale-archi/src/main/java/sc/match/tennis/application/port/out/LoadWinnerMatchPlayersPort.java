package sc.match.tennis.application.port.out;

import java.util.List;

import sc.match.tennis.domain.Player;

public interface LoadWinnerMatchPlayersPort {

    List<Player> loadWinnerMatchPlayers();
}