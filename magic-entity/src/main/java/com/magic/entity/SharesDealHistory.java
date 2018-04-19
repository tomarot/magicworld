package com.magic.entity;

import com.magic.utils.PageBean;

import java.util.Date;

public class SharesDealHistory extends PageBean {
    private String id;

    private String sharesid;

    private String accountid;

    private String offerbillid;

    private String operatetype;

    private Double price;

    private Integer num;

    private String isdel;

    private Date createtime;

    private Date updatetime;

    public SharesDealHistory(String id, String sharesid, String accountid, String offerbillid, String operatetype, Double price, Integer num, String isdel, Date createtime, Date updatetime) {
        this.id = id;
        this.sharesid = sharesid;
        this.accountid = accountid;
        this.offerbillid = offerbillid;
        this.operatetype = operatetype;
        this.price = price;
        this.num = num;
        this.isdel = isdel;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public SharesDealHistory() {
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

    public String getOfferbillid() {
        return offerbillid;
    }

    public void setOfferbillid(String offerbillid) {
        this.offerbillid = offerbillid == null ? null : offerbillid.trim();
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype == null ? null : operatetype.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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