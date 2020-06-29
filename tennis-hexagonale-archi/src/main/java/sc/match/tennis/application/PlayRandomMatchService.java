package sc.match.tennis.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;
import sc.match.tennis.application.mapper.TennisScoreMapper;
import sc.match.tennis.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.application.port.in.model.TennisScoreModel;
import sc.match.tennis.application.port.out.SaveMatchWinnerPlayerPort;
import sc.match.tennis.domain.Player;
import sc.match.tennis.domain.Referee;
import sc.match.tennis.domain.TennisScore;


public class PlayRandomMatchService implements PlayRandomMatchUseCase {

    private final TennisScoreMapper tennisScoreMapper;
    private final SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort;
    private final Random random = new Random();

    public PlayRandomMatchService(TennisScoreMapper tennisScoreMapper, SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort) {
        this.tennisScoreMapper = tennisScoreMapper;
        this.saveMatchWinnerPlayerPort = saveMatchWinnerPlayerPort;
    }

    @Override
    @Transactional
    public List<TennisScoreModel> playRandomMatch(int nbWinningSet) {
        validateNbWinningSet(nbWinningSet);

        List<TennisScoreModel> tennisScoreModel = new ArrayList<>();
        TennisScore tennisScore = new TennisScore();
        Referee referee = new Referee(tennisScore, nbWinningSet);
        while (matchIsPlaying(referee)) {
            playARandomPoint(tennisScore, referee);
            tennisScoreModel.add(map(tennisScore));
        }
        saveMatchWinnerPlayerPort.saveMatchWinnerPlayer(referee.getWiningPlayer());

        return tennisScoreModel;
    }


    private void playARandomPoint(TennisScore tennisScore, Referee referee) {
        Player winnerPointPlayer = selectRandomlyWinningPlayer(random);
        referee.winPoint(winnerPointPlayer);
    }

    private void validateNbWinningSet(int nbWinningSet) {
        if (nbWinningSet < 2 || nbWinningSet > 3) {
            throw new IllegalArgumentException("Number of winning state must be 2 or 3.");
        }
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
