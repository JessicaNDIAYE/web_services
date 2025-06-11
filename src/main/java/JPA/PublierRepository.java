package JPA;

import POJO.Publier;
import POJO.PublierId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublierRepository extends JpaRepository<Publier, PublierId> {
    List<Publier> findByChannelId(Integer channelId);
}
