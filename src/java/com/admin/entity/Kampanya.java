/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Cypher
 */
public class Kampanya {

    private Long kampanya_id;
    private int ucret;
    private String tur;
    private String kampanya_adi;
    private List<User> userList;
    

    public Kampanya() {
    }

    public Long getKampanya_id() {
        return kampanya_id;
    }

    public void setKampanya_id(Long kampanya_id) {
        this.kampanya_id = kampanya_id;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getKampanya_adi() {
        return kampanya_adi;
    }

    public void setKampanya_adi(String kampanya_adi) {
        this.kampanya_adi = kampanya_adi;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.kampanya_id);
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
        final Kampanya other = (Kampanya) obj;
        if (!Objects.equals(this.kampanya_id, other.kampanya_id)) {
            return false;
        }
        return true;
    }

}
