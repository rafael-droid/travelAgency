package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Samolot extends Pojazd{
    private double pojemnoscZbiornika;

    public Samolot(String marka, LocalDate ostatniPrzeglad, int liczbaMiejsc, double pojemnoscZbiornika){
        super(marka, ostatniPrzeglad, liczbaMiejsc);
        this.pojemnoscZbiornika = pojemnoscZbiornika;
    }

    public Samolot() {

    }

    @Basic
    public double getPojemnoscZbiornika() {
        return pojemnoscZbiornika;
    }
    public void setPojemnoscZbiornika(double pojemnoscZbiornika) {
        this.pojemnoscZbiornika = pojemnoscZbiornika;
    }

    @Override
    public String toString() {
        return getMarka() + ", miejsca: " + getLiczbaMiejsc();
    }
}
