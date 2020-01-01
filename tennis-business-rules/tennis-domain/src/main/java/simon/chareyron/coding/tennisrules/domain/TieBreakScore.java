package simon.chareyron.coding.tennisrules.domain;

import java.util.Arrays;

public class TieBreakScore extends Score<Integer> {
    @Override
    protected Integer[] initOrderedScores() {
        Integer[] orderedScore = new Integer[50];
        Arrays.setAll(orderedScore, i -> i);
        return orderedScore;
    }

    @Override
    protected boolean isPlayerWon(Player winnerPointPlayer) {
        Integer winnerPlayerScore = getScorePlayer(winnerPointPlayer);
        return winnerPlayerScore >= 7 && getScoreLooserPlayer(winnerPointPlayer) + 2 <= winnerPlayerScore;
    }
}
 