package real_estate.security;

import real_estate.model.dao.HibernateDAO;
import real_estate.model.dao.InterfaceDAO;
import real_estate.model.entities.User;
import real_estate.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("semeruUserService")
public class UserServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.isEmpty()) {
            throw new UsernameNotFoundException("Usuario nao encontrado!");
        } else {
            try {
                User usuario = findUser(username);

                String login = usuario.getUsername();
                String password = usuario.getPassword();
                boolean enabled = true; //userBean.isActive()
                boolean accountNonExpired = true;//userBean.isActive()
                boolean credentialsNonExpired = true; //userBean.isActive()
                boolean accountNonLocked = true; //userBean.isActive()

                Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                //Use assim se você usa o Spring Security 3.0.5.RELEASE
                authorities.add(new GrantedAuthorityImpl(usuario.getPermission()));
                //Já na versão 3.1.3.RELEASE essa classe foi depreciada e você deve usar como no trecho abaixo
                //authorities.add(new SimpleGrantedAuthority(usuario.getPermissao()));
                org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                        login,
                        password,
                        enabled,
                        accountNonExpired,
                        credentialsNonExpired,
                        accountNonLocked,
                        authorities);
                return user;
            } catch (Exception e) {
                return null;
            }
        }

    }

    public User findUser(String login) {
        String stringQuery = "from User pessoa where pessoa.username = "+ login;
//        return pessoaDAO().getEntityByHQLQuery(stringQuery);
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery(stringQuery);
        //query.setString(0, login);
        return (User) query.uniqueResult();
    }
    
    private InterfaceDAO<User> pessoaDAO() {
        InterfaceDAO<User> pessoaDAO = new HibernateDAO<User>(User.class, FacesContextUtil.getRequestSession());
        return pessoaDAO;
    }
}