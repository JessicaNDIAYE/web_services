package POJO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_channel")
    private Integer idChannel;

    private String topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espace_de_travail", nullable = false)
    private EspaceDeTravail espaceDeTravail;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publier> publications = new ArrayList<>();

    // Constructeurs, getters et setters
    public Channel() {}

    public Channel(String topic, EspaceDeTravail espaceDeTravail) {
        this.topic = topic;
        this.espaceDeTravail = espaceDeTravail;
    }

    public Integer getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(Integer idChannel) {
        this.idChannel = idChannel;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public EspaceDeTravail getEspaceDeTravail() {
        return espaceDeTravail;
    }

    public void setEspaceDeTravail(EspaceDeTravail espaceDeTravail) {
        this.espaceDeTravail = espaceDeTravail;
    }

    public List<Publier> getPublications() {
        return publications;
    }

    public void setPublications(List<Publier> publications) {
        this.publications = publications;
    }

    // Méthodes utilitaires
    public void addPublication(Publier publier) {
        publications.add(publier);
        publier.setChannel(this);
    }

    public void removePublication(Publier publier) {
        publications.remove(publier);
        publier.setChannel(null);
    }

}