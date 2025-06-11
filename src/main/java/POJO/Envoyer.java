package POJO;
import jakarta.persistence.*;


@Entity
@Table(name = "envoyer")
@IdClass(EnvoyerId.class)
public class Envoyer {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_message", nullable = false)
    private Message message;

    // Constructeurs, getters et setters
    public Envoyer() {}

    public Envoyer(Utilisateur utilisateur, Message message) {
        this.utilisateur = utilisateur;
        this.message = message;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
