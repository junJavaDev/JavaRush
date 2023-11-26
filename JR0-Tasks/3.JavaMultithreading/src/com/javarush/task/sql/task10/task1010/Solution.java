package com.javarush.task.sql.task10.task1010;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

/* 
task1010
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        getAll().forEach(System.out::println);
    }

    public static List<Employee> getAll() {
        Session session = MySessionFactory.getSessionFactory().openSession();
        NativeQuery<Employee> nativeQuery = session.createNativeQuery("SELECT * FROM employee ORDER BY id", Employee.class);
        return nativeQuery.list();
    }
}