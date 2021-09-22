package the.biagi.luizalabs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import the.biagi.luizalabs.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailUsuario(String email);
}
