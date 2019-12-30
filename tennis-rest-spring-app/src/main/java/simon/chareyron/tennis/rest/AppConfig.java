package simon.chareyron.tennis.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simon.chareyron.tennis.controller.SelectRandomPlayersController;
import simon.chareyron.tennis.controller.TennisMatchSimulatorController;
import simon.chareyron.tennis.controller.impl.SelectRandomPlayersControllerImpl;
import simon.chareyron.tennis.controller.impl.TennisMatchSimulatorControllerImpl;
import simon.chareyron.tennis.gateway.player.PlayerGatewayInMemoryImpl;
import simon.chareyron.tennis.mapper.mapstruct.TennisScoreMapStrutMapperImpl;
import simon.chareyron.tennis.usecase.SelectRandomPlayersUseCase;
import simon.chareyron.tennis.usecase.SimulateTennisMatchUseCase;
import simon.chareyron.tennis.usecase.gateway.PlayerGateway;
import simon.chareyron.tennis.usecase.impl.SelectRandomPlayersUseCaseImpl;
import simon.chareyron.tennis.usecase.impl.SimulateTennisMatchUseCaseImpl;
import simon.chareyron.tennis.usecase.mapper.TennisScoreMapper;

@Configuration
public class AppConfig {

    @Bean
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }

    @Bean
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapStrutMapperImpl();
    }

    @Bean
    public SelectRandomPlayersController selectRandomPlayersController(SelectRandomPlayersUseCase selectRandomPlayersUseCase) {
        return new SelectRandomPlayersControllerImpl(selectRandomPlayersUseCase);
    }

    @Bean
    public SelectRandomPlayersUseCase selectRandomPlayersUseCase(PlayerGateway playerGateway) {
        return new SelectRandomPlayersUseCaseImpl(playerGateway);
    }

    @Bean
    public PlayerGateway playerGateway() {
        return new PlayerGatewayInMemoryImpl();
    }

    @Bean
    public SimulateTennisMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper) {
        return new SimulateTennisMatchUseCaseImpl(tennisScoreMapper);
    }

}
