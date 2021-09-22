package the.biagi.luizalabs.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser preenchido")
    @Size(min = 3)
    private String nomeUsuario;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser preenchido")
    private String emailUsuario;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser preenchido")
    @Size(min = 5)
    private String senhaUsuario;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(Integer idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() { //quando ele chamar o atributo senha ele chama esse método
        return this.senhaUsuario; //indicando onde está o atributo da nossa classe
    }

    @Override
    public String getUsername() {
        return this.emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() { // A conta não está expirada ?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // A conta não está bloqueada ?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // As credencias não estão expiradas ?
        return true;
    }

    @Override
    public boolean isEnabled(){ // A conta esta habilitada
        return true;
    }

}
