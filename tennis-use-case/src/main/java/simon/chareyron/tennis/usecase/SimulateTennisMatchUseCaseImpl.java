package simon.chareyron.tennis.usecase;

import java.util.Random;
import simon.chareyron.coding.tennisrules.domain.Player;
import simon.chareyron.coding.tennisrules.domain.Referee;
import simon.chareyron.coding.tennisrules.domain.TennisScore;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

/**
 * @author djz4712
 */
public class SimulateTennisMatchUseCaseImpl implements SimulateTennisMatchUseCase {

    private final SimulateTennisMatchOutput simulateTennisMatchOutput;
    private final Random random = new Random();

    public SimulateTennisMatchUseCaseImpl(SimulateTennisMatchOutput simulateTennisMatchOutput) {
        this.simulateTennisMatchOutput = simulateTennisMatchOutput;
    }

    @Override
    public void playRandomMatch(int nbWinningSet) {
        TennisScore tennisScore = initScoreMatchTennis();
        Referee referee = new Referee(tennisScore, nbWinningSet);
        while (referee.getWiningPlayer() == null) {
            Player winnerPointPlayer = selectRandomlyWinningPlayer(random);
            referee.winPoint(winnerPointPlayer);
            System.out.println(tennisScore);
            simulateTennisMatchOutput.onScoreChanged(winnerPointPlayer.ordinal(),map(tennisScore));
        }
        simulateTennisMatchOutput.onPlayerWinTheMatch(referee.getWiningPlayer().ordinal(), map(tennisScore));
    }

    private TennisScore initScoreMatchTennis() {
        TennisScore tennisScore = new TennisScore();
        tennisScore.setSetScorePlayer(Player._1, 0, 1);
        tennisScore.setSetScorePlayer(Player._2,0,1);
        tennisScore.setGameScorePlayer(Player._1,"0");
        tennisScore.setGameScorePlayer(Player._2,"0");
        return tennisScore;
    }

    private TennisScoreModel map(TennisScore tennisScore) {
        // TODO add mapping
        return new TennisScoreModel();
    }

    private Player selectRandomlyWinningPlayer(Random random) {
        return random.nextInt() % 2 == 0 ? Player._1 : Player._2;
    }
}
