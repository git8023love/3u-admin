package com._3u.config;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class Config {
	public static String WHITE_IP;
	public static String TICKET_URL;
	public static String TICKET_RESET_URL;
	public static String LOGIN_URL;
	public static String LOGOUT_URL;
	public static String BASE_PATH;
	public static String PROJECT_PATH;
	public static String NEWS_IMG_PATH;
	public static String AD_IMG_PATH;
	public static String BASE_URL;
	public static String CDN_URL;
	public static String SORT_IMG_PATH;
	public static String PCADV_IMG_PATH;
	public static String PCLOG_URL;
	public static String SPECIAL_PAPER_CACHE_URL;
	public static String SPECIAL_RING_CACHE_URL;
	public static String SPECIAL_PAPER_IMG_PATH;
	public static String SPECIAL_RING_IMG_PATH;
	
	private static final String KEY_WHITE_IP = "white.ip";
	private static final String KEY_TICKET_URL = "ticket.url";
	private static final String KEY_TICKET_RESET_URL = "ticket.reset.url";
	private static final String KEY_LOGIN_URL = "login.url";
	private static final String KEY_LOGOUT_URL = "logout.url";
	private static final String KEY_BASE_PATH = "base.path";
	private static final String KEY_PROJECT_PATH = "project.path";
	private static final String KEY_NEWS_IMG_PATH = "news.img.path";
	private static final String KEY_AD_IMG_PATH = "ad.img.path";
	private static final String KEY_BASE_URL = "base.url";
	private static final String KEY_CDN_URL = "cdn.url";
	private static final String KEY_SORT_IMG_PATH = "sort.img.path";
	private static final String KEY_PCADV_IMG_PATH = "pcAdv.img.path";
	private static final String KEY_PCLOG_URL = "pcLog.url";
	private static final String KEY_SPECIAL_PAPER_CACHE_URL = "special.paper.cache.url";
	private static final String KEY_SPECIAL_RING_CACHE_URL = "special.ring.cache.url";
	private static final String KEY_SPECIAL_PAPER_IMG_PATH = "special.paper.img.path";
	private static final String KEY_SPECIAL_RING_IMG_PATH = "special.ring.img.path";
	
	private static final String CONF_FILE_NAME = "config.properties";
	
	static {
        try {
			InputStream fis = Config.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			WHITE_IP = props.getString(KEY_WHITE_IP);
			TICKET_URL = props.getString(KEY_TICKET_URL);
			TICKET_RESET_URL = props.getString(KEY_TICKET_RESET_URL);
			LOGIN_URL = props.getString(KEY_LOGIN_URL);
			LOGOUT_URL = props.getString(KEY_LOGOUT_URL);
			NEWS_IMG_PATH = props.getString(KEY_NEWS_IMG_PATH);
			AD_IMG_PATH = props.getString(KEY_AD_IMG_PATH);
			BASE_URL = props.getString(KEY_BASE_URL);
			CDN_URL = props.getString(KEY_CDN_URL);
			SORT_IMG_PATH = props.getString(KEY_SORT_IMG_PATH);
			PCADV_IMG_PATH = props.getString(KEY_PCADV_IMG_PATH);
			PCLOG_URL = props.getString(KEY_PCLOG_URL);
			SPECIAL_PAPER_CACHE_URL = props.getString(KEY_SPECIAL_PAPER_CACHE_URL);
			SPECIAL_RING_CACHE_URL = props.getString(KEY_SPECIAL_RING_CACHE_URL);
			SPECIAL_PAPER_IMG_PATH = props.getString(KEY_SPECIAL_PAPER_IMG_PATH);
			SPECIAL_RING_IMG_PATH = props.getString(KEY_SPECIAL_RING_IMG_PATH);
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
