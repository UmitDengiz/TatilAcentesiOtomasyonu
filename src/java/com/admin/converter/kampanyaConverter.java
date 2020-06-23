/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;

import com.admin.dao.KampanyaDao;
import com.admin.entity.Kampanya;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "kampanyaConverter")
public class kampanyaConverter implements Converter {

    private KampanyaDao moduleDao;

    public KampanyaDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new KampanyaDao();
        }
        return moduleDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getModuleDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Kampanya k = (Kampanya) o;
        return k.getKampanya_id().toString();
    }

}
