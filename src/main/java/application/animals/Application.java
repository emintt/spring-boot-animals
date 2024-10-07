package application.animals;

import application.animals.animal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner animal() {
        return args -> {
            Animal animal = new Animal("my puppy", LocalDateTime.now(), "dog", "Helsinki", new Double[]{0.45, 0.54});
            log.info("my animal" + animal);
        };
    }

}