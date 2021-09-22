package the.biagi.luizalabs.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import the.biagi.luizalabs.model.Usuario;
import the.biagi.luizalabs.repositories.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UsuarioController.class)
class UsuarioControllerTest {

    @MockBean
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioController usuarioController;

    @Test
    void cadastroCorreto() {

        Usuario usuario = Mockito.mock(Usuario.class);
        Usuario usuarioTeste = new Usuario(1,
                "Amanda", "amanda.biagi@gmail.com", "12345");
        Mockito.when(
                usuarioRepository.save(usuario)).thenReturn(null);
        ResponseEntity<?> resposta = usuarioController.cadastrar(usuarioTeste);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void cadastroIncorreto() {
            Mockito.when(
                    usuarioRepository.save(new Usuario())).thenReturn(null);
            ResponseEntity<?> resposta = usuarioController.cadastrar(null);
            assertEquals(400, resposta.getStatusCodeValue());
    }

}