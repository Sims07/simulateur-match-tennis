package simon.chareyron.tennis.usecase.impl;

import simon.chareyron.coding.tennisrules.domain.Player;
import simon.chareyron.coding.tennisrules.domain.Referee;
import simon.chareyron.coding.tennisrules.domain.TennisScore;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author djz4712
 */
public class SimulateTennisMatchUseCaseImpl<T> implements SimulateTennisMatchUseCase<T> {

    private final TennisScoreMapper tennisScoreMapper;
    private final Random random = new Random();

    public SimulateTennisMatchUseCaseImpl(TennisScoreMapper tennisScoreMapper) {
        this.tennisScoreMapper = tennisScoreMapper;
    }

    @Override
    public List<TennisScoreModel> playRandomMatch(int nbWinningSet) {
        List<TennisScoreModel> tennisScoreModel = new ArrayList<>();
        TennisScore tennisScore = initScoreMatchTennis();
        Referee referee = new Referee(tennisScore, nbWinningSet);
        while (referee.getWiningPlayer() == null) {
            Player winnerPointPlayer = selectRandomlyWinningPlayer(random);
            referee.winPoint(winnerPointPlayer);
            tennisScoreModel.add(map(tennisScore));
        }
        tennisScoreModel.add(map(tennisScore));
        return tennisScoreModel;
    }

    private TennisScore initScoreMatchTennis() {
        TennisScore tennisScore = new TennisScore();
        tennisScore.setSetScorePlayer(Player._1, 0, 1);
        tennisScore.setSetScorePlayer(Player._2, 0, 1);
        tennisScore.setGameScorePlayer(Player._1, "0");
        tennisScore.setGameScorePlayer(Player._2, "0");
        return tennisScore;
    }

    private TennisScoreModel map(TennisScore tennisScore) {
        return tennisScoreMapper.map(tennisScore);
    }

    private Player selectRandomlyWinningPlayer(Random random) {
        return random.nextInt() % 2 == 0 ? Player._1 : Player._2;
    }
}
