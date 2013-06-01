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
 * ��̨�ӿ�ʵ���࣬����ʵ������̨�ӿ��ж�������з���<p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * @author ping
 *
 */
public class tableDaoImpl extends DatabaseHelper implements tableDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * @param context
	 */
	public tableDaoImpl(Context context) {
		super(context);
	}
	/**
	 * ͨ����̨Id�Ų�����̨������Ϣ
	 * @param tableId ��̨Id��
	 * @return table ����һ����̨������Ϣ
	 */
	public Table findTableById(int tableId) {
		return null;
	}
	/**
	 * ͨ����̨��״̬�������и�״̬����̨��Ϣ
	 * @param state ��̨״̬��Ϣ
	 * @return tableList ����һ����̨�б�
	 */
	public List<Table> findTableByState(int state) {
		return null;
	}
	/**
	 * �������е���̨��Ϣ
	 * @return tableList �������е���̨��Ϣ�б�
	 */
	public List<Table> findAllTable() {
		return null;
	}
	/**
	 * ͨ��������Id�Ų��Ҹô��������е���̨��Ϣ
	 * @param roomId ����Id��
	 * @return tableList ���ظô��������е���̨��Ϣ�б�
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
	 * ������̨,�޸���̨��ص�����
	 * @param table ����һ����̨����
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
	 * ͨ����̨Id�����޸ĸ���̨��״̬
	 * @param tableId ��̨Id��
	 * @param state ��Ҫ�޸ĵ���̨״̬
	 */
	public void updateState(int tableId, int state) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("state", state);
		db.update("tb_table", val, "tableID=" + tableId, null);
	}
	/**
	 * ɾ�����е���̨��Ϣ
	 */
	public void deleteAllTable() {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_table";
		db.execSQL(sql);
	}

}
