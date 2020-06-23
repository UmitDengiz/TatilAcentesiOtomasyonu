/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.RezervasyonDao;
import com.admin.entity.Rezervasyon;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class RezervasyonBean implements Serializable {

    private Rezervasyon module;
    private RezervasyonDao moduleDao;
    private ArrayList<Rezervasyon> moduleList;
    private ArrayList<Rezervasyon> modulePageList;

    @Inject
    UserBean userBean;

    @Inject
    TurBean turBean;

    private int page = 1;

    private final int listItemCount = 5;

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

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getModuleDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public void update() {
        this.getModuleDao().update(this.getModule());
        this.clearForm();
    }

    public void clearForm() {
        this.module = new Rezervasyon();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Rezervasyon getModule() {
        if (this.module == null) {
            this.module = new Rezervasyon();
        }
        return this.module;
    }

    public void setModule(Rezervasyon module) {
        this.module = module;
    }

    public UserBean getUserBean() {
        if (this.userBean == null) {
            this.userBean = new UserBean();
        }
        return userBean;
    }

    public TurBean getTurBean() {
        if (this.turBean == null) {
            this.turBean = new TurBean();
        }
        return turBean;
    }

    public ArrayList<Rezervasyon> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Rezervasyon> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Rezervasyon>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public RezervasyonDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new RezervasyonDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Rezervasyon module) {
        this.module = module;
    }

    public void updateForm(Rezervasyon module) {
        this.module = module;
    }

}
