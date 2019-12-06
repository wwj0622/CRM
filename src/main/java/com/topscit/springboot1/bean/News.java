package com.topscit.springboot1.bean;

import java.util.Date;

public class News {
    private String nid;

    private String tid;

    private String id;

    private String nstate;

    private Date ntime;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNstate() {
        return nstate;
    }

    public void setNstate(String nstate) {
        this.nstate = nstate == null ? null : nstate.trim();
    }

    public Date getNtime() {
        return ntime;
    }

    public void setNtime(Date ntime) {
        this.ntime = ntime;
    }
}