package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Umowa {

    private Long numer;
    private String tresc;

    private List<Termin> terminy = new ArrayList<>();
    private Sprzedawca sprzedawca;
    private Klient klient;

    public Umowa(String tresc){
        this.tresc = tresc;
    }

    public Umowa() {

    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getNumer() {
        return numer;
    }
    public void setNumer(Long numer) {
        this.numer = numer;
    }


    @Basic
    public String getTresc() {
        return tresc;
    }
    public void setTresc(String tresc) {
        this.tresc = tresc;
    }


    @OneToMany(mappedBy = "umowa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Termin> getTerminy(){
        return terminy;
    }
    private void setTerminy(List<Termin> terminy) {
        this.terminy = terminy;
    }

    public void addTermin(Termin termin){
        if(!terminy.contains(termin)) {
            getTerminy().add(termin);
            termin.setUmowa(this);
        }
    }
    public void removeTermin(Termin termin){
        getTerminy().remove(termin);
        termin.setUmowa(null);
    }


    @ManyToOne
    public Sprzedawca getSprzedawca(){
        return sprzedawca;
    }
    public void setSprzedawca(Sprzedawca sprzedawca){
        this.sprzedawca = sprzedawca;
    }


    @ManyToOne
    public Klient getKlient() {
        return klient;
    }
    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    @Override
    public String toString() {
        return getNumer() + " " + getTresc();
    }
}
