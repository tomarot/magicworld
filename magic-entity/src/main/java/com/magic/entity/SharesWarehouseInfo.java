package com.magic.entity;

import com.magic.utils.PageBean;

import java.util.Date;

public class SharesWarehouseInfo extends PageBean {
    private String id;

    private String sharesid;

    private String accountid;

    private Integer num;

    private Double cost;

    private String isdel;

    private Date createtime;

    private Date updatetime;

    public SharesWarehouseInfo(String id, String sharesid, String accountid, Integer num, Double cost, String isdel, Date createtime, Date updatetime) {
        this.id = id;
        this.sharesid = sharesid;
        this.accountid = accountid;
        this.num = num;
        this.cost = cost;
        this.isdel = isdel;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public SharesWarehouseInfo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSharesid() {
        return sharesid;
    }

    public void setSharesid(String sharesid) {
        this.sharesid = sharesid == null ? null : sharesid.trim();
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}