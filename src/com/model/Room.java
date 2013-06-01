package com.model;

import java.io.Serializable;
/**
 * �����࣬��װ�˴�����ص���Ϣ����������ID�š��������Ƶȡ�
 * @author ping
 *
 */
public class Room implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String roomName;
	/**
	 * ��ȡ����Id��
	 * @return Id ����Id��
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ���ô���Id��
	 * @param _id ����Id��
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ��������
	 * @return roomName ��������
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * ���ô�������
	 * @param roomName ��������
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
