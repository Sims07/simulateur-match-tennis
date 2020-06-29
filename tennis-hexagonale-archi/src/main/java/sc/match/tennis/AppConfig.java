package sc.match.tennis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sc.match.tennis.adapter.out.console.DisplayWinnerPlayerConsoleAdapter;
import sc.match.tennis.adapter.out.persistence.LoadWinnerMatchPlayersAdapter;
import sc.match.tennis.adapter.out.persistence.SaveMatchWinnerPlayerAdapter;
import sc.match.tennis.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.application.GetWinnerMatchPlayersService;
import sc.match.tennis.application.PlayRandomMatchService;
import sc.match.tennis.application.mapper.TennisScoreMapper;
import sc.match.tennis.application.mapper.TennisScoreMapperImpl;
import sc.match.tennis.application.port.in.GetWinnerMatchPlayersQuery;
import sc.match.tennis.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.application.port.out.LoadWinnerMatchPlayersPort;
import sc.match.tennis.application.port.out.SaveMatchWinnerPlayerPort;


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
        return new SaveMatchWinnerPlayerAdapter(winnerMatchPlayerRepository);
    }

    @Bean
    public LoadWinnerMatchPlayersPort loadWinnerMatchPlayersPort(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
        return new LoadWinnerMatchPlayersAdapter(winnerMatchPlayerRepository);
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
