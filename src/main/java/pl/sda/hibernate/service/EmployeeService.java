package pl.sda.hibernate.service;

import org.hibernate.Session;
import pl.sda.hibernate.domain.Employee;

public interface EmployeeService {


    Employee findEmployee(Long id, String surname);

    Employee findEmployeeOrCreateIfNotFound(Long id, String name, String surname);

    Employee updateEmployeeInfo(Long id, String surname);

    boolean deleteEmployee(Long id, String surname);

}
