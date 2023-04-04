import entity.Klient;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListaKlientow {
    JFrame frame;
    JPanel panel1;
    JTable table1;
    List<Klient> listaKlientow = new ArrayList<>();
    JLabel klientList;
    JButton dodajButton;
    JButton wróćButton;

    int id;

    public ListaKlientow(){
        stworzGUI();
        wyswietlKlientow();

        dodajButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                NowyKlienta nowyKlienta = new NowyKlienta();
            }
        });
        wróćButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                ListaWycieczek listaWycieczek = new ListaWycieczek();
            }
        });
    }

    private void wyswietlKlientow() {
        Session session = HibernateSetup.otworzSession();
        session.beginTransaction();
        listaKlientow = session.createQuery("from Klient ").list();
        System.out.println(listaKlientow.size() + " lista");
        session.getTransaction().commit();
        session.close();

        Object[][] data = new Object[listaKlientow.size()][3];
        for(int i=0; i<listaKlientow.size(); i++){
            data[i][0] = listaKlientow.get(i).getId();
            data[i][1] = listaKlientow.get(i).getImie();
            data[i][2] = listaKlientow.get(i).getNazwisko();
        }

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Imie", "Nazwisko"}

        ));
        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(20);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(0).setCellRenderer(cellRenderer);
        columnModel.getColumn(1).setCellRenderer(cellRenderer);
        columnModel.getColumn(2).setCellRenderer(cellRenderer);

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                id = table1.getSelectedRow();


            }
        });
    }

    private void stworzGUI() {
        frame = new JFrame("Klienci");
        frame.setContentPane(panel1);
        frame.setSize(700,500);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
