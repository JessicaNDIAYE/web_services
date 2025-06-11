package POJO;

import java.io.Serializable;
import java.util.Objects;

public class EnvoyerId implements Serializable {
    private Integer utilisateur;
    private Integer message;

    // Constructeur par défaut
    public EnvoyerId() {}

    public EnvoyerId(Integer utilisateur, Integer message) {
        this.utilisateur = utilisateur;
        this.message = message;
    }

    // Getters et setters
    public Integer getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Integer utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    // equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvoyerId envoyerId = (EnvoyerId) o;
        return Objects.equals(utilisateur, envoyerId.utilisateur) &&
                Objects.equals(message, envoyerId.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateur, message);
    }
}
