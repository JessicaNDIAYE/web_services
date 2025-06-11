package JPA;

import POJO.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUtilisateurId(Integer utilisateurId);

    List<Message> findByChannelId(int channelId);
}