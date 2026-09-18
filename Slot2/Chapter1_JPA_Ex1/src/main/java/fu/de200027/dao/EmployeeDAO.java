package fu.de200027.dao;

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
}
