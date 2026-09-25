package fu.de200027;

import fu.de200027.dao.DepartmentDao;
import fu.de200027.pojo.Department;
import fu.de200027.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDAO = new DepartmentDao();

        System.out.println("==========================================");
        System.out.println("   TODO 2.8: TÁI HIỆN N+1 QUERY PROBLEM");
        System.out.println("==========================================");

        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            // 1. SELECT * FROM departments (1 query)
            List<Department> deptList = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();

            System.out.println("\n--- Bắt đầu duyệt danh sách LAZY (Sinh ra 1 + N câu SQL) ---");
            // 2. Mỗi lần d.getEmployees().size() phát sinh thêm 1 query SELECT employees (N queries)
            for (Department d : deptList) {
                System.out.println("Phòng ban: " + d.getName() + " | Số nhân viên: " + d.getEmployees().size());
            }
        } finally {
            em.close();
        }

        System.out.println("\n==========================================");
        System.out.println("     TODO 2.9: FIX N+1 BẰNG JOIN FETCH");
        System.out.println("==========================================");

        /*
         * SO SÁNH SQL LOG:
         * - TRƯỚC FIX (TODO 2.8): Phát sinh 1 + N câu SQL Query riêng biệt.
         * - SAU FIX (TODO 2.9): Chỉ phát sinh ĐÚNG 1 CÂU SQL JOIN DUY NHẤT:
         *   SELECT DISTINCT ... FROM departments d LEFT OUTER JOIN employees e ON d.id = e.department_id
         */
        List<Department> deptListFetch = departmentDAO.findAllWithEmployees();

        System.out.println("\n--- Bắt đầu duyệt danh sách JOIN FETCH (Chỉ có đúng 1 câu SQL) ---");
        for (Department d : deptListFetch) {
            System.out.println("Phòng ban: " + d.getName() + " | Số nhân viên: " + d.getEmployees().size());
        }

        // Đóng EMF ở cuối chương trình
        JPAUtil.close();
    }
}