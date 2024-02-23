package it.epicode.VeryFinalProject.service;

import it.epicode.VeryFinalProject.exception.NotFoundException;
import it.epicode.VeryFinalProject.model.Evento;
import it.epicode.VeryFinalProject.model.EventoRequest;
import it.epicode.VeryFinalProject.model.Utente;
import it.epicode.VeryFinalProject.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    public Page<Evento> cercaTuttiGliEventi(Pageable pageable){
        return  eventoRepository.findAll(pageable);
    }

    public Evento cercaEventoPerId(int id) throws NotFoundException{
        return eventoRepository.findById(id).
                orElseThrow(()->new NotFoundException("Evento con id="+id+" non trovato"));
    }

    public Evento salvaEvento(EventoRequest eventoRequest) throws NotFoundException{


        Evento evento = new Evento(eventoRequest.getTitolo(), eventoRequest.getDescrizione(), eventoRequest.getData(), eventoRequest.getLuogo(), eventoRequest.getPostiDisponibili());

        return eventoRepository.save(evento);

    }

    public Evento aggiornaEvento(int id, EventoRequest eventoRequest) throws NotFoundException{
        Evento evento = cercaEventoPerId(id);

        Utente utente = utenteService.getUtenteById(eventoRequest.getUser_id());

        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setDate(eventoRequest.getData());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setPostiDisponibili(eventoRequest.getPostiDisponibili());

        return eventoRepository.save(evento);
    }


    public Evento aggiornaPartecipanti(EventoRequest eventoRequest, int id) throws NotFoundException{
        Evento evento=cercaEventoPerId((id));
        Utente utente=utenteService.getUtenteById(eventoRequest.getUser_id());

        evento.addUtente(utente);
        return eventoRepository.save(evento);
    }
    public void cancellaEvento(int id) throws NotFoundException{
        Evento post = cercaEventoPerId(id);
        eventoRepository.delete(post);
    }


}
