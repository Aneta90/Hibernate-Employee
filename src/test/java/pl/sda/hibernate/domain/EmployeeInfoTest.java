package pl.sda.hibernate.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class EmployeeInfoTest {

    private SessionFactory sessionFactory;

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
    public void shouldCalculateDaysSinceHiredDatePostLoad() {

        Long employeeInfoId;
        Long simpleEmployeeId;

        Employee employee = new Employee.EmployeeBuilder()
                .id(1L)
                .name("Anett")
                .surname("Lucky")
                .build();

        EmployeeInfo employeeInfo = new EmployeeInfo.EmployeeInfoBuilder()
                .employeeInfoId(1L)
                .hiredDate(LocalDate.of(2018, 11, 8))
                .build();

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            session.save(employeeInfo);
            transaction.commit();

            employeeInfoId = employeeInfo.getEmployeeInfoId();
            simpleEmployeeId = employee.getId();

            assertNotNull(employeeInfoId);
            assertNotNull(simpleEmployeeId);
            assertNull(employeeInfo.getDaysSinceHired());
        }

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            employeeInfo = session.get(EmployeeInfo.class, employeeInfoId);
            assertNotNull(employeeInfo);
            assertNotNull(employeeInfo.getDaysSinceHired());
            System.out.println("Days of working in the company:" + employeeInfo.getDaysSinceHired());
            transaction.commit();
        }
    }

    @Test
    public void checkNamedQueriesTest(){

        EmployeeInfo employeeInfo = EmployeeInfo.builder()
                .employeeInfoId(6L)
                .hiredDate(LocalDate.of(2019,2,7))
                .managerName("Mike")
                .build();

        EmployeeInfo employeeInfo1 = EmployeeInfo.builder()
                .employeeInfoId(7L)
                .hiredDate(LocalDate.of(2019,2,9))
                .managerName("Jhon")
                .build();
        EmployeeInfo employeeInfo2 = EmployeeInfo.builder()
                .employeeInfoId(8L)
                .hiredDate(LocalDate.of(2019,1,1))
                .managerName("Mike")
                .build();

            try(Session session = SessionUtil.getSession()){
                Transaction transaction = session.beginTransaction();
                session.save(employeeInfo);
                session.save(employeeInfo1);
                session.save(employeeInfo2);

                transaction.commit();
                assertNotNull(employeeInfo);
                assertNotNull(employeeInfo1);
                assertNotNull(employeeInfo2);
            }

            try(Session session = SessionUtil.getSession()) {
                Query query = session.getNamedQuery("employeeInfo.findByName");
                query.setParameter("name","Jhon");
                EmployeeInfo employeeWithJhonManager = (EmployeeInfo) query.uniqueResult();
                assertEquals(employeeWithJhonManager.getHiredDate(),LocalDate.of(2019,2,9));

                query.setParameter("name", "Mike");
                List<EmployeeInfo> employeeInfoList1 = query.list();
                assertEquals(employeeInfoList1.size(), 2);
            }
            }
        }



