/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Privilege;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.admin.utility.ConnectionManager;

public class PrivilegeDao implements DAO<Privilege> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        if (this.roleDao == null) {
            this.roleDao = new RoleDao();
        }
        return roleDao;
    }

    @Override
    public void create(Privilege o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into privilege(p_module,yetki_c,yetki_r,yetki_u,yetki_d,role_id) values(?,?,?,?,?,?)");
            pst.setString(1, o.getP_Module());
            pst.setBoolean(2, o.isYetki_C());
            pst.setBoolean(3, o.isYetki_R());
            pst.setBoolean(4, o.isYetki_U());
            pst.setBoolean(5, o.isYetki_D());
            pst.setLong(6, o.getRole().getRole_Id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void updateOne(Privilege o, String s) {
        try (Connection con = cm.getConnection()) {
            String modul = "";
            switch (s) {
                case "C":
                    modul = "yetki_c";
                    break;
                case "R":
                    modul = "yetki_r";
                    break;
                case "U":
                    modul = "yetki_u";
                    break;
                case "D":
                    modul = "yetki_d";
                    break;
            }
            PreparedStatement pst = con.prepareStatement("update privilege set " + modul + "=? where privilege_id=?");
            switch (s) {
                case "C":
                    pst.setBoolean(1, !o.isYetki_C());
                    break;
                case "R":
                    pst.setBoolean(1, !o.isYetki_R());
                    break;
                case "U":
                    pst.setBoolean(1, !o.isYetki_U());
                    break;
                case "D":
                    pst.setBoolean(1, !o.isYetki_D());
                    break;
            }
            pst.setLong(2, o.getPrivilege_Id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Privilege> list() {
        ArrayList privilegeList = new ArrayList();
        Privilege tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from privilege");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Privilege();
                tmp.setPrivilege_Id(rs.getLong("privilege_id"));
                tmp.setP_Module(rs.getString("p_module"));
                tmp.setYetki_C(rs.getBoolean("yetki_c"));
                tmp.setYetki_R(rs.getBoolean("yetki_r"));
                tmp.setYetki_U(rs.getBoolean("yetki_u"));
                tmp.setYetki_D(rs.getBoolean("yetki_d"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                privilegeList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return privilegeList;
    }

    @Override
    public List<Privilege> pagedList(int page, int count) {
        ArrayList privilegeList = new ArrayList();
        int start = (page - 1) * count;
        Privilege tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from privilege order by privilege_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Privilege();
                tmp.setPrivilege_Id(rs.getLong("privilege_id"));
                tmp.setP_Module(rs.getString("p_module"));
                tmp.setYetki_C(rs.getBoolean("yetki_c"));
                tmp.setYetki_R(rs.getBoolean("yetki_r"));
                tmp.setYetki_U(rs.getBoolean("yetki_u"));
                tmp.setYetki_D(rs.getBoolean("yetki_d"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                privilegeList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return privilegeList;
    }

    @Override
    public void delete(Privilege o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from privilege where privilege_id=?");
            pst.setLong(1, o.getPrivilege_Id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Privilege detail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Privilege o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int itemCount() {
        int count = 0;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select count(privilege_id) as privilege_count from privilege;");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("privilege_count");
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return count;
    }

}
