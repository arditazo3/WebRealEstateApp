package real_estate.controller;

import real_estate.converterSHA1.ConverterSHA1;
import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Address;
import real_estate.model.entities.User;
import real_estate.util.FacesContextUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ControllerUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String confirmPassword;
    private User user = new User();
    private Address address = new Address();
    private List<User> users;
    private List<Address> addresses;

    public ControllerUser() {
    }

    private InterfaceDAO<User> userDAO() {
        InterfaceDAO<User> userDao = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return userDao;
    }

    private InterfaceDAO<Address> addressDAO() {
        InterfaceDAO<Address> addressDAO = new HibernateDAO<Address>(Address.class, FacesContextUtil.getRequestSession());
        return addressDAO;
    }

    public String cancelUser() {
        user = new User();
        address = new Address();
        return editUser();
    }

    public String editUser() {
        return "/restrict/list-users.faces";
    }

    public String addUser() {
        Date date = new Date();
        if (user.getIdUser() == null || user.getIdUser() == 0) {
            user.setDateRegister(date);
            insertUser();
        } else {
            updateUser();
        }

        return null;
    }

    private void insertUser() {
        user.setPassword(ConverterSHA1.cipher(user.getPassword()));
        if (user.getPassword() == null ? confirmPassword == null : user.getPassword().equals(ConverterSHA1.cipher(confirmPassword))) {
            user.setPermission("ROLE_ADMIN");
            userDAO().save(user);
            address.setUser(user);
            addressDAO().save(address);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "User saved successful", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Password no match", ""));
        }
    }

    private void updateUser() {
        userDAO().update(user);
        addressDAO().update(address);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated successfully", ""));
    }

    public String deleteUser() {
        userDAO().remove(user);
        addressDAO().remove(address);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted successfully", ""));
        return null;
    }

    public List<User> getUsers() {
        users = userDAO().getEntities();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Address> getAddresses() {
        addresses = addressDAO().getEntities();
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
