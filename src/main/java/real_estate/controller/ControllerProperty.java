package real_estate.controller;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Property;
import real_estate.util.FacesContextUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "manBeanProperty")
@SessionScoped
public class ControllerProperty implements Serializable {

    private Property property = new Property();
    private List<Property> properties = new ArrayList<Property>();

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
        return "/public/list-properties.faces";
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

    public List<Property> getProperties() {
        properties = propertyDAO().getEntities();
        return properties;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
