package application.animals.animal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Animal(
        Integer id,
        @Size(min = 2)
        @NotEmpty
        String animalName,
        @NotNull
        LocalDateTime birthdate,
        @NotEmpty
        String species,
        Location location
) {

        public Animal {
                if(birthdate.isAfter(LocalDateTime.now())) {
                        throw new IllegalArgumentException("birthdate is after now");
                }
        }
}
