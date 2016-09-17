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
@Table(name="type_region")
public class TypeRegion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name = "id_type_region", nullable = false)
    private Integer idTypeRegion;
    @Column(name = "descr_type_region", nullable = false, length = 40)
    private String descrTypeRegion;
    
    @OneToMany(mappedBy = "type_region", fetch = FetchType.LAZY)
    @ForeignKey(name = "id_type_region")
    private List<Address> addresses;

    public TypeRegion() {
    }

    public String getDescrTypeRegion() {
        return descrTypeRegion;
    }

    public void setDescrTypeRegion(String descrTypeRegion) {
        this.descrTypeRegion = descrTypeRegion;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getIdTypeRegion() {
        return idTypeRegion;
    }

    public void setIdTypeRegion(Integer idTypeRegion) {
        this.idTypeRegion = idTypeRegion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TypeRegion other = (TypeRegion) obj;
        if (this.idTypeRegion != other.idTypeRegion && (this.idTypeRegion == null || !this.idTypeRegion.equals(other.idTypeRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.idTypeRegion != null ? this.idTypeRegion.hashCode() : 0);
        return hash;
    }
}
