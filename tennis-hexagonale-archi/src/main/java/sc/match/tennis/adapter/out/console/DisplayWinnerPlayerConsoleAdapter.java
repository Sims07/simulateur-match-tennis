package sc.match.tennis.adapter.out.console;

import sc.match.tennis.application.port.out.SaveMatchWinnerPlayerPort;
import sc.match.tennis.domain.Player;

public class DisplayWinnerPlayerConsoleAdapter implements SaveMatchWinnerPlayerPort {

    @Override
    public void saveMatchWinnerPlayer(Player winnerMatchPlayer) {
        System.out.println(String.format("The winner is %s", winnerMatchPlayer.toString()));
    }

}
