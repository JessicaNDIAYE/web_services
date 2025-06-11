package JPA;

import POJO.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    List<Channel> findByEspaceDeTravailId(Integer espaceId);
}
