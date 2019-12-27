package com.topscit.springboot1.realm;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.topscit.springboot1.bean.Permission;
import com.topscit.springboot1.bean.Role;
import com.topscit.springboot1.bean.User;
import com.topscit.springboot1.service.LoginService;

public class MyRealm extends AuthorizingRealm{

	@Resource
	private LoginService loginService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("进入权限");
		SimpleAuthorizationInfo info;
		try {
			User user = (User) principals.getPrimaryPrincipal();
			
			List<Role> list = loginService.selectPermission(user.getId());
			
			info = new SimpleAuthorizationInfo();
			
			for (Role role : list) {
				 info.addRole(role.getName());
				 List<Permission> permission = role.getPermission();
				 for (Permission permission2 : permission) {
					  info.addStringPermission(permission2.getPercode());       
				}
			}
			return info;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      
        System.out.println("进入认证");
		String name = (String) token.getPrincipal();
	
		User user = loginService.Select(name);
	
		if(user==null)
		{
			return null;
		}
       	 SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getSalt()) , "Myrealm");
 	
	  	return simpleAuthenticationInfo;
	}

	
}
