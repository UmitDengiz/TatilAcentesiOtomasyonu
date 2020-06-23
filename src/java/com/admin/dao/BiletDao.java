/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Bilet;
import com.admin.entity.Kampanya;
import com.admin.entity.User;
import com.admin.utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BiletDao implements DAO<Bilet> {

    ConnectionManager cm = ConnectionManager.getInstance();

    @Override
    public void create(Bilet o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into bilet(tutar,nereden,nereye,yontem) values(?,?,?,?)");
            pst.setInt(1, o.getTutar());
            pst.setString(2, o.getNereden());
            pst.setString(3, o.getNereye());
            pst.setString(4, o.getYontem());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Bilet o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update bilet set tutar=?,nereden=?,nereye=?,yontem=? where bilet_id=?");
            pst.setInt(1, o.getTutar());
            pst.setString(2, o.getNereden());
            pst.setString(3, o.getNereye());
            pst.setString(4, o.getYontem());
            pst.setLong(5, o.getBilet_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Bilet> list() {
        ArrayList dataList = new ArrayList();
        Bilet tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from bilet");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Bilet();
                tmp.setBilet_id(rs.getLong("bilet_id"));
                tmp.setYontem(rs.getString("yontem"));
                tmp.setNereden(rs.getString("nereden"));
                tmp.setNereye(rs.getString("nereye"));
                tmp.setTutar(rs.getInt("tutar"));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public List<Bilet> pagedList(int page, int count) {
        ArrayList dataList = new ArrayList();
        int start = (page - 1) * count;
        Bilet tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from bilet order by bilet_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Bilet();
                tmp.setBilet_id(rs.getLong("bilet_id"));
                tmp.setYontem(rs.getString("yontem"));
                tmp.setNereden(rs.getString("nereden"));
                tmp.setNereye(rs.getString("nereye"));
                tmp.setTutar(rs.getInt("tutar"));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    @Override
    public void delete(Bilet o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from bilet where bilet_id=?");
            pst.setLong(1, o.getBilet_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Bilet detail(Long id) {
        Bilet tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from bilet where bilet_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Bilet();
                tmp.setBilet_id(rs.getLong("bilet_id"));
                tmp.setYontem(rs.getString("yontem"));
                tmp.setNereden(rs.getString("nereden"));
                tmp.setNereye(rs.getString("nereye"));
                tmp.setTutar(rs.getInt("tutar"));
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
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select count(bilet_id) as bilet_count from bilet;");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bilet_count");
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return count;
    }

    public void createBiletUser(Bilet l, User u) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst1 = con.prepareStatement("insert into bilet_satis(user_id,bilet_id) values(?,?)");
            pst1.setLong(1, u.getId());
            pst1.setLong(2, l.getBilet_id());
            pst1.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

}
