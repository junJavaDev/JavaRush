package com.javarush.task.sql.task10.task1002;

import org.hibernate.Session;
import org.hibernate.query.Query;

/* 
task1002
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        System.out.println(getDirector());
    }

    public static Employee getDirector() {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("from Employee where smth = 'director'", Employee.class);
        return (Employee) query.getSingleResult();
    }
}