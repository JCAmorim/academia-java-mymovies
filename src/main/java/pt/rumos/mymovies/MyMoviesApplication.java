package pt.rumos.mymovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class MyMoviesApplication {

    public static void main(String[] args) {

        SpringApplication.run(MyMoviesApplication.class, args);
    }
    // Adicione este Bean para o Spring saber como construir o RestClient.Builder
    // RestClient é a alternativa síncrona moderna ao antigo RestTemplate
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    // Adicione este Bean para o Spring saber como construir o RestClient.Builder
    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
