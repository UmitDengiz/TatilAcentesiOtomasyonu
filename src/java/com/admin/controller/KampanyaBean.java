/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.KampanyaDao;
import com.admin.entity.Kampanya;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class KampanyaBean implements Serializable {

    private Kampanya module;
    private KampanyaDao moduleDao;
    private ArrayList<Kampanya> moduleList;
    private ArrayList<Kampanya> modulePageList;

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
        this.module = new Kampanya();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Kampanya getModule() {
        if (this.module == null) {
            this.module = new Kampanya();
        }
        return this.module;
    }

    public void setModule(Kampanya module) {
        this.module = module;
    }

    public ArrayList<Kampanya> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Kampanya> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Kampanya>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public KampanyaDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new KampanyaDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Kampanya module) {
        this.module = module;
    }

    public void updateForm(Kampanya module) {
        this.module = module;
    }

}
