package real_estate.beans;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Gender;
import real_estate.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="beanGender")
@RequestScoped
public class BeanGender implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public List<Gender> getGenders(){
        InterfaceDAO<Gender> genderDAO = new HibernateDAO<Gender>(Gender.class, FacesContextUtil.getRequestSession());
        return genderDAO.getEntities();
    }
    
}
