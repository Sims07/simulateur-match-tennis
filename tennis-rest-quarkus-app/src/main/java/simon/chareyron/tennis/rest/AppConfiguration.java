package simon.chareyron.tennis.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenterImpl;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;

@Configuration
public class AppConfiguration {

    @Bean
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }

    @Bean
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapStrutMapperImpl();
    }

    @Bean
    public SimulateTennisMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper) {
        return new SimulateTennisMatchUseCaseImpl(tennisScoreMapper, new ReactiveSimulateMatchPresenterImpl());
    }

}