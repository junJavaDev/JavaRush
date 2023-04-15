package com.javarush.task.sql.task16.task1604;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/* 
Метод list
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            List<Object[]> list = session.createNativeQuery("SELECT id, title, created_time FROM project").list();
            for (Object[] objects : list) {
                System.out.printf("%s, %s, %s", objects[0], objects[1], objects[2]);
            }
        }
    }
}