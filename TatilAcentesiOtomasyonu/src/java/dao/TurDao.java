
package dao;

import entity.Tur;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */

public class TurDao extends DBConnection{
    
    // *** CRUD ***>>>
    
    public void create(Tur tur){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("insert into tur (TurAdi,Konum) values ('"+tur.getTurAdi()+"'+'"+tur.getKonum()+"')");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List <Tur> read(){
        
        List <Tur> list = new ArrayList<>();
        
        try {
            Statement st = this.connect().createStatement();
            ResultSet rs = st.executeQuery("select * from tur order by id desc");
            
            while (rs.next()) {                
                Tur temp = new Tur(rs.getInt("id"), rs.getString("Tur"), rs.getString("Konum"));
                list.add(temp);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void update(Tur tur){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("update tur set TurAdi = '"+tur.getTurAdi()+"',Konum='"+tur.getKonum()+"' where id = "+tur.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void delete(Tur tur ){
        
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate("delete from tur where id = "+tur.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
