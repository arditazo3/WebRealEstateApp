package real_estate.controller;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.util.FacesContextUtil;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "manBeanCity")
@RequestScoped
public class ControllerCity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private City city = new City();
    private List<City> cities;

    public ControllerCity() {
    }

    private InterfaceDAO<City> cityDAO() {
        InterfaceDAO<City> cityDAO = new HibernateDAO<City>(City.class, FacesContextUtil.getRequestSession());
        return cityDAO;
    }

    public String cancelCity() {
        city = new City();
        return editCity();
    }

    public String editCity() {
        return "/restrict/list-cities.faces";
    }

    public String addCity() {
        if (city.getIdCity() == null || city.getIdCity() == 0) {
            insertCity();
        } else {
            updateCity();
        }
        cancelCity();
        return null;
    }

    private void insertCity() {
        cityDAO().save(city);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserted successfully", ""));
    }

    private void updateCity() {
        cityDAO().update(city);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated successfully", ""));
    }
    
    public void deleteCity(){
        cityDAO().remove(city);
    }
    
    public List<City> getCities() {
        cities = cityDAO().getEntities();
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
        
}
