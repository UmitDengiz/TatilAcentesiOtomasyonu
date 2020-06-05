
package dao;

import entity.Odeme;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */

public class OdemeDao extends DBConnection{
    
    // *** CRUD ***>>>
    
    public void create(Odeme odeme){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into odeme (Sekli) values ('"+odeme.getSekli()+"')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List <Odeme> read(){
        
        List <Odeme> list = new ArrayList<>();
        
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from odeme order by id desc");
            
            while (rs.next()) {                
                Odeme temp = new Odeme(rs.getInt("id"), rs.getString("Sekli"), rs.getInt("Tutar"), rs.getInt("Tarih"));
                list.add(temp);
            }
            
        } catch (Exception e) {
        }
        return list;
    }
    
    public void update(Odeme odeme){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update odeme set sekli = '"+odeme.getSekli()+"' where id = "+odeme.getId());
        } catch (Exception e) {
        }
        
    }
    
    public void delete(Odeme odeme ){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from odeme where id = "+odeme.getId());
        } catch (Exception e) {
        }
        
    }
    
}
