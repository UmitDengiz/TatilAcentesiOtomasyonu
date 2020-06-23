package com.admin.dao;

import com.admin.entity.Role;
import com.admin.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.admin.utility.ConnectionManager;
import java.sql.Statement;

public class RoleDao implements DAO<Role> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private UserDao userDao;

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    @Override
    public void create(Role o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into role(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, o.getRole_Name());
            pst.executeUpdate();
            Long id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong(1);
            }
            o.setRole_Id(id);
            this.updateUserRole(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void updateUserRole(Role o) {
        try (Connection con = cm.getConnection()) {
            if (o.getUserList() != null) {
                for (User l : o.getUserList()) {
                    PreparedStatement pst1 = con.prepareStatement("update user set role_id=? where user_id=?");
                    pst1.setLong(1, o.getRole_Id());
                    pst1.setLong(2, l.getId());
                    pst1.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(Role o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update role set name=? where role_id=?", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, o.getRole_Name());
            pst.setLong(2, o.getRole_Id());
            pst.executeUpdate();
            this.updateUserRole(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<Role> list() {
        ArrayList roleList = new ArrayList();
        Role tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from role");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Role();
                tmp.setRole_Id(rs.getLong("role_id"));
                tmp.setRole_Name(rs.getString("name"));
                roleList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return roleList;
    }

    @Override
    public List<Role> pagedList(int page, int count) {
        ArrayList roleList = new ArrayList();
        int start = (page - 1) * count;
        Role tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from role order by role_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Role();
                tmp.setRole_Id(rs.getLong("role_id"));
                tmp.setRole_Name(rs.getString("name"));
                tmp.setUserList(this.roleUserList(rs.getLong("role_id")));
                roleList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return roleList;
    }

    @Override
    public void delete(Role o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from role where role_id=?");
            pst.setLong(1, o.getRole_Id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public Role detail(Long id) {
        Role tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from role where role_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Role();
                tmp.setRole_Id(rs.getLong("role_id"));
                tmp.setRole_Name(rs.getString("name"));
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
        String modul = "role";
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

    private List<User> roleUserList(long aLong) {
        ArrayList userList = new ArrayList();
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user where role_id=?");
            pst.setLong(1, aLong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                userList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return userList;
    }
}
