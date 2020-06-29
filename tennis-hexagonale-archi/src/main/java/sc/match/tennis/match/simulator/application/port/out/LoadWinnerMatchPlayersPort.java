package sc.match.tennis.match.simulator.application.port.out;

import java.util.List;

import sc.match.tennis.match.simulator.domain.Player;

public interface LoadWinnerMatchPlayersPort {

    List<Player> loadWinnerMatchPlayers();
}