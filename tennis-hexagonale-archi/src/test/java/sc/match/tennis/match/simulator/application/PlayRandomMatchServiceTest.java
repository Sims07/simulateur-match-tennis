package sc.match.tennis.match.simulator.application;

import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import sc.match.tennis.match.simulator.application.mapper.TennisScoreMapper;
import sc.match.tennis.match.simulator.application.mapper.TennisScoreMapperImpl;
import sc.match.tennis.match.simulator.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.match.simulator.application.port.in.model.TennisScoreModel;
import sc.match.tennis.match.simulator.application.port.out.SaveMatchWinnerPlayerPort;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

class PlayRandomMatchServiceTest {
    @Test
    public void should_playRandomMatch_2_sets() {

        TennisScoreMapper tennisScoreMapper = new TennisScoreMapperImpl();
        SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort = mock(SaveMatchWinnerPlayerPort.class);
        PlayRandomMatchUseCase playRandomMatchUseCase = new PlayRandomMatchService(tennisScoreMapper, saveMatchWinnerPlayerPort);

        List<TennisScoreModel> simulatedMatch = playRandomMatchUseCase.playRandomMatch(2);

        then(simulatedMatch).isNotEmpty();
        BDDMockito.then(saveMatchWinnerPlayerPort).should().saveMatchWinnerPlayer(any());
    }

    @Test
    public void should_playRandomMatch_GreaterThan3_sets() {

        TennisScoreMapper tennisScoreMapper = new TennisScoreMapperImpl();
        SaveMatchWinnerPlayerPort saveMatchWinnerPlayerPort = mock(SaveMatchWinnerPlayerPort.class);
        PlayRandomMatchUseCase playRandomMatchUseCase = new PlayRandomMatchService(tennisScoreMapper, saveMatchWinnerPlayerPort);

        BDDAssertions.thenThrownBy(() -> playRandomMatchUseCase.playRandomMatch(4)).isInstanceOf(IllegalArgumentException.class);
    }

}