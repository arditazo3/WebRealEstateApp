package real_estate.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="address")
public class Address implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name="id_address", nullable=false)
    private Integer idAddress;
    @Column(name="street", length=80)
    private String street;
    @Column (name="region", length=80)
    private String region;
    @Column (name="code_street", length=9)
    private String codeStreet;
    @Column (name="region_number")
    private Integer regionNumber;
    @Column (name="zip")
    private Integer zip;
    
    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="address_user_key")
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_region_key")
    @JoinColumn(name = "id_type_region", referencedColumnName = "id_type_region")
    private TypeRegion type_region;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_nation_key")
    @JoinColumn(name = "id_nation", nullable = false)
    private Nation nation;
        
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_type_address")
    @JoinColumn(name = "id_type_address", referencedColumnName="id_type_address")
    private TypeAddress type_address;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_city_key")
    @JoinColumn(name = "id_city", referencedColumnName="id_city")
    private City city;

    public Address() {
        this.city = new City();
        this.nation = new Nation();
        this.type_region = new TypeRegion();
        this.type_address = new TypeAddress();
        this.user = new User();
    }    
    
    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idEndereco) {
        this.idAddress = idEndereco;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String bairro) {
        this.street = bairro;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String nomeLogradouro) {
        this.region = nomeLogradouro;
    }

    public String getCodeStreet() {
        return codeStreet;
    }

    public void setCodeStreet(String cep) {
        this.codeStreet = cep;
    }

    public Integer getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(Integer numero) {
        this.regionNumber = numero;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer complemento) {
        this.zip = complemento;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public TypeAddress getTypeAddress() {
        return type_address;
    }

    public void setTypeAddress(TypeAddress type_address) {
        this.type_address = type_address;
    }

    public TypeRegion getTypeRegion() {
        return type_region;
    }

    public void setTypeRegion(TypeRegion type_region) {
        this.type_region = type_region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idAddress != null ? this.idAddress.hashCode() : 0);
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
        final Address other = (Address) obj;
        if (this.idAddress != other.idAddress && (this.idAddress == null || !this.idAddress.equals(other.idAddress))) {
            return false;
        }
        return true;
    }
        
}
