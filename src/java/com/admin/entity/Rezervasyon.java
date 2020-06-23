/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.Date;
import java.util.Objects;

public class Rezervasyon {
    private Long rezervasyon_id;
    private Date tarih;
    private Tur tur;
    private User user;

    public Rezervasyon() {
    }

    
    
    public Long getRezervasyon_id() {
        return rezervasyon_id;
    }

    public void setRezervasyon_id(Long rezervasyon_id) {
        this.rezervasyon_id = rezervasyon_id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public Tur getTur() {
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.rezervasyon_id);
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
        final Rezervasyon other = (Rezervasyon) obj;
        return Objects.equals(this.rezervasyon_id, other.rezervasyon_id);
    }
    
    
}
