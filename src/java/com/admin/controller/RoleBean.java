/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.RoleDao;
import com.admin.entity.Role;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Cypher
 */
@Named
@SessionScoped
public class RoleBean implements Serializable {

    private Role module;
    private RoleDao moduleDao;
    private ArrayList<Role> moduleList;
    private ArrayList<Role> modulePageList;

    @Inject
    UserBean userBean;

    public UserBean getUserBean() {
        if (this.userBean == null) {
            this.userBean = new UserBean();
        }
        return userBean;
    }

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
        this.module = new Role();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Role getModule() {
        if (this.module == null) {
            this.module = new Role();
        }
        return this.module;
    }

    public void setModule(Role module) {
        this.module = module;
    }

    public ArrayList<Role> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Role> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Role>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public RoleDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new RoleDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Role module) {
        this.module = module;
    }

    public void updateForm(Role module) {
        this.module = module;
    }
}
