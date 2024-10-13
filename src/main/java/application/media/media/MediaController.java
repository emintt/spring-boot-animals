package application.media.media;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Get all media items",
            responses = @ApiResponse(
                    responseCode = "200"
            )
    )
    @GetMapping("")
    List<Media> findAll() {
        return mediaRepository.findAll();
    }


    @Operation(
            summary = "Get media item by id",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "success"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "media not found"
                )
            }
    )
    @GetMapping("/{id}")
    Media findById(@PathVariable Integer id) {

        Optional<Media> media = mediaRepository.findById(id);
        if(media.isEmpty()) {
            throw new MediaNotFoundException();
        }

        return media.get();
    }

    @Operation(
            summary = "Get all media items by user id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "media not found"
                    )
            }
    )
    @GetMapping("/user/{id}")
    List<Media> findByUser(@PathVariable Integer id) {
        List<Media> mediaItems = mediaRepository.findMediaItemsByUser(id);
        if(mediaItems.isEmpty()) {
            throw new MediaNotFoundException();
        }

        return mediaItems;
    }

    // post
    @Operation(
            summary = "Create a media",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "bad request"
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Media media) {  // tell Spring that it's coming as a request body @RequestBody
        mediaRepository.create(media);
    }

    // put
    @Operation(
            summary = "Modify a media",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "bad request"
                    )
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Media media, @PathVariable Integer id) {
        mediaRepository.update(media, id);
    }


    // delete
    @Operation(
            summary = "Delete a media",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "success"
                    )
            }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        mediaRepository.delete(id);
    }

}
