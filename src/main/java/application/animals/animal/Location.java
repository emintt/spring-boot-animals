package application.animals.animal;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record Location(
        @NotEmpty
        String type,
        @NotEmpty
        List<Double> coordinates
) {
}
