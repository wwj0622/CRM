/*package com.topscit.springboot1.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.topscit.springboot1.realm.MyRealm;



//说明这个类用于做配置
@Configuration
public class ShiroConfig {

	@Bean
	public HashedCredentialsMatcher credentialsMatcher()
	{
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("md5");
		credentialsMatcher.setHashIterations(1);
		return credentialsMatcher;
	}
	@Bean
	public MyRealm myRelam()
	{
		MyRealm myRealm = new MyRealm();
		myRealm.setCredentialsMatcher(credentialsMatcher());
		return myRealm;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager()
	{
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(60*30*1000);
		sessionManager.setDeleteInvalidSessions(true);
		return sessionManager;
	}
	@Bean
	public CacheManager cacheManager()
	{
		return new EhCacheManager();
	}
	@Bean
	public SecurityManager securityManager()
	{
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRelam());
		securityManager.setSessionManager(sessionManager());
		securityManager.setCacheManager(cacheManager());
		return securityManager;
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean()
	{
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager());
		factoryBean.setLoginUrl("/Login.jsp");
		factoryBean.setSuccessUrl("/permiss");
		factoryBean.setUnauthorizedUrl("/unauthorized.jsp");

		Map<String,String> hashMap = new HashMap<String, String>();
		hashMap.put("/js/**", "anon");
		hashMap.put("/lib/**", "anon");
		hashMap.put("/static/**", "anon");
		hashMap.put("/temp/**", "anon");
		hashMap.put("/login", "anon");
		hashMap.put("/Login.jsp", "anon");
		hashMap.put("/logout", "logout");
		
		hashMap.put("/**", "authc");
	
		factoryBean.setFilterChainDefinitionMap(hashMap);
		return factoryBean;
	}
	
	开启shiro注解
	@Bean
    @ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
          DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		  creator.setProxyTargetClass(true);
		  return creator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
		
	}
	
	    @Bean
	    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
	        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
	        Properties properties = new Properties();
	        未授权处理页
	        properties.setProperty("UnauthorizedException", "403.html");
	        resolver.setExceptionMappings(properties);
	        return resolver;
	    }
	

}
*/