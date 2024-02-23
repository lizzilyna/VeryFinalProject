package it.epicode.VeryFinalProject.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EventoRequest {
    @NotBlank(message = "campo obbligatorio")
    private String titolo;
    @NotBlank(message = "campo obbligatorio")
    private String descrizione;
    @NotBlank(message = "campo obbligatorio")
    private String luogo;
    @NotBlank(message = "campo obbligatorio")
    private Date data;
    @NotBlank(message = "campo obbligatorio")
    private int postiDisponibili;

    private int user_id;
}
