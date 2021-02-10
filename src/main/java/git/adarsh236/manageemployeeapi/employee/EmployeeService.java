package git.adarsh236.manageemployeeapi.employee;

import git.adarsh236.manageemployeeapi.exception.UserNotFoundException;
import git.adarsh236.manageemployeeapi.employee.Employee;
import git.adarsh236.manageemployeeapi.employee.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final IEmployee iEmployee;

    @Autowired
    public EmployeeService(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return iEmployee.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return iEmployee.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return iEmployee.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return iEmployee.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id+ "was not found"));
    }

    public void deleteEmployee(Long id){
        iEmployee.deleteById(id);
    }
}
