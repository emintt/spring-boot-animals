package application.media.media;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


// tell spring this anno is gonna in charge of this class, so Spring has an instance on this class
@Repository
public class MediaRepository {

    private final List<Media> mediaItems = new ArrayList<>();

    List<Media> findAll() {
        return mediaItems;
    }

    Optional<Media> findById(Integer id) {
        return mediaItems.stream()
                .filter(media -> Objects.equals(media.mediaId(), id))
                .findFirst();
    }

    void create(Media media) {
        this.mediaItems.add(media);
    }

    void update(Media media, Integer id) {
        Optional<Media> existingMedia = findById(id);
        if (existingMedia.isPresent()) {
            this.mediaItems.set(this.mediaItems.indexOf(existingMedia.get()), media);
        }
    }

    void delete(Integer id) {
        mediaItems.removeIf(media -> media.mediaId().equals(id));
    }

    // Do some initialization for the class
    @PostConstruct
    public void init() {

        mediaItems.add(new Media(
                1,
                1,
                "beautiful-sun.jpg",
                10000,
                "image/jpeg",
                "beautiful sun",
                "example media item",
                LocalDateTime.now()
        ));
        mediaItems.add(new Media(
                2,
                2,
                "under-the-moon.jpg",
                10770,
                "image/jpeg",
                "under the moon",
                "example media item 2",
                LocalDateTime.now()
        ));

    }

}
