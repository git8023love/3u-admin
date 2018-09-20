package com._3u.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author xiaof
 */
public class RemoteAdressUtils {

	/**
	 * 得到IP的值
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpValue(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 得到用户浏览器信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}

	/**
	 * 得到本次访问链接的来源网址
	 * 
	 * @param request
	 * @return
	 */
	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("referer");
	}

}
