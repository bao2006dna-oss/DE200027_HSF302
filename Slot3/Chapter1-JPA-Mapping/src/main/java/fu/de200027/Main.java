package fu.de200027;

import fu.de200027.dao.DepartmentDao;
import fu.de200027.dao.EmployeeDao;
import fu.de200027.pojo.Department;
import fu.de200027.pojo.Employee;
import fu.de200027.pojo.Gender;
import fu.de200027.util.JPAUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDao deptDao = new DepartmentDao();
        EmployeeDao empDao = new EmployeeDao();

        System.out.println("=== 1. TEST SAVE ===");
        Department dept = new Department("HR", "Tầng 3");
        deptDao.save(dept);

        Employee emp = new Employee("hr_test@company.com", "Nguyen Van HR", Gender.MALE, new BigDecimal("1500"), LocalDate.now());
        dept.addEmployee(emp);
        empDao.save(emp);
        System.out.println("Lưu thành công ID Dept: " + dept.getId() + " | ID Emp: " + emp.getId());

        System.out.println("\n=== 2. TEST FIND BY ID & UPDATE ===");
        Department foundDept = deptDao.findById(dept.getId());
        foundDept.setLocation("Tầng 4 - Đã chuyển");

        // Sửa và nhận lại đối tượng đã merge
        Department updatedDept = deptDao.update(foundDept);
        System.out.println("Địa điểm mới sau update: " + updatedDept.getLocation());

        System.out.println("\n=== 3. TEST FIND ALL ===");
        List<Department> list = deptDao.findAll();
        System.out.println("Tổng số phòng ban: " + list.size());

        System.out.println("\n=== 4. TEST DELETE ===");
        empDao.delete(emp.getId());
        deptDao.delete(dept.getId());
        System.out.println("Đã xóa thành công!");

        JPAUtil.close();
    }
}