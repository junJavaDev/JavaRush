package com.javarush.task.sql.task16.task1605;

import org.hibernate.Session;
import org.hibernate.Transaction;

/* 
Транзакции
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        switchToRemote();
    }

    public static void switchToRemote() {
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.createQuery("update Employee e set e.smth = concat('virtual ', e.smth) where e.smth like 'office%'").executeUpdate();
            session.createQuery("delete from Task t where t.title = 'Купить кофе' or t.title = 'Убрать офис'").executeUpdate();

            transaction.commit();
        }
    }
}