package the.biagi.DesafioMagaLab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import the.biagi.DesafioMagaLab.DesafioMagaLabApplication;
import the.biagi.DesafioMagaLab.model.Endereco;
import the.biagi.DesafioMagaLab.viacep.ViaCepService;

@Service
public class EnderecoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoService.class);

    @Autowired
    ViaCepService viaCepService;


    //Método que valida o cep, se é apenas números e se tem a quantidade correta de caracteres
    public Boolean validarCep(String cep) {

        String cepFormat = cep.replace("-", "");
        return cepFormat.matches("[0-9]+") && cepFormat.length() == 8;

    }

    //Método que modifica o CEP para localizar um existente
    public ResponseEntity<Endereco> enderecoExistente(String cep) {
        StringBuilder novoCep = new StringBuilder(cep);
        int index = 7;
        do{
            Endereco endereco = new Endereco(viaCepService.buscarCep(novoCep.toString()));
            if(endereco.getCep() != null){
                LOGGER.info("Endereço encontrado.");
                return ResponseEntity.ok(endereco);
            }
            novoCep.setCharAt(index, '0');
            index --;

        }while (index >= 0);

        LOGGER.info("Endereço não encontrado.");
        return ResponseEntity.notFound().build();

    }


}
