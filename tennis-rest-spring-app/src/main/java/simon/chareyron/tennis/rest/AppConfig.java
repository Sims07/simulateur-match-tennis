package simon.chareyron.tennis.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.presenter.ReactiveSimulateMatchPresenterImpl;
import simon.chareyron.tennis.usecase.SimulateTennisMatchOutput;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapperImpl;
import simon.chareyron.tennis.usecase.model.TennisScoreModel;

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

    @Bean
    public SimulateTennisMatchOutput<Flux<TennisScoreModel>> simulateTennisMatchOutput() {
        return new ReactiveSimulateMatchPresenterImpl();
    }

}
