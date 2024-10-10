package application.animals.animal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Animal(
        Integer id,
        String animalName,
        LocalDateTime birthdate,
        String species,
        Location location
) { }
