package simon.chareyron.coding.tennisrules;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import simon.chareyron.coding.tennisrules.domain.Player;
import simon.chareyron.coding.tennisrules.domain.Referee;
import simon.chareyron.coding.tennisrules.domain.TennisScore;

public class BDDTennisAssertion {

    private final Referee referee;
    private final TennisScore tennisScore;

    public BDDTennisAssertion(TennisScore tennisScore, int nbWinningSet) {
        this.referee = new Referee(tennisScore, nbWinningSet);
        this.tennisScore = tennisScore;
    }

    public BDDTennisAssertion whenPlayerWinPoint(String winnerPointPlayer) {
        Player player = "1".equals(winnerPointPlayer) ? Player._1 : Player._2;
        referee.winPoint(player);
        return this;
    }

    public BDDTennisAssertion thenSetScoreIs(String... expectedSetScores) {
        AtomicInteger setIndex = new AtomicInteger(1);
        Arrays.asList(expectedSetScores).forEach(expectedSetScore -> {
            String[] setScorePlayers = expectedSetScore.split("-");
            then(tennisScore.getSetScore(setIndex.get()).getScorePlayer1().toString()).isEqualTo(setScorePlayers[0]);
            then(tennisScore.getSetScore(setIndex.get()).getScorePlayer2().toString()).isEqualTo(setScorePlayers[1]);
            setIndex.getAndIncrement();
        });
        return this;
    }

    public BDDTennisAssertion thenGameScoreIs(String expectedGameScore) {
        String[] gameScorePlayers = expectedGameScore.split("-");
        then(tennisScore.getGameScore().getScorePlayer1()).isEqualTo(gameScorePlayers[0]);
        then(tennisScore.getGameScore().getScorePlayer2()).isEqualTo(gameScorePlayers[1]);
        return this;
    }

    public BDDTennisAssertion thenTieBreakScoreIs(String expectedTieBreakScore) {
        String[] gameScorePlayers = expectedTieBreakScore.split("-");
        then(tennisScore.getTieBreakScore().getScorePlayer1().toString()).isEqualTo(gameScorePlayers[0]);
        then(tennisScore.getTieBreakScore().getScorePlayer2().toString()).isEqualTo(gameScorePlayers[1]);
        return this;
    }

    public void thenWinningPlayerIs(String winningPlayer) {
        then(formatPlayer()).isEqualTo(winningPlayer);
    }

    private String formatPlayer() {
        return referee.getWiningPlayer() == Player._1 ? "1" : "2";
    }
}
