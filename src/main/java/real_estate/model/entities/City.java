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
@Table(name="city")
public class City implements Serializable{
    
    private static final long serialVersionUID =  1L; 
    
    @Id
    @GeneratedValue
    @Column(name="id_city", nullable=false)
    private Integer idCity;
    @Column(name="name", length=80, nullable=false)
    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @ForeignKey(name="address_city_key")
    private List<Address> addresses;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @ForeignKey(name="property_city_key")
    private List<Property> properties;

    public City() {
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.idCity != null ? this.idCity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.idCity != other.idCity && (this.idCity == null || !this.idCity.equals(other.idCity))) {
            return false;
        }
        return true;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
