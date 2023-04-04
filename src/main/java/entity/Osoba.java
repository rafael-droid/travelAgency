package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//enum OsobaRodzaj {Osoba, Przewodnik, Klient, Sprzedawca}
// enum 51min wykład 05.20

@Entity(name ="Osoba")
@Inheritance(strategy = InheritanceType.JOINED)
public class Osoba {
    private String imie;
    private String nazwisko;
    private String numerTel;
    private List<String> email;
    private Long id;

    public Osoba(String  imie, String nazwisko, String numerTel, List<String> email){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTel = numerTel;
        this.email = email;
    }

    public Osoba() {

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
    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    public String getNumerTel() {
        return numerTel;
    }
    public void setNumerTel(String numerTel) {
        this.numerTel = numerTel;
    }

    @ElementCollection
    public List<String> getEmail() {
        return email;
    }
    public void setEmail(List<String> email) {
        this.email = email;
    }




}
