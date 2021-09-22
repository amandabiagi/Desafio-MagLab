package the.biagi.luizalabs.model.FormLogin;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FormLogin {

    private String email;
    private String senha;

    public FormLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public FormLogin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email,senha);
    }
}
