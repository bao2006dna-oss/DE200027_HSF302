package fu.de200027.dao;

import fu.de200027.pojo.Employee;
import fu.de200027.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Collections;
import java.util.List;

public class EmployeeDao {

    public void save(Employee employee) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(employee);
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

    public List<Employee> findAll() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public Employee findById(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            return em.find(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Employee update(Employee employee) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Employee updatedEmp = null;
        try {
            tx.begin();
            // gán lại kết quả trả về từ em.merge()
            updatedEmp = em.merge(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return updatedEmp;
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
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
}