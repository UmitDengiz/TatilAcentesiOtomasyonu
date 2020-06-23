/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.UserDao;
import com.admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserBean implements Serializable {

    private User module;
    private UserDao moduleDao;
    private ArrayList<User> moduleList;
    private ArrayList<User> modulePageList;

    @Inject
    RoleBean roleBean;
    
    @Inject
    KampanyaBean kampanyaBean;
    
    @Inject
    BiletBean biletBean;

    public BiletBean getBiletBean() {
        if(this.biletBean==null)
            this.biletBean=new BiletBean();
        return biletBean;
    }
    

    public KampanyaBean getKampanyaBean() {
        if(this.kampanyaBean==null)
            this.kampanyaBean=new KampanyaBean();
        return kampanyaBean;
    }
    
    
    
      

    public RoleBean getRoleBean() {
        if (this.roleBean == null) {
            this.roleBean = new RoleBean();
        }
        return roleBean;
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
        this.setModule(new User());
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public User getModule() {
        if (this.module == null) {
            this.module = new User();
        }
        return this.module;
    }

    public void setModule(User module) {
        this.module = module;
    }

    public ArrayList<User> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<User> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<User>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public UserDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new UserDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(User module) {
        this.module = module;
    }

    public void updateForm(User module) {
        this.module = module;
    }
}
