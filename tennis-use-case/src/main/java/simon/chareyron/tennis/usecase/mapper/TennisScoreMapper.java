package simon.chareyron.tennis.usecase.mapper;

import org.mapstruct.Mapper;
import simon.chareyron.coding.tennisrules.domain.TennisScore;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

@Mapper
public interface TennisScoreMapper {

    TennisScoreModel map(TennisScore tennisScore);
}
