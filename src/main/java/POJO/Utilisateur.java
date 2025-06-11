package POJO;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "date_inscription")
    private java.util.Date dateInscription;

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }


    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "administrer",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_espace_de_travail")
    )
    private List<EspaceDeTravail> espacesAdministres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "reagir",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_message")
    )
    private List<Message> messagesReagis = new ArrayList<>();

    @ManyToMany(mappedBy = "utilisateurs")
    private List<Envoyer> messagesEnvoyes = new ArrayList<>();

    // Constructeurs, getters et setters
    public Utilisateur() {}

    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Getters et setters pour tous les attributs
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<EspaceDeTravail> getEspacesAdministres() {
        return espacesAdministres;
    }

    public void setEspacesAdministres(List<EspaceDeTravail> espacesAdministres) {
        this.espacesAdministres = espacesAdministres;
    }

    public List<Message> getMessagesReagis() {
        return messagesReagis;
    }

    public void setMessagesReagis(List<Message> messagesReagis) {
        this.messagesReagis = messagesReagis;
    }

    public List<Envoyer> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public void setMessagesEnvoyes(List<Envoyer> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }

    // Méthodes utilitaires
    public void addMessage(Message message) {
        messages.add(message);
        message.setUtilisateur(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setUtilisateur(null);
    }
}
