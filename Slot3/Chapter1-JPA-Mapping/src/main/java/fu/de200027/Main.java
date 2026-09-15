package fu.de200027;

import fu.de200027.pojo.Department;
import fu.de200027.pojo.Employee;
import fu.de200027.pojo.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department dept = new Department("IT", "Ha Noi");

        Employee emp = new Employee("test@company.com", "Test", Gender.OTHER,
                new BigDecimal("1000"), LocalDate.now());

        dept.addEmployee(emp);

        // Kiểm chứng đồng bộ 2 chiều (Mong muốn: ra 2 dòng true)
        System.out.println(dept.getEmployees().contains(emp)); // true
        System.out.println(emp.getDepartment() == dept);        // true
    }
}