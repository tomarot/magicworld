package com.magic.entity;

import com.magic.utils.PageBean;

import java.util.Date;

public class SharesAccountInfo extends PageBean{
    private String id;

    private String userid;

    private String accountname;

    private Double initmoney;

    private Double availablemoney;

    private String isdel;

    private Date createtime;

    private Date updatetime;

    public SharesAccountInfo(String id, String userid, String accountname, Double initmoney, Double availablemoney, String isdel, Date createtime, Date updatetime) {
        this.id = id;
        this.userid = userid;
        this.accountname = accountname;
        this.initmoney = initmoney;
        this.availablemoney = availablemoney;
        this.isdel = isdel;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public SharesAccountInfo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public Double getInitmoney() {
        return initmoney;
    }

    public void setInitmoney(Double initmoney) {
        this.initmoney = initmoney;
    }

    public Double getAvailablemoney() {
        return availablemoney;
    }

    public void setAvailablemoney(Double availablemoney) {
        this.availablemoney = availablemoney;
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