package com.topscit.springboot1.bean;

import java.util.Date;

public class BuyDetail {
    private String bdid;

    private String bid;

    private String pid;

    private String bdcount;

    private String bdprice;

    private String bdstate;

    private String bdman;

    private String bdremark;

    private Date bdupdateTime;

    public String getBdid() {
        return bdid;
    }

    public void setBdid(String bdid) {
        this.bdid = bdid == null ? null : bdid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getBdcount() {
        return bdcount;
    }

    public void setBdcount(String bdcount) {
        this.bdcount = bdcount == null ? null : bdcount.trim();
    }

    public String getBdprice() {
        return bdprice;
    }

    public void setBdprice(String bdprice) {
        this.bdprice = bdprice == null ? null : bdprice.trim();
    }

    public String getBdstate() {
        return bdstate;
    }

    public void setBdstate(String bdstate) {
        this.bdstate = bdstate == null ? null : bdstate.trim();
    }

    public String getBdman() {
        return bdman;
    }

    public void setBdman(String bdman) {
        this.bdman = bdman == null ? null : bdman.trim();
    }

    public String getBdremark() {
        return bdremark;
    }

    public void setBdremark(String bdremark) {
        this.bdremark = bdremark == null ? null : bdremark.trim();
    }

    public Date getBdupdateTime() {
        return bdupdateTime;
    }

    public void setBdupdateTime(Date bdupdateTime) {
        this.bdupdateTime = bdupdateTime;
    }
}