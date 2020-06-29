package sc.match.tennis.application.port.out;

import sc.match.tennis.domain.Player;

public interface SaveMatchWinnerPlayerPort {

    void saveMatchWinnerPlayer(Player winnerMatchPlayer);
}
