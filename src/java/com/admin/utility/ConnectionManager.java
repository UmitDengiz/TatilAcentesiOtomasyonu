
package com.admin.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    /*
    private static final String url = "jdbc:postgresql://localhost:5432/tatilveri";    
    private static final String driverName = "org.postgresql.Driver";   
    private static final String username = "postgres";   
    private static final String password = "root12345";
    */
    
    private static final String url = "jdbc:mysql://localhost:3306/tatil_otomasyonu";    
    private static final String driverName = "com.mysql.jdbc.Driver";   
    private static final String username = "root";   
    private static final String password = "root1234";
    private static Connection con;
    

    private  ConnectionManager(){
    }
    

    public synchronized static ConnectionManager getInstance()
    {

        if(instance == null)
        {
            instance  = new ConnectionManager();
        }
        return instance;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("Ben eşsiz bir parçayım");
    }

    
    public  Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."+ex.getMessage()); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found "); 
        }
        return con;
    }
}

