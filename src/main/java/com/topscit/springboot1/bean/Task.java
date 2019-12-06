package com.topscit.springboot1.bean;

import java.util.Date;

public class Task {
    private String tid;

    private String tcontent;

    private String tstate;

    private Date ttime;

    private String id;

    private String userid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent == null ? null : tcontent.trim();
    }

    public String getTstate() {
        return tstate;
    }

    public void setTstate(String tstate) {
        this.tstate = tstate == null ? null : tstate.trim();
    }

    public Date getTtime() {
        return ttime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}