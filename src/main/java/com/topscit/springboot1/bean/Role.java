package com.topscit.springboot1.bean;

import java.util.List;

public class Role {
    private String id;

    private String name;

    private String available;
    
    private List<Permission> permission;
    
    private User user;
    
    private String  PerID[];
    
    
    

    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getPerID() {
		return PerID;
	}

	public void setPerID(String[] perID) {
		PerID = perID;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}