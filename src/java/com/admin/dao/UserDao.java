/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.entity.Bilet;
import com.admin.entity.Kampanya;
import com.admin.entity.Rezervasyon;
import com.admin.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.admin.utility.ConnectionManager;
import java.sql.Statement;

public class UserDao implements DAO<User> {

    ConnectionManager cm = ConnectionManager.getInstance();
    private RoleDao roleDao;
    private KampanyaDao kampanyaDao;
    private TurDao turDao;
    private BiletDao biletDao;

    public BiletDao getBiletDao() {
        if (this.biletDao == null) {
            this.biletDao = new BiletDao();
        }
        return biletDao;
    }

    public TurDao getTurDao() {
        if (this.turDao == null) {
            this.turDao = new TurDao();
        }
        return turDao;
    }

    public KampanyaDao getKampanyaDao() {
        if (this.kampanyaDao == null) {
            this.kampanyaDao = new KampanyaDao();
        }
        return kampanyaDao;
    }

    public RoleDao getRoleDao() {
        if (this.roleDao == null) {
            this.roleDao = new RoleDao();
        }
        return roleDao;
    }

    @Override
    public void create(User o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into user (name_surname,password,email,role_id,phone) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, o.getName_Surname());
            pst.setString(2, o.getPassword());
            pst.setString(3, o.getEmail());
            pst.setLong(4, o.getRole().getRole_Id());
            pst.setString(5, o.getPhone());
            pst.executeUpdate();
            Long id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong(1);
            }
            o.setId(id);
            this.addKampanyaUser(o);
            this.addBiletUser(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    private void addBiletUser(User o) {
        try (Connection con = cm.getConnection()) {
            if (o.getBiletList() != null) {
                for (Bilet l : o.getBiletList()) {
                    PreparedStatement pst1 = con.prepareStatement("insert into bilet_satis(user_id,bilet_id) values(?,?)");
                    pst1.setLong(1, o.getId());
                    pst1.setLong(2, l.getBilet_id());
                    pst1.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void deleteBiletUser(User o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from bilet_satis where user_id=?");
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    private void addKampanyaUser(User o) {
        try (Connection con = cm.getConnection()) {
            if (o.getKampanyaList() != null) {
                for (Kampanya l : o.getKampanyaList()) {
                    PreparedStatement pst1 = con.prepareStatement("insert into user_kampanya(user_id,kampanya_id) values(?,?)");
                    pst1.setLong(1, o.getId());
                    pst1.setLong(2, l.getKampanya_id());
                    pst1.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    public void deleteUserKampanya(User o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from user_kampanya where user_id=?");
            pst.setLong(1, o.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public void update(User o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("update user set email=?,password=?,name_surname=?,role_id=?,phone=? where user_id=?");
            pst.setString(1, o.getEmail());
            pst.setString(2, o.getPassword());
            pst.setString(3, o.getName_Surname());
            pst.setLong(4, o.getRole().getRole_Id());
            pst.setString(5, o.getPhone());
            pst.setLong(6, o.getId());
            pst.executeUpdate();

            this.deleteUserKampanya(o);
            this.addKampanyaUser(o);
            this.deleteBiletUser(o);
            this.addBiletUser(o);
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public ArrayList<User> list() {
        ArrayList userList = new ArrayList();
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                userList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return userList;
    }

    @Override
    public List<User> pagedList(int page, int count) {
        ArrayList userList = new ArrayList();
        int start = (page - 1) * count;
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user order by user_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                tmp.setKampanyaList(this.userKampanyaList(rs.getLong("user_id")));
                tmp.setRezervasyonList(this.userRezervasyonList(rs.getLong("user_id")));
                tmp.setBiletList(this.userBiletList(rs.getLong("user_id")));
                userList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return userList;
    }

    @Override
    public void delete(User o) {
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("delete from user where user_id=?");
            pst.setLong(1, o.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

    @Override
    public User detail(Long id) {
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user where user_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return tmp;
    }

    public User detailFrontend(Long id) {
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user where user_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPhone(rs.getString("phone"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                tmp.setKampanyaList(this.userKampanyaList(rs.getLong("user_id")));
                tmp.setRezervasyonList(this.userRezervasyonList(rs.getLong("user_id")));
                tmp.setBiletList(this.userBiletList(rs.getLong("user_id")));
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return tmp;
    }

    public User login(User u) {
        User tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user where email=? and password=?");
            pst.setString(1, u.getEmail());
            pst.setString(2, u.getPassword());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new User();
                tmp.setId(rs.getLong("user_id"));
                tmp.setName_Surname(rs.getString("name_surname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setEmail(rs.getString("email"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                tmp.setKampanyaList(this.userKampanyaList(rs.getLong("user_id")));
                tmp.setRezervasyonList(this.userRezervasyonList(rs.getLong("user_id")));
                tmp.setBiletList(this.userBiletList(rs.getLong("user_id")));
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
            PreparedStatement pst = con.prepareStatement("select count(user_id) as user_count from user;");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("user_count");
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return count;
    }

    private List<Kampanya> userKampanyaList(long user_id) {
        ArrayList dataList = new ArrayList();
        Kampanya tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from user_kampanya where user_id=?");
            pst.setLong(1, user_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("kampanya_id");
                tmp = this.getKampanyaDao().detail(id);
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    private List<Rezervasyon> userRezervasyonList(long aLong) {
        ArrayList dataList = new ArrayList();
        Rezervasyon tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from rezervasyon where user_id=?");
            pst.setLong(1, aLong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Rezervasyon();
                tmp.setRezervasyon_id(rs.getLong("rezervasyon_id"));
                tmp.setTarih(rs.getDate("tarih"));
                tmp.setTur(this.getTurDao().detail(rs.getLong("tur_id")));
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    private List<Bilet> userBiletList(long aLong) {
        ArrayList dataList = new ArrayList();
        Bilet tmp = null;
        try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from bilet_satis where user_id=?");
            pst.setLong(1, aLong);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("bilet_id");
                tmp = this.getBiletDao().detail(id);
                dataList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
        return dataList;
    }

    public void createFrontendUser(User o) {
         try (Connection con = cm.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into user (name_surname,password,email,phone) values(?,?,?,?)");
            pst.setString(1, o.getName_Surname());
            pst.setString(2, o.getPassword());
            pst.setString(3, o.getEmail());
            pst.setString(4, o.getPhone());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(this.getClass().getSimpleName() + " " + new Object() {
            }.getClass().getEnclosingMethod().getName() + " error = " + ex);
        }
    }

}
