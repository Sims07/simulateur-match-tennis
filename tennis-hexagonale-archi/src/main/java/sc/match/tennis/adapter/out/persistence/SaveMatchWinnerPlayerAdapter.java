package sc.match.tennis.adapter.out.persistence;

import sc.match.tennis.adapter.out.persistence.model.WinnerMatchPlayerEntity;
import sc.match.tennis.adapter.out.persistence.repository.WinnerMatchPlayerRepository;
import sc.match.tennis.application.port.out.SaveMatchWinnerPlayerPort;
import sc.match.tennis.domain.Player;

public class SaveMatchWinnerPlayerAdapter implements SaveMatchWinnerPlayerPort {

    private final WinnerMatchPlayerRepository winnerMatchPlayerRepository;

    public SaveMatchWinnerPlayerAdapter(WinnerMatchPlayerRepository winnerMatchPlayerRepository) {
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
