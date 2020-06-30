package sc.match.tennis.match.simulator.adapter.in.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import sc.match.tennis.match.simulator.application.port.in.PlayRandomMatchUseCase;
import sc.match.tennis.match.simulator.application.port.in.model.TennisScoreModel;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@WebFluxTest(controllers = { TennisMatchSimulatorRestController.class })
class TennisMatchSimulatorRestControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private PlayRandomMatchUseCase playRandomMatchUseCase;

    @Test
    public void should_playRandomMatch() {
        given(playRandomMatchUseCase.playRandomMatch(2))
                .willReturn(mockNScore(3));

        List<TennisScoreModel> res = webClient
                .get()
                .uri("/tennisMatchSimulation")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(TennisScoreModel.class)
                .getResponseBody()
                .take(3)
                .collectList()
                .block();

        then(res).isNotEmpty();
        then(res).size().isEqualTo(3);
    }

    private List<TennisScoreModel> mockNScore(int nbScore) {

        return IntStream.range(0, nbScore).mapToObj(i -> {
            return new TennisScoreModel();
        }).collect(Collectors.toList());

    }
}

