
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */

public abstract class DBConnection {
    
    public Connection connect(){
        
        Connection c = null;
        
        try {
            
            //MySQL Connection
            /*
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/TatilOtomasyonu?user=root&password=123456");
            */
            
            //PostgreSQL Connection
            Class.forName("org.postgresql.Driver").newInstance();
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TatilOtomasyonu?user=postgres&password=123456");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
