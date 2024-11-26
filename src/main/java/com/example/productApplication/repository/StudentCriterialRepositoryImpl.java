package com.example.productApplication.repository;

import com.example.productApplication.model.Student;
import com.example.productApplication.model.StudentPage;
import com.example.productApplication.model.StudentSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentCriterialRepositoryImpl
{

    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;


    public StudentCriterialRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Student> findAllWithFilters(StudentPage studentPage, StudentSearchCriteria studentSearchCriteria) {

        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        Predicate predicate = getPredicate(studentSearchCriteria, studentRoot);
        criteriaQuery.where(predicate);

        setOrder(studentPage, criteriaQuery, studentRoot);

        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(studentPage.getPageNumber() * studentPage.getPageSize());
        typedQuery.setMaxResults(studentPage.getPageSize());
        Sort sort = Sort.by(studentPage.getSortDirection(), studentPage.getSortBy());
        Pageable pageable = PageRequest.of(studentPage.getPageNumber(), studentPage.getPageSize(), sort);
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Student> countRoot = countQuery.from(Student.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(typedQuery.getResultList(), pageable, total);


    }

    private Predicate getPredicate(StudentSearchCriteria studentSearchCriteria, Root<Student> studentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (studentSearchCriteria.getFirstName() != null) {
            predicates.add(criteriaBuilder.like(studentRoot.get("firstName"), "%" + studentSearchCriteria.getFirstName() + "%"));
        }
        if (studentSearchCriteria.getLastName() != null) {
            predicates.add(criteriaBuilder.like(studentRoot.get("lastName"), "%" + studentSearchCriteria.getLastName() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    private void setOrder(StudentPage studentPage, CriteriaQuery<Student> criteriaQuery, Root<Student> studentRoot) {
        if (studentPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(studentRoot.get(studentPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(studentRoot.get(studentPage.getSortBy())));
        }
    }



}
