package com.admin.entity;

import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private String password;
    private String name_Surname;
    private String phone;
    private Role role;
    private List<Kampanya> kampanyaList;
    private List<Bilet> biletList;
    private List<Rezervasyon> rezervasyonList;

    public User() {
    }

    public User(Long id, String email, String password, String name_Surname, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name_Surname = name_Surname;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName_Surname() {
        return name_Surname;
    }

    public void setName_Surname(String name_Surname) {
        this.name_Surname = name_Surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Kampanya> getKampanyaList() {
        return kampanyaList;
    }

    public void setKampanyaList(List<Kampanya> kampanyaList) {
        this.kampanyaList = kampanyaList;
    }

    public List<Bilet> getBiletList() {
        return biletList;
    }

    public void setBiletList(List<Bilet> biletList) {
        this.biletList = biletList;
    }

    public List<Rezervasyon> getRezervasyonList() {
        return rezervasyonList;
    }

    public void setRezervasyonList(List<Rezervasyon> rezervasyonList) {
        this.rezervasyonList = rezervasyonList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

}
