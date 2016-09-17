package real_estate.support;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Nation;
import real_estate.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="manBeanNation")
@RequestScoped
public class BeanNation implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<Nation> getNations() {
        InterfaceDAO<Nation> nationDAO = new HibernateDAO<Nation>(Nation.class, FacesContextUtil.getRequestSession());
        return nationDAO.getEntities();
    }	
}