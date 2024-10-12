package application.media.media;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MediaJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MediaJsonDataLoader.class);
    private final MediaRepository mediaRepository;
    private final ObjectMapper objectMapper;

    public MediaJsonDataLoader(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        // to deserialize json into a list of media
        this.objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void run(String... args) throws Exception {
        if(mediaRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/mediaItems.json")) {
                MediaItems allMediaItems = objectMapper.readValue(inputStream, MediaItems.class);
                log.info("Reading {} media items from JSON data and saving to in-memory collection", allMediaItems.mediaItems().size());
                mediaRepository.saveAll(allMediaItems.mediaItems());
            } catch (IOException e) {
                log.error("Error reading media items from JSON data", e);
                throw new RuntimeException("Error reading media items from JSON data", e);
            }
        } else {
            log.info(" Not loading media items from JSOSN data because the collection contains data");
        }
    }
}
