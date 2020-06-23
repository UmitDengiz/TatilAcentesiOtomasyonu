/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Dosya;
import com.admin.utility.ConnectionManager;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DosyaDao {

    ConnectionManager cm = ConnectionManager.getInstance();
    private final String uploadPath = "C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files\\";

    public Long create(Dosya o) {
        Long dosya_id = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into dosya(name,path,type) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, o.getName());
            pst.setString(2, o.getPath());
            pst.setString(3, o.getType());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                dosya_id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dosya_id;
    }

    public Dosya detail(Long id) {
        Dosya tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from dosya where dosya_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Dosya();
                tmp.setFile_Id(rs.getLong("dosya_id"));
                tmp.setName(rs.getString("name"));
                tmp.setPath(rs.getString("path"));
                tmp.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return tmp;
    }

    public void delete(Dosya o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from dosya where dosya_id=?");
            pst.setLong(1, o.getFile_Id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void clearDataTur(Long tur_id) {
        Dosya dosya;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from tur where tur_id=?");
            pst.setLong(1, tur_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("dosya_id");
                if (id != null && id > 0) {
                    dosya = this.detail(id);
                    PreparedStatement pst1 = con.prepareStatement("update tur set dosya_id=? where tur_id=?");
                    pst1.setNull(1, java.sql.Types.INTEGER);
                    pst1.setLong(2, tur_id);
                    pst1.executeUpdate();
                    this.delete(dosya);
                    this.removeData(dosya.getName());
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }

    }

    public void clearDataOtel(Long otel_id) {
        Dosya dosya;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel where otel_id=?");
            pst.setLong(1, otel_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("dosya_id");
                if (id != null && id > 0) {
                    dosya = this.detail(id);
                    PreparedStatement pst1 = con.prepareStatement("update tur set dosya_id=? where otel_id=?");
                    pst1.setNull(1, java.sql.Types.INTEGER);
                    pst1.setLong(2, otel_id);
                    pst1.executeUpdate();
                    this.delete(dosya);
                    this.removeData(dosya.getName());
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    private void removeData(String name) {
        try {
            String path = this.uploadPath + name;
            File file = new File(path);
            if (file.delete()) {
                System.out.println(path + " File deleted");
            } else {
                System.out.println("File " + path + " doesn't exist");
            }
        } catch (Exception ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);

        }

    }

}
