package com.dao.Impl;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.dao.foodCategoryDao;
import com.db.DatabaseHelper;
import com.model.FoodCategory;

/**
 * ��Ʒ����ӿ�ʵ���࣬����ʵ���˲�Ʒ����ӿ��ж�������з���
 * <p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * 
 * @author ping
 * 
 */
public class foodCategoryDaoImpl extends DatabaseHelper implements
		foodCategoryDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * 
	 * @param context
	 */
	public foodCategoryDaoImpl(Context context) {
		super(context);
	}
/**
 * 
 */
	/**
	 * ��ѯ���в�Ʒ������Ϣ
	 */
	public List<FoodCategory> findAll() {
		return null;
	}

	/**
	 * ͨ����Ʒ�����Id���Ҹò�Ʒ���������
	 * 
	 * @param categoryId
	 *            ��Ʒ����Id
	 * @return CategoryName ��Ʒ��������
	 */
	public String findCategoryName(int categoryId, Context context) {
		SQLiteDatabase db = openDatabase();
		Cursor c = null;
		try {
			c = db.query("tb_foodCategory", null, "foodCategory="
					+ categoryId, null, null, null, null);
		} catch (SQLiteException sqlexception) {
			Toast.makeText(context, "���ݿ��ѯʧ�ܣ�����", Toast.LENGTH_SHORT).show();
			sqlexception.printStackTrace();
		}
		c.moveToFirst();
		String categoryName = c.getString(c.getColumnIndex("categoryName"));
		c.close();
		return categoryName;
	}

}
