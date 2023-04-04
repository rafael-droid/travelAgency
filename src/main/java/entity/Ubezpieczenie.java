package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Ubezpieczenie")
public class Ubezpieczenie {
    private Long id;
    private String nazwa;
    private double kwota;
    private List<Wycieczka> wycieczki = new ArrayList<>();

    public Ubezpieczenie(String nazwa, double kwota){
        this.nazwa =nazwa;
        this.kwota = kwota;

    }

    public Ubezpieczenie() {

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
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    public double getKwota() {
        return kwota;
    }
    public void setKwota(double kwota) {
        this.kwota = kwota;
    }



    @OneToMany(mappedBy = "ubezpieczenie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wycieczka> getWycieczki(){
        return wycieczki;
    }
    private void setWycieczki(List<Wycieczka> wycieczki) {
        this.wycieczki = wycieczki;
    }
    public void addWycieczka(Wycieczka wycieczka){
        if(!wycieczki.contains(wycieczka)) {
            getWycieczki().add(wycieczka);
            wycieczka.setUbezpieczenie(this);
        }
    }
    public void removeWycieczka(Wycieczka wycieczka){
        getWycieczki().remove(wycieczka);
        wycieczka.setUbezpieczenie(null);
    }

    @Override
    public String toString() {
        return getNazwa()  + " " +getKwota();
    }
}



