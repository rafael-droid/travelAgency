package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Bus extends Pojazd{
    private double maksymalnaPredkosc;

    public Bus(String marka, LocalDate ostatniPrzeglad, int liczbaMiejsc, double maksymalnaPredkosc){
        super(marka,ostatniPrzeglad,liczbaMiejsc);
        this.maksymalnaPredkosc = maksymalnaPredkosc;
    }

    public Bus() {

    }

    @Basic
    public double getMaksymalnaPredkosc() {
        return maksymalnaPredkosc;
    }
    public void setMaksymalnaPredkosc(double maksymalnaPredkosc) {
        this.maksymalnaPredkosc = maksymalnaPredkosc;
    }

    @Override
    public String toString() {
        return getMarka() + ", miejsca: " + getLiczbaMiejsc();
    }
}
