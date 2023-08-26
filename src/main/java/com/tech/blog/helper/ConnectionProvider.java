package com.tech.blog.helper;
import java.sql.*;
public class ConnectionProvider {

    private static Connection conn;
    public static Connection getConnection() {
        try{
           if(conn == null){
               Class.forName("com.mysql.jdbc.driver");
               conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/techblog","root", "root");
           }

        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
