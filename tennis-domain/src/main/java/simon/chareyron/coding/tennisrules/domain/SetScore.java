package simon.chareyron.coding.tennisrules.domain;

public class SetScore extends Score<Integer> {

    private TieBreakScore tieBreakScore;

    protected Integer[] initOrderedScores() {
        return new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    protected boolean isPlayerWon(Player winnerPointPlayer) {

        return isPlayerWonWithoutTieBreak(winnerPointPlayer) || tieBreakScore != null;
    }

    private boolean isPlayerWonWithoutTieBreak(Player winnerPointPlayer) {
        return getScorePlayer(winnerPointPlayer) >= 6 && getScoreLooserPlayer(winnerPointPlayer) < 6;
    }

    public TieBreakScore getTieBreakScore() {
        return this.tieBreakScore;
    }

    public boolean isInTieBreak() {
        return getScorePlayer1() == 6 && getScorePlayer1().equals(getScorePlayer2());
    }

    public void setTieBreakScore(TieBreakScore tieBreakScore) {
        this.tieBreakScore = tieBreakScore;
    }

    public Player getWinninPlayer() {
        return isPlayerWon(Player._1)?Player._1:Player._2;
    }
}
