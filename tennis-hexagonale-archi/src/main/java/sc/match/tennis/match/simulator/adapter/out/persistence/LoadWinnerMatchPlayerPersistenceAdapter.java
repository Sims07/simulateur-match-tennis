package sc.match.tennis.match.simulator.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import sc.match.tennis.match.simulator.adapter.out.persistence.model.WinnerMatchPlayerEntity;
import sc.match.tennis.match.simulator.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.match.simulator.application.port.out.LoadWinnerMatchPlayersPort;
import sc.match.tennis.match.simulator.domain.Player;

public class LoadWinnerMatchPlayerPersistenceAdapter implements LoadWinnerMatchPlayersPort {

    private final WinnerMatchPlayerRepository winnerMatchPlayerRepository;

    public LoadWinnerMatchPlayerPersistenceAdapter(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
        this.winnerMatchPlayerRepository = winnerMatchPlayerRepository;
    }

    @Override
    public List<Player> loadWinnerMatchPlayers() {
        return winnerMatchPlayerRepository.findAll().stream()
                .map(WinnerMatchPlayerEntity::getPlayer)
                .map(Player::valueOf)
                .collect(Collectors.toList());
    }
}
