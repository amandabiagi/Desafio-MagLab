package the.biagi.DesafioMagaLab.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import the.biagi.DesafioMagaLab.controllers.EnderecoController;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoServiceTest {

    @MockBean
    EnderecoService enderecoService;

    @Autowired
    EnderecoController enderecoController;

    @Test
    @DisplayName("Deve-se retornar cep inválido e status 404 por conter letras")
    void cepComLetras() {
        String cep = "23233ed2";
        Mockito.when(
                        enderecoService.validarCep(cep))
                .thenReturn(false);
        ResponseEntity<?> resposta = enderecoController.cadastrarEndereco(cep);
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("CEP inválido", resposta.getBody());
    }

    @Test
    @DisplayName("Deve-se retornar cep inválido e status 404 por conter menos caracteres que o desejado")
    void cepNumeroMenor() {
        String cep = "02536";
        Mockito.when(
                        enderecoService.validarCep(cep))
                .thenReturn(false);
        ResponseEntity<?> resposta = enderecoController.cadastrarEndereco(cep);
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("CEP inválido", resposta.getBody());

    }

    @Test
    @DisplayName("Deve-se retornar cep inválido e status 404 por conter mais caracteres que o desejado")
    void cepNumeroMaior() {
        String cep = "025362563";
        Mockito.when(
                        enderecoService.validarCep(cep))
                .thenReturn(false);
        ResponseEntity<?> resposta = enderecoController.cadastrarEndereco(cep);
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("CEP inválido", resposta.getBody());

    }

    @Test
    @DisplayName("Deve-se retornar true, ignorando o hífen (-) no cep disponibilizado")
    void cepComHifen() {
        String cep = "03223-070";
        Mockito.when(
                        enderecoService.validarCep(cep))
                .thenReturn(true);
        Boolean resposta = enderecoService.validarCep(cep);
        assertTrue(resposta);

    }
}