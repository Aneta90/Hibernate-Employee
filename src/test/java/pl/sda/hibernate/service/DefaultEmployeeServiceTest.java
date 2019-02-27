package pl.sda.hibernate.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pl.sda.hibernate.domain.Adress;
import pl.sda.hibernate.domain.Employee;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

import static org.testng.Assert.*;

public class DefaultEmployeeServiceTest {

    private EmployeeService employeeService;
    private SessionFactory sessionFactory;

    @BeforeSuite
    public void setup() {

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(standardServiceRegistry)
                .buildMetadata()
                .buildSessionFactory();
        employeeService = new DefaultEmployeeService();

        insertIntoDatabase();
    }

    private Employee createEmployee(final Long id, final String name, final String surname) {

        return Employee.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
    }

    private Adress createAdress(final String city, final String street, final String zipCode,final int streetNumber) {

        return Adress.builder()
                .city(city)
                .street(street)
                .zipCode(zipCode)
                .streetNumber(streetNumber)
                .build();
    }

    private void insertIntoDatabase() {

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.save(createEmployee(1L, "Aneta", "Wrobel"));
            session.save(createEmployee(2L, "Kuba", "Kowalski"));
            session.save(createEmployee(3L, "Kasia", "Kowalska"));
            session.save(createAdress("Warsaw","Rondo ONZ","90098",89));
            tx.commit();
        }
    }

    @Test
    public void isEmployeeSavedToDatabaseTest() {
            Employee employee = employeeService.findEmployee(2L, "Kowalski");
            assertNotNull(employee.getId());
            assertEquals(employee.getSurname(), "Kowalski");
        }

    @Test
    public void isEmployeeFoundTest() {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = employeeService.findEmployeeOrCreateIfNotFound(4L, "Maryla", "Rodowicz");
            transaction.commit();
            assertNotNull(employee.getId());
            assertEquals(employee.getSurname(), "Rodowicz");
        }
    }

    @Test
    public void isEmployeeUpdatedTest() {

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = employeeService.findEmployeeOrCreateIfNotFound( 4L, "Maryla", "Rodowicz");
            assertEquals(employee.getSurname(), "Rodowicz");
            employeeService.updateEmployeeInfo( 4L, "Krol");
            transaction.commit();
        }
        try(Session session = SessionUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            Employee employee = employeeService.findEmployeeOrCreateIfNotFound(4L,"Maryla","Krol");
            transaction.commit();
            assertEquals(employee.getSurname(),"Krol");
        }
        }

        @Test
        public void isEmployeeRemovedTest() {

            try (Session session = SessionUtil.getSession()) {
                Transaction transaction = session.beginTransaction();
                Employee employee = employeeService.findEmployee(1L, "Wrobel");
                assertNotNull(employee,"Hibernate found employee");
                assertEquals(employee.getSurname(), "Wrobel");
                session.delete(employee);
                transaction.commit();
            }
                Employee employee = employeeService.findEmployee(1L, "Wrobel");
                assertNull(employee);
            }
        }












