package fu.de200027;
import fu.de200027.dao.EmployeeDAO;
import fu.de200027.pojo.Employee;
import fu.de200027.pojo.Gender;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
//da cap nhat todo 10
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsf302FU");
        // TODO 0.10 - Entity Lifecycle Explanation
        EmployeeDAO dao = new EmployeeDAO(emf);

        // ===== CREATE =====
        // [Lifecycle] emp dang o trang thai NEW/TRANSIENT (moi "new", chua lien quan DB)
        Employee emp = Employee.builder()
                .fullName("Nguyen Van A")
                .email("a@fpt.edu.vn")
                .salary(new BigDecimal("15000000"))
                .gender(Gender.MALE)
                .hireDate(LocalDate.of(2022, 3, 1))
                .active(true)
                .build();

        dao.save(emp);
        // [Lifecycle] sau save(): trong luc persist() emp la MANAGED; sau khi method
        // save() return (EntityManager da dong), emp tro thanh DETACHED.
        System.out.println("Da tao: " + emp);

        // ===== READ =====
        Employee found = dao.findById(emp.getId());
        // [Lifecycle] found la mot object MANAGED trong pham vi EntityManager cua findById(),
        // nhung EntityManager cung da dong ngay sau khi return -> found cung la DETACHED
        // ngay khi ra khoi method.
        System.out.println("Doc lai: " + found);

        // ===== UPDATE =====
        found.setSalary(new BigDecimal("17000000"));
        // [Lifecycle] found dang DETACHED, sua field luc nay KHONG tu dong sync xuong DB
        Employee updated = dao.update(found);
        // [Lifecycle] update() goi merge(found) -> tra ve "updated" la MANAGED (trong luc
        // transaction dang chay); sau khi method return, "updated" tro thanh DETACHED.
        System.out.println("Sau update: " + updated);

        // Doc lai de kiem chung
        Employee reChecked = dao.findById(emp.getId());
        System.out.println("Kiem tra lai sau update: " + reChecked);

        // ===== DELETE =====
        dao.delete(emp.getId());
        // [Lifecycle] ben trong delete(): entity tim duoc chuyen MANAGED -> REMOVED,
        // bi xoa that su khoi DB khi commit().
        // --- READ LẠI KIỂM TRA ĐÃ XÓA ---
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("Sau khi xoa, tim lai: " + afterDelete); // ky vong: null

// --- day la TODO 0.9: CHECK TRÙNG EMAIL ---
        Employee dup1 = Employee.builder()
                .fullName("User 1")
                .email("trung@fpt.edu.vn")
                .salary(new BigDecimal("10000000"))
                .gender(Gender.FEMALE)
                .hireDate(LocalDate.now())
                .active(true)
                .build();

        Employee dup2 = Employee.builder()
                .fullName("User 2")
                .email("trung@fpt.edu.vn") // trung email voi dup1
                .salary(new BigDecimal("11000000"))
                .gender(Gender.MALE)
                .hireDate(LocalDate.now())
                .active(true)
                .build();

        dao.save(dup1);
        try {
            dao.save(dup2); // ky vong: nem exception vi vi pham UNIQUE
            System.out.println("LOI: khong thay exception nhu ky vong!");
        } catch (RuntimeException ex) {
            System.out.println("Da bat duoc loi trung email nhu ky vong: "
                    + ex.getClass().getSimpleName());
        }

    }
    //da capnhat todo 10
}