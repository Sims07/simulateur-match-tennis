package sc.match.tennis.application.mapper;

import org.mapstruct.Mapper;
import sc.match.tennis.application.port.in.model.TennisScoreModel;
import sc.match.tennis.domain.TennisScore;

@Mapper
public interface TennisScoreMapper {

    TennisScoreModel map(TennisScore tennisScore);
}
