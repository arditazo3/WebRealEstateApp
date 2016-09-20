package real_estate.model.entities;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="property")
public class Property implements Serializable {

    private static final long serialVersionUID =  1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_property", nullable=false)
    private Integer idProperty;

    @Column(name = "descr_property")
    private String descrProperty;

    @Column(name = "type_property")
    private String typeProperty;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_register")
    private Date dateRegister;

    @Column(name = "address")
    private String address;

    @Column(name = "surface_area")
    private String surfaceArea;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_city_key")
    @JoinColumn(name = "id_city", referencedColumnName="id_city")
    private City city;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ForeignKey(name="property_region_key")
    @JoinColumn(name = "id_type_region", referencedColumnName = "id_type_region")
    private TypeRegion typeRegion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ForeignKey(name="property_user_key")
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="sale_property_key")
    private Sale sale;

    public Property() {
        this.typeRegion = new TypeRegion();
        this.user = new User();
    }

    public Integer getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Integer idProperty) {
        this.idProperty = idProperty;
    }

    public String getDescrProperty() {
        return descrProperty;
    }

    public void setDescrProperty(String descrProperty) {
        this.descrProperty = descrProperty;
    }

    public String getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {

        if(city == null)
            city = new City();

        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public TypeRegion getTypeRegion() {
        return typeRegion;
    }

    public void setTypeRegion(TypeRegion typeRegion) {
        this.typeRegion = typeRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (idProperty != null ? !idProperty.equals(property.idProperty) : property.idProperty != null) return false;
        if (descrProperty != null ? !descrProperty.equals(property.descrProperty) : property.descrProperty != null)
            return false;
        if (typeProperty != null ? !typeProperty.equals(property.typeProperty) : property.typeProperty != null)
            return false;
        if (dateRegister != null ? !dateRegister.equals(property.dateRegister) : property.dateRegister != null)
            return false;
        if (address != null ? !address.equals(property.address) : property.address != null) return false;
        if (city != null ? !city.equals(property.city) : property.city != null) return false;
        return !(typeRegion != null ? !typeRegion.equals(property.typeRegion) : property.typeRegion != null);

    }

    @Override
    public int hashCode() {
        int result = idProperty != null ? idProperty.hashCode() : 0;
        result = 31 * result + (descrProperty != null ? descrProperty.hashCode() : 0);
        result = 31 * result + (typeProperty != null ? typeProperty.hashCode() : 0);
        result = 31 * result + (dateRegister != null ? dateRegister.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (typeRegion != null ? typeRegion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Property{" +
                "idProperty=" + idProperty +
                ", descrProperty='" + descrProperty + '\'' +
                ", typeProperty='" + typeProperty + '\'' +
                ", dateRegister=" + dateRegister +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", typeRegion=" + typeRegion +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(String surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
