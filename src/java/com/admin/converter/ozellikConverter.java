/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;

import com.admin.dao.OzellikDao;
import com.admin.entity.Ozellik;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "ozellikConverter")
public class ozellikConverter implements Converter{
     private OzellikDao ozellikDao;

    public OzellikDao getOzellikDao() {
        if(this.ozellikDao==null)
            this.ozellikDao=new OzellikDao();
        return ozellikDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getOzellikDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Ozellik k = (Ozellik) o;
        return k.getOzellik_id().toString();
    }
}
