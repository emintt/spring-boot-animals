package application.animals.animal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalRepository animalRepository;

    // controller's constructor
    public AnimalController(AnimalRepository animalRepository) {    // ask Spring for RunRepository
        this.animalRepository = animalRepository;
    }

    // return a list of animals
    @GetMapping("")
    List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    Animal findById(@PathVariable Integer id) {
        return animalRepository.findById(id);
    }


}
