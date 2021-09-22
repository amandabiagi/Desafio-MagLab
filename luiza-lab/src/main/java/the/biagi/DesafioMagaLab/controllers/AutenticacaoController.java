package the.biagi.DesafioMagaLab.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacaoController.class);

    //Não é permitido injetar essa classe, mas podemos sobrescrever um método da classe WebSecurityConfigurerAdapter que permite injeta-lá, fiz isso na classe SecurityConfigurations
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    TokenService tokenService;

    //Esse endpoint ele recebe as credenciais do usuario, verifica no banco se é válido e caso seja, retorna
    //um token, gerado pelo JWT.

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid FormLogin formLogin){
        UsernamePasswordAuthenticationToken dadosLogin = formLogin.converter();

        try {
            //Aqui o spring olha as configurações e chama a service
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            //Retorna um token com o tipo definido, no caso Bearer.
            LOGGER.info("Acesso autorizado");
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        }catch (AuthenticationException e){

            LOGGER.info("Acesso não autorizado");
            return ResponseEntity.status(404).body(e);
        }


    }


}
