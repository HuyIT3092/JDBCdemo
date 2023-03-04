package com.vn.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection getConnection(){
        try {
            //2. Load driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //3. DriverManager => Connection (host, port, username/password)
            String url ="jdbc:sqlserver://localhost:1433;encrypt=false;database=StudentMS;integratedSecurity=false;";
            String username = "sa";
            String password = "3092";
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("connect success");

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
