package it.epicode.VeryFinalProject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titolo;
    private String descrizione;
    private Date date;
    private String luogo;

    @Column(name="posti_disponibili")
    private int postiDisponibili;

    @ManyToMany
    @JoinTable(name = "lista_utenti")
    private List<Utente> utenti;

    public Evento(String titolo, String descrizione, Date date, String luogo, int postiDisponibili ) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.date = date;
        this.luogo = luogo;
        this.postiDisponibili = postiDisponibili;
        this.utenti = new ArrayList<>();
    }

    public Evento() {
    }

    public void addUtente(Utente utente){
        utenti.add(utente);
    }
}



