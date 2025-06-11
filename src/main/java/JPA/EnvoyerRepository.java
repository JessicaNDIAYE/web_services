package JPA;

import POJO.Envoyer;
import POJO.EnvoyerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvoyerRepository extends JpaRepository<Envoyer, EnvoyerId> {
    List<Envoyer> findByUtilisateurId(Integer utilisateurId);
}
