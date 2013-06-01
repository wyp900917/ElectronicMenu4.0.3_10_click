package com.dao.Impl;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.waiterDao;
import com.db.DatabaseHelper;
import com.model.Waiter;
/**
 * ����Ա�ӿ�ʵ���࣬����ʵ���˷���Ա�ӿ��ж�������з���<p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * @author ping
 *
 */
public class waiterDaoImpl extends DatabaseHelper implements waiterDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * @param context
	 */
	public waiterDaoImpl(Context context) {
		super(context);
	}
	/**
	 * ͨ������ID���ҷ���Ա����
	 * @param writerId ����Id��
	 * @return ����һ������Ա����
	 */
	public Waiter findWriterById(int writerId) {
		return null;
	}
	/**
	 * ���ҷ���Ա�������еķ���Ա��Ϣ
	 * @return ���ط���Ա��Ϣ�б�
	 */
	public List<Waiter> findAllWaiter() {
		return null;
	}
	/**
	 * ͨ������Id�Ų��Ҹ÷���Ա������
	 * @param waiterId ����Id��
	 * @return ���ظ÷���Id�ŵ�����
	 */
	public String checkPassword(int waiterId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_waiter", null, "waiterID=" + waiterId, null,
				null, null, null);
		if (!c.moveToNext()) {
			return null;
		} else
			return c.getString(c.getColumnIndex("password"));
	}

}
