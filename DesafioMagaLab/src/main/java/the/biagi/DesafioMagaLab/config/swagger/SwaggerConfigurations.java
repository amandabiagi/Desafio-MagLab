package the.biagi.DesafioMagaLab.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

    @Value("${apicep.swagger.path}")
    private String swaggerPath;


    public Docket enderecoApi() {

        List<Parameter> listaParametros = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)//Tipo do documento que será gerado
                .host(swaggerPath)//Local de acesso
                .groupName("All")
                .apiInfo(metaInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("the.biagi.DesafioMAgaLab")) // pacote inicial de onde o swagger vai começar a mapear
                .paths(PathSelectors.any()) //EndPoints liberados para o mapeamento
                .build() //criar build
                .globalOperationParameters(listaParametros)
        ;
    }

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Desafio MagaLab")
                .description("API Rest de cadastro de endereço")
                .termsOfServiceUrl("http://localhost:8080")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .contact(new Contact("Amanda Rocha de Biagi",
                        "https://github.com/amandabiagi",
                        "amanda.biagi@outlook.com"))
                .build();
    }

}
