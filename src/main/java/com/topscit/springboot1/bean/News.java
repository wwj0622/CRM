package com.topscit.springboot1.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class News {
    private String nid;

    private String newnumber;

    private String userid;

    private String receiveuid;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date sendtime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date receivetime;

    private String nstate;
    
    private User user;
    
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber == null ? null : newnumber.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getReceiveuid() {
        return receiveuid;
    }

    public void setReceiveuid(String receiveuid) {
        this.receiveuid = receiveuid == null ? null : receiveuid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    public String getNstate() {
        return nstate;
    }

    public void setNstate(String nstate) {
        this.nstate = nstate == null ? null : nstate.trim();
    }
}