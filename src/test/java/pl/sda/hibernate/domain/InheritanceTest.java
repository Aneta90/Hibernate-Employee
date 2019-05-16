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
import static org.testng.AssertJUnit.assertNotNull;

public class InheritanceTest {

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
    public void inheritanceAndSalaryCounterTest(){

        Long employeeId;
        Long employee1Id;

        Employee employee = new NonPhysicalEmployee();
        employee.setId(4L);
        employee.setName("Ana");
        employee.setSurname("Ann");
        ((NonPhysicalEmployee) employee).setSalary(2000.00);
        ((NonPhysicalEmployee) employee).setExtraSalary(200.00);

        Employee employee1 = new PhysicalEmployee();
        employee1.setId(5L);
        employee1.setName("Tom");
        employee1.setSurname("Tomman");
        ((PhysicalEmployee) employee1).setSalary(2000.00);
        ((PhysicalEmployee) employee1).setExtraSalary(100.00);

        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            session.save(employee1);
            transaction.commit();
            employeeId = employee.getId();
            employee1Id = employee1.getId();
        }

        try(Session session = sessionFactory.openSession()) {
            employee = session.get(NonPhysicalEmployee.class,employeeId);
            employee1 = session.get(PhysicalEmployee.class,employee1Id);
            assertNotNull(((NonPhysicalEmployee) employee).getTotalSalary());
            assertNotNull(((PhysicalEmployee) employee1).getTotalSalary());
            System.out.println("Salary of NonPhysicalEmployee: " + ((NonPhysicalEmployee) employee).getTotalSalary());
            System.out.println("Salary of Physicalemployee: " + ((PhysicalEmployee) employee1).getTotalSalary());
        }
    }
}
