package POJO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "espace_de_travail")
public class EspaceDeTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espace_de_travail")
    private Integer idEspaceDeTravail;

    @Column(name = "nom_espace_de_travail", nullable = false)
    private String nomEspaceDeTravail;

    @OneToMany(mappedBy = "espaceDeTravail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Channel> channels = new ArrayList<>();

    @ManyToMany(mappedBy = "espacesAdministres")
    private List<Utilisateur> administrateurs = new ArrayList<>();

    // Constructeurs, getters et setters
    public EspaceDeTravail() {}

    public EspaceDeTravail(String nomEspaceDeTravail) {
        this.nomEspaceDeTravail = nomEspaceDeTravail;
    }

    public Integer getIdEspaceDeTravail() {
        return idEspaceDeTravail;
    }

    public void setIdEspaceDeTravail(Integer idEspaceDeTravail) {
        this.idEspaceDeTravail = idEspaceDeTravail;
    }

    public String getNomEspaceDeTravail() {
        return nomEspaceDeTravail;
    }

    public void setNomEspaceDeTravail(String nomEspaceDeTravail) {
        this.nomEspaceDeTravail = nomEspaceDeTravail;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Utilisateur> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<Utilisateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    // Méthodes utilitaires
    public void addChannel(Channel channel) {
        channels.add(channel);
        channel.setEspaceDeTravail(this);
    }

    public void removeChannel(Channel channel) {
        channels.remove(channel);
        channel.setEspaceDeTravail(null);
    }
}
