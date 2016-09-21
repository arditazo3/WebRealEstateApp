package real_estate.model.entities;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="sales")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_sale", nullable=false)
    private Integer idSale;

    @Column(name = "descr_sale")
    private String descrSale;

    @Column(name = "prize")
    private Integer prize;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "date_sale")
    private Date dateSale;

    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="address_user_key")
    @JoinColumn(name = "id_property", referencedColumnName = "id_property")
    private Property property;


    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="sale_user_key")
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    public Sale() {

        this.property = new Property();
        this.user = new User();

    }

    public Sale(String descrSale, Integer prize, Date dateSale, Property property, User user) {
        this.descrSale = descrSale;
        this.prize = prize;
        this.dateSale = dateSale;
        this.property = property;
        this.user = user;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public String getDescrSale() {
        return descrSale;
    }

    public void setDescrSale(String descrSale) {
        this.descrSale = descrSale;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public Property getProperty() {

        if(property == null)
            property = new Property();

        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {

        if(user == null)
            user = new User();

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (idSale != null ? !idSale.equals(sale.idSale) : sale.idSale != null) return false;
        if (descrSale != null ? !descrSale.equals(sale.descrSale) : sale.descrSale != null) return false;
        if (prize != null ? !prize.equals(sale.prize) : sale.prize != null) return false;
        if (dateSale != null ? !dateSale.equals(sale.dateSale) : sale.dateSale != null) return false;
        if (property != null ? !property.equals(sale.property) : sale.property != null) return false;
        return user != null ? user.equals(sale.user) : sale.user == null;

    }

    @Override
    public int hashCode() {
        int result = idSale != null ? idSale.hashCode() : 0;
        result = 31 * result + (descrSale != null ? descrSale.hashCode() : 0);
        result = 31 * result + (prize != null ? prize.hashCode() : 0);
        result = 31 * result + (dateSale != null ? dateSale.hashCode() : 0);
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", descrSale='" + descrSale + '\'' +
                ", prize=" + prize +
                ", dateSale=" + dateSale +
                ", property=" + property +
                ", user=" + user +
                '}';
    }


}
