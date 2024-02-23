package it.epicode.VeryFinalProject.controller;

import it.epicode.VeryFinalProject.model.LoginRequest;
import it.epicode.VeryFinalProject.model.Utente;
import it.epicode.VeryFinalProject.model.UtenteRequest;
import it.epicode.VeryFinalProject.security.JwtTools;
import it.epicode.VeryFinalProject.service.UtenteService;
import it.epicode.VeryFinalProject.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);

    }
    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws ChangeSetPersister.NotFoundException {
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());

        return jwtTools.createToken(utente);
    }

}
