package com.model;

import java.io.Serializable;
/**
 * 大厅类，封装了大厅相关的信息，包含大厅ID号、大厅名称等。
 * @author ping
 *
 */
public class Room implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String roomName;
	/**
	 * 获取大厅Id号
	 * @return Id 大厅Id号
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置大厅Id号
	 * @param _id 大厅Id号
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取大厅名称
	 * @return roomName 大厅名称
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * 设置大厅名称
	 * @param roomName 大厅名称
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
