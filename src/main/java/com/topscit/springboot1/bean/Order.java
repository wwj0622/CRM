package com.topscit.springboot1.bean;

import java.util.Date;

public class Order {
    private String oid;

    private String cid;

    private String oremark;

    private String osum;

    private String ostate;

    private Date otime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getOremark() {
        return oremark;
    }

    public void setOremark(String oremark) {
        this.oremark = oremark == null ? null : oremark.trim();
    }

    public String getOsum() {
        return osum;
    }

    public void setOsum(String osum) {
        this.osum = osum == null ? null : osum.trim();
    }

    public String getOstate() {
        return ostate;
    }

    public void setOstate(String ostate) {
        this.ostate = ostate == null ? null : ostate.trim();
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }
}