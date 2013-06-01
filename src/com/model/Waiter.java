package com.model;

import java.io.Serializable;
/**
 * 服务员类，该类主要是封装了服务员的基本信息，包含服务员的ID号、服务密码、服务员姓名、服务员年龄、服务员性别以及服务员职务。
 * @author ping
 *
 */
public class Waiter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String password;
	
	private String waiterName;
	
	private int age;
	
	private String gender;
	
	private String post;
	/**
	 * 获取服务员Id号
	 * @return Id 服务员Id号
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置服务员Id号
	 * @param _id 服务员Id号
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取服务员密码
	 * @return password 服务密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置服务员密码
	 * @param password 服务密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取服务员姓名
	 * @return waiterName 服务员姓名
	 */
	public String getWaiterName() {
		return waiterName;
	}
	/**
	 * 设置服务员姓名
	 * @param waiterName 服务员姓名
	 */
	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}
	/**
	 * 获取服务员年龄
	 * @return age 服务员年龄
	 */
	public int getAge() {
		return age;
	}
	/**
	 * 设置服务员年龄
	 * @param age 服务员年龄
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * 获取服务员性别
	 * @return gender 服务员性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置服务员性别
	 * @param gender 服务员性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取服务员职务
	 * @return post 服务员职务
	 */
	public String getPost() {
		return post;
	}
	/**
	 * 设置服务员职务
	 * @param post 服务员职务
	 */
	public void setPost(String post) {
		this.post = post;
	}

}
