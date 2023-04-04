package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Termin {
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    private Wycieczka wycieczka;
    private Umowa umowa;
    private Long id;

    public Termin(LocalDate dataRozpoczecia, LocalDate dataZakonczenia){
        this.dataRozpoczecia =dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
    }


    public Termin() {

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

    @ManyToOne
    public Wycieczka getWycieczka(){
        return wycieczka;
    }
    public void setWycieczka(Wycieczka wycieczka) {
        this.wycieczka = wycieczka;
    }

    @ManyToOne
    public Umowa getUmowa(){
        return umowa;
    }
    public void setUmowa(Umowa umowa) {
        this.umowa = umowa;
    }

    public LocalDate getDataRozpoczecia() {
        return dataRozpoczecia;
    }
    public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public LocalDate getDataZakonczenia() {
        return dataZakonczenia;
    }
    public void setDataZakonczenia(LocalDate dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    @Override
    public String toString() {
        return getDataRozpoczecia() + " - " + getDataZakonczenia();
    }
}
