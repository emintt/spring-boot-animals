package application.media.media;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


public record Media(
        @Id
        Integer mediaId,
        Integer userId,
        @NotNull
        String filename,
        @NotNull
        Integer filesize,
        @NotNull
        String mediaType,
        @Size(min = 2, max = 255)
        @NotNull
        String title,
        @Size(min = 2, max = 255)
        @NotNull
        String description,
        LocalDateTime createdAt
) {
}
