package fu.de200027.dao;

import fu.de200027.pojo.Department;
import fu.de200027.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class DepartmentDao {

    public void save(Department department) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(department);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Department> findAll() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public Department findById(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.find(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Department update(Department department) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Department updatedDept = null;
        try {
            tx.begin();
            // em.merge() trả về instance mới đã managed, gán lại kết quả
            updatedDept = em.merge(department);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return updatedDept;
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Department department = em.find(Department.class, id);
            if (department != null) {
                em.remove(department);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    // TODO 2.6: Lấy Department kèm danh sách Employees bằng JOIN FETCH
    public Department findByIdWithEmployees(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery(
                            "SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id",
                            Department.class
                    )
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close(); // Đóng EntityManager nhưng dữ liệu employees đã được nạp (fetched) trước đó
        }
    }
    // TODO 2.9: Fix N+1 Query Problem bằng cách lấy tất cả Department kèm Employees trong đúng 1 câu SQL
    public List<Department> findAllWithEmployees() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees",
                    Department.class
            ).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close(); // an toàn vì dữ liệu employees đã được fetch ngay trong query
        }
    }
    public List<Object[]> getProjectEmployeeStats() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        try {
            String jpql = """
            SELECT p.projectName, COUNT(e), SUM(e.salary)
            FROM Project p JOIN p.employees e
            WHERE e.active = true
            GROUP BY p.projectName
        """;

            return em.createQuery(jpql, Object[].class)
                    .getResultList();
        } finally {
            em.close();
        }
    }














}