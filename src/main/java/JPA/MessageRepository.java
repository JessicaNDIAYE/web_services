package JPA;

import POJO.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    // Tous les messages d’un utilisateur
    List<Message> findByUtilisateurId(Integer utilisateurId);

    // Tous les messages d’un canal
    List<Message> findByChannelId(int channelId);

    // Les 5 derniers messages (par date décroissante)
    List<Message> findTop5ByOrderByDateEtHeureDesc();
}
