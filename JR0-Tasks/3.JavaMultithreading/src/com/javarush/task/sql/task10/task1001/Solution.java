package com.javarush.task.sql.task10.task1001;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/* 
task1001
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Session currentSession = MySessionFactory.getSessionFactory().openSession();
        Query<String> query = currentSession.createQuery("select distinct smth from Employee where age > 18 order by smth", String.class);
        query.list().forEach(System.out::println);
    }
}