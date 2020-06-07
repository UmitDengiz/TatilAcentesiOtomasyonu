
package Controller;


import Dao.OtelDao;
import Entity.Otel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Acar
 */
@Named
@SessionScoped

public class Otelcontroller implements Serializable {
  
    private List<Otel>  clist;
    private OtelDao cdao;
    private Otel otel;

    public List<Otel> getClist() {
                this.clist=this.getCdao().getActors();

        return clist;
    }
    public Otelcontroller() {
        this.clist = new ArrayList();
        cdao= new OtelDao();
    }

    public void setClist(List<Otel> clist) {
        this.clist = clist;
    }

    public OtelDao getCdao() {
          if(this.cdao==null)
            this.cdao = new OtelDao();
        return cdao;
    }

   
        
    public void setCdao(OtelDao cdao) {
        this.cdao = cdao;
    }

    

    public Otel getOtel() {
             if(this.otel==null)
            this.otel= new Otel();
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
    }

 
    public  String  delete(Otel cat){
        this.getCdao().delete(cat);
        
        return "otel";
    }
    
    public String  create (){
        this.getCdao().insert(this.otel);
        return "otel";        
    }

    
}
