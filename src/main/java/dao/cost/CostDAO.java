package dao.cost;

import entity.Cost;

import java.util.List;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public interface CostDAO {
    List<Cost> findAll() throws RuntimeException;

    List<Cost> findByPage(int page, int pageSize) throws Exception;

    int findTotalPage(int pageSize) throws Exception;

    void deleteCost(int cost_id) throws Exception;

    Cost findByName(String name) throws Exception;

    void save(Cost cost) throws Exception;

    Cost findById(int cost_id) throws Exception;

    void updateCost(Cost cost) throws Exception;
}
