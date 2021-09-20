package the.biagi.DesafioMagaLab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import the.biagi.DesafioMagaLab.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailUsuario(String email);
}
