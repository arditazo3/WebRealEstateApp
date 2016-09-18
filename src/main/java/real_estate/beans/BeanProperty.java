package real_estate.beans;


import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.model.entities.Property;
import real_estate.util.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="beanProperty")
@RequestScoped
public class BeanProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Property> properties;

    public List<Property> getProperties() {
        InterfaceDAO<Property> propertyDAO = new HibernateDAO<Property>(Property.class, FacesContextUtil.getRequestSession());
        return propertyDAO.getEntities();
    }
}