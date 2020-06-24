package sc.match.tennis.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sc.match.tennis.application.mapper.TennisScoreMapper;
import sc.match.tennis.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.application.port.in.model.TennisScoreModel;
import sc.match.tennis.domain.Player;
import sc.match.tennis.domain.Referee;
import sc.match.tennis.domain.TennisScore;


/**
 * @author djz4712
 */
public class PlayRandomMatchService implements PlayRandomMatchUseCase {

    private final TennisScoreMapper tennisScoreMapper;
    private final Random random = new Random();

    public PlayRandomMatchService(TennisScoreMapper tennisScoreMapper) {
        this.tennisScoreMapper = tennisScoreMapper;
    }

    @Override
    public List<TennisScoreModel> playRandomMatch(int nbWinningSet) {
        List<TennisScoreModel> tennisScoreModel = new ArrayList<>();
        TennisScore tennisScore  = new TennisScore();
        Referee referee = new Referee(tennisScore, nbWinningSet);

        while (matchIsPlaying(referee)) {
            playARandomPoint(tennisScoreModel, tennisScore, referee);
        }

        return tennisScoreModel;
    }

    private void playARandomPoint(List<TennisScoreModel> tennisScoreModel, TennisScore tennisScore, Referee referee) {
        Player winnerPointPlayer = selectRandomlyWinningPlayer(random);
        referee.winPoint(winnerPointPlayer);
        tennisScoreModel.add(map(tennisScore));
    }

    private boolean matchIsPlaying(Referee referee) {
        return referee.getWiningPlayer() == null;
    }


    private TennisScoreModel map(TennisScore tennisScore) {
        return tennisScoreMapper.map(tennisScore);
    }

    private Player selectRandomlyWinningPlayer(Random random) {
        return random.nextInt() % 2 == 0 ? Player._1 : Player._2;
    }
}
