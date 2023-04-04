package entity;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Adres {
    private String miasto;
    private String ulica;

    public Adres(String miasto, String ulica){
        this.miasto = miasto;
        this.ulica = ulica;
    }

    public Adres() {

    }

    @Basic
    public String getMiasto(){
        return miasto;
    }
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Basic
    public String getUlica(){
        return ulica;
    }
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Override
    public String toString() {
        return getMiasto() + ", ulica: " + getUlica();
    }
}
