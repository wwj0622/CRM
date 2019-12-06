package com.topscit.springboot1.bean;

public class ResultBean {
	
	private boolean state;
	private String msg;
	private Object data;
	
	public static final boolean STATA_SUCCESS = true;
	public static final boolean STATA_FIAIL = false;
	
	public ResultBean()
	{
		
	}
	
	public ResultBean(boolean state)
	{
		this.state = state;
	}
	
	public ResultBean(boolean state,String msg)
	{
		this(state);
		this.msg = msg;
	}
	public ResultBean(boolean state,String msg,Object data)
	{
		this(state,msg);
		this.data = data;
	}
	
	
	
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
