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

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class EmployeeInfoValidatorTest {

    SessionFactory sessionFactory;

    @BeforeSuite
    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

    }


    @Test
    public void shouldAllowPersistingValidBean() {

        EmployeeInfoValidator employeeInfoValidator = new EmployeeInfoValidator.EmployeeInfoValidatorBuilder()
                .employeeInfoId(1L)
                .hiredDate(LocalDate.of(2019, 1, 1))
                .build();

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employeeInfoValidator);
            transaction.commit();
            assertNotNull(employeeInfoValidator);
        }
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void shouldNotAllowPersistingNotValidBean() {

        EmployeeInfoValidator employeeInfoValidator2 = EmployeeInfoValidator.builder()
                .employeeInfoId(1L)
                .hiredDate(LocalDate.of(2000, 1, 1))
                .build();

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.getTransaction();
            session.save(employeeInfoValidator2);
            transaction.commit();
        }
        fail();

    }

    }



