/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Tur {
    private Long tur_id;
    private Date bas_tarih;
    private Date son_tarih;
    private String guzergah;
    private int kapasite;
    private int ucret;
    private Dosya dosya;
    private List<Rezervasyon> rezervasyonList;

    public Tur() {
    }

    public Long getTur_id() {
        return tur_id;
    }

    public void setTur_id(Long tur_id) {
        this.tur_id = tur_id;
    }

    public Date getBas_tarih() {
        return bas_tarih;
    }

    public void setBas_tarih(Date bas_tarih) {
        this.bas_tarih = bas_tarih;
    }

    public Date getSon_tarih() {
        return son_tarih;
    }

    public void setSon_tarih(Date son_tarih) {
        this.son_tarih = son_tarih;
    }

    public String getGuzergah() {
        return guzergah;
    }

    public void setGuzergah(String guzergah) {
        this.guzergah = guzergah;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public Dosya getDosya() {
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public List<Rezervasyon> getRezervasyonList() {
        return rezervasyonList;
    }

    public void setRezervasyonList(List<Rezervasyon> rezervasyonList) {
        this.rezervasyonList = rezervasyonList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.tur_id);
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
        final Tur other = (Tur) obj;
        return Objects.equals(this.tur_id, other.tur_id);
    }
}
