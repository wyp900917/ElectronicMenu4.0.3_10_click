package com.model;

import java.io.Serializable;
/**
 * ����Ա�࣬������Ҫ�Ƿ�װ�˷���Ա�Ļ�����Ϣ����������Ա��ID�š��������롢����Ա����������Ա���䡢����Ա�Ա��Լ�����Աְ��
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
	 * ��ȡ����ԱId��
	 * @return Id ����ԱId��
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ���÷���ԱId��
	 * @param _id ����ԱId��
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ����Ա����
	 * @return password ��������
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * ���÷���Ա����
	 * @param password ��������
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * ��ȡ����Ա����
	 * @return waiterName ����Ա����
	 */
	public String getWaiterName() {
		return waiterName;
	}
	/**
	 * ���÷���Ա����
	 * @param waiterName ����Ա����
	 */
	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}
	/**
	 * ��ȡ����Ա����
	 * @return age ����Ա����
	 */
	public int getAge() {
		return age;
	}
	/**
	 * ���÷���Ա����
	 * @param age ����Ա����
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * ��ȡ����Ա�Ա�
	 * @return gender ����Ա�Ա�
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * ���÷���Ա�Ա�
	 * @param gender ����Ա�Ա�
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * ��ȡ����Աְ��
	 * @return post ����Աְ��
	 */
	public String getPost() {
		return post;
	}
	/**
	 * ���÷���Աְ��
	 * @param post ����Աְ��
	 */
	public void setPost(String post) {
		this.post = post;
	}

}
