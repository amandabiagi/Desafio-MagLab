package the.biagi.DesafioMagaLab.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.config.TokenService;
import the.biagi.DesafioMagaLab.model.FormLogin.FormLogin;
import the.biagi.DesafioMagaLab.model.dto.TokenDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authManager;//Não é permitido injetar essa classe, mas podemos sobrescrever um método da classe WebSecurityConfigurerAdapter que permite injeta-lá, fiz isso na classe SecurityConfigurations

    @Autowired
    TokenService tokenService;

    //Esse endpoint ele recebe as credenciais do usuario verifica no banco se é válido e caso seja vállido ele retorna
    //um token que é foi gerado utilizando as dependencias do jasontoken.

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid FormLogin formLogin){
        UsernamePasswordAuthenticationToken dadosLogin = formLogin.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin); //quando o codigo chegar nessa linha o spring vai olhar as configurações que fizemos e ele vai saber que vai ter que chamar a nossa service
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer")); //Retorna o token que será gerado e o tipo que foi definido que será o Bearer.
        }catch (AuthenticationException e){
            return ResponseEntity.status(404).body(e);
        }


    }


}
