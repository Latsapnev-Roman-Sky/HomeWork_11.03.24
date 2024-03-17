package pro.sky.employeesbooklist.Service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbooklist.Employee.Employee;
import pro.sky.employeesbooklist.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbooklist.Exceptions.EmployeeNotFoundException;
import pro.sky.employeesbooklist.Exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;
    static int maxCount = 6;
    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.size() >= maxCount) {
            throw new EmployeeStorageIsFullException(); // превышен лимит количества сотрудников в фирме
        } else if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException(); // такой сотрудник уже добавлен
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeList.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employeeList.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeList.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> showEmployeeList() {
        return Collections.unmodifiableList(employeeList);
    }

}
