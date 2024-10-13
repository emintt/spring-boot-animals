package application.media.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Order(1)
public class UserJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(UserJsonDataLoader.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserJsonDataLoader(UserRepository userRepository) {

        this.userRepository = userRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/users.json")) {
                Users allUsers = objectMapper.readValue(inputStream, Users.class);
                log.info("Reading {} users from JSON data and saving to in-memory collection", allUsers.users().size());
                userRepository.saveAll(allUsers.users());
            } catch (IOException e) {
                log.error("Error reading users from JSON data", e);
                throw new RuntimeException("Error reading users from JSON data", e);
            }
        } else {
            log.info(" Not loading users from JSON data because the collection contains data");
        }
    }
}
