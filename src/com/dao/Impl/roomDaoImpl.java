package com.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.roomDao;
import com.db.DatabaseHelper;
import com.model.Room;
/**
 * �����ӿ�ʵ���࣬����ʵ���˴����ӿ��ж�������з���<p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * @author ping
 *
 */
public class roomDaoImpl extends DatabaseHelper implements roomDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * @param context
	 */
	public roomDaoImpl(Context context) {
		super(context);
	}
	/**
	 * ͨ������Id�Ų��Ҵ���������Ϣ
	 * @param roomId ����Id��
	 * @return room ����һ����������
	 */
	public Room findRoomById(int roomId) {
		return null;
	}
	/**
	 * �������еĴ�����Ϣ
	 * @return roomList ����һ�������б�
	 */
	public List<Room> findAll() {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_room", null, null, null, null, null, null);
		List<Room> roomlist = new ArrayList<Room>();
		while (c.moveToNext()) {
			Room room = new Room();
			room.set_id(c.getInt(c.getColumnIndex("roomID")));
			room.setRoomName(c.getString(c.getColumnIndex("roomName")));
			roomlist.add(room);
		}
		c.close();
		return roomlist;
	}

}
