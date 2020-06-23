/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.bean;

import com.admin.dao.RezervasyonDao;
import com.admin.dao.TurDao;
import com.admin.entity.Rezervasyon;
import com.admin.entity.Tur;
import com.admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendTurBean implements Serializable {

    private Rezervasyon rezervasyon;
    private Tur tur;
    private TurDao turDao;
    private RezervasyonDao rezervasyonDao;
    private ArrayList<Tur> turPageList;

    @Inject
    FrontendUserBean frontendUserBean;

    public FrontendUserBean getFrontendUserBean() {
        if (this.frontendUserBean == null) {
            this.frontendUserBean = new FrontendUserBean();
        }
        return frontendUserBean;
    }

    private int page = 1;

    private final int listItemCount = 3;

    private int pageCount;

    public Rezervasyon getRezervasyon() {
        if (this.rezervasyon == null) {
            this.rezervasyon = new Rezervasyon();
        }
        return rezervasyon;
    }

    public void setRezervasyon(Rezervasyon rezervasyon) {
        this.rezervasyon = rezervasyon;
    }

    public Tur getTur() {
        if (this.tur == null) {
            this.tur = new Tur();
        }
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public RezervasyonDao getRezervasyonDao() {
        if (this.rezervasyonDao == null) {
            this.rezervasyonDao = new RezervasyonDao();
        }
        return rezervasyonDao;
    }

    public TurDao getTurDao() {
        if (this.turDao == null) {
            this.turDao = new TurDao();
        }
        return turDao;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getTurDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public ArrayList<Tur> getTurPageList() {
        this.turPageList = new ArrayList<>();
        this.turPageList = (ArrayList<Tur>) this.getTurDao().pagedList(this.page, this.listItemCount);
        return turPageList;
    }

    public String stringControl(String name) {
        return name.length() >= 44 ? name.substring(0, 44) + "..." : name;
    }

    public String updateTur(Tur tur) {
        this.tur = tur;
        return "/frontend/turdetail/tur_detail?faces-redirect=true";
    }

    public String createRezervasyon(User u) {
        this.getRezervasyon().setUser(u);
        this.getRezervasyon().setTur(this.getTur());
        this.getRezervasyonDao().create(this.getRezervasyon());
        this.rezervasyon = new Rezervasyon();
        this.getFrontendUserBean().userBilgi(u);
        return "/frontend/userBilgi/userBilgi?faces-redirect=true";
    }

}
