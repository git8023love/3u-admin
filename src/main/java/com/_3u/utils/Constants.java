package com._3u.utils;

import java.math.BigDecimal;

public class Constants {

	// 有效值
	public final static int EFFECTIVE = 0;

	// 无效值
	public final static int INVALID = 1;

	// 待审核
	public final static int AUDIT = 2;

	// session key
	public final static String SESSION_USER = "3u_user";
	
	public final static String SESSION_FUNCTIONS = "3u_functions";
	
	public final static String COOKIE_TICKET = "3u_cookie_ticket";
	
	public final static String SESSION_MAIN_FUNCTIONS = "3u_main_functions";
	
	public final static String DEFAULT_PAGE_SIZE = "20";

	public final static int SUPER_ADMIN = 1;

	public static final String SEPARATOR = "/";
	
	public final static String PHONE_REGEX = "^1[3|4|5|8][0-9]\\d{8}$";
	
	public final static String SPECIAL_PAPER_KEY = "special_paper_key";
	
	public final static String SPECIAL_RING_KEY = "special_ring_key";
	
	public final static int DEFAULT_CACHE_TIME = 30 * 24 * 60 * 60; 
	
	/**
	 * 判断文件是否是图片
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImage(String fileName) {
		String[] validFiles = { "gif", "jpg", "jpeg", "png" };
		boolean ret = false;
		for (int i = 0; i < validFiles.length; i++) {
			if (fileName.toLowerCase().endsWith(validFiles[i])) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	/**
	 * 截取小数点后两位
	 * 
	 * @param totalPrice
	 * @return
	 */
	public static String roundTotalPrice(double totalPrice) {
		String result = new BigDecimal(totalPrice).setScale(2,
				BigDecimal.ROUND_HALF_UP).toString();
		if (!result.contains(".")) {
			result += ".00";
		} else if (result.substring(result.indexOf(".")).length() == 2) {
			result += "0";
		}
		return result;
	}

}
