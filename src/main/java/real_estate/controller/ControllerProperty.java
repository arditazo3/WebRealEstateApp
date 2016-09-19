package real_estate.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cache.spi.Region;
import real_estate.beans.BeanUserLogged;
import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.*;
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
    private Integer idRegion = null;
    private City city = new City();

    private InterfaceDAO<Property> propertyDAO() {
        InterfaceDAO<Property> propertyDao = new HibernateDAO<Property>(Property.class, FacesContextUtil.getRequestSession());
        return propertyDao;
    }

    private InterfaceDAO<User> userDAO() {
        InterfaceDAO<User> userDAOcityDAOMethod = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return userDAOcityDAOMethod;
    }

    private InterfaceDAO<City> cityDAO() {
        InterfaceDAO<City> cityDAOMethod = new HibernateDAO<City>(City.class, FacesContextUtil.getRequestSession());
        return cityDAOMethod;
    }

    private InterfaceDAO<TypeRegion> typeRegionDAO() {
        InterfaceDAO<TypeRegion> propertyRegionDaocityDAOMethod = new HibernateDAO<TypeRegion>(TypeRegion.class, FacesContextUtil.getRequestSession());
        return propertyRegionDaocityDAOMethod;
    }

    private InterfaceDAO<TypeAddress> typeAddressDAO() {
        InterfaceDAO<TypeAddress> typeAddressDAOcityDAOMethod = new HibernateDAO<TypeAddress>(TypeAddress.class, FacesContextUtil.getRequestSession());
        return typeAddressDAOcityDAOMethod;
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

            BeanUserLogged beanUser = new BeanUserLogged();

            User user = userDAO().getEntity( beanUser.getUserLogged().getIdUser() );

            TypeRegion typeRegion = typeRegionDAO().getEntity( property.getTypeRegion().getIdTypeRegion() );

            City city = cityDAO().getEntity( property.getCity().getIdCity() );

            property.setUser(user);
            property.setCity(city);
            property.setTypeRegion(typeRegion);
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

        } else if(this.getIdRegion() != null) {

            String queryString = " select p from Property p      " +
                    " where p.typeRegion.idTypeRegion = :idTypeRegion ";
            Session session = FacesContextUtil.getRequestSession();
            Query query = session.createQuery(queryString)
                    .setParameter("idTypeRegion",  this.getIdRegion() );
            return query.list();

        } else if(this.getIdUser() != null) {

            String queryString = " select p from Property p      " +
                    " where p.user.idUser = :idUser ";
            Session session = FacesContextUtil.getRequestSession();
            Query query = session.createQuery(queryString)
                    .setParameter("idUser",  this.getIdUser() );
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    private Integer idUser = null;

    public ControllerProperty() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
