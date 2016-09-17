package real_estate.beans;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.util.FacesContextUtil;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="beanCity")
@RequestScoped
public class BeanCity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<City> cities;

    public List<City> getCities() {
        InterfaceDAO<City> cityDAO = new HibernateDAO<City>(City.class, FacesContextUtil.getRequestSession());
        return cityDAO.getEntities();
    }
}
