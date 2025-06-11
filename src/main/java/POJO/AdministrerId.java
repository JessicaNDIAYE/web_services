package POJO;

import java.io.Serializable;
import java.util.Objects;

public class AdministrerId implements Serializable {
    private Integer utilisateur;
    private Integer espaceDeTravail;

    // Constructeur par défaut
    public AdministrerId() {}

    public AdministrerId(Integer utilisateur, Integer espaceDeTravail) {
        this.utilisateur = utilisateur;
        this.espaceDeTravail = espaceDeTravail;
    }

    // Getters et setters
    public Integer getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Integer utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getEspaceDeTravail() {
        return espaceDeTravail;
    }

    public void setEspaceDeTravail(Integer espaceDeTravail) {
        this.espaceDeTravail = espaceDeTravail;
    }

    // equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrerId that = (AdministrerId) o;
        return Objects.equals(utilisateur, that.utilisateur) &&
                Objects.equals(espaceDeTravail, that.espaceDeTravail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateur, espaceDeTravail);
    }
}