package real_estate.beans;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Log;
import real_estate.model.entities.Sale;
import real_estate.util.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="beanLog")
@RequestScoped
public class BeanLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Log> logs;

    public List<Log> getLogs() {
        InterfaceDAO<Log> logDAO = new HibernateDAO<Log>(Log.class, FacesContextUtil.getRequestSession());
        return logDAO.getEntities();
    }


}
