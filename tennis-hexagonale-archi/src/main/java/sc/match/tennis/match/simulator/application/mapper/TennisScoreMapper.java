package sc.match.tennis.match.simulator.application.mapper;

import org.mapstruct.Mapper;
import sc.match.tennis.match.simulator.application.port.in.model.TennisScoreModel;
import sc.match.tennis.match.simulator.domain.TennisScore;

@Mapper
public interface TennisScoreMapper {

    TennisScoreModel map(TennisScore tennisScore);
}
