package pro.sky.employeesbooklist.Service;

import org.springframework.stereotype.Service;
import pro.sky.employeesbooklist.Employee.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employeeList;
    static int maxCount = 6;
    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

}
