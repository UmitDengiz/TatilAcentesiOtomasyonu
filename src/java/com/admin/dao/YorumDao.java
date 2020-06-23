/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Yorum;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YorumDao implements DAO<Yorum> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private UserDao userDao;
    private OtelDao otelDao;

    @Override
    public void create(Yorum o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into yorum(yorum,otel_id,user_id) values(?,?,?)");
            pst.setString(1, o.getYorumunuz());
            pst.setLong(2, o.getOtel().getOtel_id());
            pst.setLong(3, o.getUser().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Yorum o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update yorum set yorum=?,otel_id=?,user_id=? where yorum_id=?");
            pst.setString(1, o.getYorumunuz());
            pst.setLong(2, o.getOtel().getOtel_id());
            pst.setLong(3, o.getUser().getId());
            pst.setLong(4, o.getYorum_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Yorum> list() {
        ArrayList dataList = new ArrayList();
        Yorum tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from yorum");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Yorum();
                tmp.setYorum_id(rs.getLong("yorum_id"));
                tmp.setYorumunuz(rs.getString("yorum"));
                tmp.setOtel(this.getOtelDao().detail(rs.getLong("otel_id")));
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
    public List<Yorum> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Yorum tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from yorum order by yorum_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Yorum();
                tmp.setYorum_id(rs.getLong("yorum_id"));
                tmp.setYorumunuz(rs.getString("yorum"));
                tmp.setOtel(this.getOtelDao().detail(rs.getLong("otel_id")));
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
    public void delete(Yorum o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from yorum where yorum_id=?");
            pst.setLong(1, o.getYorum_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Yorum detail(Long id) {

        Yorum tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from yorum where yorum_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Yorum();
                tmp.setYorum_id(rs.getLong("yorum_id"));
                tmp.setYorumunuz(rs.getString("yorum"));
                tmp.setOtel(this.getOtelDao().detail(rs.getLong("otel_id")));
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
        String modul = "yorum";
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

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public OtelDao getOtelDao() {
        if (this.otelDao == null) {
            this.otelDao = new OtelDao();
        }
        return otelDao;
    }

}
