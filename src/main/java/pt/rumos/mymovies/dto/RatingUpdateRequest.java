package pt.rumos.mymovies.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RatingUpdateRequest(

        @NotNull(message = "Nota obrigatória")
        @DecimalMin(value="0.0", message = "Nota minima é 0.0")
        @DecimalMax(value="10.0", message = "Nota maxima é 10.0")
        Double rating
) {
}
