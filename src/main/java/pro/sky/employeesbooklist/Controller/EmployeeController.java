package pro.sky.employeesbooklist.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeesbooklist.Employee.Employee;
import pro.sky.employeesbooklist.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesbooklist.Exceptions.EmployeeNotFoundException;
import pro.sky.employeesbooklist.Exceptions.EmployeeStorageIsFullException;
import pro.sky.employeesbooklist.Service.EmployeeService;

import java.util.Collection;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.add(firstName, lastName);
        } catch (EmployeeStorageIsFullException e) {
            System.out.println("Превышен лимит сотрудников");
            throw e;
        } catch (EmployeeAlreadyAddedException e) {
            System.out.println("Такой сотрудник уже существует");
            throw e;
        }
    }
    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.remove(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Сотрудник не найден");
            throw e;
        }
    }
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        try {
            return employeeService.find(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println("Сотрудник не найден");
            throw e;
        }
    }
    @GetMapping
    public Collection<Employee> showEmployeeList() {
        return employeeService.showEmployeeList();
    }
}

