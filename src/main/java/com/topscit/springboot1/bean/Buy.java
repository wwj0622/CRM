package com.topscit.springboot1.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Buy {
    private String bid;

    private String bstate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date btime;

    private String baddress;

    private String bman;

    private String bremark;

    private String sid;
    
    private String state;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gupdateTime;
    
    private BuyDetail buyDetail;
    
    private Supplier supplier;
    
    
    
    public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBstate() {
        return bstate;
    }

    public void setBstate(String bstate) {
    	 this.bstate = bstate == null ? null : bstate.trim();
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public String getBaddress() {
        return baddress;
    }

    public void setBaddress(String baddress) {
        this.baddress = baddress == null ? null : baddress.trim();
    }

    public String getBman() {
        return bman;
    }

    public void setBman(String bman) {
        this.bman = bman == null ? null : bman.trim();
    }

    public String getBremark() {
        return bremark;
    }

    public void setBremark(String bremark) {
        this.bremark = bremark == null ? null : bremark.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Date getGupdateTime() {
        return gupdateTime;
    }

    public void setGupdateTime(Date gupdateTime) {
        this.gupdateTime = gupdateTime;
    }

    
    
	public BuyDetail getBuyDetail() {
		return buyDetail;
	}

	public void setBuyDetail(BuyDetail buyDetail) {
		this.buyDetail = buyDetail;
	}

	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Buy [bid=" + bid + ", bstate=" + bstate + ", btime=" + btime + ", baddress=" + baddress + ", bman="
				+ bman + ", bremark=" + bremark + ", sid=" + sid + ", state=" + state + ", gupdateTime=" + gupdateTime
				+ ", buyDetail=" + buyDetail + ", supplier=" + supplier + "]";
	}



    
    
}