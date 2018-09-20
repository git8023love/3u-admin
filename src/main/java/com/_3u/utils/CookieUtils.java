package com._3u.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaof
 * 
 */
public class CookieUtils {

	private static final String path = "/";

	/**
	 * 获得cookie的值
	 */
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {
		try {
			request.setCharacterEncoding("UTF-8");
			Cookie[] cookie = request.getCookies();
			int len = cookie == null ? 0 : cookie.length;
			for (int i = 0; i < len; i++) {
				if (cookieName != null
						&& cookieName.equalsIgnoreCase(cookie[i].getName())) {
					return URLEncodeUtils.decodeURL(cookie[i].getValue());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 设置cookie
	 * 
	 */
	public static void setCookieTicket(HttpServletResponse response,
			String ticket, int expiry) {
		// 设定COOKIE值
		Cookie ticketCookie = new Cookie(Constants.COOKIE_TICKET, ticket);
		ticketCookie.setPath(path);

		ticketCookie.setMaxAge(expiry);
		// 添加COOKIE
		response.addCookie(ticketCookie);
	}

	/**
	 * 清除cookie
	 */
	public static void clearCookieTicket(HttpServletResponse response) {
		Cookie ticketCookie = new Cookie(Constants.COOKIE_TICKET, null);
		ticketCookie.setPath(path);
		ticketCookie.setMaxAge(0);
		// 添加COOKIE
		response.addCookie(ticketCookie);
	}

}
