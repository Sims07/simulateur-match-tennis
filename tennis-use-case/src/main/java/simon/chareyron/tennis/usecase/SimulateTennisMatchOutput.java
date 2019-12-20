package simon.chareyron.tennis.usecase;

import simon.chareyron.tennis.usecase.model.TennisScoreModel;

/**
 * @author djz4712
 */
public interface SimulateTennisMatchOutput {

    void onScoreChanged(int winningPlayer, TennisScoreModel tennisScoreModel);
}
