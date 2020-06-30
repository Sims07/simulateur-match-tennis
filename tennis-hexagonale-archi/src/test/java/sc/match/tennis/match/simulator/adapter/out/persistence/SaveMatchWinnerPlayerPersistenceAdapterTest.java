package sc.match.tennis.match.simulator.adapter.out.persistence;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import sc.match.tennis.match.simulator.adapter.out.persistence.model.WinnerMatchPlayerEntity;
import sc.match.tennis.match.simulator.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.match.simulator.domain.Player;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Import({ SaveMatchWinnerPlayerPersistenceAdapter.class })
class SaveMatchWinnerPlayerPersistenceAdapterTest {

    @Autowired
    private SaveMatchWinnerPlayerPersistenceAdapter saveMatchWinnerPlayerPersistenceAdapter;

    @Autowired
    private WinnerMatchPlayerRepository winnerMatchPlayerRepository;

    @Test
    public void should_saveWinnerPlayer() {
        saveMatchWinnerPlayerPersistenceAdapter.saveMatchWinnerPlayer(Player._1);

        List<WinnerMatchPlayerEntity> matchWinnerPlayers = winnerMatchPlayerRepository.findAll();
        then(matchWinnerPlayers).isNotEmpty();
        then(matchWinnerPlayers).size().isEqualTo(1);
        then(matchWinnerPlayers.get(0).getPlayer()).isEqualTo(Player._1.name());
    }

}