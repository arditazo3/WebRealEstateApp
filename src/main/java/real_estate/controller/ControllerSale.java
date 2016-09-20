package real_estate.controller;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.model.entities.Property;
import real_estate.model.entities.Sale;
import real_estate.model.entities.User;
import real_estate.util.FacesContextUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "manBeanSale")
@RequestScoped
public class ControllerSale implements Serializable {

    private Sale sale = new Sale();
    private List<Sale> sales;

    public ControllerSale() {
    }

    private InterfaceDAO<User> userDAO() {
        InterfaceDAO<User> userDAO = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return userDAO;
    }

    private InterfaceDAO<Property> propertyDAO() {
        InterfaceDAO<Property> propertyDAO = new HibernateDAO<Property>(Property.class, FacesContextUtil.getRequestSession());
        return propertyDAO;
    }

    private InterfaceDAO<Sale> saleDAO() {
        InterfaceDAO<Sale> saleDAO = new HibernateDAO<Sale>(Sale.class, FacesContextUtil.getRequestSession());
        return saleDAO;
    }

    public String editSale() {

        sale = new Sale();

        return "/restrict/sell-process.faces";
    }

    public void insertSale() {

        if(sale.getIdSale() == null) {

            Property property = propertyDAO().getEntity( sale.getProperty().getIdProperty() );

            User user = userDAO().getEntity( sale.getUser().getIdUser() );

            sale.setProperty( property );
            sale.setUser( user );

            saleDAO().save(sale);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserted successfully", ""));
        } else {
            updateSale();
        }
    }

    public void updateSale() {
        saleDAO().update(sale);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated successfully", ""));
    }

    public List<Sale> getSales() {
        sales = saleDAO().getEntities();
        return sales;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
