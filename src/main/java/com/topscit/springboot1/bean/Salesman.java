package com.topscit.springboot1.bean;

public class Salesman {
    private String smid;

    private String smname;

    private String smphone;

    private String smemail;

    private String smaddress;

    private String smremark;

    private String did;

    private String wid;

    public String getSmid() {
        return smid;
    }

    public void setSmid(String smid) {
        this.smid = smid == null ? null : smid.trim();
    }

    public String getSmname() {
        return smname;
    }

    public void setSmname(String smname) {
        this.smname = smname == null ? null : smname.trim();
    }

    public String getSmphone() {
        return smphone;
    }

    public void setSmphone(String smphone) {
        this.smphone = smphone == null ? null : smphone.trim();
    }

    public String getSmemail() {
        return smemail;
    }

    public void setSmemail(String smemail) {
        this.smemail = smemail == null ? null : smemail.trim();
    }

    public String getSmaddress() {
        return smaddress;
    }

    public void setSmaddress(String smaddress) {
        this.smaddress = smaddress == null ? null : smaddress.trim();
    }

    public String getSmremark() {
        return smremark;
    }

    public void setSmremark(String smremark) {
        this.smremark = smremark == null ? null : smremark.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid == null ? null : wid.trim();
    }
}