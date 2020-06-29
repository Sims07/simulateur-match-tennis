package sc.match.tennis.match.simulator.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sc.match.tennis.match.simulator.adapter.out.persistence.model.WinnerMatchPlayerEntity;

public interface WinnerMatchPlayerRepository extends JpaRepository<WinnerMatchPlayerEntity, Long> {
}
