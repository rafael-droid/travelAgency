package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sprzedawca")
public class Sprzedawca extends Osoba {
    private LocalDate dataZatrudnienia;
    private List<Umowa> umowy = new ArrayList<>();

    public Sprzedawca(String imie, String nazwisko, String numerTel, List<String> email,LocalDate dataZatrudnienia){
        super(imie, nazwisko, numerTel, email);
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public Sprzedawca() {

    }

    @Basic
    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }
    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    @Transient
    public int getLataStazu(){
        return LocalDate.now().getYear() - getDataZatrudnienia().getYear();
    }


    @OneToMany(mappedBy = "sprzedawca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Umowa> getUmowy(){ return umowy;}
    private void setUmowy(List<Umowa> umowy) { this.umowy = umowy;}
    public void addUmowa(Umowa umowa){
        if(!umowy.contains(umowa)) {
            getUmowy().add(umowa);
            umowa.setSprzedawca(this);
        }
    }
    public void removeUmowa(Umowa umowa){
        getUmowy().remove(umowa);
        umowa.setSprzedawca(null);
    }

    @Override
    public String toString() {
        return getImie() + " " + getNazwisko();
    }
}
