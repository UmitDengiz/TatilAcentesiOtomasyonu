
package controller;

import dao.TurDao;
import entity.Tur;
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

public class TurBean implements Serializable{
    
    private TurDao turDao;
    private Tur tur;
    
    // CRUD
    
    public String create(){
        this.getTurDao().create(tur);
        this.tur = new Tur();
        return "/tur/list";
    }
    
    public List<Tur> getRead(){
        return this.getTurDao().read();
    }
    
    public String updateForm(Tur tur){
        this.tur = tur;
        return "/tur/update";
    }
    
    public String update(){
        this.getTurDao().update(tur);
        this.tur = new Tur();
        return "/tur/list";
    }
    
    public void delete(Tur tur){
        this.getTurDao().delete(tur);
    }
    
    public TurBean() {
    }
    
    public TurDao getTurDao() {
        if(this.turDao == null)
            this.turDao = new TurDao();
        return turDao;
    }

    public void setTurDao(TurDao turDao) {
        this.turDao = turDao;
    }

    public Tur getTur() {
        if(this.tur == null)
            this.tur = new Tur();
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }
    
}
