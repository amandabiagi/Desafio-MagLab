package the.biagi.DesafioMagaLab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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

	@Value("${apicep.swagger.path}")
	private String swaggerPath;


	@Bean
	public Docket enderecoApi() {

		List<Parameter> listaParametros = new ArrayList<>();

		return new Docket(DocumentationType.SWAGGER_2)//Tipo do documento que será gerado
				.host(swaggerPath)//Local de acesso
				.apiInfo(info()) // Informações da API
				.select()
				.apis(RequestHandlerSelectors.basePackage(this.getClass().getPackageName()))
				.paths(PathSelectors.ant("/endereco/**")) //EndPoints liberados para o mapeamento
				.build() //criar build
				.globalOperationParameters(listaParametros)
				;
	}

	private ApiInfo info() {
		return new ApiInfoBuilder()
				.title("Desafio MagaLab")
				.description("API Rest de cadastro de endereço")
				.termsOfServiceUrl("http://localhost:8080")
				.license("")
				.licenseUrl("")
				.version("1.0")
				.contact(new Contact(
						"Amanda Rocha de Biagi",
						"https://github.com/amandabiagi",
						"amanda.biagi@outlook.com"))
				.build();
	}


}
