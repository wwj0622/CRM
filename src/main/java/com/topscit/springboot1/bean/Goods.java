package com.topscit.springboot1.bean;

import java.util.List;

public class Goods {
    private String gid;

    private String gname;

    private String goriginal;

    private String gprice;

    private String ptid;

    private String gremark;

    private String glogo;

    private String gcount;

    private String gdanger;

    private String gstate;
    
    private Storage storage;

    private String  par[];
    
    private List<Parts> parts;
    
    
	public List<Parts> getParts() {
		return parts;
	}

	public void setParts(List<Parts> parts) {
		this.parts = parts;
	}

	public String[] getPar() {
		return par;
	}

	public void setPar(String[] par) {
		this.par = par;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

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

    public String getGremark() {
        return gremark;
    }

    public void setGremark(String gremark) {
        this.gremark = gremark == null ? null : gremark.trim();
    }

    public String getGlogo() {
        return glogo;
    }

    public void setGlogo(String glogo) {
        this.glogo = glogo == null ? null : glogo.trim();
    }

    public String getGcount() {
        return gcount;
    }

    public void setGcount(String gcount) {
        this.gcount = gcount == null ? null : gcount.trim();
    }

    public String getGdanger() {
        return gdanger;
    }

    public void setGdanger(String gdanger) {
        this.gdanger = gdanger == null ? null : gdanger.trim();
    }

    public String getGstate() {
        return gstate;
    }

    public void setGstate(String gstate) {
        this.gstate = gstate == null ? null : gstate.trim();
    }
}