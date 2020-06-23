/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.List;
import java.util.Objects;

public class Ozellik {

    private Long ozellik_id;
    private String ozellik_adi;
    private List<Otel> oteller;

    public Ozellik() {
    }

    public Long getOzellik_id() {
        return ozellik_id;
    }

    public void setOzellik_id(Long ozellik_id) {
        this.ozellik_id = ozellik_id;
    }

    public String getOzellik_adi() {
        return ozellik_adi;
    }

    public void setOzellik_adi(String ozellik_adi) {
        this.ozellik_adi = ozellik_adi;
    }

    public List<Otel> getOteller() {
        return oteller;
    }

    public void setOteller(List<Otel> oteller) {
        this.oteller = oteller;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ozellik_id);
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
        final Ozellik other = (Ozellik) obj;
        if (!Objects.equals(this.ozellik_id, other.ozellik_id)) {
            return false;
        }
        return true;
    }

}
