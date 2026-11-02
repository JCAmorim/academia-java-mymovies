package pt.rumos.mymovies.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovieRequest(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 120, message = "O título não pode exceder 120 caracteres")
        String title,

        @NotBlank(message = "O realizador é obrigatório")
        String director,

        @NotNull(message = "O ano é obrigatório")
        @Min(value = 1888, message = "Ano inválido para o cinema")
        Integer year
) {}
