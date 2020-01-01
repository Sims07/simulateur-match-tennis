package simon.chareyron.tennis.usecase;

import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

import java.util.List;

/**
 * @author djz4712
 */
public interface SimulateTennisMatchUseCase<T> {

    List<TennisScoreModel> playRandomMatch(int nbWinningSet);
}
