package the.biagi.DesafioMagaLab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class DesafioMagaLabApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(DesafioMagaLabApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Iniciando API de cadastro de endereco");
		SpringApplication.run(DesafioMagaLabApplication.class, args);
		LOGGER.info("API iniciada com sucesso");

	}

}
