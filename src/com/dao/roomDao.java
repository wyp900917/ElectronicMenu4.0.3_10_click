package com.dao;

import java.util.List;

import com.model.Room;
/**
 * 大厅接口，该接口中定义了有关对大厅操作的方法
 * @author ping
 *
 */
public interface roomDao {
	/**
	 * 通过大厅Id号查找大厅对象信息
	 * @param roomId 大厅Id号
	 * @return room 返回一个大厅对象
	 */
	public Room findRoomById(int roomId);
	/**
	 * 查找所有的大厅信息
	 * @return roomList 返回一个大厅列表
	 */
	public List<Room> findAll();

}
