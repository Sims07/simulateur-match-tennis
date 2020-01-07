package tennis.api.gateway.aws.lamda.micronaut.app;

import io.micronaut.context.annotation.Factory;
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

import javax.inject.Singleton;

@Factory
public class AppConfig {

    @Singleton
    public TennisMatchSimulatorController tennisMatchSimulatorController(SimulateTennisMatchUseCase simulateTennisMatchUseCase) {
        return new TennisMatchSimulatorControllerImpl(simulateTennisMatchUseCase);
    }

    @Singleton
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapStrutMapperImpl();
    }

    @Singleton
    public SelectRandomPlayersController selectRandomPlayersController(SelectRandomPlayersUseCase selectRandomPlayersUseCase) {
        return new SelectRandomPlayersControllerImpl(selectRandomPlayersUseCase);
    }

    @Singleton
    public SelectRandomPlayersUseCase selectRandomPlayersUseCase(PlayerGateway playerGateway) {
        return new SelectRandomPlayersUseCaseImpl(playerGateway);
    }

    @Singleton
    public PlayerGateway playerGateway() {
        return new PlayerGatewayInMemoryImpl();
    }

    @Singleton
    public SimulateTennisMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper) {
        return new SimulateTennisMatchUseCaseImpl(tennisScoreMapper);
    }

}
