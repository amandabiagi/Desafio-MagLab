package the.biagi.DesafioMagaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.model.Usuario;
import the.biagi.DesafioMagaLab.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return ResponseEntity.created(null).build();
    }
}
