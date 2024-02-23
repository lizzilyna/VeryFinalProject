package it.epicode.VeryFinalProject.controller;


import it.epicode.VeryFinalProject.exception.BadRequestException;
import it.epicode.VeryFinalProject.model.Evento;
import it.epicode.VeryFinalProject.model.EventoRequest;
import it.epicode.VeryFinalProject.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventi")
    public Page<Evento> getAll(Pageable pageable){

        return eventoService.cercaTuttiGliEventi(pageable);
    }
    @GetMapping("/eventi/{id}")
    public Evento getEventoById(@PathVariable int id){
        return eventoService.cercaEventoPerId(id);

    }
    @PostMapping("/eventi")
    public Evento saveEvento(@RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.salvaEvento(eventoRequest);
    }
    @PatchMapping("/eventi/{id}")
    public Evento aggiornaPartecipanti(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.aggiornaPartecipanti(eventoRequest, id);
    }
    @PutMapping("/eventi/{id}")
    public Evento updateEvento(@PathVariable int id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return eventoService.aggiornaEvento(id, eventoRequest);
    }

    @DeleteMapping("/eventi/{id}")
    public void deleteEvento(@PathVariable int id){
        eventoService.cancellaEvento(id);
    }
}