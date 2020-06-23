/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.bean;

import com.admin.dao.UserDao;
import com.admin.entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendUserBean implements Serializable {

    private User module;
    private UserDao moduleDao;
    private User fuser;

    public User getFuser() {
        if (this.fuser == null) {
            this.fuser = new User();
        }
        return fuser;
    }

    public void setFuser(User fuser) {
        this.fuser = fuser;
    }

    public String createUser() {
        this.getModuleDao().createFrontendUser(this.getFuser());
        this.fuser = new User();
        return "/admin/login?faces-redirect=true";
    }

    public User getModule() {
        if (this.module == null) {
            this.module = new User();
        }
        return this.module;
    }

    public UserDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new UserDao();
        }
        return moduleDao;
    }

    public String userBilgi(User u) {
        this.module = this.getModuleDao().detailFrontend(u.getId());
        return "/frontend/userBilgi/userBilgi?faces-redirect=true";
    }
}
