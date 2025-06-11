package POJO;

import java.io.Serializable;
import java.util.Objects;

public class ReagirId implements Serializable {
    private Integer utilisateur;
    private Integer message;

    // Constructeur par défaut
    public ReagirId() {}

    public ReagirId(Integer utilisateur, Integer message) {
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
        ReagirId reagirId = (ReagirId) o;
        return Objects.equals(utilisateur, reagirId.utilisateur) &&
                Objects.equals(message, reagirId.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateur, message);
    }
}
