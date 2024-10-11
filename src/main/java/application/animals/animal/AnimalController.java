package application.animals.animal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isEmpty()) {
            throw new AnimalNotFoundException();
        }

        return animal.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Animal animal) {  // tell Spring that it's coming as a request body @RequestBody
        animalRepository.create(animal);
    }

    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Animal animal, @PathVariable Integer id) {
        animalRepository.update(animal, id);
    }


    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        animalRepository.delete(id);
    }

}
