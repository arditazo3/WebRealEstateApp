package real_estate.controller;

import real_estate.conversores.ConverterSHA1;
import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.Endereco;
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
    private Endereco endereco = new Endereco();
    private List<User> users;
    private List<Endereco> enderecos;

    public ControllerUser() {
    }

    private InterfaceDAO<User> userDAO() {
        InterfaceDAO<User> userDao = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return userDao;
    }

    private InterfaceDAO<Endereco> enderecoDAO() {
        InterfaceDAO<Endereco> enderecoDAO = new HibernateDAO<Endereco>(Endereco.class, FacesContextUtil.getRequestSession());
        return enderecoDAO;
    }

    public String limpUser() {
        user = new User();
        endereco = new Endereco();
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
            endereco.setPessoa(user);
            enderecoDAO().save(endereco);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "User saved successful", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Password no match", ""));
        }
    }

    private void updateUser() {
        userDAO().update(user);
        enderecoDAO().update(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    public String deleteUser() {
        userDAO().remove(user);
        enderecoDAO().remove(endereco);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluído com sucesso", ""));
        return null;
    }

    public List<User> getUsers() {
        users = userDAO().getEntities();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Endereco> getEnderecos() {
        enderecos = enderecoDAO().getEntities();
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
