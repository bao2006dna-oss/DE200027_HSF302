package fu.de200027;

import fu.de200027.dao.DepartmentDao;
import fu.de200027.pojo.Department;

public class Main {
    public static void main(String[] args) {
        DepartmentDao deptDao = new DepartmentDao();

        // 1. Gọi hàm JOIN FETCH (EntityManager mở -> Query -> Đóng EntityManager)
        Department dept = deptDao.findByIdWithEmployees(1L);

        if (dept != null) {
            System.out.println("Tên phòng ban: " + dept.getName());

            // 2. Kiểm chứng: Duyệt danh sách employees SAU KHI EntityManager đã đóng
            // Nếu dùng findById() thường + Lazy Load, dòng dưới sẽ ném LazyInitializationException
            // Khi dùng JOIN FETCH, dòng dưới sẽ chạy thành công 100%!
            System.out.println("Số nhân viên: " + dept.getEmployees().size());
            dept.getEmployees().forEach(emp ->
                    System.out.println(" - " + emp.getFullName() + " | Email: " + emp.getEmail())
            );
        } else {
            System.out.println("Không tìm thấy Department với ID = 1");
        }
    }
}