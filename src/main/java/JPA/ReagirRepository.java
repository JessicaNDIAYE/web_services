package JPA;

import POJO.Reagir;
import POJO.ReagirId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReagirRepository extends JpaRepository<Reagir, ReagirId> {
    List<Reagir> findByMessageId(Integer messageId);
}
