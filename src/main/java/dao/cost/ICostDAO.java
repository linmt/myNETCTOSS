package dao.cost;

import entity.Cost;

import java.util.List;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public interface ICostDAO {
    List<Cost> findAll() throws RuntimeException;

    /**
     * 分页查询资费数据
     * @param page 当前页
     * @param pageSize 页容量
     * @return
     * @throws Exception
     */
    List<Cost> findByPage(int page, int pageSize) throws Exception;

    /**
     * 查询总页数
     * @param pageSize 页容量
     * @return
     * @throws Exception
     */
    int findTotalPage(int pageSize) throws Exception;

    /**
     * 根據id刪除資費数据
     * @param id
     * @throws Exception
     */
    void deleteCost(int id) throws Exception;

    /**
     * 根据名称查信息
     * @param name
     * @return
     * @throws Exception
     */
    Cost findByName(String name) throws Exception;

    /**
     * 新增一条资费数据
     * @param cost
     * @throws Exception
     */
    void save(Cost cost) throws Exception;

    /**
     * 根据id查询id
     * @param id
     * @return
     * @throws Exception
     */
    Cost findById(int id) throws Exception;

    /**
     * 更新
     * @param cost
     * @throws Exception
     */
    void updateCost(Cost cost) throws Exception;
}
