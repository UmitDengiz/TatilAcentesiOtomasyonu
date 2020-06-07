/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Util.Connector;
import Entity.Musteri;
import Entity.Otel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acar
 */
public class OtelDao {
    private Connector connector;
    private Connection c;

    //    DBconnection DB = new DBconnection();
    //Connection c = DB.connect();
    public List<Otel> getActors() {
        List<Otel> clist= new ArrayList();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from otel");
            ResultSet rs = pst.executeQuery();
            //st.executeUpdate("insert into actor values('Ahmet Kaya',43)");

            while (rs.next()) {

           Otel tmp = new Otel(rs.getInt("otelid"),rs.getString("ad"),rs.getString("adres"),rs.getInt("yildiz") );
           clist.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return clist;

    }

    public Connector getConnector() {
        if (this.connector == null) {
            this.connector = new Connector();
        }
        return connector;
    }

    public Connection getConnection() {
        if (this.c == null) {
            this.c = this.getConnector().connect();
        }
        return c;
    }
    
       public void insert(Otel otel) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into otel(otelid,ad,adres,yildiz) values(?,?,?,?)");
            pst.setInt(1, otel.getOtelid());
            pst.setString(2, otel.getAd());
            pst.setString(3, otel.getAdres());
           pst.setInt(4, otel.getYildiz());

            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(OtelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    public void delete(Otel cat) {
        
        
         try{
            Statement pst = this.getConnection().createStatement( );
         
            pst.executeUpdate("delete from otel where otelid="+cat.getOtelid());
        }   catch(SQLException ex){
            Logger.getLogger(OtelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
    }
   
/*
    public void delete(Musteri ac) {
 
      try{
    
          PreparedStatement pst = this.getConnection().prepareStatement("delete from musteri  where musteriid =? ");
          pst.setInt(1, ac.getMusteriid());
          pst.executeUpdate();

                          
      }
      catch(SQLException ex){
      }


    }
*/

