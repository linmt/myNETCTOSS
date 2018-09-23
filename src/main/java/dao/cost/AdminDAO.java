package dao.cost;

import entity.Admin;

/**
 * Created by 热带雨林 on 2018/9/24.
 */
public interface AdminDAO {
    Admin findByCode(String admin_code) throws Exception;
}
