import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {



    public void start(){
        HibernateSetup.wlaczHibernate();
        ListaWycieczek listaWycieczek = new ListaWycieczek();
    }


    public void wypelnijBaze(){
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try{
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();


            Ubezpieczenie ubezpieczenie1 = new Ubezpieczenie("Od nieszczęśliwych przypadków", 1112);
            Ubezpieczenie ubezpieczenie2 = new Ubezpieczenie("Od śmierci", 1112);

            Kraj kraj1 = new Kraj("Polska",10.0);
            Kraj kraj2 = new Kraj("Norwegia",2200.0);

            Wycieczka wycieczka1 = new Wycieczka("Wycieczka po Polsce",1000.0, "wycieczka krajoznawcza po Mazurach");
            Wycieczka wycieczka2 = new Wycieczka("Wycieczka skandynawska",1000.0, "wycieczka po norweskich fiordach");


            Umowa umowa1 = new Umowa("tresc1");
            Umowa umowa2 = new Umowa("tresc2");


            Klient klient1 = new Klient("Piotr1", "Nowak1","500500050", List.of("klient1@aaa", "klien1@bb"), "1234567890");
            Klient klient2 = new Klient("Maciek2", "Maciek2","123123123", List.of("klient2@aaa"), "0987654321");


            Termin termin1 = new Termin(LocalDate.parse("2020-07-01"), LocalDate.parse("2020-07-10"));
            Termin termin2 = new Termin(LocalDate.parse("2020-08-01"), LocalDate.parse("2020-08-10"));
            Termin termin3 = new Termin(LocalDate.parse("2020-09-01"), LocalDate.parse("2020-09-10"));


            Adres adres1 = new Adres("Warszawa", "Koszykowa");
            Adres adres2 = new Adres("Warszawa", "Złota");

            Przewodnik przewodnik1 = new Przewodnik("Adam1","Kowal1", "11221212", List.of("przewodnik@aaa"), "zabytki", Przewodnik.Jezyk.angielski) ;
            Przewodnik przewodnik2 = new Przewodnik("Adam2","Kowal2", "11221212", List.of("przewodnik@aaa"), "nowoczesna architektura", Przewodnik.Jezyk.francuski) ;


            Sprzedawca sprzedawca1 = new Sprzedawca("Szymon1", "Kowalski1","123123123", List.of("sprzedawca1@aaa"), LocalDate.parse("2020-09-09"));
            Sprzedawca sprzedawca2 = new Sprzedawca("Andrzej2", "Kowalski2","12316656", List.of("sprzedawca2@aaa"), LocalDate.parse("2020-09-09"));

            Autokar autokar = new Autokar("autokar", LocalDate.parse("2022-09-09"), 50,50.0);
            Samolot samolot = new Samolot("samolot",LocalDate.parse("2020-09-09"), 100,7000);

            klient1.setAdres(adres1);
            klient2.setAdres(adres2);


            wycieczka1.addTermin(termin1);
            wycieczka1.addTermin(termin3);
            wycieczka1.setUbezpieczenie(ubezpieczenie1);
            przewodnik1.addWycieczka(wycieczka1);
            umowa1.addTermin(termin1);
            sprzedawca1.addUmowa(umowa1);
            klient1.addUmowa(umowa1);
            autokar.addWycieczka(wycieczka1);
            wycieczka1.addKraj(kraj1);
            wycieczka1.addPrzewodnik(przewodnik1);
            wycieczka1.addPrzewodnik(przewodnik2);


            wycieczka2.addPrzewodnik(przewodnik2);
            wycieczka2.addKraj(kraj2);
            wycieczka2.addTermin(termin2);
            wycieczka2.setUbezpieczenie(ubezpieczenie2);
            wycieczka2.setPojazd(samolot);
            wycieczka2.setCena(54545);
            klient2.addUmowa(umowa2);
            sprzedawca2.addUmowa(umowa2);
            termin2.setUmowa(umowa2);



            session.save(ubezpieczenie1);
            session.save(autokar);
            session.save(przewodnik1);
            session.save(kraj1);;
            session.save(termin1);
            session.save(wycieczka1);
            session.save(umowa1);
            session.save(klient1);
            session.save(samolot);
            session.save(sprzedawca1);

            session.save(ubezpieczenie2);
            session.save(przewodnik2);
            session.save(kraj2);;
            session.save(termin2);
            session.save(wycieczka2);
            session.save(umowa2);
            session.save(klient2);
            session.save(sprzedawca2);

            session.save(termin3);


        session.getTransaction().commit();
        session.close();

    }catch (Exception e){
        e.printStackTrace();
        StandardServiceRegistryBuilder.destroy(registry);
    }
        finally {
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }


}
}
