package com.model;

import java.io.Serializable;
/**
 * IP�࣬��Ҫ��������װIP��ַ��Ϣ��
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
	 * ��ȡIP��ַ��Id��
	 * @return id IP��ַId��
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ����IP��ַId��
	 * @param _id IP��ַId��
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡIP��ַ�ĸ�����ֵ�ĵ�һ����
	 * @return IPpart1 IP��ַ�ĸ�����ֵ�ĵ�һ����
	 */
	public int getIPpart1() {
		return IPpart1;
	}
	/**
	 * ����IP��ַ�ĸ�����ֵ�ĵ�һ����
	 * @param iPpart1 IP��ַ�ĸ�����ֵ�ĵ�һ����
	 */
	public void setIPpart1(int iPpart1) {
		IPpart1 = iPpart1;
	}
	/**
	 * ��ȡIP��ַ�ĸ�����ֵ�ĵڶ�����
	 * @return IPpart2 IP��ַ�ĸ�����ֵ�ĵڶ�����
	 */
	public int getIPpart2() {
		return IPpart2;
	}
	/**
	 * ����IP��ַ�ĸ�����ֵ�ĵڶ�����
	 * @param iPpart2 IP��ַ�ĸ�����ֵ�ĵڶ�����
	 */
	public void setIPpart2(int iPpart2) {
		IPpart2 = iPpart2;
	}
	/**
	 * ��ȡIP��ַ�ĸ�����ֵ�ĵ�������
	 * @return IPpart1 IP��ַ�ĸ�����ֵ�ĵ�������
	 */
	public int getIPpart3() {
		return IPpart3;
	}
	/**
	 * ����IP��ַ�ĸ�����ֵ�ĵ�������
	 * @param iPpart3 IP��ַ�ĸ�����ֵ�ĵ�������
	 */
	public void setIPpart3(int iPpart3) {
		IPpart3 = iPpart3;
	}
	/**
	 * ��ȡIP��ַ�ĸ�����ֵ�ĵ��Ĳ���
	 * @return IPpart4 IP��ַ�ĸ�����ֵ�ĵ��Ĳ���
	 */
	public int getIPpart4() {
		return IPpart4;
	}
	/**
	 * ����IP��ַ�ĸ�����ֵ�ĵ��Ĳ���
	 * @param iPpart4 IP��ַ�ĸ�����ֵ�ĵ��Ĳ���
	 */
	public void setIPpart4(int iPpart4) {
		IPpart4 = iPpart4;
	}
	/**
	 * ��ȡ�˿ں�
	 * @return Port �˿ں�
	 */
	public int getPort() {
		return Port;
	}
	/**
	 * ���ö˿ں�
	 * @param port �˿ں�
	 */
	public void setPort(int port) {
		Port = port;
	}

}
