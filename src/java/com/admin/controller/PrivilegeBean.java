/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.PrivilegeDao;
import com.admin.entity.Privilege;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PrivilegeBean implements Serializable {

    private Privilege module;
    private PrivilegeDao moduleDao;
    private ArrayList<Privilege> moduleList;
    private ArrayList<Privilege> modulePageList;

    @Inject
    RoleBean roleBean;

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
        this.setModule(new Privilege());
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Privilege getModule() {
        if (this.module == null) {
            this.module = new Privilege();
        }
        return this.module;
    }

    public void setModule(Privilege module) {
        this.module = module;
    }

    public ArrayList<Privilege> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Privilege> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Privilege>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public PrivilegeDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new PrivilegeDao();
        }
        return moduleDao;
    }

    public void replace(Privilege p, String s) {
        this.getModuleDao().updateOne(p, s);
    }

    public RoleBean getRoleBean() {
        if (this.roleBean == null) {
            this.roleBean = new RoleBean();
        }
        return roleBean;
    }

    public void deleteConfirm(Privilege module) {
        this.module = module;
    }

    public void updateForm(Privilege module) {
        this.module = module;
    }
}
