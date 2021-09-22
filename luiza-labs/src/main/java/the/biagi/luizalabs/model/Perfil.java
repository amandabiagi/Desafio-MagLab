package the.biagi.luizalabs.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfil implements GrantedAuthority{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;
    private String tipoPerfil;

    public Perfil(Integer idPerfil, String tipoPerfil) {
        this.idPerfil = idPerfil;
        this.tipoPerfil = tipoPerfil;
    }

    public Perfil() {
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    @Override
    public String getAuthority() {
        return this.tipoPerfil;
    }
}
