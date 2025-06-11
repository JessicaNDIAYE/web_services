package JPA;

import POJO.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByNomContaining(String nom);

    Optional<Utilisateur> findByEmailAndPassword(String email, String password);
}