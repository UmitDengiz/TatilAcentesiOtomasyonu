/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.entity;

import java.util.Objects;

/**
 *
 * @author Cypher
 */
public class Yorum {

    private Long yorum_id;
    private String yorumunuz;
    private Otel otel;
    private User user;

    public Yorum() {
    }

    public Yorum(Long yorum_id, String yorumunuz, Otel otel, User user) {
        this.yorum_id = yorum_id;
        this.yorumunuz = yorumunuz;
        this.otel = otel;
        this.user = user;
    }

    public Long getYorum_id() {
        return yorum_id;
    }

    public void setYorum_id(Long yorum_id) {
        this.yorum_id = yorum_id;
    }

    public String getYorumunuz() {
        return yorumunuz;
    }

    public void setYorumunuz(String yorumunuz) {
        this.yorumunuz = yorumunuz;
    }

    public Otel getOtel() {
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
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
        hash = 83 * hash + Objects.hashCode(this.yorum_id);
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
        final Yorum other = (Yorum) obj;
        return Objects.equals(this.yorum_id, other.yorum_id);
    }

}
