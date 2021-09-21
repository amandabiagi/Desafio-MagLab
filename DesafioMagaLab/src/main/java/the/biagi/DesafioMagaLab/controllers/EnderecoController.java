package the.biagi.DesafioMagaLab.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.service.EnderecoService;
import the.biagi.DesafioMagaLab.viacep.ViaCepService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable String cep) {

        if (!enderecoService.validarCep(cep)) {
            LOGGER.info("Cep invalido.");
            return ResponseEntity.status(404).body("CEP inválido");
        }
        LOGGER.info("Retorno de endereço com sucesso");
        return enderecoService.enderecoExistente(cep);

    }
}





