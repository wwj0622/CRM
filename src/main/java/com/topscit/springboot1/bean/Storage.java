package com.topscit.springboot1.bean;

import java.util.Date;

public class Storage {
    private String stid;

    private String stname;

    private String stremark;

    private Date stupdateTime;

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid == null ? null : stid.trim();
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname == null ? null : stname.trim();
    }

    public String getStremark() {
        return stremark;
    }

    public void setStremark(String stremark) {
        this.stremark = stremark == null ? null : stremark.trim();
    }

    public Date getStupdateTime() {
        return stupdateTime;
    }

    public void setStupdateTime(Date stupdateTime) {
        this.stupdateTime = stupdateTime;
    }
}