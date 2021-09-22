package the.biagi.luizalabs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.luizalabs.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable String cep) {

        if (!enderecoService.validarCep(cep)) {
            LOGGER.info("CEP inválido.");
            return ResponseEntity.status(404).body("CEP inválido");
        }
        LOGGER.info("Endereço retornado");
        return enderecoService.enderecoExistente(cep);

    }
}





