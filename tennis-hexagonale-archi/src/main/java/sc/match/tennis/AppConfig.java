package sc.match.tennis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sc.match.tennis.application.PlayRandomMatchService;
import sc.match.tennis.application.mapper.TennisScoreMapper;
import sc.match.tennis.application.mapper.TennisScoreMapperImpl;
import sc.match.tennis.application.port.in.PlayRandomMatchUseCase;


@Configuration
public class AppConfig {

    @Bean
    public TennisScoreMapper tennisScoreMapper() {
        return new TennisScoreMapperImpl();
    }

    @Bean
    public PlayRandomMatchUseCase simulateTennisMatchUseCase(TennisScoreMapper tennisScoreMapper) {
        return new PlayRandomMatchService(tennisScoreMapper);
    }

}
