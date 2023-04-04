package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Przewodnik extends Osoba{

    public enum Jezyk {angielski, niemiecki, czeski, francuski};
    private String specjalnosc;
    private Jezyk jezyk;
    private List<Wycieczka> wycieczki = new ArrayList<>();

    public Przewodnik(String imie, String nazwisko, String numerTel, List<String> email, String specjalnosc, Jezyk jezyk){
        super(imie, nazwisko, numerTel, email);
        this.jezyk = jezyk;
        this.specjalnosc = specjalnosc;
    }
    public Przewodnik() {

    }

    @Enumerated(EnumType.STRING)
    public Jezyk getJezyk(){
        return jezyk;
    }
    public void setJezyk(Jezyk jezyk){

    }
    @Basic
    public String getSpecjalnosc() {
        return specjalnosc;
    }
    public void setSpecjalnosc(String specjalnosc) {
        this.specjalnosc = specjalnosc;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "Wycieczka_Przewodnicy", joinColumns = @JoinColumn(name = "wycieczkaId"), inverseJoinColumns = @JoinColumn(name = "przewodnikId"))
    private List<Wycieczka> getWycieczki(){ return wycieczki;}
    private void setWycieczki(List<Wycieczka> wycieczki){ this.wycieczki = wycieczki;}
    public void addWycieczka(Wycieczka wycieczka){
        if(!wycieczki.contains(wycieczka)) {
            getWycieczki().add(wycieczka);
            wycieczka.addPrzewodnik(this);
        }
    }
    public void removeWycieczka(Wycieczka wycieczka){
        getWycieczki().remove(wycieczka);
        wycieczka.addPrzewodnik(null);
    }

    @Override
    public String toString() {
        return getImie() +" "+ getNazwisko();
    }
}
