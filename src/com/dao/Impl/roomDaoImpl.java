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
 * 大厅接口实现类，该类实现了大厅接口中定义的所有方法<p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * @author ping
 *
 */
public class roomDaoImpl extends DatabaseHelper implements roomDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * @param context
	 */
	public roomDaoImpl(Context context) {
		super(context);
	}
	/**
	 * 通过大厅Id号查找大厅对象信息
	 * @param roomId 大厅Id号
	 * @return room 返回一个大厅对象
	 */
	public Room findRoomById(int roomId) {
		return null;
	}
	/**
	 * 查找所有的大厅信息
	 * @return roomList 返回一个大厅列表
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
