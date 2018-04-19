package com.magic.entity;

import com.magic.utils.PageBean;

import java.util.Date;

public class SharesOfferBill extends PageBean {
    private String id;

    private String accountid;

    private String quotationbilltype;

    private Double price;

    private Integer num;

    private String state;

    private String isdel;

    private Date createtime;

    private Date updatetime;

    public SharesOfferBill(String id, String accountid, String quotationbilltype, Double price, Integer num, String state, String isdel, Date createtime, Date updatetime) {
        this.id = id;
        this.accountid = accountid;
        this.quotationbilltype = quotationbilltype;
        this.price = price;
        this.num = num;
        this.state = state;
        this.isdel = isdel;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public SharesOfferBill() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public String getQuotationbilltype() {
        return quotationbilltype;
    }

    public void setQuotationbilltype(String quotationbilltype) {
        this.quotationbilltype = quotationbilltype == null ? null : quotationbilltype.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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