
package Entity;

public class Musteri {

    private String ad,soyad;
    private int musteriid;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public int getMusteriid() {
        return musteriid;
    }

    public void setMusteriid(int musteriid) {
        this.musteriid = musteriid;
    }



    
    public Musteri( int musteriid,String ad ,String soyad) {
    this.musteriid=musteriid;
    this.ad=ad;
    this.soyad=soyad;

    
    }
 
    public Musteri() {
        
    }


    @Override
    public String toString() {
        return "Musteri{" + "musteriid=" + musteriid + ", ad=" + ad + ", soyad=" + soyad +  '}';
    }
 
    
    
   
}
