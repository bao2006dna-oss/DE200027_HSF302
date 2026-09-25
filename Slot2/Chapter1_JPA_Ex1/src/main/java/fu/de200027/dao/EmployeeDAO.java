package fu.de200027.dao;

import java.math.*;
import java.util.*;

import jakarta.persistence.*;
import fu.de200027.pojo.Employee;


public class EmployeeDAO {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsf302FU");

    //TODO 3:create
    public void Save(Employee e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            ;

        } catch (RuntimeException Ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw Ex;


        } finally {
            em.close();
        }


    }

    // TODO 4:read
    public Employee findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }


    }

    public List<Employee> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();


        } finally {
            em.close();
        }

    }
//add to do 5 method
    //TODO 5:read by condition
    public Employee FindByEmail(String email) {

        EntityManager em = emf.createEntityManager();
        List<Employee> result = em.createQuery("SELECT e FROM Emloyee e WHERE e.email=:email", Employee.class).setParameter("email", email).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
public Employee findBySalaryGreaterThanAndActive(BigDecimal minsalary){

    EntityManager em = emf.createEntityManager();

    try {
        return (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.salary>: minsalary AND e.active=true", Employee.class).setParameter("minsalary",minsalary).getResultList();


    } finally {
        em.close();
    }



}

}
