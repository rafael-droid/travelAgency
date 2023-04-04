import entity.Wycieczka;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListaWycieczek {
    JFrame frame;
    JPanel panel1;
    JLabel wycieczki;
    JTable tabela;
    JButton szczegoly;
    JButton stworzUmowe;
    JButton klienci;
    List<Wycieczka> wycieczkaList;
    int id = -1;

    public ListaWycieczek(){
        Session session = HibernateSetup.otworzSession();
        session.beginTransaction();
        wycieczkaList = session.createQuery("from Wycieczka ").list();

        session.getTransaction().commit();
        session.close();

        frame = new JFrame("Wycieczki");
        frame.setContentPane(panel1);
        frame.setSize(700,500);
        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        wyswietlWycieczki();


        stworzUmowe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(id >=0) {
                    super.mouseClicked(e);
                    frame.dispose();
                    NowaUmowa nowaUmowa = new NowaUmowa(wycieczkaList.get(id));
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Najpierw wybierz wycieczkę, żeby stworzyć umowę.",
                            "UWAGA",
                            JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        szczegoly.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(id>=0) {
                    super.mouseClicked(e);
                    frame.dispose();
                    WycieczkaSzczegoly wycieczkaSzczegoly = new WycieczkaSzczegoly(wycieczkaList.get(id));
                }else{
                    JOptionPane.showMessageDialog(frame,
                            "Najpierw wybierz wycieczkę, żeby wyświetlić szczegóły.",
                            "UWAGA",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        klienci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                ListaKlientow listaKlientow = new ListaKlientow();
            }
        });
    }

    private void wyswietlWycieczki() {

        Object[][] data = new Object[wycieczkaList.size()][4];
        for(int i=0; i<wycieczkaList.size(); i++){
            data[i][0] = wycieczkaList.get(i).getId();
            data[i][1] = wycieczkaList.get(i).getNazwa();
            data[i][2] = wycieczkaList.get(i).getOpis();
            data[i][3] = wycieczkaList.get(i).getCena();
        }

        tabela.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Nazwa", "Opis", "Kwota"}

        ));
        TableColumnModel columnModel = tabela.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(20);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(0).setCellRenderer(cellRenderer);
        columnModel.getColumn(1).setCellRenderer(cellRenderer);
        columnModel.getColumn(2).setCellRenderer(cellRenderer);
        columnModel.getColumn(3).setCellRenderer(cellRenderer);

        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                id = tabela.getSelectedRow();

            }
        });


    }
}
