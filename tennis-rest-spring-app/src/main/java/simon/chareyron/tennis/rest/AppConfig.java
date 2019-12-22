package simon.chareyron.tennis.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenter;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenterImpl;
import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapperImpl;

@Configuration
public class AppConfig {

    @Bean
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }

    @Bean
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapperImpl();
    }

    @Bean
    public SimulateTennisMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper, SimulateTennisMatchOutput simulateTennisMatchOutput) {
        return new SimulateTennisMatchUseCaseImpl(tennisScoreMapper, simulateTennisMatchOutput);
    }

    //TODO do not use singleton we need one by request because this bean is statefull and
    // scope request is not possible in reactive project.(ReactiveContext may be used)
    @Bean
    public ReactiveSimulateMatchPresenter simulateTennisMatchOutput() {
        return new ReactiveSimulateMatchPresenterImpl();
    }

}
