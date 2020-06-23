/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;

import com.admin.dao.UserDao;
import com.admin.entity.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "userConverter")
public class userConverter implements Converter {

    private UserDao userDao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
        return this.getuserDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object o) {
       User k = (User) o;
        return k.getId().toString();
    }

    public UserDao getuserDao() {
        if (this.userDao == null) 
            this.userDao = new UserDao();
        
        return userDao;
    }

}