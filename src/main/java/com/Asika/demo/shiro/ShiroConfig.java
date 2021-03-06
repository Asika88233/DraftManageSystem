package com.Asika.demo.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	@Autowired
	private  ShiroFilters shirofilters;
	@Bean
	public UserRealm getUserRealm(
			@Qualifier("md5Matcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
		UserRealm userRealm = new UserRealm();//需要一个md5加密方法，生成userRealm
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return userRealm;
	}

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("getDefaultSecurityManager") DefaultSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);//注入securityManager
		shiroFilterFactoryBean.setLoginUrl(shirofilters.getLoginUrl());//配置登录url
		shiroFilterFactoryBean.setFilterChainDefinitionMap(shirofilters.getFilter());//配置权限表
		return shiroFilterFactoryBean;
	}

	@Bean
	public  DefaultSecurityManager getDefaultSecurityManager(@Qualifier("getUserRealm") UserRealm realm) {
		DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;
	}
    /**
               *     配置加密方式
     * @return
     */
	@Bean
	public HashedCredentialsMatcher md5Matcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// 指定加密方式
		credentialsMatcher.setHashAlgorithmName("MD5");
		System.out.println("加密方式：MD5");
		// 加密次数
		credentialsMatcher.setHashIterations(1024);
		// 此处的设置，true加密用的hex编码，false用的base64编码
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
	    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
	    return authorizationAttributeSourceAdvisor;
	}
}
