package application.media.user;

import application.media.media.Media;
import application.media.media.MediaNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // return a list of user
    @Operation(
            summary = "Get all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success"
                    )
            }
    )
    @GetMapping("")
    List<User> findAll() {
        return userRepository.findAll();
    }

    @Operation(
            summary = "Get a user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "user not found"
                    )
            }
    )
    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    // post
    @Operation(
            summary = "Create a user",
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
    void create(@Valid @RequestBody User user) {  // tell Spring that it's coming as a request body @RequestBody
        userRepository.create(user);
    }

    // put
    @Operation(
            summary = "Modify a user",
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
    void update(@Valid @RequestBody User user, @PathVariable Integer id) {
        userRepository.update(user, id);
    }


    // delete
    @Operation(
            summary = "Delete a user",
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
        userRepository.delete(id);
    }
}
