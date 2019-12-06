package com.topscit.springboot1.bean;

import java.util.Date;

public class Buy {
    private String bid;

    private String bstate;

    private Date btime;

    private String baddress;

    private String bman;

    private String bremark;

    private String sid;

    private Date gupdateTime;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBstate() {
        return bstate;
    }

    public void setBstate(String bstate) {
        this.bstate = bstate == null ? null : bstate.trim();
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public String getBaddress() {
        return baddress;
    }

    public void setBaddress(String baddress) {
        this.baddress = baddress == null ? null : baddress.trim();
    }

    public String getBman() {
        return bman;
    }

    public void setBman(String bman) {
        this.bman = bman == null ? null : bman.trim();
    }

    public String getBremark() {
        return bremark;
    }

    public void setBremark(String bremark) {
        this.bremark = bremark == null ? null : bremark.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Date getGupdateTime() {
        return gupdateTime;
    }

    public void setGupdateTime(Date gupdateTime) {
        this.gupdateTime = gupdateTime;
    }
}