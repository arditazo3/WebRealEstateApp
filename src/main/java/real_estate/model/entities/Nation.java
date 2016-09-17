package real_estate.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="nation")
public class Nation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "id_nation", nullable = false)
    private Integer idNation;
    @Column(name = "name_nation", nullable = false, length = 40)
    private String nameNation;
    
    @OneToMany(mappedBy = "nation", fetch = FetchType.LAZY)
    @ForeignKey(name="address_nation_key")
    private List<Address> addresses;

    public Nation() {
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getIdNation() {
        return idNation;
    }

    public void setIdNation(Integer idNation) {
        this.idNation = idNation;
    }

    public String getNameNation() {
        return nameNation;
    }

    public void setNameNation(String nameNation) {
        this.nameNation = nameNation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nation other = (Nation) obj;
        if (this.idNation != other.idNation && (this.idNation == null || !this.idNation.equals(other.idNation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idNation != null ? this.idNation.hashCode() : 0);
        return hash;
    }
}
