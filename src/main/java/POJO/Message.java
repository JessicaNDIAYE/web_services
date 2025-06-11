package POJO;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Integer idMessage;

    @Column(nullable = false)
    private String contenu;

    @Column(name = "date_et_heure", nullable = false)
    private LocalDateTime dateEtHeure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publier> publications = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "reagir",
            joinColumns = @JoinColumn(name = "id_message"),
            inverseJoinColumns = @JoinColumn(name = "id_utilisateur")
    )
    private List<Utilisateur> utilisateursReactions = new ArrayList<>();

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Envoyer> envois = new ArrayList<>();

    // Constructeurs, getters et setters
    public Message() {
        this.dateEtHeure = LocalDateTime.now();
    }

    public Message(String contenu, Utilisateur utilisateur) {
        this.contenu = contenu;
        this.utilisateur = utilisateur;
        this.dateEtHeure = LocalDateTime.now();
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateEtHeure() {
        return dateEtHeure;
    }

    public void setDateEtHeure(LocalDateTime dateEtHeure) {
        this.dateEtHeure = dateEtHeure;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Publier> getPublications() {
        return publications;
    }

    public void setPublications(List<Publier> publications) {
        this.publications = publications;
    }

    public List<Utilisateur> getUtilisateursReactions() {
        return utilisateursReactions;
    }

    public void setUtilisateursReactions(List<Utilisateur> utilisateursReactions) {
        this.utilisateursReactions = utilisateursReactions;
    }

    public List<Envoyer> getEnvois() {
        return envois;
    }

    public void setEnvois(List<Envoyer> envois) {
        this.envois = envois;
    }

    // Méthodes utilitaires
    public void addPublication(Publier publier) {
        publications.add(publier);
        publier.setMessage(this);
    }

    public void removePublication(Publier publier) {
        publications.remove(publier);
        publier.setMessage(null);
    }

    public void addReaction(Utilisateur utilisateur) {
        utilisateursReactions.add(utilisateur);
        utilisateur.getMessagesReagis().add(this);
    }

    public void removeReaction(Utilisateur utilisateur) {
        utilisateursReactions.remove(utilisateur);
        utilisateur.getMessagesReagis().remove(this);
    }
}