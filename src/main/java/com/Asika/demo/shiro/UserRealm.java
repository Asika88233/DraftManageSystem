package com.Asika.demo.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Asika.demo.controller.DraftController;
import com.Asika.demo.entity.User;
import com.Asika.demo.serivce.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
	@Autowired
	UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
		throws AuthenticationException {
		logger.info("执行认证逻辑");
		UsernamePasswordToken userToken=(UsernamePasswordToken) token;
		String userId = userToken.getUsername();//获取前端返回的用户名和密码
		QueryWrapper<User> warpper=new QueryWrapper<User>();
		warpper.eq("user_id",userId);
		warpper.last("limit 1");
		List<User> list =userService.list(warpper);//数据库中是否当前用户名
		if (list.size()==0) {
			logger.info("该用户不存在，userid={}",userToken.getUsername());
			return null;//查询不到，则抛出UnknownAccountException
		}
		else {
				Object principal=userId;
				Object credentials=list.get(0).getPassword();
				String realmName=getName();
				SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(principal, 
				credentials, ByteSource.Util.bytes(userId), realmName);
				logger.info("用户尝试登陆，userid={}",userToken.getUsername());
				return info;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal=principals.getPrimaryPrincipal();
		String userId=principal.toString();
		Subject subject =SecurityUtils.getSubject();
		Session session =subject.getSession();
		String auth =session.getAttribute("auth").toString();
		Set<String> roles =new HashSet<String>();
		roles.add(auth);
		logger.info("执行授权逻辑,userId={},auth={}",userId,auth);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

}
