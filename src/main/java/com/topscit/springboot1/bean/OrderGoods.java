package com.topscit.springboot1.bean;

public class OrderGoods {
    private String oid;

    private String gid;

    private String ogcount;

    private String ogremark;

    private String uid;
    
    private Goods goods;

    public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getOgcount() {
        return ogcount;
    }

    public void setOgcount(String ogcount) {
        this.ogcount = ogcount == null ? null : ogcount.trim();
    }

    public String getOgremark() {
        return ogremark;
    }

    public void setOgremark(String ogremark) {
        this.ogremark = ogremark == null ? null : ogremark.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

	@Override
	public String toString() {
		return "OrderGoods [oid=" + oid + ", gid=" + gid + ", ogcount=" + ogcount + ", ogremark=" + ogremark + ", uid="
				+ uid + ", goods=" + goods + "]";
	}

}