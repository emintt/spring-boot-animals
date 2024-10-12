package application.media.media;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public record Media(
        Integer mediaId,
        Integer userId,
        @NotNull
        String filename,
        @NotNull
        Integer filesize,
        @NotNull
        String mediaType,
        @NotNull
        String title,
        @NotNull
        String description,
        LocalDateTime createdAt
) {

        public Media {
                if(createdAt == null) {
                        createdAt = LocalDateTime.now();
                }
        }
}
