/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.List;
import java.util.Objects;

public class Otel {

    private Long otel_id;
    private String name;
    private String address;
    private Dosya dosya;
    private List<Ozellik> otelOzellikler;
    private List<Yorum> otelYorumlar;

    public Otel() {
    }

    public Otel(Long otel_id, String name, String address) {
        this.otel_id = otel_id;
        this.name = name;
        this.address = address;
    }

    public Long getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(Long otel_id) {
        this.otel_id = otel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Dosya getDosya() {
        return dosya;
    }

    public void setDosya(Dosya dosya) {
        this.dosya = dosya;
    }

    public List<Ozellik> getOtelOzellikler() {
        return otelOzellikler;
    }

    public void setOtelOzellikler(List<Ozellik> otelOzellikler) {
        this.otelOzellikler = otelOzellikler;
    }

    public List<Yorum> getOtelYorumlar() {
        return otelYorumlar;
    }

    public void setOtelYorumlar(List<Yorum> otelYorumlar) {
        this.otelYorumlar = otelYorumlar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.otel_id);
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
        final Otel other = (Otel) obj;
        return Objects.equals(this.otel_id, other.otel_id);
    }

}
