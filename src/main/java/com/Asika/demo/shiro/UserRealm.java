package com.Asika.demo.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Asika.demo.controller.DraftController;
import com.Asika.demo.entity.User;
import com.Asika.demo.serivce.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(DraftController.class);
	@Autowired
	UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("执行认证逻辑");
		UsernamePasswordToken userToken=(UsernamePasswordToken) token;
		String userId = userToken.getUsername();
		QueryWrapper<User> warpper=new QueryWrapper<User>();
		warpper.eq("user_id",userId);
		warpper.last("limit 1");
		List<User> list =userService.list(warpper);
		if (list.size()==0) {
			logger.info("该用户不存在，userid={}",userToken.getUsername());
			return null;
		}
		else {
				Object principal=userId;
				Object credentials=list.get(0).getPassword();
				String realmName=getName();
				SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(principal, credentials, ByteSource.Util.bytes(userId), realmName);
				logger.info("该用户登陆成功，userid={}",userToken.getUsername());
				return info;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("执行授权逻辑");
		return null;
	}

}
