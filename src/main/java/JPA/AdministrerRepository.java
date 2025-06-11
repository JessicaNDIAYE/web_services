package JPA;

import POJO.Administrer;
import POJO.AdministrerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministrerRepository extends JpaRepository<Administrer, AdministrerId> {
    List<Administrer> findByUtilisateurId(Integer utilisateurId);
}
