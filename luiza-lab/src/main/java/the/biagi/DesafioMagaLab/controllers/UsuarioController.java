package the.biagi.DesafioMagaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.model.Usuario;
import the.biagi.DesafioMagaLab.repositories.UsuarioRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
        if (usuario != null) {
            BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();
            usuario.setSenhaUsuario(criptografia.encode(usuario.getSenhaUsuario()));
            usuarioRepository.save(usuario);
            return ResponseEntity.created(null).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
