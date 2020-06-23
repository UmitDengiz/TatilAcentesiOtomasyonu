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
public class Dosya {
    private Long file_Id;
    private String name;
    private String path;
    private String type;

    public Dosya() {
    }

    public Long getFile_Id() {
        return file_Id;
    }

    public void setFile_Id(Long file_Id) {
        this.file_Id = file_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.file_Id);
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
        final Dosya other = (Dosya) obj;
        return Objects.equals(this.file_Id, other.file_Id);
    }
    
    
    
}
