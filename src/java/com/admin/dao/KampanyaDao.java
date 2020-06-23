/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Kampanya;
import com.admin.entity.User;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KampanyaDao implements DAO<Kampanya> {

    ConnectionManager cm = ConnectionManager.getInstance();

    @Override
    public void create(Kampanya o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into kampanya(isim,ucret,tur) values(?,?,?)");
            pst.setString(1, o.getKampanya_adi());
            pst.setInt(2, o.getUcret());
            pst.setString(3, o.getTur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Kampanya o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update kampanya set isim=?,ucret=?,tur=? where kampanya_id=?");
            pst.setString(1, o.getKampanya_adi());
            pst.setInt(2, o.getUcret());
            pst.setString(3, o.getTur());
            pst.setLong(4, o.getKampanya_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Kampanya> list() {
        ArrayList dataList = new ArrayList();
        Kampanya tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from kampanya");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kampanya();
                tmp.setKampanya_id(rs.getLong("kampanya_id"));
                tmp.setKampanya_adi(rs.getString("isim"));
                tmp.setTur(rs.getString("tur"));
                tmp.setUcret(rs.getInt("ucret"));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public List<Kampanya> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Kampanya tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from kampanya order by kampanya_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Kampanya();
                tmp.setKampanya_id(rs.getLong("kampanya_id"));
                tmp.setKampanya_adi(rs.getString("isim"));
                tmp.setTur(rs.getString("tur"));
                tmp.setUcret(rs.getInt("ucret"));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Kampanya o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from kampanya where kampanya_id=?");
            pst.setLong(1, o.getKampanya_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Kampanya detail(Long id) {
        Kampanya tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from kampanya where kampanya_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Kampanya();
                tmp.setKampanya_id(rs.getLong("kampanya_id"));
                tmp.setKampanya_adi(rs.getString("isim"));
                tmp.setTur(rs.getString("tur"));
                tmp.setUcret(rs.getInt("ucret"));
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return tmp;
    }

    @Override
    public int itemCount() {
        int count = 0;
        String modul = "kampanya";
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select count(" + modul + "_id) as " + modul + "_count from " + modul);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(modul + "_count");
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return count;
    }

    public void createKampanyaUser(Kampanya module, User user) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst1 = con.prepareStatement("insert into user_kampanya(user_id,kampanya_id) values(?,?)");
            pst1.setLong(1, user.getId());
            pst1.setLong(2, module.getKampanya_id());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

}
