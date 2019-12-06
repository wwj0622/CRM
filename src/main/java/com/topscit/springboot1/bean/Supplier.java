package com.topscit.springboot1.bean;

import java.util.Date;

public class Supplier {
    private String sid;

    private String sname;

    private String sphone;

    private String saddress;

    private String semail;

    private String saccount;

    private String sstate;

    private String sremark;

    private Date supdateTime;

    private Date screateTime;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone == null ? null : sphone.trim();
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress == null ? null : saddress.trim();
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail == null ? null : semail.trim();
    }

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount == null ? null : saccount.trim();
    }

    public String getSstate() {
        return sstate;
    }

    public void setSstate(String sstate) {
        this.sstate = sstate == null ? null : sstate.trim();
    }

    public String getSremark() {
        return sremark;
    }

    public void setSremark(String sremark) {
        this.sremark = sremark == null ? null : sremark.trim();
    }

    public Date getSupdateTime() {
        return supdateTime;
    }

    public void setSupdateTime(Date supdateTime) {
        this.supdateTime = supdateTime;
    }

    public Date getScreateTime() {
        return screateTime;
    }

    public void setScreateTime(Date screateTime) {
        this.screateTime = screateTime;
    }
}