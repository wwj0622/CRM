package com.topscit.springboot1.bean;

import java.util.Date;

public class Dept {
    private String did;

    private String dname;

    private String dremark;

    private Date dupdateTime;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getDremark() {
        return dremark;
    }

    public void setDremark(String dremark) {
        this.dremark = dremark == null ? null : dremark.trim();
    }

    public Date getDupdateTime() {
        return dupdateTime;
    }

    public void setDupdateTime(Date dupdateTime) {
        this.dupdateTime = dupdateTime;
    }
}