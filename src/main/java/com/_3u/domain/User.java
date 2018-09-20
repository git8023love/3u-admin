package com._3u.domain;

import java.io.Serializable;

/**
 * 用户基本信息
 * 
 * @author jonny
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8407630188480345146L;

	private int id;

	// 登录名称
	private String username;
	// 真实姓名
	private String realName;
	// 手机号码
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
