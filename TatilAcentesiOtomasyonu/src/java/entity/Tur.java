package entity;

/**
 *
 * @author Ümit Dengiz - 50160076067
 */
public class Tur {

    private int id;
    private String TurAdi;
    private String Konum;

    public Tur() {
    }

    public Tur(int id, String TurAdi, String Konum) {
        this.id = id;
        this.TurAdi = TurAdi;
        this.Konum = Konum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurAdi() {
        return TurAdi;
    }

    public void setTurAdi(String TurAdi) {
        this.TurAdi = TurAdi;
    }

    public String getKonum() {
        return Konum;
    }

    public void setKonum(String Konum) {
        this.Konum = Konum;
    }
    
}
