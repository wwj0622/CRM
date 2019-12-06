package com.topscit.springboot1.bean;

import java.util.Date;

public class Parts {
    private String pid;

    private String pname;

    private String pexplain;

    private String punit;

    private String ptid;

    private String pcount;

    private String pdanger;

    private String pstate;

    private String pprice;

    private String premark;

    private Date pupdateTime;

    private Date pcreateTime;

    private String plogo;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPexplain() {
        return pexplain;
    }

    public void setPexplain(String pexplain) {
        this.pexplain = pexplain == null ? null : pexplain.trim();
    }

    public String getPunit() {
        return punit;
    }

    public void setPunit(String punit) {
        this.punit = punit == null ? null : punit.trim();
    }

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid == null ? null : ptid.trim();
    }

    public String getPcount() {
        return pcount;
    }

    public void setPcount(String pcount) {
        this.pcount = pcount == null ? null : pcount.trim();
    }

    public String getPdanger() {
        return pdanger;
    }

    public void setPdanger(String pdanger) {
        this.pdanger = pdanger == null ? null : pdanger.trim();
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate == null ? null : pstate.trim();
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice == null ? null : pprice.trim();
    }

    public String getPremark() {
        return premark;
    }

    public void setPremark(String premark) {
        this.premark = premark == null ? null : premark.trim();
    }

    public Date getPupdateTime() {
        return pupdateTime;
    }

    public void setPupdateTime(Date pupdateTime) {
        this.pupdateTime = pupdateTime;
    }

    public Date getPcreateTime() {
        return pcreateTime;
    }

    public void setPcreateTime(Date pcreateTime) {
        this.pcreateTime = pcreateTime;
    }

    public String getPlogo() {
        return plogo;
    }

    public void setPlogo(String plogo) {
        this.plogo = plogo == null ? null : plogo.trim();
    }
}