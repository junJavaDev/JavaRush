package com.javarush.task.sql.task16.task1603;

/* 
Удаление через Criteria API
*/

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Solution {

    public static void main(String[] args) {
        EmployeeFactory.initEmployees();
        deleteEmployeeById(2L);
    }

    public static void deleteEmployeeById(long id) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Employee> criteriaDelete = criteriaBuilder.createCriteriaDelete(Employee.class);
            Root<Employee> root = criteriaDelete.from(Employee.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }
}
