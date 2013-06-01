package com.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.tableDao;
import com.db.DatabaseHelper;
import com.model.Table;
/**
 * 桌台接口实现类，该类实现了桌台接口中定义的所有方法<p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * @author ping
 *
 */
public class tableDaoImpl extends DatabaseHelper implements tableDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * @param context
	 */
	public tableDaoImpl(Context context) {
		super(context);
	}
	/**
	 * 通过桌台Id号查找桌台对象信息
	 * @param tableId 桌台Id号
	 * @return table 返回一个桌台对象信息
	 */
	public Table findTableById(int tableId) {
		return null;
	}
	/**
	 * 通过桌台的状态查找所有该状态的桌台信息
	 * @param state 桌台状态信息
	 * @return tableList 返回一个桌台列表
	 */
	public List<Table> findTableByState(int state) {
		return null;
	}
	/**
	 * 查找所有的桌台信息
	 * @return tableList 返回所有的桌台信息列表
	 */
	public List<Table> findAllTable() {
		return null;
	}
	/**
	 * 通过大厅的Id号查找该大厅下所有的桌台信息
	 * @param roomId 大厅Id号
	 * @return tableList 返回该大厅下所有的桌台信息列表
	 */
	public List<Table> findTableByroomId(int roomId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_table", null, "roomID = " + roomId, null, null,
				null, null);
		List<Table> tablelist = new ArrayList<Table>();
		while (c.moveToNext()) {
			Table table = new Table();
			table.set_id(c.getInt(c.getColumnIndex("tableID")));
			table.setPeopleNum(c.getInt(c.getColumnIndex("peopleNum")));
			table.setRoomId(c.getInt(c.getColumnIndex("roomID")));
			table.setState(c.getInt(c.getColumnIndex("state")));
			tablelist.add(table);
		}
		c.close();
		return tablelist;
	}
	/**
	 * 更新桌台,修改桌台相关的属性
	 * @param table 传递一个桌台对象
	 */
	public void update(Table table) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("tableID", table.get_id());
		val.put("state", table.getState());
		val.put("peopleNum", table.getPeopleNum());
		val.put("roomID", table.getRoomId());
		db.insert("tb_table", null, val);
	}
	/**
	 * 通过桌台Id号来修改该桌台的状态
	 * @param tableId 桌台Id号
	 * @param state 需要修改的桌台状态
	 */
	public void updateState(int tableId, int state) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("state", state);
		db.update("tb_table", val, "tableID=" + tableId, null);
	}
	/**
	 * 删除所有的桌台信息
	 */
	public void deleteAllTable() {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_table";
		db.execSQL(sql);
	}

}
