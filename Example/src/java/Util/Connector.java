/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {
     
    public Connection connect(){
        Connection c = null;

        try{
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
         c  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tatil2", "postgres", "12,");
            System.out.println("basarili");
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
           
        } 
                return c;

        
    }
    
    
    
}
