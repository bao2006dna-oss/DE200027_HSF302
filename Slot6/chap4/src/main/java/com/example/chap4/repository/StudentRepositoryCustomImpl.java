package com.example.chap4.repository;

import com.example.chap4.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsWithComplexConditionCustom(String deptCode, double minGpa, boolean activeOnly) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (deptCode != null && !deptCode.isBlank()) {
            predicates.add(cb.equal(root.get("department").get("code"), deptCode.trim().toUpperCase()));
        }

        predicates.add(cb.greaterThanOrEqualTo(root.get("gpa"), minGpa));

        if (activeOnly) {
            predicates.add(cb.equal(root.get("active"), true));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Student> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}