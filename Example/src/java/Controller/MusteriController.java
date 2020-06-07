package Controller;

import Dao.MusteriDao;
import Entity.Musteri;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped

public class MusteriController implements Serializable {
  
    private List<Musteri>  clist;
    private MusteriDao cdao;
    private Musteri actor;

     public  void  remove(Musteri cat){
        this.getCdao().delete(cat);
    }
    
    public String  create (){
        this.getCdao().insert(this.actor);
        return "musteri";        
    }
    
    
    public Musteri getActor() {
        if(this.actor==null)
            this.actor= new Musteri();
        return actor;
    }

    public void setActor(Musteri actor) {
        this.actor = actor;
    }
 
    public MusteriController() {
        this.clist = new ArrayList();
        cdao= new MusteriDao();
    }
    
    public List<Musteri> getClist() {

        this.clist=this.getCdao().getActors();
        return clist;
    }

    public void setClist(List<Musteri>  clist) {
        this.clist = clist;
    }

    public MusteriDao getCdao() {
        if(this.cdao==null)
            this.cdao = new MusteriDao();
        return cdao;
    }

    public void setCdao(MusteriDao cdao) {
        this.cdao = cdao;
    }

   public void update() {
        this.getCdao().update(this.actor);
        this.actor = new Musteri();
    }
    
    
}
