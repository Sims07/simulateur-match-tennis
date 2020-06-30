package sc.match.tennis.match.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sc.match.tennis.match.simulator.adapter.out.console.DisplayWinnerPlayerConsoleAdapter;
import sc.match.tennis.match.simulator.adapter.out.persistence.LoadWinnerMatchPlayerPersistenceAdapter;
import sc.match.tennis.match.simulator.adapter.out.persistence.SaveMatchWinnerPlayerPersistenceAdapter;
import sc.match.tennis.match.simulator.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.match.simulator.application.GetWinnerMatchPlayersService;
import sc.match.tennis.match.simulator.application.PlayRandomMatchService;
import sc.match.tennis.match.simulator.application.mapper.TennisScoreMapper;
import sc.match.tennis.match.simulator.application.mapper.TennisScoreMapperImpl;
import sc.match.tennis.match.simulator.application.port.in.GetWinnerMatchPlayersQuery;
import sc.match.tennis.match.simulator.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.match.simulator.application.port.out.LoadWinnerMatchPlayersPort;
import sc.match.tennis.match.simulator.application.port.out.SaveMatchWinnerPlayerPort;


@Configuration
public class AppConfig {

    @Bean
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapperImpl();
    }

    @Bean
    @Profile("console")
    public SaveMatchWinnerPlayerPort displayWinnerPlayerConsoleAdapter() {
        return new DisplayWinnerPlayerConsoleAdapter();
    }

    @Bean
    @Profile("db")
    public SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
        return new SaveMatchWinnerPlayerPersistenceAdapter(winnerMatchPlayerRepository);
    }

    @Bean
    public LoadWinnerMatchPlayersPort loadWinnerMatchPlayersPort(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
        return new LoadWinnerMatchPlayerPersistenceAdapter(winnerMatchPlayerRepository);
    }

    @Bean
    public GetWinnerMatchPlayersQuery getWinnerMatchPlayersQuery(LoadWinnerMatchPlayersPort loadWinnerMatchPlayersPort) {
        return new GetWinnerMatchPlayersService(loadWinnerMatchPlayersPort);
    }

    @Bean
    public PlayRandomMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper, SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort) {
        return new PlayRandomMatchService(tennisScoreMapper, saveMatchWinnerPlayerPort);
    }


}
