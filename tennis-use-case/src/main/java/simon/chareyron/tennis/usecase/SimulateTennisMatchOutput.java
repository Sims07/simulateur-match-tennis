package simon.chareyron.tennis.usecase;

import simon.chareyron.tennis.usecase.model.TennisScoreModel;

import java.util.List;

/**
 * @author djz4712
 */
public interface SimulateTennisMatchOutput<T> {

    T presentMatchScores(List<TennisScoreModel> tennisScoreModel);
}
