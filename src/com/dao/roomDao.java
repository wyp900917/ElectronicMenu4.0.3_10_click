package com.dao;

import java.util.List;

import com.model.Room;
/**
 * �����ӿڣ��ýӿ��ж������йضԴ��������ķ���
 * @author ping
 *
 */
public interface roomDao {
	/**
	 * ͨ������Id�Ų��Ҵ���������Ϣ
	 * @param roomId ����Id��
	 * @return room ����һ����������
	 */
	public Room findRoomById(int roomId);
	/**
	 * �������еĴ�����Ϣ
	 * @return roomList ����һ�������б�
	 */
	public List<Room> findAll();

}
