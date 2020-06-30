package sc.match.tennis.match.simulator.application.port.in;

import java.util.List;

import sc.match.tennis.match.simulator.application.port.in.model.TennisScoreModel;

public interface PlayRandomMatchUseCase {

    List<TennisScoreModel> playRandomMatch(int nbWinningSet);
}
