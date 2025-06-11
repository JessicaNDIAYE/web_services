package POJO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reagir")
@IdClass(ReagirId.class)
public class Reagir {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_message", nullable = false)
    private Message message;

    @Column(nullable = false)
    private String typeReaction; // like, love, haha, etc.

    // Constructeurs, getters et setters
    public Reagir() {}

    public Reagir(Utilisateur utilisateur, Message message, String typeReaction) {
        this.utilisateur = utilisateur;
        this.message = message;
        this.typeReaction = typeReaction;
    }

    // Getters et setters
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
}