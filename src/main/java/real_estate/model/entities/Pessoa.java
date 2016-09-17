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
public class Pessoa implements Serializable {
    
    private static final long serialVersionUID =  1L;
    
    @Id
    @GeneratedValue
    @Column(name="id_user", nullable=false)
    private Integer idPessoa;
    @Column (name="name", nullable = false, length = 80 )
    private String nome;
    @Column (name="email", nullable = false, length = 80 )
    private String email;
    @Column (name="phone", nullable = false, length = 15 )//(034)-8888-8888
    private String telefone;
    @Column (name="CPF", nullable = false, length = 14 )
    private String cpf;
    @Column (name="date_birth", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeNascimento;
    @Column (name="date_register", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDeCadastro;
        
    @Column(name = "username", unique=true, length = 25)
    private String login;
    @Column(name = "password", length = 40)
    private String senha;
    @Column(name = "permission", length = 36)
    private String permissao;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @ForeignKey(name="address_user_key")
    private Endereco address;
    
    @ManyToOne(optional=false)
    @ForeignKey(name = "user_gender_key")
    @JoinColumn(name="id_gender", referencedColumnName = "id_gender")
    private Sexo gender;

    public Pessoa() {
        this.gender = new Sexo();
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public Sexo getSexo() {
        return gender;
    }

    public void setSexo(Sexo gender) {
        this.gender = gender;
    }

    public Endereco getEndereco() {
        return address;
    }

    public void setEndereco(Endereco address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
         
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.idPessoa != null ? this.idPessoa.hashCode() : 0);
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
        final Pessoa other = (Pessoa) obj;
        if (this.idPessoa != other.idPessoa && (this.idPessoa == null || !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }
             
}
