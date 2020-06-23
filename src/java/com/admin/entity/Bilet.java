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
public class Bilet {

    private Long bilet_id;
    private int tutar;
    private String nereden;
    private String nereye;
    private String yontem;
    private List<User> userList;

    public Bilet() {
    }

    public Bilet(Long bilet_id, int tutar, String nereden, String nereye, String yontem) {
        this.bilet_id = bilet_id;
        this.tutar = tutar;
        this.nereden = nereden;
        this.nereye = nereye;
        this.yontem = yontem;
    }

    public Long getBilet_id() {
        return bilet_id;
    }

    public void setBilet_id(Long bilet_id) {
        this.bilet_id = bilet_id;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public String getNereden() {
        return nereden;
    }

    public void setNereden(String nereden) {
        this.nereden = nereden;
    }

    public String getNereye() {
        return nereye;
    }

    public void setNereye(String nereye) {
        this.nereye = nereye;
    }

    public String getYontem() {
        return yontem;
    }

    public void setYontem(String yontem) {
        this.yontem = yontem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.bilet_id);
        return hash;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        final Bilet other = (Bilet) obj;
        if (!Objects.equals(this.bilet_id, other.bilet_id)) {
            return false;
        }
        return true;
    }

}
