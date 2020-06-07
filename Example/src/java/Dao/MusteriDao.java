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
public class MusteriDao {
    private Connector connector;
    private Connection c;

    //    DBconnection DB = new DBconnection();
    //Connection c = DB.connect();
    public List<Musteri> getActors() {
        List<Musteri> clist = new ArrayList();

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from musteri");
            ResultSet rs = pst.executeQuery();
            //st.executeUpdate("insert into actor values('Ahmet Kaya',43)");

            while (rs.next()) {

                Musteri tmp = new Musteri(rs.getInt("musteriid"),rs.getString("ad"),rs.getString("soyad") );
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
    
       public void insert(Musteri actor) {
        
        try{
            PreparedStatement pst = this.getConnection().prepareStatement("insert into musteri(musteriid,ad,soyad) values(?,?,?)");
            pst.setInt(1, actor.getMusteriid());
            pst.setString(2, actor.getAd());
            pst.setString(3, actor.getSoyad());
            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    public void delete(Musteri actor) {
        
        
         try{
            PreparedStatement pst = this.getConnection().prepareStatement("delete from musteri where musteriid=? ");
            pst.setInt(1, actor.getMusteriid());
            pst.executeUpdate();
        }   catch(SQLException ex){
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   



    public void update(Musteri actor) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update musteri set musteriid=?, ad=?, soyad=?  where musteriid = ?");
             pst.setInt(1, actor.getMusteriid());
            pst.setString(2, actor.getAd());
            pst.setString(3, actor.getSoyad());
           
            
            pst.executeUpdate();
             

        } catch (SQLException ex) {
            Logger.getLogger(MusteriDao.class.getName()).log(Level.SEVERE, null, ex);
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

