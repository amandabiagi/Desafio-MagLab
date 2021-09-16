package the.biagi.DesafioMagaLab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.DesafioMagaLab.viacep.EnderecoViaCep;
import the.biagi.DesafioMagaLab.viacep.ViaCepService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCep> cadastrarEndereco(@PathVariable String cep){

        return ResponseEntity.ok(viaCepService.buscarCep(cep));
    }
}
