package the.biagi.luizalabs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.luizalabs.model.Usuario;
import the.biagi.luizalabs.repositories.UsuarioRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
        if (usuario != null) {
            BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();
            usuario.setSenhaUsuario(criptografia.encode(usuario.getSenhaUsuario()));
            usuarioRepository.save(usuario);
            LOGGER.info("Usuário criado");
            return ResponseEntity.created(null).build();
        }
        LOGGER.info("Problema ao criar um usuário");
        return ResponseEntity.badRequest().build();
    }
}
