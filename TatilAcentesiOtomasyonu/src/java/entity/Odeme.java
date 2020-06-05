
package entity;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */

public class Odeme {
    
    private int id;
    private String Sekli;
    private int Tutar;
    private int Tarih;
    
    public Odeme() {
    }

    public Odeme(int id, int Tutar, int Tarih) {
        this.id = id;
        this.Tutar = Tutar;
        this.Tarih = Tarih;
    }

    public Odeme(int aInt, String string, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSekli() {
        return Sekli;
    }

    public void setSekli(String Sekli) {
        this.Sekli = Sekli;
    }
    
    
    public int getTutar() {
        return Tutar;
    }

    public void setTutar(int Tutar) {
        this.Tutar = Tutar;
    }

    public int getTarih() {
        return Tarih;
    }

    public void setTarih(int Tarih) {
        this.Tarih = Tarih;
    }
    
    
}
