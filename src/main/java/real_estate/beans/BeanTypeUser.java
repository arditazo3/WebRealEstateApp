package real_estate.beans;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Gender;
import real_estate.model.entities.TypeUser;
import real_estate.util.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="beanTypeUser")
@RequestScoped
public class BeanTypeUser implements Serializable{

    private static final long serialVersionUID = 1L;

    public List<TypeUser> getTypeUsers(){
        InterfaceDAO<TypeUser> typeUserDAO = new HibernateDAO<TypeUser>(TypeUser.class, FacesContextUtil.getRequestSession());
        return typeUserDAO.getEntities();
    }

}
