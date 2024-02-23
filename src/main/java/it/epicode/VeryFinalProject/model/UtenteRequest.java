package it.epicode.VeryFinalProject.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteRequest {

    @NotBlank(message = "campo obbligatorio")
    private String nome;
    @NotBlank(message = "campo obbligatorio")
    private  String cognome;
    @NotBlank(message = "campo obbligatorio")
    private String username;
    @NotBlank(message = "campo obbligatorio")
    private String password;
}
