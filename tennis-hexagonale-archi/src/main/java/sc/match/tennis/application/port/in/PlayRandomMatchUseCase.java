package sc.match.tennis.application.port.in;

import java.util.List;

import sc.match.tennis.application.port.in.model.TennisScoreModel;

/**
 * @author djz4712
 */
public interface PlayRandomMatchUseCase {

    List<TennisScoreModel> playRandomMatch(int nbWinningSet);
}
