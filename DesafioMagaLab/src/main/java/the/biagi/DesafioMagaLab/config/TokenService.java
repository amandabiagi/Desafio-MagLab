package the.biagi.DesafioMagaLab.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import the.biagi.DesafioMagaLab.model.Usuario;

import java.util.Date;

@Service
public class TokenService {


    @Value("${crud.jwt.expiration}")
    String expiration; //Injeta o que foi criado no application.properties.

    @Value("${crud.jwt.secret}")
    String secret; //Injeta o que foi criado no application.properties.


    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal(); //Busca o Usuario logado
        Date hoje = new Date(); //Pegando a data e hora atual
        Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration)); // pegando a data e hora atual e acrescentando o EXPIRATION que foi criado no application.properties
        return Jwts.builder() //Vai buildar o token
                .setIssuer("API CRUD") //Nome de quem está gerando o token
                .setSubject(logado.getIdUsuario().toString()) //busca o Id do usuario que está logado.
                .setIssuedAt(hoje) //Data da geração do token
                .setExpiration(expiracao) //Data de expiração do token
                .signWith(SignatureAlgorithm.HS256, secret) // Geração do token com a chave que foi criada no application.properties
                .compact();
    }

    public Boolean isTokenValido(String token) {
        try {
            Jwts.parser()//Método que vai fazer a lógica, o parser do token, descriptografar e vê se é válido.
                    .setSigningKey(this.secret) //Passar a chave secret que ele vai usar para descriptografar
                    .parseClaimsJws(token);//Devolve um objeto que da para recuperar o token e o que tem dentro dele, ele retorna o objeto ou um exception
            return true;
        } catch (Exception error) {
            return false;
        }


    }

    public Integer getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}