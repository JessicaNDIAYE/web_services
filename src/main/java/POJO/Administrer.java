package POJO;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "administrer")
@IdClass(AdministrerId.class)
public class Administrer {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espace_de_travail", nullable = false)
    private EspaceDeTravail espaceDeTravail;

    // Constructeurs, getters et setters
    public Administrer() {}

    public Administrer(Utilisateur utilisateur, EspaceDeTravail espaceDeTravail) {
        this.utilisateur = utilisateur;
        this.espaceDeTravail = espaceDeTravail;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public EspaceDeTravail getEspaceDeTravail() {
        return espaceDeTravail;
    }

    public void setEspaceDeTravail(EspaceDeTravail espaceDeTravail) {
        this.espaceDeTravail = espaceDeTravail;
    }
}
