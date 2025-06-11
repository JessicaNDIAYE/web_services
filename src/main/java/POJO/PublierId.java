package POJO;


import java.io.Serializable;
import java.util.Objects;


public class PublierId implements Serializable {
    private Integer message;
    private Integer channel;

    // Constructeur par défaut
    public PublierId() {}

    public PublierId(Integer message, Integer channel) {
        this.message = message;
        this.channel = channel;
    }

    // Getters et setters
    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    // equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublierId publierId = (PublierId) o;
        return Objects.equals(message, publierId.message) &&
                Objects.equals(channel, publierId.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, channel);
    }
}
