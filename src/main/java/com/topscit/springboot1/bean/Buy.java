package com.topscit.springboot1.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Buy {
    private String bid;

    private String bstate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date btime;

    private String baddress;

    private String bman;

    private String bremark;

    private String sid;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gupdateTime;
    
    private BuyDetail buyDetail;
    
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
    	if(bstate.equals("1")){
    		this.bstate = "已付款";
    	}
    	else{
    		this.bstate = "未付款";
    	}
         
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

	@Override
	public String toString() {
		return "Buy [bid=" + bid + ", bstate=" + bstate + ", btime=" + btime + ", baddress=" + baddress + ", bman="
				+ bman + ", bremark=" + bremark + ", sid=" + sid + ", gupdateTime=" + gupdateTime + ", buyDetail="
				+ buyDetail + "]";
	}


    
    
}