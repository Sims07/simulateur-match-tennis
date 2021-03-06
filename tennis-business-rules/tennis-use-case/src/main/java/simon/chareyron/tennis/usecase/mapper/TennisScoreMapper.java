package simon.chareyron.tennis.usecase.mapper;

import simon.chareyron.coding.tennisrules.domain.TennisScore;
import simon.chareyron.tennis.usecase.model.score.TennisScoreModel;

public interface TennisScoreMapper {

    TennisScoreModel map(TennisScore tennisScore);
}
