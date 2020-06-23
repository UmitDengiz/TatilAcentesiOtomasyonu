/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;

import com.admin.dao.BiletDao;
import com.admin.entity.Bilet;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "biletConverter")
public class biletConverter implements Converter {

    private BiletDao biletDao;

    public BiletDao getBiletDao() {
        if (this.biletDao == null) {
            this.biletDao = new BiletDao();
        }
        return biletDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getBiletDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Bilet k = (Bilet) o;
        return k.getBilet_id().toString();
    }
}
