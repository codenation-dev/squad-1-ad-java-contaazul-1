package com.central.config.swagger;


import java.util.Arrays;
import org.springframework.context.annotation.Configuration;
import com.central.entity.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.ws.rs.Path;

@Path("/documentacao")
@Configuration
@EnableSwagger2
public class SwaggerConfiguration   {
	
	/**
	 * http://localhost:8080/swagger-ui.html
	 */
	
	public static final Contact DEFAULT_CONTACT = new Contact("squad 1", "https://github.com/codenation-dev/squad-1-ad-java-contaazul-1", 
			"Larissa, Nathalia, Paula, Silmara, Vanessa");
	
			
public Docket api() {			
	return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.central"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .ignoredParameterTypes(Usuario.class)
            .globalOperationParameters(
                    Arrays.asList(
                            new ParameterBuilder()
                                .name("LADY BUG - Central de Erros REST API")
                                .description("Rest API")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()))
            					.apiInfo(apiInfo());
}

private ApiInfo apiInfo() {
	return new ApiInfoBuilder()
			.title("LADY BUG - Central de Erros REST API")
			.description("\"Rest API\"")
			.contact(new Contact("Squad 1 ", "https://github.com/codenation-dev/squad-1-ad-java-contaazul-1", "Larissa, Nathalia, Paula, Silmara, Vanessa"))
			.build();

	}

}
