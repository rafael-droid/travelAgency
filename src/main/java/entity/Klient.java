package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Klient extends Osoba{
    private String pesel;
    private Adres adres;
    private List<Umowa> umowy = new ArrayList<>();

    public Klient(String imie, String nazwisko, String numerTel, List<String> emails, String pesel){
        super(imie, nazwisko, numerTel,emails);
        this.pesel = pesel;
    }

    public Klient() {

    }

    @Column( unique = true)
    public String getPesel() {
        return pesel;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Embedded
    public Adres getAdres(){
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Umowa> getUmowy(){ return umowy;}
    private void setUmowy(List<Umowa> umowy) {
        this.umowy = umowy;
    }
    public void addUmowa(Umowa umowa){
        if(!umowy.contains(umowa)) {
            getUmowy().add(umowa);
            umowa.setKlient(this);
        }
    }
    public void removeUmowa(Umowa umowa){
        getUmowy().remove(umowa);
        umowa.setKlient(null);
    }


    @Override
    public String toString() {
        return getImie()+ " "+ getNazwisko();
    }
}
