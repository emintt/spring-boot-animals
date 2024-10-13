package application.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    // add data to db
//    @Bean
//    CommandLineRunner media(MediaRepository mediaRepository) {
//        return args -> {
//            Media media = new Media(1, 1, "test-file-name.jpg", 123456, "image/jpg", "my test title", "test descrription", LocalDateTime.now());
//            mediaRepository.create(media);
//            log.info("my media" + media);
//        };
//    }

}