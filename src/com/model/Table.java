package com.model;

import java.io.Serializable;
/**
 * 桌台信息类。主要是封装了桌台的相关信息，包含桌台ID、桌台状态、入座人数、所在大厅ID号。
 * @author ping
 *
 */
public class Table implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private int state;//1为有人；0为空座；-1为预定
	
	private int peopleNum;
	
	private int roomId;
	/**
	 * 获取桌台Id号
	 * @return Id 桌台Id号
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置桌台Id号
	 * @param _id 桌台Id号
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取桌台状态信息
	 * @return state 桌台状态信息
	 */
	public int getState() {
		return state;
	}
	/**
	 * 设置桌台状态信息
	 * @param state 桌台状态信息
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * 获取桌台入座人数
	 * @return peopleNum 桌台入座人数
	 */
	public int getPeopleNum() {
		return peopleNum;
	}
	/**
	 * 设置桌台入座人数
	 * @param peopleNum 桌台入座人数
	 */
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	/**
	 * 获取桌台所在大厅Id号
	 * @return roomId 大厅Id号
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * 设置桌台所在大厅的Id号
	 * @param roomId 大厅Id号
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

}
