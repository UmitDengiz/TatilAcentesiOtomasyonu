/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.converter;


import com.admin.dao.RoleDao;
import com.admin.entity.Role;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "roleConverter")
public class roleConverter implements Converter {
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        if(this.roleDao==null)
            this.roleDao=new RoleDao();
        return roleDao;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getRoleDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Role k = (Role) o;
        return k.getRole_Id().toString();
    }
    
    
}
