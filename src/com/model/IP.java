package com.model;

import java.io.Serializable;
/**
 * IP类，主要是用来封装IP地址信息。
 * @author ping
 *
 */
public class IP implements Serializable {
	private static final long serialVersionUID = 1L;

	private int _id;

	private int IPpart1;

	private int IPpart2;

	private int IPpart3;

	private int IPpart4;
	
	private int Port;
	/**
	 * 获取IP地址的Id号
	 * @return id IP地址Id号
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置IP地址Id号
	 * @param _id IP地址Id号
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取IP地址四个整形值的第一部分
	 * @return IPpart1 IP地址四个整形值的第一部分
	 */
	public int getIPpart1() {
		return IPpart1;
	}
	/**
	 * 设置IP地址四个整形值的第一部分
	 * @param iPpart1 IP地址四个整形值的第一部分
	 */
	public void setIPpart1(int iPpart1) {
		IPpart1 = iPpart1;
	}
	/**
	 * 获取IP地址四个整形值的第二部分
	 * @return IPpart2 IP地址四个整形值的第二部分
	 */
	public int getIPpart2() {
		return IPpart2;
	}
	/**
	 * 设置IP地址四个整形值的第二部分
	 * @param iPpart2 IP地址四个整形值的第二部分
	 */
	public void setIPpart2(int iPpart2) {
		IPpart2 = iPpart2;
	}
	/**
	 * 获取IP地址四个整形值的第三部分
	 * @return IPpart1 IP地址四个整形值的第三部分
	 */
	public int getIPpart3() {
		return IPpart3;
	}
	/**
	 * 设置IP地址四个整形值的第三部分
	 * @param iPpart3 IP地址四个整形值的第三部分
	 */
	public void setIPpart3(int iPpart3) {
		IPpart3 = iPpart3;
	}
	/**
	 * 获取IP地址四个整形值的第四部分
	 * @return IPpart4 IP地址四个整形值的第四部分
	 */
	public int getIPpart4() {
		return IPpart4;
	}
	/**
	 * 设置IP地址四个整形值的第四部分
	 * @param iPpart4 IP地址四个整形值的第四部分
	 */
	public void setIPpart4(int iPpart4) {
		IPpart4 = iPpart4;
	}
	/**
	 * 获取端口号
	 * @return Port 端口号
	 */
	public int getPort() {
		return Port;
	}
	/**
	 * 设置端口号
	 * @param port 端口号
	 */
	public void setPort(int port) {
		Port = port;
	}

}
