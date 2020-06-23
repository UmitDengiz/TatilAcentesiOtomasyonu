/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.YorumDao;
import com.admin.entity.Yorum;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class YorumBean implements Serializable {

    private Yorum module;
    private YorumDao moduleDao;
    private ArrayList<Yorum> moduleList;
    private ArrayList<Yorum> modulePageList;

    @Inject
    UserBean userBean;

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
        this.module = new Yorum();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Yorum getModule() {
        if (this.module == null) {
            this.module = new Yorum();
        }
        return this.module;
    }

    public void setModule(Yorum module) {
        this.module = module;
    }

    public UserBean getUserBean() {
        if (this.userBean == null) {
            this.userBean = new UserBean();
        }
        return userBean;
    }

    public OtelBean getOtelBean() {
        if (this.otelBean == null) {
            this.otelBean = new OtelBean();
        }
        return otelBean;
    }

    public ArrayList<Yorum> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Yorum> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Yorum>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public YorumDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new YorumDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Yorum module) {
        this.module = module;
    }

    public void updateForm(Yorum module) {
        this.module = module;
    }

}
