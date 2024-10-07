package application.animals.animal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Animal(
        String animalName,
        LocalDateTime birthdate,
        String species,
        String location,
        Double[] coordinates
) { }
