package real_estate.controller;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Log;
import real_estate.model.entities.Sale;
import real_estate.model.entities.User;
import real_estate.util.FacesContextUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "manBeanLog")
@RequestScoped
public class ControllerLog {

    private User user = new User();
    private List<Log> logs = new ArrayList<Log>();
    private Log log = new Log();


    public ControllerLog() {
    }

    public ControllerLog(User user) {
        this.user = user;
    }

    private InterfaceDAO<User> userDAO() {
        InterfaceDAO<User> userDAO = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return userDAO;
    }

    public void insertLog(String url, User user) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        Log logInsert = new Log(url, ipAddress, user);

        logDAO().save(logInsert);

    }

    private InterfaceDAO<Log> logDAO() {
        InterfaceDAO<Log> logDAO = new HibernateDAO<Log>(Log.class, FacesContextUtil.getRequestSession());
        return logDAO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Log> getLogs() {
        logs = logDAO().getEntities();
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
