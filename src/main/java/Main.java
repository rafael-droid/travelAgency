import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.util.List;

public class Main {
    public static  void main(String[] args) {
        Database database = new Database();
        database.start();
        //database.wypelnijBaze();



    }

}
