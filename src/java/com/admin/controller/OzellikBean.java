/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.OzellikDao;
import com.admin.entity.Ozellik;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class OzellikBean implements Serializable {

    private Ozellik module;
    private OzellikDao moduleDao;
    private ArrayList<Ozellik> moduleList;
    private ArrayList<Ozellik> modulePageList;

    @Inject
    OtelBean otelBean;

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
        this.module = new Ozellik();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Ozellik getModule() {
        if (this.module == null) {
            this.module = new Ozellik();
        }
        return this.module;
    }

    public void setModule(Ozellik module) {
        this.module = module;
    }

    public OtelBean getOtelBean() {
        if (this.otelBean == null) {
            this.otelBean = new OtelBean();
        }
        return otelBean;
    }

    public ArrayList<Ozellik> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Ozellik> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Ozellik>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public OzellikDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new OzellikDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Ozellik module) {
        this.module = module;
    }

    public void updateForm(Ozellik module) {
        this.module = module;
    }
}
