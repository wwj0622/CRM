package com.topscit.springboot1.bean;

public class Goods {
    private String gid;

    private String gname;

    private String goriginal;

    private String gprice;

    private String ptid;

    private String gcount;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getGoriginal() {
        return goriginal;
    }

    public void setGoriginal(String goriginal) {
        this.goriginal = goriginal == null ? null : goriginal.trim();
    }

    public String getGprice() {
        return gprice;
    }

    public void setGprice(String gprice) {
        this.gprice = gprice == null ? null : gprice.trim();
    }

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid == null ? null : ptid.trim();
    }

    public String getGcount() {
        return gcount;
    }

    public void setGcount(String gcount) {
        this.gcount = gcount == null ? null : gcount.trim();
    }
}