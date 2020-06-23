package com.admin.entity;

import java.util.Objects;

public class Privilege {

    private Long privilege_Id;
    private String p_Module;
    private boolean yetki_C;
    private boolean yetki_R;
    private boolean yetki_U;
    private boolean yetki_D;
    private Role role;

    public Privilege() {
    }

    public Long getPrivilege_Id() {
        return privilege_Id;
    }

    public void setPrivilege_Id(Long privilage_Id) {
        this.privilege_Id = privilage_Id;
    }


    public String getP_Module() {
        return p_Module;
    }

    public void setP_Module(String p_Module) {
        this.p_Module = p_Module;
    }

    public boolean isYetki_C() {
        return yetki_C;
    }

    public void setYetki_C(boolean yetki_C) {
        this.yetki_C = yetki_C;
    }

    public boolean isYetki_R() {
        return yetki_R;
    }

    public void setYetki_R(boolean yetki_R) {
        this.yetki_R = yetki_R;
    }

    public boolean isYetki_U() {
        return yetki_U;
    }

    public void setYetki_U(boolean yetki_U) {
        this.yetki_U = yetki_U;
    }

    public boolean isYetki_D() {
        return yetki_D;
    }

    public void setYetki_D(boolean yetki_D) {
        this.yetki_D = yetki_D;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.privilege_Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Privilege other = (Privilege) obj;
        return Objects.equals(this.privilege_Id, other.privilege_Id);
    }


}
