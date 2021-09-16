package the.biagi.DesafioMagaLab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import the.biagi.DesafioMagaLab.model.Endereco;
import the.biagi.DesafioMagaLab.viacep.ViaCepService;

@Service
public class EnderecoService {

    @Autowired
    ViaCepService viaCepService;


    //Método que valida o cep, se é apenas números e se tem a quantidade correta de caracteres
    public Boolean validarCep(String cep) {

        String cepFormat = cep.replace("-", "");
        return cepFormat.matches("[0-9]+") && cepFormat.length() == 8;
    }

    //Método que modifica o CEP para localizar um existente
    public ResponseEntity<?> enderecoExistente(String cep) {
        StringBuilder novoCep = new StringBuilder(cep);
        int index = 7;
        do{
            Endereco endereco = new Endereco(viaCepService.buscarCep(novoCep.toString()));
            if(endereco.getCep() != null){
                return ResponseEntity.ok(endereco);
            }
            novoCep.setCharAt(index, '0');
            index --;

        }while (index >= 0);

        return ResponseEntity.notFound().build();

    }


}
