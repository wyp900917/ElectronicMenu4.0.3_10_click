package com.model;

import java.io.Serializable;
/**
 * ��̨��Ϣ�ࡣ��Ҫ�Ƿ�װ����̨�������Ϣ��������̨ID����̨״̬���������������ڴ���ID�š�
 * @author ping
 *
 */
public class Table implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private int state;//1Ϊ���ˣ�0Ϊ������-1ΪԤ��
	
	private int peopleNum;
	
	private int roomId;
	/**
	 * ��ȡ��̨Id��
	 * @return Id ��̨Id��
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ������̨Id��
	 * @param _id ��̨Id��
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ��̨״̬��Ϣ
	 * @return state ��̨״̬��Ϣ
	 */
	public int getState() {
		return state;
	}
	/**
	 * ������̨״̬��Ϣ
	 * @param state ��̨״̬��Ϣ
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * ��ȡ��̨��������
	 * @return peopleNum ��̨��������
	 */
	public int getPeopleNum() {
		return peopleNum;
	}
	/**
	 * ������̨��������
	 * @param peopleNum ��̨��������
	 */
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	/**
	 * ��ȡ��̨���ڴ���Id��
	 * @return roomId ����Id��
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * ������̨���ڴ�����Id��
	 * @param roomId ����Id��
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
