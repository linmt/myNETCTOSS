package dao.cost;

import entity.Cost;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class CostDAOImpl implements CostDAO{
    public List<Cost> findAll() throws RuntimeException {
        List<Cost> list = new ArrayList<Cost>();
        String sql = "select * from COST order by cost_id";
        Connection conn = DBUtil.getConnection();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Cost c = createCost(rs);
                list.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("查询资费数据失败",e);
        }finally{
            DBUtil.closeConnection();
        }
        return list;
    }

    public void save(Cost cost) throws Exception {
        if(cost == null){
            return;
        }
        String sql = "insert into cost values(cost_seq.nextval,?,?,?,?,'1',?,sysdate,null,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement prep;
        try {
            //设置不自动提交
            conn.setAutoCommit(false);
            prep = conn.prepareStatement(sql);
            prep.setString(1, cost.getName());
            prep.setObject(2, cost.getBase_duration());
            prep.setObject(3, cost.getBase_cost());
            prep.setObject(4, cost.getUnit_cost());
            prep.setString(5, cost.getDescr());
            prep.setObject(6, cost.getCost_type());
            prep.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new Exception("新增资费数据失败",e);
        }finally{
            DBUtil.closeConnection();
        }
    }

    public Cost findById(int cost_id) throws Exception {
        String sql = "select * from cost where cost_id=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cost_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return createCost(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("根据ID查询资费数据失败！", e);
        } finally {
            DBUtil.closeConnection();
        }
        return null;
    }

    private Cost createCost(ResultSet rs) throws SQLException {
        Cost c = new Cost();
        c.setCost_id(rs.getInt("cost_id"));
        c.setName(rs.getString("name"));
        c.setBase_duration(rs.getInt("base_duration"));
        c.setBase_cost(rs.getDouble("base_cost"));
        c.setUnit_cost(rs.getDouble("unit_cost"));
        c.setStatus(rs.getString("status"));
        c.setDescr(rs.getString("descr"));
        c.setCreat_time(rs.getTimestamp("creat_time"));
        c.setStart_time(rs.getTimestamp("start_time"));
        c.setCost_type(rs.getString("cost_type"));
        return c;
    }

    public void updateCost(Cost cost) throws Exception {
        if(cost == null){
            return;
        }
        String sql = "update cost set name=?,base_duration=?," +
                "base_cost=?,unit_cost=?,descr=?,cost_type=?" +
                "where cost_id=?";
        Connection conn = DBUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setObject(1, cost.getName());
            prep.setObject(2, cost.getBase_duration());
            prep.setObject(3, cost.getBase_cost());
            prep.setObject(4, cost.getUnit_cost());
            prep.setObject(5, cost.getDescr());
            prep.setObject(6, cost.getCost_type());
            prep.setObject(7, cost.getCost_id());
            prep.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new Exception("更新数据失败",e);
        }finally{
            DBUtil.closeConnection();
        }
    }

    public void deleteCost(int cost_id) throws Exception {
        String sql = "delete from cost where cost_id=?";
        Connection con = DBUtil.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,cost_id);
            int i = ps.executeUpdate();
            System.out.println("删除："+i);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new Exception("删除资费数据失败！",e);
        } finally {
            DBUtil.closeConnection();
        }
    }

    public List<Cost> findByPage(int page, int pageSize) throws Exception {
        String sql="select * from (select c.*,rownum r from cost c where rownum<? )where r>?";
        Connection conn = DBUtil.getConnection();
        List<Cost> list = new ArrayList<Cost>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int nextMin = page*pageSize + 1;
            pstmt.setInt(1, nextMin);
            int lastMax = (page-1)*pageSize;
            pstmt.setInt(2, lastMax);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Cost c = createCost(rs);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("分页查询失败！",e);
        }finally{
            DBUtil.closeConnection();
        }
        return list;
    }

    public int findTotalPage(int pageSize) throws Exception {
        String sql = "select count(*) form cost";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int rows = rs.getInt(1);
                if(rows%pageSize == 0){
                    return rows/pageSize;
                }else{
                    return rows/pageSize+1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("查询总页数失败！",e);
        }
        return 0;
    }

    public Cost findByName(String name) throws Exception {
        if(name == null){
            return null;
        }
        String sql = "select * from cost where name=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement prep;
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                Cost cost = createCost(rs);
                return cost;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("根据名字查询数据失败",e);
        }finally{
            DBUtil.closeConnection();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        CostDAO dao = new CostDAOImpl();
//		List<Cost> list = dao.findAll();

//		List<Cost> list = dao.findByPage(1, 5);
//		for(Cost  c : list){
//			System.out.println(c.getCost_id()+" "+c.getName()+" "+c.getCost_type());
//		}
//		System.out.println(dao.findTotalPage(5));
//		dao.deleteCost(6);

//		Cost cost = dao.findByName("6.9元套餐");
//		System.out.println(cost.getCost_id()+" "+cost.getName());

//		Cost cost = new Cost();
//		cost.setName("aaa");
//		cost.setBase_duration(1000);
//		cost.setBase_cost(10.00);
//		cost.setDescr("dfadf");
//		cost.setCost_type("0");
//		cost.setUnit_cost(5.00);
//		dao.addCost(cost);

//        dao.deleteCost(1003);

		Cost cost = dao.findById(100);
//		cost.setName("fjak");
//		cost.setBase_cost(10.00);
//		dao.updateCost(cost);
//		System.out.println(cost.getName()+","+cost.getBase_cost());
		System.out.println(cost.getName()+","+cost.getBase_duration());
//		dao.deleteCost(1);

//        Cost c=new Cost();
//        c.setName("包月");
//        c.setBase_duration(600);
//        c.setBase_cost(1000.0);
//        c.setUnit_cost(0.6);
//        c.setDescr("包月很爽");
//        c.setCost_type("1");
//        dao.save(c);
    }

}
