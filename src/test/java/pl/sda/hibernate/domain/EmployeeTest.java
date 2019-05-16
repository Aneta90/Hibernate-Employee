package pl.sda.hibernate.domain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;


public class EmployeeTest {

    private SessionFactory sessionFactory;

    @BeforeSuite
    public void setup(){

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(standardServiceRegistry)
                .buildMetadata()
                .buildSessionFactory();
    }


    @Test
    public void shouldSaveGivenEmployeeTest(){

        Adress adress = new Adress();
        Employee employee = new Employee();
                employee.setId(1L);
                employee.setName("Anett");
                employee.setSurname("Wrobel");
                employee.setAdress(adress);

        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(adress);
            session.save(employee);
            transaction.commit();
        }
    }
}
