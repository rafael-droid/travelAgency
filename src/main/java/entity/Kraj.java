package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kraj {

    private Long id;
    private String nazwa;
    private double odleglosc;
    private List<Wycieczka> wycieczkiList = new ArrayList<>();

    public Kraj(String nazwa, double odleglosc){
        this.nazwa = nazwa;
        this.odleglosc = odleglosc;
    }

    public Kraj() {

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
    public double getOdleglosc() {
        return odleglosc;
    }
    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Wycieczka_Kraj", joinColumns = @JoinColumn(name="wycieczkaId"), inverseJoinColumns = @JoinColumn(name = "krajId"))
    public List<Wycieczka> getWycieczki(){ return wycieczkiList;}
    private void setWycieczki(List<Wycieczka> wycieczki){ this.wycieczkiList = wycieczki;}
    public void addWycieczka(Wycieczka wycieczka){
        if(!wycieczkiList.contains(wycieczka)){
            getWycieczki().add(wycieczka);
            wycieczka.addKraj(this);
        }
    }
    public void removeWycieczka(Wycieczka wycieczka){
        getWycieczki().remove(wycieczka);
        wycieczka.addKraj(null);
    }

    @Override
    public String toString() {
        return getNazwa() + ", odległość: "+getOdleglosc();
    }
}
