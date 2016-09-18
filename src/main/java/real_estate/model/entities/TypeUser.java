package real_estate.model.entities;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "type_user")
public class TypeUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_type_user", nullable = false)
    private Integer idTypeUser;

    @Column(name = "description", unique = true, nullable = false)
    private String descrTypeUser;

    @OneToMany(mappedBy = "typeUser", fetch = FetchType.LAZY)
    @ForeignKey(name = "user_type_user_key")
    private List<User> users;

    public TypeUser() {
    }

    public TypeUser(Integer idTypeUser, String descrTypeUser) {

        this.idTypeUser = idTypeUser;
        this.descrTypeUser = descrTypeUser;
    }

    public Integer getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(Integer idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public String getDescrTypeUser() {
        return descrTypeUser;
    }

    public void setDescrTypeUser(String descrTypeUser) {
        this.descrTypeUser = descrTypeUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeUser typeUser = (TypeUser) o;

        if (idTypeUser != null ? !idTypeUser.equals(typeUser.idTypeUser) : typeUser.idTypeUser != null) return false;
        if (descrTypeUser != null ? !descrTypeUser.equals(typeUser.descrTypeUser) : typeUser.descrTypeUser != null)
            return false;
        return !(users != null ? !users.equals(typeUser.users) : typeUser.users != null);

    }

    @Override
    public int hashCode() {
        int result = idTypeUser != null ? idTypeUser.hashCode() : 0;
        result = 31 * result + (descrTypeUser != null ? descrTypeUser.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypeUser{" +
                "idTypeUser=" + idTypeUser +
                ", descrTypeUser='" + descrTypeUser + '\'' +
                ", users=" + users +
                '}';
    }
}
