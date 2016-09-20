package real_estate.beans;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.City;
import real_estate.model.entities.Sale;
import real_estate.util.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="beanSale")
@RequestScoped
public class BeanSale implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Sale> cities;

    public List<Sale> getCities() {
        InterfaceDAO<Sale> saleDAO = new HibernateDAO<Sale>(Sale.class, FacesContextUtil.getRequestSession());
        return saleDAO.getEntities();
    }

}
