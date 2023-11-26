package com.javarush.task.sql.task13.task1306;

import com.javarush.task.sql.task13.task1306.entities.Author;
import com.javarush.task.sql.task13.task1306.entities.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* 
Соавторство
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Query<Author> query = session.createQuery("from Author where fullName = :FULL_NAME", Author.class);
            query.setParameter("FULL_NAME", "Mark Twain");
            Author twain = query.getSingleResult();
            twain.getBooks().stream()
                    .flatMap(book -> book.getAuthors().stream())
                    .distinct()
                    .filter(author -> !twain.equals(author))
                    .map(Author::getFullName)
                    .forEach(System.out::println);
        }
    }
}
