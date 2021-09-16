package the.biagi.DesafioMagaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.model.Endereco;
import the.biagi.DesafioMagaLab.service.EnderecoService;
import the.biagi.DesafioMagaLab.viacep.ViaCepService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    ViaCepService viaCepService;

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable String cep) {

        if (!enderecoService.validarCep(cep)) {
            return ResponseEntity.status(404).body("CEP inválido");
        }
        return enderecoService.enderecoExistente(cep);

    }
}





