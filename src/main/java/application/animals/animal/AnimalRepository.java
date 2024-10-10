package application.animals.animal;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


// tell spring this anno is gonna in charge of this class, so Spring has an instance on this class
@Repository
public class AnimalRepository {

    private List<Animal> animals = new ArrayList<>();

    List<Animal> findAll() {
        return animals;
    }

    // Do some initialization for the class
    @PostConstruct
    public void init() {
        animals.add(new Animal(
            "Osin",
            LocalDateTime.now().minus(10, ChronoUnit.YEARS),
            "Dog",
            new Location("Helsinki", List.of(0.45, 0.54))
        ));
        animals.add(new Animal(
            "Seppo",
            LocalDateTime.now().minus(10, ChronoUnit.MONTHS),
            "Cat",
            new Location("Helsinki", List.of(0.45, 0.54))
        ));

    }

}
