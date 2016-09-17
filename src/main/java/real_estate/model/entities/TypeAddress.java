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
@Table(name="type_address")
public class TypeAddress implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_type_address", nullable = false)
    private Integer idTypeAddress;
    @Column(name = "descr_type_address", nullable = false, length = 35)
    private String descrTypeAddress;
    
    @OneToMany(mappedBy = "type_address", fetch = FetchType.LAZY)
    @ForeignKey(name="address_type_address_key")
    private List<Address> addresses;

    public TypeAddress() {
    }

    public String getDescrTypeAddress() {
        return descrTypeAddress;
    }

    public void setDescrTypeAddress(String descrTypeAddress) {
        this.descrTypeAddress = descrTypeAddress;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Integer getIdTypeAddress() {
        return idTypeAddress;
    }

    public void setIdTypeAddress(Integer idTypeAddress) {
        this.idTypeAddress = idTypeAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TypeAddress other = (TypeAddress) obj;
        if (this.idTypeAddress != other.idTypeAddress && (this.idTypeAddress == null || !this.idTypeAddress.equals(other.idTypeAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.idTypeAddress != null ? this.idTypeAddress.hashCode() : 0);
        return hash;
    }
}
