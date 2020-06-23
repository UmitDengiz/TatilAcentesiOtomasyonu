/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.bean;

import com.admin.dao.KampanyaDao;
import com.admin.entity.Kampanya;
import com.admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendKampanyaBean implements Serializable {

    
   @Inject
   FrontendUserBean frontendUserBean;

    public FrontendUserBean getFrontendUserBean() {
        if(this.frontendUserBean==null)
            this.frontendUserBean=new FrontendUserBean();
        return frontendUserBean;
    }
   
   
           
    
    private Kampanya module;
    private KampanyaDao moduleDao;
    private ArrayList<Kampanya> modulePageList;

    private int page = 1;

    private final int listItemCount = 3;

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

    public void clearForm() {
        this.module = new Kampanya();
    }

    public String createKampanya(User user) {
        this.getModuleDao().createKampanyaUser(this.getModule(),user);
        this.clearForm();
        this.getFrontendUserBean().userBilgi(user);
        return "/frontend/userBilgi/userBilgi?faces-redirect=true";
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

    public String updateKampanya(Kampanya kampanya) {
        this.module = kampanya;
        return "/frontend/kampanyadetail/kampanya_detail?faces-redirect=true";
    }

}
