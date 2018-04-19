package com.magic.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SharesGameRecord {

    private String id;

    private String accountid;

    private String gamecode;

    private String sharesid;

    private String frequency;

    private Double initmoney;

    private Double currentmoney;

    private String userid;

    private String startrecordid;

    private Long offsetnum;

    private String gamestate;

    private Date createtime;

    public SharesGameRecord(String id, String gamecode, String sharesid, String frequency, Double initmoney, Double currentmoney, String userid, String startrecordid, Long offsetnum, String gamestate, Date createtime) {
        this.id = id;
        this.gamecode = gamecode;
        this.sharesid = sharesid;
        this.frequency = frequency;
        this.initmoney = initmoney;
        this.currentmoney = currentmoney;
        this.userid = userid;
        this.startrecordid = startrecordid;
        this.offsetnum = offsetnum;
        this.gamestate = gamestate;
        this.createtime = createtime;
    }

    public SharesGameRecord() {
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
        this.accountid = accountid;
    }

    public String getGamecode() {
        return gamecode;
    }

    public void setGamecode(String gamecode) {
        this.gamecode = gamecode == null ? null : gamecode.trim();
    }

    public String getSharesid() {
        return sharesid;
    }

    public void setSharesid(String sharesid) {
        this.sharesid = sharesid == null ? null : sharesid.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public Double getInitmoney() {
        return initmoney;
    }

    public void setInitmoney(Double initmoney) {
        this.initmoney = initmoney;
    }

    public Double getCurrentmoney() {
        return currentmoney;
    }

    public void setCurrentmoney(Double currentmoney) {
        this.currentmoney = currentmoney;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getStartrecordid() {
        return startrecordid;
    }

    public void setStartrecordid(String startrecordid) {
        this.startrecordid = startrecordid == null ? null : startrecordid.trim();
    }

    public Long getOffsetnum() {
        return offsetnum;
    }

    public void setOffsetnum(Long offsetnum) {
        this.offsetnum = offsetnum;
    }

    public String getGamestate() {
        return gamestate;
    }

    public void setGamestate(String gamestate) {
        this.gamestate = gamestate == null ? null : gamestate.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}