package pro.sky.employeesbooklist.Service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbooklist.Employee.Employee;
import pro.sky.employeesbooklist.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbooklist.Exceptions.EmployeeNotFoundException;
import pro.sky.employeesbooklist.Exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    static int maxCount = 6;
    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= maxCount) {
            throw new EmployeeStorageIsFullException(); // превышен лимит количества сотрудников в фирме
        } else if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(); // такой сотрудник уже добавлен
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> showEmployeeList() {
        return Collections.unmodifiableCollection(employees.values());
    }

}
