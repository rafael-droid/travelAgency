import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSetup {
    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory = null;


    public static void wlaczHibernate(){
        try{
            registry=new StandardServiceRegistryBuilder().configure().build();
            sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Session otworzSession(){
        return sessionFactory.openSession();
    }
}
