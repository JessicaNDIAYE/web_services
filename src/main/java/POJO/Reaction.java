package POJO;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reagir")
@IdClass(ReactionId.class)
public class Reaction {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_message", nullable = false)
    private Message message;

    @Column(nullable = false)
    private String typeReaction; // ex: like, love, etc.

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @Column(name = "date_reaction")
    private Date date;

    // Constructeurs
    public Reaction() {}

    public Reaction(Utilisateur utilisateur, Message message, String typeReaction) {
        this.utilisateur = utilisateur;
        this.message = message;
        this.typeReaction = typeReaction;
        this.date = new Date(); // valeur par défaut
    }

    // GETTERS & SETTERS
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

    public String getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(String typeReaction) {
        this.typeReaction = typeReaction;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Clé composée
    public ReactionId getId() {
        return new ReactionId(
                utilisateur != null ? utilisateur.getIdUtilisateur() : null,
                message != null ? message.getIdMessage() : null
        );
    }

    public void setId(ReactionId id) {
        // Méthode présente pour compatibilité JPA mais non utilisée ici
        throw new UnsupportedOperationException("Utilise setUtilisateur() et setMessage()");
    }
}
