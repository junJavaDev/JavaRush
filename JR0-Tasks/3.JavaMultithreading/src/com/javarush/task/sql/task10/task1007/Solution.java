package com.javarush.task.sql.task10.task1007;

import com.javarush.task.sql.task10.task1007.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/* 
task1007
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        getIn(List.of("hr", "dev", "qa")).forEach(System.out::println);
    }

    public static List<Employee> getIn(List<String> in) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Query<Employee> query = session.createQuery("from Employee where smth in :in_list order by age", Employee.class);
        query.setParameterList("in_list", in);
        return query.list();
    }
}