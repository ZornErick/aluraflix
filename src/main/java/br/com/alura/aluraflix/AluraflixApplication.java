package br.com.alura.aluraflix;

import br.com.alura.aluraflix.domain.categoria.Categoria;
import br.com.alura.aluraflix.domain.categoria.CategoriaRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AluraflixApplication {

    public static void main(String[] args) {
        SpringApplication.run(AluraflixApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner run(CategoriaRepo categoriaRepo) {
        return args -> {
            if(categoriaRepo.findById(1L).isEmpty()) categoriaRepo.save(new Categoria("LIVRE", "BRANCA"));
        };
    }

}
