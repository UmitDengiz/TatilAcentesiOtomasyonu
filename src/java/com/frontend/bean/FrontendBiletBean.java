/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.bean;

import com.admin.dao.BiletDao;
import com.admin.entity.Bilet;
import com.admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendBiletBean implements Serializable {

    private Bilet module;
    private BiletDao moduleDao;
    private ArrayList<Bilet> moduleList;
    @Inject
    FrontendUserBean frontendUserBean;

    public FrontendUserBean getFrontendUserBean() {
        if (this.frontendUserBean == null) {
            this.frontendUserBean = new FrontendUserBean();
        }
        return frontendUserBean;
    }

    public void clearForm() {
        this.module = new Bilet();
    }

    public String createBilet(User u) {
        this.getModuleDao().createBiletUser(this.getModule(), u);
        this.clearForm();
        this.getFrontendUserBean().userBilgi(u);
        return "/frontend/userBilgi/userBilgi?faces-redirect=true";
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
