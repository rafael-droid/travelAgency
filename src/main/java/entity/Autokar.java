package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Autokar extends Pojazd{
    private double  srednieSpalanie;

    public Autokar(String marka, LocalDate ostatniPrzeglad, int liczbaMiejsc, double srednieSpalanie){
        super(marka, ostatniPrzeglad, liczbaMiejsc);
        this.srednieSpalanie = srednieSpalanie;
    }

    public Autokar() {

    }

    @Basic
    public double getSrednieSpalanie() {
        return srednieSpalanie;
    }
    public void setSrednieSpalanie(double srednieSpalanie) {
        this.srednieSpalanie = srednieSpalanie;
    }

    @Override
    public String toString() {
        return getMarka() + ", miejsca: " + getLiczbaMiejsc();
    }
}
