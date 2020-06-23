/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;
import com.admin.dao.OtelDao;
import com.admin.entity.Otel;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "otelConverter")
public class otelConverter implements Converter {
    private OtelDao otelDao;

    public OtelDao getOtelDao() {
        if(this.otelDao==null)
            this.otelDao=new OtelDao();
        return otelDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getOtelDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Otel k = (Otel) o;
        return k.getOtel_id().toString();
    }
    
    
}
