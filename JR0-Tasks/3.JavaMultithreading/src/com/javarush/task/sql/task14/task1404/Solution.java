package com.javarush.task.sql.task14.task1404;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/* 
task1404
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        List<Task> tasks = getTasks();
        tasks.forEach(System.out::println);
    }

    public static List<Task> getTasks() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Task> query = session.createQuery("select distinct t from Task t left join fetch t.employees order by t.deadline", Task.class);
            return query.list();
        }
    }
}