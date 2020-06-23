/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Tur;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurDao implements DAO<Tur> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private DosyaDao dosyaDao;

    public DosyaDao getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDao();
        }
        return dosyaDao;
    }

    @Override
    public void create(Tur o) {
        try (Connection con = cm.getConnection()) {
            if (o.getDosya() != null && o.getDosya().getFile_Id() != null) {
                PreparedStatement pst = con.prepareStatement("insert into tur(guzergah,kapasite,bas_tarih,son_tarih,ucret,dosya_id) values(?,?,?,?,?,?)");
                pst.setString(1, o.getGuzergah());
                pst.setInt(2, o.getKapasite());
                pst.setDate(3, new Date(o.getBas_tarih().getTime()));
                pst.setDate(4, new Date(o.getSon_tarih().getTime()));
                pst.setInt(5, o.getUcret());
                pst.setLong(6, o.getDosya().getFile_Id());
                pst.executeUpdate();
            } else {
                PreparedStatement pst = con.prepareStatement("insert into tur(guzergah,kapasite,bas_tarih,son_tarih,ucret) values(?,?,?,?,?)");
                pst.setString(1, o.getGuzergah());
                pst.setInt(2, o.getKapasite());
                pst.setDate(3, new Date(o.getBas_tarih().getTime()));
                pst.setDate(4, new Date(o.getSon_tarih().getTime()));
                pst.setInt(5, o.getUcret());
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Tur o) {
        try (Connection con = cm.getConnection()) {
            if (o.getDosya() != null && o.getDosya().getFile_Id() != null) {
                PreparedStatement pst = con.prepareStatement("update tur set guzergah=?,kapasite=?,bas_tarih=?,son_tarih=?,ucret=?,dosya_id=? where tur_id=?");
                pst.setString(1, o.getGuzergah());
                pst.setInt(2, o.getKapasite());
                pst.setDate(3, new Date(o.getBas_tarih().getTime()));
                pst.setDate(4, new Date(o.getSon_tarih().getTime()));
                pst.setInt(5, o.getUcret());
                pst.setLong(6, o.getDosya().getFile_Id());
                pst.setLong(7, o.getTur_id());
                pst.executeUpdate();
            } else {
                PreparedStatement pst = con.prepareStatement("update tur set guzergah=?,kapasite=?,bas_tarih=?,son_tarih=?,ucret=? where tur_id=?");
                pst.setString(1, o.getGuzergah());
                pst.setInt(2, o.getKapasite());
                pst.setDate(3, new Date(o.getBas_tarih().getTime()));
                pst.setDate(4, new Date(o.getSon_tarih().getTime()));
                pst.setInt(5, o.getUcret());
                pst.setLong(6, o.getTur_id());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Tur> list() {
        ArrayList dataList = new ArrayList();
        Tur tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from tur");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Tur();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setBas_tarih(rs.getDate("bas_tarih"));
                tmp.setSon_tarih(rs.getDate("son_tarih"));
                tmp.setGuzergah(rs.getString("guzergah"));
                tmp.setKapasite(rs.getInt("kapasite"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setDosya(this.getDosyaDao().detail(rs.getLong("dosya_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public List<Tur> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Tur tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from tur order by tur_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Tur();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setBas_tarih(rs.getDate("bas_tarih"));
                tmp.setSon_tarih(rs.getDate("son_tarih"));
                tmp.setGuzergah(rs.getString("guzergah"));
                tmp.setKapasite(rs.getInt("kapasite"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setDosya(this.getDosyaDao().detail(rs.getLong("dosya_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Tur o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from tur where tur_id=?");
            pst.setLong(1, o.getTur_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Tur detail(Long id) {
        Tur tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from tur where tur_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Tur();
                tmp.setTur_id(rs.getLong("tur_id"));
                tmp.setBas_tarih(rs.getDate("bas_tarih"));
                tmp.setSon_tarih(rs.getDate("son_tarih"));
                tmp.setGuzergah(rs.getString("guzergah"));
                tmp.setKapasite(rs.getInt("kapasite"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setDosya(this.getDosyaDao().detail(rs.getLong("dosya_id")));
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
        String modul = "tur";
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
