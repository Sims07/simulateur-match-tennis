package sc.match.tennis.adapter.out.persistence;

import org.springframework.data.repository.CrudRepository;
import sc.match.tennis.adapter.out.persistence.model.WinnerMatchPlayerEntity;

public interface WinnerMatchPlayerRepository extends CrudRepository<WinnerMatchPlayerEntity, Long> {
}
