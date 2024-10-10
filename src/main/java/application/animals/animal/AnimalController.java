package application.animals.animal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    private final AnimalRepository animalRepository;

    // controller's constructor
    public AnimalController(AnimalRepository animalRepository) {    // ask Spring for RunRepository
        this.animalRepository = animalRepository;
    }

    // return a list of animals
    @GetMapping("/api/animals")
    List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @GetMapping("/api/hello")
    String hone () {
        return "Hello";
    }
}
