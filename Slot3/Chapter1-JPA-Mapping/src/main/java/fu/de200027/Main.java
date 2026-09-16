package fu.de200027;

import fu.de200027.pojo.Department;
import fu.de200027.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   TODO 2.8: TÁI HIỆN N+1 QUERY PROBLEM");
        System.out.println("==========================================");

        EntityManager em = JPAUtil.getEMF().createEntityManager();

        try {
            // 1. Query lấy danh sách Department -> Phát sinh 1 câu SQL SELECT * FROM departments
            List<Department> deptList = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();

            System.out.println("\n--- Bắt đầu duyệt danh sách Department (Quan sát SQL log phía dưới) ---");

            // 2. Vòng lặp truy cập collection LAZY khi Session vẫn đang MỞ:
            // Mỗi lần gọi d.getEmployees().size(), Hibernate lại phát sinh thêm 1 câu SQL SELECT riêng cho Department đó (N câu SQL)
            for (Department d : deptList) {
                System.out.println("Phòng ban: " + d.getName() + " | Số nhân viên: " + d.getEmployees().size());
            }

        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}