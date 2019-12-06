package com.topscit.springboot1.bean;

import java.util.Date;

public class Sign {
    private String sid;

    private String id;

    private Date stime;

    private String year;

    private String month;

    private String dat;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat == null ? null : dat.trim();
    }
}