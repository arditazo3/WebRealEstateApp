package real_estate.security;

import real_estate.model.entities.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

    public Assembler() {
    }

    @Transactional(readOnly = true)
    org.springframework.security.core.userdetails.User buildUserFromUserEntity(User pessoa) {

        String username = pessoa.getUsername();
        String password = pessoa.getPassword();
        boolean enabled = true; //userBean.isActive()
        boolean accountNonExpired = true;//userBean.isActive()
        boolean credentialsNonExpired = true; //userBean.isActive()
        boolean accountNonLocked = true; //userBean.isActive()

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl(pessoa.getPermission()));

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        return user;
    }
}
