/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.bean;

import com.admin.dao.OtelDao;
import com.admin.dao.YorumDao;
import com.admin.entity.Otel;
import com.admin.entity.User;
import com.admin.entity.Yorum;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendOtelBean implements Serializable {

    private Otel otel;
    private Yorum yorum;
    private OtelDao otelDao;
    private YorumDao yorumDao;

    private ArrayList<Otel> otelPageList;

    private int page = 1;

    private final int listItemCount = 6;

    private int pageCount;

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

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getOtelDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public Otel getOtel() {
        if (this.otel == null) {
            this.otel = new Otel();
        }
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
    }

    public Yorum getYorum() {
        if (this.yorum == null) {
            this.yorum = new Yorum();
        }
        return yorum;
    }

    public ArrayList<Otel> getOtelPageList() {
        this.otelPageList = new ArrayList<>();
        this.otelPageList = (ArrayList<Otel>) this.getOtelDao().pagedList(this.page, this.listItemCount);
        return otelPageList;
    }

    public YorumDao getYorumDao() {
        if (this.yorumDao == null) {
            this.yorumDao = new YorumDao();
        }
        return yorumDao;
    }

    public OtelDao getOtelDao() {
        if (this.otelDao == null) {
            this.otelDao = new OtelDao();
        }
        return otelDao;
    }

    public String stringControl(String name) {
        return name.length() >= 44 ? name.substring(0, 44) + "..." : name;
    }

    public void createYorum(User u) {
        this.getYorum().setUser(u);
        this.getYorum().setOtel(this.getOtel());
        this.getYorumDao().create(this.getYorum());
        this.setOtel(this.getOtelDao().detailFrontend(this.getOtel().getOtel_id()));
        this.yorum = new Yorum();
    }

    public String updateOtel(Otel otel) {
        this.otel = otel;
        return "/frontend/oteldetail/otel_detail?faces-redirect=true";
    }

}
