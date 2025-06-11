package JPA;

import POJO.EspaceDeTravail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspaceDeTravailRepository extends JpaRepository<EspaceDeTravail, Integer> {
    List<EspaceDeTravail> findByNomEspaceDeTravailContaining(String nom);
}
