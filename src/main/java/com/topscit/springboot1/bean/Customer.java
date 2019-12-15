package com.topscit.springboot1.bean;

public class Customer {
    private String cid;

    private String cname;

    private String cproterty;

    private String cstate;

    private String caccount;

    private String cemail;

    private String cphone;

    private String caddress;

    private String bremark;

    private String smid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCproterty() {
        return cproterty;
    }

    public void setCproterty(String cproterty) {
        this.cproterty = cproterty == null ? null : cproterty.trim();
    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate == null ? null : cstate.trim();
    }

    public String getCaccount() {
        return caccount;
    }

    public void setCaccount(String caccount) {
        this.caccount = caccount == null ? null : caccount.trim();
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail == null ? null : cemail.trim();
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone == null ? null : cphone.trim();
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress == null ? null : caddress.trim();
    }

    public String getBremark() {
        return bremark;
    }

    public void setBremark(String bremark) {
        this.bremark = bremark == null ? null : bremark.trim();
    }

    public String getSmid() {
        return smid;
    }

    public void setSmid(String smid) {
        this.smid = smid == null ? null : smid.trim();
    }

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", cproterty=" + cproterty + ", cstate=" + cstate
				+ ", caccount=" + caccount + ", cemail=" + cemail + ", cphone=" + cphone + ", caddress=" + caddress
				+ ", bremark=" + bremark + ", smid=" + smid + "]";
	}
    
}