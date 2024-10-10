package application.animals.animal;

import java.util.List;

public record Location(
        String type,
        List<Double> coordinates
) {
}
