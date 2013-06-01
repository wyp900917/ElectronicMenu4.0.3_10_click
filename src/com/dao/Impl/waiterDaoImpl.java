package com.dao.Impl;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.waiterDao;
import com.db.DatabaseHelper;
import com.model.Waiter;
/**
 * 服务员接口实现类，该类实现了服务员接口中定义的所有方法<p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * @author ping
 *
 */
public class waiterDaoImpl extends DatabaseHelper implements waiterDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * @param context
	 */
	public waiterDaoImpl(Context context) {
		super(context);
	}
	/**
	 * 通过服务ID查找服务员对象
	 * @param writerId 服务Id号
	 * @return 返回一个服务员对象
	 */
	public Waiter findWriterById(int writerId) {
		return null;
	}
	/**
	 * 查找服务员表中所有的服务员信息
	 * @return 返回服务员信息列表
	 */
	public List<Waiter> findAllWaiter() {
		return null;
	}
	/**
	 * 通过服务Id号查找该服务员的密码
	 * @param waiterId 服务Id号
	 * @return 返回该服务Id号的密码
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
