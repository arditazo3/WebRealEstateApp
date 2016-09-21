package real_estate.model.entities;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="log")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_log", nullable=false)
    private Integer idLog;

    @Column (name="ip", nullable = false, length = 80 )
    private String ip;

    @Column (name="url", nullable = false, length = 80 )
    private String url;

    @ManyToOne(optional=false)
    @ForeignKey(name = "log_user_key")
    @JoinColumn(name="id_user", referencedColumnName = "id_user")
    private User user;

    public Log() {
    }

    public Log(String url, String ip, User user) {
        this.ip = ip;
        this.url = url;
        this.user = user;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (idLog != null ? !idLog.equals(log.idLog) : log.idLog != null) return false;
        if (url != null ? !url.equals(log.url) : log.url != null) return false;
        return user != null ? user.equals(log.user) : log.user == null;

    }

    @Override
    public int hashCode() {
        int result = idLog != null ? idLog.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "idLog=" + idLog +
                ", url='" + url + '\'' +
                ", user=" + user +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
