package com.topscit.springboot1.bean;

import java.util.Date;

public class Work {
    private String wid;

    private String wname;

    private String did;

    private String wremark;

    private Date wupdateTime;
    
    

    @Override
	public String toString() {
		return "Work [wid=" + wid + ", wname=" + wname + ", did=" + did + ", wremark=" + wremark + ", wupdateTime="
				+ wupdateTime + "]";
	}

	public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid == null ? null : wid.trim();
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname == null ? null : wname.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getWremark() {
        return wremark;
    }

    public void setWremark(String wremark) {
        this.wremark = wremark == null ? null : wremark.trim();
    }

    public Date getWupdateTime() {
        return wupdateTime;
    }

    public void setWupdateTime(Date wupdateTime) {
        this.wupdateTime = wupdateTime;
    }
}