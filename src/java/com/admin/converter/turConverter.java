/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;


import com.admin.dao.TurDao;
import com.admin.entity.Tur;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "turConverter")
public class turConverter implements Converter{
      private TurDao turDao;

    public TurDao getTurDao() {
        if(this.turDao==null)
            this.turDao=new TurDao();
        return turDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getTurDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Tur k = (Tur) o;
        return k.getTur_id().toString();
    }
}
