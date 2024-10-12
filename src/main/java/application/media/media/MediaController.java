package application.media.media;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaRepository mediaRepository;

    // controller's constructor
    public MediaController(MediaRepository mediaRepository) {    // ask Spring for RunRepository
        this.mediaRepository = mediaRepository;
    }

    // return a list of media
    @GetMapping("")
    List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @GetMapping("/{id}")
    Media findById(@PathVariable Integer id) {

        Optional<Media> media = mediaRepository.findById(id);
        if(media.isEmpty()) {
            throw new MediaNotFoundException();
        }

        return media.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Media media) {  // tell Spring that it's coming as a request body @RequestBody
        mediaRepository.create(media);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Media media, @PathVariable Integer id) {
        mediaRepository.update(media, id);
    }


    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        mediaRepository.delete(id);
    }

}
