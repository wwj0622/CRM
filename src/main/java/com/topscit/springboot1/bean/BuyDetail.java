package com.topscit.springboot1.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuyDetail {
    private String bdid;

    private String buyId;

    private String partsId;

    private String bdcount;

    private String bdprice;

    private String bdstate;

    private String bdman;

    private String bdremark;
    
    private String state;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bdupdateTime;

    private Parts parts;
    

	public Parts getParts() {
		return parts;
	}

	public void setParts(Parts parts) {
		this.parts = parts;
	}

	public String getBdid() {
        return bdid;
    }

    public void setBdid(String bdid) {
        this.bdid = bdid == null ? null : bdid.trim();
    }


    public String getBdcount() {
        return bdcount;
    }

    public void setBdcount(String bdcount) {
        this.bdcount = bdcount == null ? null : bdcount.trim();
    }

    public String getBdprice() {
        return bdprice;
    }

    public void setBdprice(String bdprice) {
        this.bdprice = bdprice == null ? null : bdprice.trim();
    }

    public String getBdstate() {
        return bdstate;
    }

    public void setBdstate(String bdstate) {
        this.bdstate = bdstate == null ? null : bdstate.trim();
    }

    public String getBdman() {
        return bdman;
    }

    public void setBdman(String bdman) {
        this.bdman = bdman == null ? null : bdman.trim();
    }

    public String getBdremark() {
        return bdremark;
    }

    public void setBdremark(String bdremark) {
        this.bdremark = bdremark == null ? null : bdremark.trim();
    }

    public Date getBdupdateTime() {
        return bdupdateTime;
    }

    public void setBdupdateTime(Date bdupdateTime) {
        this.bdupdateTime = bdupdateTime;
    }

    
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public String getPartsId() {
		return partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	@Override
	public String toString() {
		return "BuyDetail [bdid=" + bdid + ", buyId=" + buyId + ", partsId=" + partsId + ", bdcount=" + bdcount
				+ ", bdprice=" + bdprice + ", bdstate=" + bdstate + ", bdman=" + bdman + ", bdremark=" + bdremark
				+ ", state=" + state + ", bdupdateTime=" + bdupdateTime + ", parts=" + parts + "]";
	}

    
}