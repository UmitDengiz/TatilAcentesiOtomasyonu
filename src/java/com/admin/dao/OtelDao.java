/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Otel;
import com.admin.entity.Ozellik;
import com.admin.entity.Yorum;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OtelDao implements DAO<Otel> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private DosyaDao dosyaDao;
    private OzellikDao ozellikDao;
    private UserDao userDao;

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public OzellikDao getOzellikDao() {
        if (this.ozellikDao == null) {
            this.ozellikDao = new OzellikDao();
        }
        return ozellikDao;
    }

    public DosyaDao getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDao();
        }
        return dosyaDao;
    }

    @Override
    public void create(Otel o) {
        try (Connection con = cm.getConnection()) {
            if (o.getDosya() != null && o.getDosya().getFile_Id() != null) {
                PreparedStatement pst = con.prepareStatement("insert into otel(isim,adres,dosya_id) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, o.getName());
                pst.setString(2, o.getAddress());
                pst.setLong(3, o.getDosya().getFile_Id());
                pst.executeUpdate();
                Long otel_id = null;
                ResultSet gk = pst.getGeneratedKeys();
                if (gk.next()) {
                    otel_id = gk.getLong(1);
                }
                o.setOtel_id(otel_id);
                this.addOtelOzellik(o);

            } else {
                PreparedStatement pst = con.prepareStatement("insert into otel(isim,adres) values(?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, o.getName());
                pst.setString(2, o.getAddress());
                pst.executeUpdate();
                Long otel_id = null;
                ResultSet gk = pst.getGeneratedKeys();
                if (gk.next()) {
                    otel_id = gk.getLong(1);
                }
                o.setOtel_id(otel_id);
                this.addOtelOzellik(o);

            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void addOtelOzellik(Otel o) {
        try (Connection con = cm.getConnection()) {
            if (o.getOtelOzellikler() != null) {
                for (Ozellik l : o.getOtelOzellikler()) {
                    PreparedStatement pst1 = con.prepareStatement("insert into otel_ozellik(otel_id,ozellik_id) values(?,?)");
                    pst1.setLong(1, o.getOtel_id());
                    pst1.setLong(2, l.getOzellik_id());
                    pst1.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void deleteOtelOzellik(Otel o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from otel_ozellik where otel_id=?");
            pst.setLong(1, o.getOtel_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Otel o) {
        try (Connection con = cm.getConnection()) {
            if (o.getDosya() != null && o.getDosya().getFile_Id() != null) {
                PreparedStatement pst = con.prepareStatement("update otel set isim=?,adres=?,dosya_id=? where otel_id=?");
                pst.setString(1, o.getName());
                pst.setString(2, o.getAddress());
                pst.setLong(3, o.getDosya().getFile_Id());
                pst.setLong(4, o.getOtel_id());
                pst.executeUpdate();
            } else {
                PreparedStatement pst = con.prepareStatement("update otel set isim=?,adres=? where otel_id=?");
                pst.setString(1, o.getName());
                pst.setString(2, o.getAddress());
                pst.setLong(3, o.getOtel_id());
                pst.executeUpdate();
            }
            this.deleteOtelOzellik(o);
            this.addOtelOzellik(o);

        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Otel> list() {
        ArrayList dataList = new ArrayList();
        Otel tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Otel();
                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setName(rs.getString("isim"));
                tmp.setAddress(rs.getString("adres"));
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
    public List<Otel> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Otel tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel order by otel_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Otel();
                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setName(rs.getString("isim"));
                tmp.setAddress(rs.getString("adres"));
                tmp.setDosya(this.getDosyaDao().detail(rs.getLong("dosya_id")));
                tmp.setOtelOzellikler(this.otelOzelliklerList(rs.getLong("otel_id")));
                tmp.setOtelYorumlar(this.otelYorumlar(rs.getLong("otel_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Otel o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from otel where otel_id=?");
            pst.setLong(1, o.getOtel_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Otel detail(Long id) {
        Otel tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel where otel_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Otel();
                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setName(rs.getString("isim"));
                tmp.setAddress(rs.getString("adres"));
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
        String modul = "otel";
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

    private List<Ozellik> otelOzelliklerList(long otel_id) {
        ArrayList dataList = new ArrayList();
        Ozellik tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel_ozellik where otel_id=?");
            pst.setLong(1, otel_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Ozellik();
                Long ozellik_id = rs.getLong("ozellik_id");
                tmp = this.getOzellikDao().detail(ozellik_id);
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    private List<Yorum> otelYorumlar(long aLong) {
        ArrayList dataList = new ArrayList();
        Yorum tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from yorum where otel_id=?");
            pst.setLong(1, aLong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Yorum();
                tmp.setYorum_id(rs.getLong("yorum_id"));
                tmp.setYorumunuz(rs.getString("yorum"));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    public Otel detailFrontend(Long otel_id) {
        Otel tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from otel where otel_id=?");
            pst.setLong(1, otel_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Otel();
                tmp.setOtel_id(rs.getLong("otel_id"));
                tmp.setName(rs.getString("isim"));
                tmp.setAddress(rs.getString("adres"));
                tmp.setDosya(this.getDosyaDao().detail(rs.getLong("dosya_id")));
                tmp.setOtelOzellikler(this.otelOzelliklerList(rs.getLong("otel_id")));
                tmp.setOtelYorumlar(this.otelYorumlar(rs.getLong("otel_id")));
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return tmp;
    }

}
