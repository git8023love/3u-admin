package com._3u.utils;

import java.util.Set;

import javax.servlet.http.HttpSession;

import com._3u.domain.User;
import org.json.JSONArray;


/**
 * 
 * @author xiaof
 * 
 */
public class SessionUtils {

	public static void setSession(HttpSession session, User user, JSONArray mainFunctions, Set<String> funcHrefs) {
		// 设定用户的session
		session.setAttribute(Constants.SESSION_USER, user);
		session.setAttribute(Constants.SESSION_MAIN_FUNCTIONS, mainFunctions);
		// 设定该用户的所有功能值
		session.setAttribute(Constants.SESSION_FUNCTIONS, funcHrefs);
	}
	
	/**
	 * 清除当前用户的session值
	 * 
	 * @param session
	 */
	public static void destroySession(HttpSession session) {
		session.removeAttribute(Constants.SESSION_USER);
		session.removeAttribute(Constants.SESSION_MAIN_FUNCTIONS);
		session.removeAttribute(Constants.SESSION_FUNCTIONS);
		session.invalidate();
	}
	
	/**
	 * 从session中取出用户信息
	 * @param
	 * @return
	 * 
	 * @auther hgw
	 *
	 * @date 2018-9-18
	 */
	public static User getSession(){
		HttpSession session = HttpUtil.getRequest().getSession();
		User user = (User) session.getAttribute(Constants.SESSION_USER);
		return user;
	}

}