import entity.Wycieczka;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class WycieczkaSzczegoly {
    JFrame frame;
    JPanel panel1;
    JLabel szczegolyWycieczki;
    JFormattedTextField formattedTextCena;
    JButton wroc;
    JFormattedTextField formattedTextTermin;
    JFormattedTextField formattedTextKraj;
    JFormattedTextField formattedTextUbezpieczenie;
    JFormattedTextField formattedTextPrzewodnik;
    JFormattedTextField formattedTextPojazd;
    JFormattedTextField formattedTextNazwa;
    JLabel cena;
    JLabel Termin;
    JLabel Kraj;
    JLabel Ubezpieczenie;
    JLabel Przewodnik;
    JLabel Pojazd;
    JFormattedTextField formattedTextOpis;
    JLabel opis;
    JLabel nazwa;
    Wycieczka wycieczka;

    public WycieczkaSzczegoly(Wycieczka wycieczka){
        this.wycieczka = wycieczka;

        wypelnijListe();
        stworzOkno();
        wroc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                ListaWycieczek listaWycieczek = new ListaWycieczek();
            }
        });
    }

    private void stworzOkno() {
        frame = new JFrame("Wycieczki");
        frame.setContentPane(panel1);
        frame.setSize(700,500);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void wypelnijListe() {
        formattedTextCena.setText(String.valueOf(wycieczka.getCena()));
        formattedTextKraj.setText(wycieczka.getKraje().toString());
        formattedTextPojazd.setText(wycieczka.getPojazd().toString());
        formattedTextPrzewodnik.setText(wycieczka.getPrzewodnicy().toString());
        formattedTextTermin.setText(wycieczka.getTerminy().toString());
        formattedTextUbezpieczenie.setText(wycieczka.getUbezpieczenie().toString());
        formattedTextNazwa.setText(wycieczka.getNazwa());
        formattedTextOpis.setText(wycieczka.getOpis());
    }
}
