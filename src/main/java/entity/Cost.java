package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class Cost implements Serializable {
    private Integer id;
    private String cost_name;//资费名
    private Integer base_duration;//基本时长
    private Double base_cost;//基本费用
    private Double unit_cost;//单位费用
    private String status;
    private String descr;//描述
    //SQL的日期类型：Date年月日 Time日分秒 Timestamp年月日时分秒
    private Timestamp creat_date;
    private Timestamp start_date;
    //1-包月 2-套餐 3-计时
    private String cost_type;//资费类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCost_name() {
        return cost_name;
    }

    public void setCost_name(String cost_name) {
        this.cost_name = cost_name;
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

    public Timestamp getCreat_date() {
        return creat_date;
    }

    public void setCreat_date(Timestamp creat_date) {
        this.creat_date = creat_date;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }
}
