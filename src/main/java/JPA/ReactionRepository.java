package JPA;

import POJO.Reaction;
import POJO.ReactionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, ReactionId> {
    List<Reaction> findByMessageId(Integer messageId);
}
