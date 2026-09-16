package fu.de200027;

import fu.de200027.dao.DepartmentDao;
import fu.de200027.pojo.Department;
import fu.de200027.pojo.Employee;
import fu.de200027.pojo.Gender;
import fu.de200027.util.JPAUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDAO = new DepartmentDao();

        // 1) Tạo Department + 3 Employee, add qua helper method (TODO 2.4)
        Department it = new Department("Marketing", "Ha Noi");

        Employee e1 = new Employee("aa.nguyen@company.com", "Nguyen Van A", Gender.MALE,
                new BigDecimal("15000000"), LocalDate.of(2022, 1, 10));

        Employee e2 = new Employee("bb.tran@company.com", "Tran Thi B", Gender.FEMALE,
                new BigDecimal("18000000"), LocalDate.of(2021, 6, 1));

        Employee e3 = new Employee("cc.le@company.com", "Le Van C", Gender.OTHER,
                new BigDecimal("12000000"), LocalDate.of(2023, 3, 15));

        it.addEmployee(e1);
        it.addEmployee(e2);
        it.addEmployee(e3);

        // 2) Chỉ persist(department) — cascade = ALL tự lo phần Employee (TODO 2.7)
        departmentDAO.save(it);

        System.out.println("Da luu Department, id = " + it.getId());

        // 3) Tim lai kem employees bang JOIN FETCH (TODO 2.6)
        Department found = departmentDAO.findByIdWithEmployees(it.getId());

        System.out.println("Phong ban: " + found.getName());
        for (Employee e : found.getEmployees()) {
            System.out.println("  - " + e);
        }

        JPAUtil.close();
    }
}