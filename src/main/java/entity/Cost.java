package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class Cost implements Serializable {
    private Integer cost_id;
    private String name;//资费名
    private Integer base_duration;//基本时长
    private Double base_cost;//基本费用
    private Double unit_cost;//单位费用
    private String status;
    private String descr;//描述
    //SQL的日期类型：Date年月日 Time日分秒 Timestamp年月日时分秒
    private Timestamp creat_time;
    private Timestamp start_time;
    //1-包月 2-套餐 3-计时
    private String cost_type;//资费类型

    public Integer getCost_id() {
        return cost_id;
    }

    public void setCost_id(Integer cost_id) {
        this.cost_id = cost_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBase_duration() {
        return base_duration;
    }

    public void setBase_duration(Integer base_duration) {
        this.base_duration = base_duration;
    }

    public Double getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(Double base_cost) {
        this.base_cost = base_cost;
    }

    public Double getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(Double unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Timestamp getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Timestamp creat_time) {
        this.creat_time = creat_time;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }
}
