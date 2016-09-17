package real_estate.support;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.TypeAddress;
import real_estate.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;

@ManagedBean(name="manBeanTypeAddress")
@RequestScoped
public class BeanTypeAddress implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public List<TypeAddress> getTypeAddress() {
        Session session = FacesContextUtil.getRequestSession();
        InterfaceDAO<TypeAddress> typeAddressDAO = new HibernateDAO<TypeAddress>(TypeAddress.class, session);
        return typeAddressDAO.getEntities();
    }
}