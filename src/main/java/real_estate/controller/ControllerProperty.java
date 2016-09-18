package real_estate.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.model.entities.Property;
import real_estate.model.entities.User;
import real_estate.util.FacesContextUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "manBeanProperty")
@SessionScoped
public class ControllerProperty implements Serializable {

    private Property property = new Property();
    private List<Property> properties = new ArrayList<Property>();

    private Integer idCityProp = null;

    public ControllerProperty() {
    }

    private InterfaceDAO<Property> propertyDAO() {
        InterfaceDAO<Property> propertyDao = new HibernateDAO<Property>(Property.class, FacesContextUtil.getRequestSession());
        return propertyDao;
    }

    public String cancelProperty() {
        property = new Property();
        return editProperty();
    }

    public String editProperty() {
        return "/restrict/list-properties.faces";
    }

    public String addProperty() {
        Date dateNow = new Date();
        if(property.getIdProperty() == null) {

            property.setDateRegister(dateNow);
            propertyDAO().save(property);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Property saved successfully", ""));
        } else {

            updateProperty();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Property not saved successfully", ""));
        }

        return null;
    }

    private void insertProperty() {
        propertyDAO().save(property);
    }

    private void updateProperty() {
        propertyDAO().update(property);
    }

    public String deleteProperty() {
        propertyDAO().remove(property);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted successfully", ""));
        return null;
    }

    public void searchUpdateProperty() {

        List<Property> listProperties = getSearchProperty();
    }

    public List<Property> getSearchProperty() {

        if( property.getDescrProperty() != null && !property.getDescrProperty().equals("") ) {

            String queryString = " select p from Property p        " +
                    " where p.descrProperty like :descrProperty or " +
                    " p.typeProperty like :typeProperty            ";
            Session session = FacesContextUtil.getRequestSession();
            Query query = session.createQuery(queryString)
                    .setParameter("descrProperty", "%" + property.getDescrProperty() + "%" )
                    .setParameter("typeProperty", "%" + property.getDescrProperty() + "%" );
            return query.list();

        } else if(this.getIdCityProp() != null) {

            String queryString = " select p from Property p      " +
                                 " where p.city.idCity = :idCity ";
            Session session = FacesContextUtil.getRequestSession();
            Query query = session.createQuery(queryString)
                    .setParameter("idCity",  this.getIdCityProp() );
            return query.list();

        } else {

            return getProperties();
        }
    }

    public void handleChange(){
        List<Property> listProperties = getSearchProperty();
    }

    public List<Property> getProperties() {
        properties = propertyDAO().getEntities();
        return properties;
    }

    public Property getProperty() {

        City city = new City();
        property.setCity(city);

        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Integer getIdCityProp() {
        return idCityProp;
    }

    public void setIdCityProp(Integer idCityProp) {
        this.idCityProp = idCityProp;
    }
}
