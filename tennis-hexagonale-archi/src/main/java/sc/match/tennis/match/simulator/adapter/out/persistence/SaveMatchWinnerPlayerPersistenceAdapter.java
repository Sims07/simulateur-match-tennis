package sc.match.tennis.match.simulator.adapter.out.persistence;

import sc.match.tennis.match.simulator.adapter.out.persistence.model.WinnerMatchPlayerEntity;
import sc.match.tennis.match.simulator.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.match.simulator.application.port.out.SaveMatchWinnerPlayerPort;
import sc.match.tennis.match.simulator.domain.Player;

public class SaveMatchWinnerPlayerPersistenceAdapter implements SaveMatchWinnerPlayerPort {

    private final WinnerMatchPlayerRepository winnerMatchPlayerRepository;

    public SaveMatchWinnerPlayerPersistenceAdapter(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
        this.winnerMatchPlayerRepository = winnerMatchPlayerRepository;
    }

    @Override
    public void saveMatchWinnerPlayer(Player winnerMatchPlayer) {
        WinnerMatchPlayerEntity winnerMatchPlayerEntity = mapToEntity(winnerMatchPlayer);
        winnerMatchPlayerRepository.save(winnerMatchPlayerEntity);
    }

    private WinnerMatchPlayerEntity mapToEntity(Player winnerMatchPlayer) {
        WinnerMatchPlayerEntity winnerMatchPlayerEntity = new WinnerMatchPlayerEntity();
        winnerMatchPlayerEntity.setPlayer(winnerMatchPlayer.name());
        return winnerMatchPlayerEntity;
    }
}
