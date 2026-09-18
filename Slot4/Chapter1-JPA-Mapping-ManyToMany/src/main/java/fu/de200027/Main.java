package fu.de200027;

import fu.de200027.dao.EmployeeDAO;
import fu.de200027.pojo.Employee;
import fu.de200027.pojo.Gender;
import fu.de200027.pojo.Project;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        try {
            // TODO 5.7: Tạo 3 Employee
            Employee nv1 = new Employee(
                    "nv1@gmail.com",
                    "Nguyen Van A",
                    Gender.MALE,
                    new BigDecimal("1500.00"),
                    LocalDate.of(2024, 1, 10)
            );

            Employee nv2 = new Employee(
                    "nv2@gmail.com",
                    "Tran Thi B",
                    Gender.FEMALE,
                    new BigDecimal("1800.00"),
                    LocalDate.of(2024, 3, 15)
            );

            Employee nv3 = new Employee(
                    "nv3@gmail.com",
                    "Le Van C",
                    Gender.MALE,
                    new BigDecimal("2000.00"),
                    LocalDate.of(2024, 5, 20)
            );

            nv1.setActive(true);
            nv2.setActive(true);
            nv3.setActive(true);

            // TODO 5.7: Tạo 2 Project
            Project projectA = new Project(
                    "PRJ001",
                    "Project A",
                    new BigDecimal("10000.00"),
                    LocalDate.of(2025, 1, 1),
                    null
            );

            Project projectB = new Project(
                    "PRJ002",
                    "Project B",
                    new BigDecimal("20000.00"),
                    LocalDate.of(2025, 2, 1),
                    null
            );

            // Lưu Employee và Project
            Long nv1Id = dao.create(nv1);
            Long nv2Id = dao.create(nv2);
            Long nv3Id = dao.create(nv3);

            Long projectAId = dao.createProject(projectA);
            Long projectBId = dao.createProject(projectB);

            // TODO 5.7: Phân công chéo
            // NV1 -> Project A + B
            dao.assignEmployeeToProject(nv1Id, projectAId);
            dao.assignEmployeeToProject(nv1Id, projectBId);

            // NV2 -> Project B
            dao.assignEmployeeToProject(nv2Id, projectBId);

            // NV3 -> Project A
            dao.assignEmployeeToProject(nv3Id, projectAId);

            // In danh sách project của từng nhân viên
            System.out.println("\n===== PROJECTS OF EMPLOYEES =====");

            Employee e1 = dao.findById(nv1Id);
            Employee e2 = dao.findById(nv2Id);
            Employee e3 = dao.findById(nv3Id);

            System.out.println("\n" + e1.getFullName() + ":");
            for (Project p : e1.getProjects()) {
                System.out.println("- " + p.getProjectCode()
                        + " - " + p.getProjectName());
            }

            System.out.println("\n" + e2.getFullName() + ":");
            for (Project p : e2.getProjects()) {
                System.out.println("- " + p.getProjectCode()
                        + " - " + p.getProjectName());
            }

            System.out.println("\n" + e3.getFullName() + ":");
            for (Project p : e3.getProjects()) {
                System.out.println("- " + p.getProjectCode()
                        + " - " + p.getProjectName());
            }

        } finally {
            dao.close();
        }
    }
}