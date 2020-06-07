
package Entity;

public class Otel {

    private String ad,adres;
    private int otelid,yildiz;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getOtelid() {
        return otelid;
    }

    public void setOtelid(int otelid) {
        this.otelid = otelid;
    }

    public int getYildiz() {
        return yildiz;
    }

    public void setYildiz(int yildiz) {
        this.yildiz = yildiz;
    }

    

    
    public Otel( int otelid,String ad ,String adres,int yildiz) {
    this.otelid=otelid;
    this.ad=ad;
    this.adres=adres;
    this.yildiz=yildiz;

    
    }
 
    public Otel() {
        
    }


    @Override
    public String toString() {
        return "Otel{" + "otelid=" + otelid + ", ad=" + ad + ", adres=" + adres +",yildiz="+yildiz+ '}';
    }
 
    
    
   
}
