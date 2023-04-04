package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name ="Wycieczka")
public class Wycieczka {
    private double cena;
    private String opis;
    private String nazwa;
    private Long id;
    private List<Kraj> kraje;
    private List<Termin> terminy;
    private Pojazd pojazd;
    private List<Przewodnik> przewodnicy;
    private Ubezpieczenie ubezpieczenie;


    public Wycieczka(String nazwa, double cena, String opis){
        this.nazwa = nazwa;
        this.cena = cena;
        this.opis = opis;
        terminy = new ArrayList<>();
        kraje = new ArrayList<>();
        przewodnicy = new ArrayList<>();
    }

    public Wycieczka() {

    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    public double getCena() {
        return cena;
    }
    public void setCena(double cena) {
        this.cena = cena;
    }

    @OneToMany(mappedBy = "wycieczka", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Termin> getTerminy(){
        return terminy;
    }
    public void setTerminy(List<Termin> terminy) {
        this.terminy = terminy;
    }

    public void addTermin(Termin termin){
        getTerminy().add(termin);
        termin.setWycieczka(this);
    }
    public void removeTermin(Termin termin){
        getTerminy().remove(termin);
        termin.setWycieczka(null);
    }

    @ManyToOne
    public Pojazd getPojazd(){
        return pojazd;
    }
    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }


    @ManyToMany(mappedBy = "wycieczki", cascade = { CascadeType.ALL })
    public List<Przewodnik> getPrzewodnicy(){ return przewodnicy;}
    private void setPrzewodnicy(List<Przewodnik> przewodnicy) {this.przewodnicy = przewodnicy;}
    public void addPrzewodnik(Przewodnik przewodnik){
        if(!przewodnicy.contains(przewodnik)) {
            getPrzewodnicy().add(przewodnik);
            przewodnik.addWycieczka(this);
        }
    }
    public  void removePrzewodnik(Przewodnik przewodnik){
        getPrzewodnicy().remove(przewodnik);
        przewodnik.addWycieczka(null);
    }


    @ManyToMany(mappedBy = "wycieczki",cascade = { CascadeType.ALL })
    public List<Kraj> getKraje(){ return kraje;}
    private void setKraje(List<Kraj> kraje){this.kraje = kraje;}
    public void addKraj(Kraj kraj){
        if(!kraje.contains(kraj)){
            getKraje().add(kraj);
            kraj.addWycieczka(this);
        }
    }
    public void removeKraj(Kraj kraj){
        getKraje().remove(kraj);
        kraj.addWycieczka(null);
    }



    @ManyToOne
    @JoinColumn(name = "ubezpieczenie_id")
    public Ubezpieczenie getUbezpieczenie(){ return ubezpieczenie;}
    public void setUbezpieczenie(Ubezpieczenie ubezpieczenie) {
        this.ubezpieczenie = ubezpieczenie;
    }

    @Basic
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
