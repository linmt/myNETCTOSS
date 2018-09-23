package dao.cost;

import entity.Admin;
import util.DBUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 热带雨林 on 2018/9/24.
 */
public class AdminDAOImpl implements AdminDAO,Serializable{
    public Admin findByCode(String admin_code) throws Exception {
        String sql = "select * from ADMIN_INFO where admin_code=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin_code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return createAdmin(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("查询管理员失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return null;
    }

    private Admin createAdmin(ResultSet rs) throws SQLException {
        Admin a = new Admin();
        a.setAdmin_id(rs.getInt("admin_id"));
        a.setAdmin_code(rs.getString("admin_code"));
        a.setPassword(rs.getString("password"));
        a.setName(rs.getString("name"));
        a.setTelephone(rs.getString("telephone"));
        a.setEmail(rs.getString("email"));
        a.setEnrolldate(rs.getTimestamp("enrolldate"));
        return a;
    }

    public static void main(String[] args) throws Exception {
        AdminDAO dao = new AdminDAOImpl();
        Admin a = dao.findByCode("caocao");
        System.out.println(a.getAdmin_code());
    }
}
