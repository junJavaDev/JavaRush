package com.javarush.task.sql.task07.task0705;

import java.sql.*;

/* 
task0705
*/

public class Solution {

    public static void main(String[] args) throws Exception {
       Connection connection = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/test"
               , "root"
               , "root"
       );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT MIN(age) FROM employee");
        resultSet.next();
        System.out.println(resultSet.getString(1));
        statement.close();
        connection.close();
    }
}
