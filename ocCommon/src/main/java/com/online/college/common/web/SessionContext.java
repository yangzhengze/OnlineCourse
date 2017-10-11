package com.online.college.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

import com.online.college.common.web.auth.SessionUser;

/**
 * session工具类
 */
public class SessionContext {
	public static final String IDENTIFY_CODE_KEY = "_consts_identify_code_key_";// 其他人不得占用
	public static final String AUTH_USER_KEY = "_consts_auth_user_key_";// 其他人不得占用

	public static Long getUserId(){
		return 1L;
//		return getAuthUser().getUserId();
	}
	
	// 获取当前登录用户
	public static SessionUser getAuthUser() {
		return (SessionUser) SecurityUtils.getSubject().getPrincipal();
	}

	// 获取验证码
	public static String getIdentifyCode(HttpServletRequest request) {
		if (request.getSession().getAttribute(IDENTIFY_CODE_KEY) != null) {
			return getAttribute(request, IDENTIFY_CODE_KEY).toString();
		} else {
			return null;
		}
	}

	// 获取属性
	public static Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	// 设置属性
	public static void setAttribute(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	// 删除属性
	public static void removeAttribute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	//shiro logout
	public static void shiroLogout(){
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
}