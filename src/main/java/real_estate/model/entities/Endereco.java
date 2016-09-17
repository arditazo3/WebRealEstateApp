package real_estate.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="address")
public class Endereco implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name="id_address", nullable=false)
    private Integer idEndereco;
    @Column(name="street", length=80)
    private String bairro;
    @Column (name="region", length=80)
    private String nomeLogradouro;
    @Column (name="code_street", length=9)
    private String cep;
    @Column (name="region_number")
    private Integer numero;
    @Column (name="zip")
    private Integer complemento;
    
    @OneToOne(optional=true, fetch= FetchType.LAZY)
    @ForeignKey(name="address_user_key")
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_region_key")
    @JoinColumn(name = "id_type_region", referencedColumnName = "id_type_region")
    private TipoLogradouro type_region;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_nation_key")
    @JoinColumn(name = "id_nation", nullable = false)
    private Estado nation;
        
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_type_address")
    @JoinColumn(name = "id_type_address", referencedColumnName="id_type_address")
    private TipoEndereco type_address;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="address_city_key")
    @JoinColumn(name = "id_city", referencedColumnName="id_city")
    private Cidade city;

    public Endereco() {
        this.city = new Cidade();
        this.nation = new Estado();
        this.type_region = new TipoLogradouro();
        this.type_address = new TipoEndereco();
        this.user = new User();
    }    
    
    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getComplemento() {
        return complemento;
    }

    public void setComplemento(Integer complemento) {
        this.complemento = complemento;
    }

    public User getPessoa() {
        return user;
    }

    public void setPessoa(User user) {
        this.user = user;
    }

    public Estado getEstado() {
        return nation;
    }

    public void setEstado(Estado nation) {
        this.nation = nation;
    }

    public TipoEndereco getTipoendereco() {
        return type_address;
    }

    public void setTipoendereco(TipoEndereco type_address) {
        this.type_address = type_address;
    }

    public TipoLogradouro getTipologradouro() {
        return type_region;
    }

    public void setTipologradouro(TipoLogradouro type_region) {
        this.type_region = type_region;
    }

    public Cidade getCidade() {
        return city;
    }

    public void setCidade(Cidade city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idEndereco != null ? this.idEndereco.hashCode() : 0);
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
        final Endereco other = (Endereco) obj;
        if (this.idEndereco != other.idEndereco && (this.idEndereco == null || !this.idEndereco.equals(other.idEndereco))) {
            return false;
        }
        return true;
    }
        
}
