/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Otel;
import com.admin.entity.Ozellik;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OzellikDao implements DAO<Ozellik> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private OtelDao otelDao;

    public OtelDao getOtelDao() {
        if (this.otelDao == null) {
            this.otelDao = new OtelDao();
        }
        return otelDao;
    }

    @Override
    public void create(Ozellik o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into ozellik(isim) values(?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, o.getOzellik_adi());
            pst.executeUpdate();
            Long ozellik_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                ozellik_id = gk.getLong(1);
            }
            o.setOzellik_id(ozellik_id);
            this.deleteOzellikOtel(o);
            this.addOzellikOtel(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    private void addOzellikOtel(Ozellik o) {
        try (Connection con = cm.getConnection()) {
            if (o.getOteller() != null) {
                for (Otel l : o.getOteller()) {
                    PreparedStatement pst = con.prepareStatement("insert into otel_ozellik(otel_id,ozellik_id) values(?,?)");
                    pst.setLong(1, l.getOtel_id());
                    pst.setLong(2, o.getOzellik_id());
                    pst.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void deleteOzellikOtel(Ozellik o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from otel_ozellik where ozellik_id=?");
            pst.setLong(1, o.getOzellik_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Ozellik o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update ozellik set isim=? where ozellik_id=?");
            pst.setString(1, o.getOzellik_adi());
            pst.setLong(2, o.getOzellik_id());
            pst.executeUpdate();
            this.deleteOzellikOtel(o);
            this.addOzellikOtel(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Ozellik> list() {
        ArrayList dataList = new ArrayList();
        Ozellik tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from ozellik");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Ozellik();
                tmp.setOzellik_id(rs.getLong("ozellik_id"));
                tmp.setOzellik_adi(rs.getString("isim"));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public List<Ozellik> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Ozellik tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from ozellik order by ozellik_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Ozellik();
                tmp.setOzellik_id(rs.getLong("ozellik_id"));
                tmp.setOzellik_adi(rs.getString("isim"));
                tmp.setOteller(this.ozellikOtellerList(rs.getLong("ozellik_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Ozellik o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from ozellik where ozellik_id=?");
            pst.setLong(1, o.getOzellik_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Ozellik detail(Long id) {
        Ozellik tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from ozellik where ozellik_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Ozellik();
                tmp.setOzellik_id(rs.getLong("ozellik_id"));
                tmp.setOzellik_adi(rs.getString("isim"));
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
        String modul = "ozellik";
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

    private List<Otel> ozellikOtellerList(long ozellik_id) {
        ArrayList dataList = new ArrayList();
        Otel tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select otel_id from otel_ozellik where ozellik_id=?");
            pst.setLong(1, ozellik_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long otel_id = rs.getLong("otel_id");
                tmp = this.getOtelDao().detail(otel_id);
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

}
