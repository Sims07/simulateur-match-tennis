package tennis.aws.lambda.micronaut.app;

import io.micronaut.context.annotation.Factory;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.impl.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;

import javax.inject.Singleton;

@Factory
public class AppConfig {

    @Singleton
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapStrutMapperImpl();
    }

    @Singleton
    public SimulateTennisMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper) {
        return new SimulateTennisMatchUseCaseImpl(tennisScoreMapper);
    }

}
