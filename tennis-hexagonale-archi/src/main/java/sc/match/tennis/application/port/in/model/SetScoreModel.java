package sc.match.tennis.application.port.in.model;

public class SetScoreModel extends ScoreModel<Integer> {

    private TieBreakScoreModel tieBreakScore;

    public TieBreakScoreModel getTieBreakScore() {
        return tieBreakScore;
    }

    public void setTieBreakScore(TieBreakScoreModel tieBreakScore) {
        this.tieBreakScore = tieBreakScore;
    }
}
