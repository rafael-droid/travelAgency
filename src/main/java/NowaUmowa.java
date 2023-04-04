import entity.*;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NowaUmowa {
    JFrame frame;
    JPanel panel1;
    JLabel nowaUmowa;
    JLabel termin;
    JLabel klient;
    JLabel sprzedawca;
    JLabel tresc;
    JButton dodaj;
    JButton wroc;
    JList list1;
    JFormattedTextField formattedTextField1;
    JList list2;
    JList list3;
    int idWycieczka;
    int idTermin ;
    int idKlient;
    int idSprzedawca;
    List<Sprzedawca> sprzedawcaList;
    List<Klient> klientList;
    List<Termin> terminList;
    private List<Wycieczka> wycieczkaList;


    public NowaUmowa(Wycieczka wycieczkaList){
       // this.idWycieczka = idWycieczka;
        terminList = wycieczkaList.getTerminy();

        stworzGUI();
        wypelnijListe1();
        wypelnijListe2();
        wypelnijListe3();

        dodaj.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!(list1.isSelectionEmpty() || list2.isSelectionEmpty() || list3.isSelectionEmpty() || formattedTextField1.getText().isEmpty())) {
                    super.mouseClicked(e);
                    Session session = HibernateSetup.otworzSession();
                    session.beginTransaction();
                    Umowa umowa = new Umowa(formattedTextField1.getText());
                    umowa.setKlient(klientList.get(idKlient));
                    umowa.addTermin(terminList.get(idTermin));
                    umowa.setSprzedawca(sprzedawcaList.get(idSprzedawca));
                    session.save(umowa);

                    session.getTransaction().commit();
                    session.close();
                    JOptionPane.showMessageDialog(frame,
                            "Dodano nową umowę",
                            "Potwierdzenie",
                            JOptionPane.WARNING_MESSAGE);

                    ListaWycieczek listaWycieczek = new ListaWycieczek();
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Najpierw uzupełnij brakujące dane, żeby dodac umowę.",
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

    private void wypelnijListe1() {


        DefaultListModel defaultListModel = new DefaultListModel();

        for(int i=0; i<terminList.size(); i++){
                defaultListModel.addElement(terminList.get(i).getDataRozpoczecia() + " " + terminList.get(i).getDataZakonczenia());
        }

        list1.setModel(defaultListModel);

        list1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                idTermin = list1.getSelectedIndex();
            }
        });
    }

    private void wypelnijListe2() {
        Session session = HibernateSetup.otworzSession();
        session.beginTransaction();
        klientList =  session.createQuery("from Klient ").list();
        session.getTransaction().commit();
        session.close();
        DefaultListModel defaultListModel = new DefaultListModel();

        for(int i=0; i<klientList.size(); i++){
            defaultListModel.addElement(klientList.get(i).getImie() + " " + klientList.get(i).getNazwisko());

        }

        list2.setModel(defaultListModel);

        list2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                idKlient = list2.getSelectedIndex();
            }
        });
    }

    private void wypelnijListe3() {
        Session session = HibernateSetup.otworzSession();
        session.beginTransaction();
         sprzedawcaList =  session.createQuery("from Sprzedawca ").list();
        session.getTransaction().commit();
        session.close();
        DefaultListModel defaultListModel = new DefaultListModel();

        for(int i=0; i<sprzedawcaList.size(); i++){
            defaultListModel.addElement(sprzedawcaList.get(i).getImie() + " " + sprzedawcaList.get(i).getNazwisko());

        }

        list3.setModel(defaultListModel);

        list3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                idSprzedawca = list3.getSelectedIndex();
            }
        });
    }

    private void stworzGUI() {
        frame = new JFrame("Dodaj Wycieczke");
        frame.setContentPane(panel1);
        frame.setSize(700,500);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
