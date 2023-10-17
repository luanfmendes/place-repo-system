package io.github.places;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Places System API", version = "1.0", description = "Place system"))
public class PlacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacesApplication.class, args);
	}

}
