package the.biagi.DesafioMagaLab.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCep", url = "http://viacep.com.br/ws/")
public interface ViaCepService {

    @GetMapping("{cep}/json")
    EnderecoViaCep buscarCep(@PathVariable String cep);

}
