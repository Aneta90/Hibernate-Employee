package pl.sda.hibernate.service;

import lombok.Builder;
import lombok.Data;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.domain.Employee;
import pl.sda.hibernate.pl.sda.hibernate.util.SessionUtil;

@Data
@Builder
public class DefaultEmployeeService implements EmployeeService {

    @Override
    public Employee findEmployee(Long id, String surname) {
        Query<Employee> query = SessionUtil.getSession().createQuery("from Employee emp where emp.id=:id AND emp.surname =:surname",Employee.class);
        query.setParameter("id",id);
        query.setParameter("surname",surname);
        return query.uniqueResult();
    }

    @Override
    public Employee findEmployeeOrCreateIfNotFound(Long id, String name, String surname) {

        Employee employee = findEmployee(id,surname);
        if(employee==null){
            employee=new Employee();
         employee.setId(id);
         employee.setName(name);
         employee.setSurname(surname);
         SessionUtil.getSession().save(employee);
        }

        return employee;
    }

    @Override
    public Employee updateEmployeeInfo(Long id, String surname) {
        return null;
    }

    public Employee updateMovieInfo(Long id, String surname) {

        Employee employee = findEmployee(id,surname);
        if(employee==null){
            return null;
        }
        Transaction transaction = SessionUtil.getSession().beginTransaction();
        employee.setId(id);
        employee.setName(surname);
        transaction.commit();
        return employee;
    }

    @Override
    public boolean deleteEmployee(Long id, String surname) {

        Employee employee = findEmployee(id,surname);
        Transaction transaction = SessionUtil.getSession().beginTransaction();
        if(employee==null){
            return false;
        }
        SessionUtil.getSession().delete(employee);
        transaction.commit();
        return true;
    }
}
