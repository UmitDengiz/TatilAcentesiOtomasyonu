
package controller;

import dao.OdemeDao;
import entity.Odeme;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.util.List;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */

@Named
@SessionScoped

public class OdemeBean implements Serializable{
    
    private OdemeDao odemeDao;
    private Odeme odeme;
    
    // CRUD
    
    public String create(){
        this.getOdemeDao().create(odeme);
        this.odeme = new Odeme();
        return "/odeme/list";
    }
    
    public List<Odeme> getRead(){
        return this.getOdemeDao().read();
    }
    
    public String updateForm(Odeme odeme){
        this.odeme = odeme;
        return "/odeme/update";
    }
    
    public String update(){
        this.getOdemeDao().update(odeme);
        this.odeme = new Odeme();
        return "/odeme/list";
    }
    
    public void delete(Odeme odeme){
        this.getOdemeDao().delete(odeme);
    }
    
    public OdemeBean() {
    }
    
    public OdemeDao getOdemeDao() {
        if(this.odemeDao == null)
            this.odemeDao = new OdemeDao();
        return odemeDao;
    }

    public void setOdemeDao(OdemeDao odemeDao) {
        this.odemeDao = odemeDao;
    }

    public Odeme getOdeme() {
        if(this.odeme == null)
            this.odeme = new Odeme();
        return odeme;
    }

    public void setOdeme(Odeme odeme) {
        this.odeme = odeme;
    }
      
}
