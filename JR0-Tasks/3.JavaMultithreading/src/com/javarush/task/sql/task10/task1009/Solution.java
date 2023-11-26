package com.javarush.task.sql.task10.task1009;

import org.hibernate.Session;
import org.hibernate.query.Query;

/* 
task1009
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        System.out.println("Salary fund: $" + getSalaryFund());
        System.out.println("Agerage age: " + getAverageAge());
    }

    public static Long getSalaryFund() {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("select sum(salary) from Employee", Long.class);
        return query.uniqueResult();
    }

    public static Double getAverageAge() {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Query<Double> query = session.createQuery("select avg(age) from Employee", Double.class);
        return query.uniqueResult();
    }
}