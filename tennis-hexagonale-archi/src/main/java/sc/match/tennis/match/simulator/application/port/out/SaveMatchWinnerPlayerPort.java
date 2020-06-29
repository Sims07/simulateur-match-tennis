package sc.match.tennis.match.simulator.application.port.out;

import sc.match.tennis.match.simulator.domain.Player;

public interface SaveMatchWinnerPlayerPort {

    void saveMatchWinnerPlayer(Player winnerMatchPlayer);
}
