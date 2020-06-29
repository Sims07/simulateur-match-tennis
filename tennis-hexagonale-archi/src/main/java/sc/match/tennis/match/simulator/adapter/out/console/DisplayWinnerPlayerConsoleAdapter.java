package sc.match.tennis.match.simulator.adapter.out.console;

import sc.match.tennis.match.simulator.application.port.out.SaveMatchWinnerPlayerPort;
import sc.match.tennis.match.simulator.domain.Player;

public class DisplayWinnerPlayerConsoleAdapter implements SaveMatchWinnerPlayerPort {

    @Override
    public void saveMatchWinnerPlayer(Player winnerMatchPlayer) {
        System.out.println(String.format("The winner is %s", winnerMatchPlayer.toString()));
    }

}
