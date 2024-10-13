package application.media.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


public record User(
        Integer userId,
        @NotEmpty
        String username,
        @Size(min = 5)
        @NotEmpty
        String password,
        @Email
        String email,
        Integer userLevelId,
        LocalDateTime createdAt
) {
//    public User {
//        if(userLevelId == null) {
//            userLevelId = 2;
//        }
//    }
}
