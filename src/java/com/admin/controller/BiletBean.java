/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.BiletDao;
import com.admin.entity.Bilet;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BiletBean implements Serializable {

    private Bilet module;
    private BiletDao moduleDao;
    private ArrayList<Bilet> moduleList;
    private ArrayList<Bilet> modulePageList;


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
        this.module = new Bilet();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Bilet getModule() {
        if (this.module == null) {
            this.module = new Bilet();
        }
        return this.module;
    }

    public void setModule(Bilet module) {
        this.module = module;
    }

    public ArrayList<Bilet> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Bilet> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Bilet>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public BiletDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new BiletDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Bilet module) {
        this.module = module;
    }

    public void updateForm(Bilet module) {
        this.module = module;
    }
}
