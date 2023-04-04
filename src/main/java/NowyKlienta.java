import entity.Adres;
import entity.Klient;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NowyKlienta {
    JFrame frame;
    JPanel panel1;
    JLabel dodajKlienta;
    JLabel imie;
    JLabel nazwisko;
    JLabel nrTel;
    JLabel email;
    JLabel pesel;
    JLabel miasto;
    JLabel ulica;
    JButton wroc;
    JButton dodaj;
    JFormattedTextField formattedTextImie;
    JFormattedTextField formattedTextNazwisko;
    JFormattedTextField formattedTextNrTel;
    JFormattedTextField formattedTextEmail;
    JFormattedTextField formattedTextPesel;
    JFormattedTextField formattedTextMiasto;
    JFormattedTextField formattedTextUlica;


    public NowyKlienta(){
        frame = new JFrame("Dodaj klienta");
        frame.setContentPane(panel1);
        frame.setSize(700,500);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        dodaj.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(formattedTextUlica.getText().isEmpty() || formattedTextMiasto.getText().isEmpty() || formattedTextEmail.getText().isEmpty() || formattedTextImie.getText().isEmpty() || formattedTextNazwisko.getText().isEmpty() || formattedTextNrTel.getText().isEmpty() || formattedTextPesel.getText().isEmpty())) {
                    super.mouseClicked(e);
                    Klient klient = new Klient(formattedTextImie.getText(), formattedTextNazwisko.getText(), formattedTextNrTel.getText(), List.of(formattedTextEmail.getText()), formattedTextPesel.getText());
                    Adres adres = new Adres(formattedTextMiasto.getText(), formattedTextUlica.getText());
                    klient.setAdres(adres);

                    Session session = HibernateSetup.otworzSession();
                    session.beginTransaction();
                    session.save(klient);
                    session.getTransaction().commit();
                    session.close();
                    JOptionPane.showMessageDialog(frame,
                            "Dodano nowego klienta",
                            "Potwierdzenie",
                            JOptionPane.WARNING_MESSAGE);

                    ListaKlientow listaKlientow = new ListaKlientow();
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(frame,
                            "Najpierw uzupełnij brakujące dane, żeby dodac klienta.",
                            "UWAGA",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        wroc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ListaWycieczek listaWycieczek = new ListaWycieczek();
                frame.dispose();

            }
        });
    }
}
