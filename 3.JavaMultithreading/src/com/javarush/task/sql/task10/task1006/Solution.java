package com.javarush.task.sql.task10.task1006;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/* 
task1006
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        getBetween(25, 32).forEach(System.out::println);
    }

    public static List<Employee> getBetween(int from, int to) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("from Employee where age > :from and age < :to order by age", Employee.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.list();
    }
}