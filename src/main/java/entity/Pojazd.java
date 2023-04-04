package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pojazd {
    private Long id;
    private String marka;
    private LocalDate ostatniPrzeglad;
    private int liczbaMiejsc;

    private List<Wycieczka> wycieczki = new ArrayList<>();

    public Pojazd(String marka, LocalDate ostatniPrzeglad, int liczbaMiejsc){
        this.marka = marka;
        this.ostatniPrzeglad = ostatniPrzeglad;
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public Pojazd() {

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
    public String getMarka() {
        return marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Basic
    public LocalDate getOstatniPrzeglad() {
        return ostatniPrzeglad;
    }
    public void setOstatniPrzeglad(LocalDate ostatniPrzeglad) {
        this.ostatniPrzeglad = ostatniPrzeglad;
    }

    @Basic
    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }
    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    @OneToMany(mappedBy = "pojazd", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wycieczka> getWycieczki(){
        return wycieczki;
    }
    private void setWycieczki(List<Wycieczka> wycieczki) {
        this.wycieczki = wycieczki;
    }
    public void addWycieczka(Wycieczka wycieczka){
        if(!wycieczki.contains(wycieczka)){
            getWycieczki().add(wycieczka);
            wycieczka.setPojazd(this);
        }

    }
    public void removeWycieczka(Wycieczka wycieczka){
        getWycieczki().remove(wycieczka);
        wycieczka.setPojazd(null);
    }
}
