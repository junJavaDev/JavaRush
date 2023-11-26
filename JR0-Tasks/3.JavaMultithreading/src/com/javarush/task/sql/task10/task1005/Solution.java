package com.javarush.task.sql.task10.task1005;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.javarush.task.sql.task10.task1005.entities.Book;

import java.util.List;

/* 
task1005
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        List<Book> books;
        Session session = MySessionFactory.getSessionFactory().openSession();
        books = session.createQuery("from Book where author.fullName is 'Mark Twain' and publisher.name is 'Фолио'", Book.class).list();
        books.forEach(System.out::println);
    }
}