/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Rezervasyon;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonDao implements DAO<Rezervasyon> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private UserDao userDao;
    private TurDao turDao;

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public TurDao getTurDao() {
        if (this.turDao == null) {
            this.turDao = new TurDao();
        }
        return turDao;
    }

    @Override
    public void create(Rezervasyon o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into rezervasyon(tarih,tur_id,user_id) values(?,?,?)");
            pst.setDate(1, new Date(o.getTarih().getTime()));
            pst.setLong(2, o.getTur().getTur_id());
            pst.setLong(3, o.getUser().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Rezervasyon o) {

        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update rezervasyon set tarih=?,tur_id=?,user_id=? where rezervasyon_id=?");
            pst.setDate(1, new Date(o.getTarih().getTime()));
            pst.setLong(2, o.getTur().getTur_id());
            pst.setLong(3, o.getUser().getId());
            pst.setLong(4, o.getRezervasyon_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Rezervasyon> list() {
        ArrayList dataList = new ArrayList();
        Rezervasyon tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from rezervasyon");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Rezervasyon();
                tmp.setRezervasyon_id(rs.getLong("rezervasyon_id"));
                tmp.setTarih(rs.getDate("tarih"));
                tmp.setTur(this.getTurDao().detail(rs.getLong("tur_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public List<Rezervasyon> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Rezervasyon tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from rezervasyon order by rezervasyon_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Rezervasyon();
                tmp.setRezervasyon_id(rs.getLong("rezervasyon_id"));
                tmp.setTarih(rs.getDate("tarih"));
                tmp.setTur(this.getTurDao().detail(rs.getLong("tur_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Rezervasyon o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from rezervasyon where rezervasyon_id=?");
            pst.setLong(1, o.getRezervasyon_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Rezervasyon detail(Long id) {
        Rezervasyon tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from rezervasyon where rezervasyon_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Rezervasyon();
                tmp.setRezervasyon_id(rs.getLong("rezervasyon_id"));
                tmp.setTarih(rs.getDate("tarih"));
                tmp.setTur(this.getTurDao().detail(rs.getLong("tur_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
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
        String modul = "rezervasyon";
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

}
