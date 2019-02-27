package pl.sda.hibernate.domain;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class DomainRelationsTest {

    @Test
    public void EmployeeAndAdressRelaionTest() {

        Long employeeId;
        Long adressId;

        Employee employee;
        Adress adress;

        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            employee = new Employee();
            adress = new Adress();

            employee.setAdress(adress);
            adress.setId(employee.getId());

            session.save(employee);
            session.save(adress);

            employeeId = employee.getId();
            adressId = adress.getId();

            transaction.commit();
        }
        assertNotNull(adress.getId());
        assertNotNull(employee.getAdress());

        try (Session session = SessionUtil.getSession()) {
            employee = session.get(Employee.class, employeeId);
            adress = session.get(Adress.class, adressId);
        }

        assertNotNull(employee.getAdress());
        assertNotNull(adress.getId());
    }


    @Test
    public void shouldRemoveCascade() {

        Long departmentId, employeeId, employee1Id;
        List<Employee> employeeList = new LinkedList<>();
        Department department = new Department.DepartmentBuilder()
                .id(1L)
                .name("Law Department")
                .build();

        Employee employee = new Employee.EmployeeBuilder()
                .name("Jhon")
                .surname("Ponson")
                //.salary(10.000)
                .build();

        Employee employee1 = new Employee.EmployeeBuilder()
                .name("Joanna")
                .surname("Black")
                //.salary(20.000)
                .build();


        employeeList.add(employee);
        employeeList.add(employee1);

        department.setEmployeeList(employeeList);

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.save(department);
            tx.commit();
        }


            departmentId = department.getId();
            assertNotNull(departmentId);
            employeeId = employee.getId();
            assertNotNull(employeeId);
            employee1Id = employee1.getId();
            assertNotNull(employee1Id);

        try (Session session = SessionUtil.getSession()) {
            department = session.get(Department.class, departmentId);
            assertNotNull(department);
            employee = session.get(Employee.class, employeeId);
            assertNotNull(employee);
            employee1 = session.get(Employee.class, employee1Id);
            assertNotNull(employee1);
            Transaction tx = session.beginTransaction();
            session.delete(department);
            tx.commit();
        }

        try (Session session = SessionUtil.getSession()) {
            department = session.get(Department.class, departmentId);
            assertNull(department);
            employee = session.get(Employee.class, employeeId);
            assertNull(employee);
            employee1 = session.get(Employee.class, employee1Id);
            assertNull(employee1);
        }
    }
}
