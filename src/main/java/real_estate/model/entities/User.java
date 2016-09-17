package real_estate.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table (name="user")
public class User implements Serializable {
    
    private static final long serialVersionUID =  1L;
    
    @Id
    @GeneratedValue
    @Column(name="id_user", nullable=false)
    private Integer idUser;
    @Column (name="name", nullable = false, length = 80 )
    private String name;
    @Column (name="email", nullable = false, length = 80 )
    private String email;
    @Column (name="phone", nullable = false, length = 15 )//(034)-8888-8888
    private String phone;
    @Column (name="CPF", nullable = false, length = 14 )
    private String cpf;
    @Column (name="date_birth", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBirth;
    @Column (name="date_register", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRegister;
        
    @Column(name = "username", unique=true, length = 25)
    private String username;
    @Column(name = "password", length = 40)
    private String password;
    @Column(name = "permission", length = 36)
    private String permission;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @ForeignKey(name="address_user_key")
    private Endereco address;
    
    @ManyToOne(optional=false)
    @ForeignKey(name = "user_gender_key")
    @JoinColumn(name="id_gender", referencedColumnName = "id_gender")
    private Gender gender;

    public User() {
        this.gender = new Gender();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Endereco getEndereco() {
        return address;
    }

    public void setEndereco(Endereco address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
         
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idUser != null ? this.idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.idUser != other.idUser && (this.idUser == null || !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }
             
}
