package sc.match.tennis.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;

import sc.match.tennis.adapter.out.persistence.model.WinnerMatchPlayerEntity;
import sc.match.tennis.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.application.port.out.LoadWinnerMatchPlayersPort;
import sc.match.tennis.domain.Player;

public class LoadWinnerMatchPlayersAdapter implements LoadWinnerMatchPlayersPort {

    private final WinnerMatchPlayerRepository winnerMatchPlayerRepository;

    public LoadWinnerMatchPlayersAdapter(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
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
