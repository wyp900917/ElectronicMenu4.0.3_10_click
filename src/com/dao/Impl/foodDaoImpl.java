package com.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.dao.foodDao;
import com.db.DatabaseHelper;
import com.model.Food;

/**
 * ��Ʒ�ӿ�ʵ���࣬����ʵ���˲�Ʒ�ӿ��ж�������з���
 * <p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * 
 * @author ping
 * 
 */
public class foodDaoImpl extends DatabaseHelper implements foodDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * 
	 * @param context
	 */
	public foodDaoImpl(Context context) {
		super(context);
	}

	/**
	 * �������в�Ʒ��Ϣ������һ����Ʒ�б�
	 * 
	 * @return foodList ���в�Ʒ��Ϣ���б�
	 */
	public List<Food> findAll() {
		SQLiteDatabase db = openDatabase();
		String sql = "select * from tb_food";
		db.execSQL(sql);
		return null;
	}

	/**
	 * ͨ����ƷId���Ҳ�Ʒ��Ϣ��������һ����Ʒ����
	 * 
	 * @param foodId
	 *            ��ƷId��
	 * @return food ����һ����Ʒ����
	 */
	public Food findFoodById(int foodId, Context context) {
		Food food = null;
		try {
			SQLiteDatabase db = openDatabase();
			Cursor c = db.query("tb_food", null, "foodID=" + foodId, null,
					null, null, null);
			c.moveToFirst();
			food = new Food();
			food.set_id(c.getInt(c.getColumnIndex("foodID")));
			food.setFoodName(c.getString(c.getColumnIndex("foodName")));
			food.setCostPrice(c.getFloat(c.getColumnIndex("costPrice")));
			food.setPresentPrice(c.getFloat(c.getColumnIndex("presentPrice")));
			food.setIntroduction(c.getString(c.getColumnIndex("introduction")));
			food.setPicture(c.getString(c.getColumnIndex("picture")));
			food.setFoodCategoryId(c.getInt(c.getColumnIndex("foodCategoryID")));
			c.close();
		} catch (SQLiteException sqlexception) {
			Toast.makeText(context, "���ݿ��ѯʧ�ܣ�����", Toast.LENGTH_SHORT).show();
			sqlexception.printStackTrace();
		}
		return food;
	}

	/**
	 * ͨ����Ʒ����Id�Ų��Ҹò�Ʒ���������������в�Ʒ��Ϣ��
	 * 
	 * @param categoryId
	 *            ��Ʒ����Id��
	 * @return foodList ����һ����Ʒ�б�
	 */
	public List<Food> findBycategoryId(int categoryId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_food", null, "foodCategoryID=" + categoryId,
				null, null, null, null);
		List<Food> foodList = new ArrayList<Food>();
		while (c.moveToNext()) {
			Food food = new Food();
			food.set_id(c.getInt(c.getColumnIndex("foodID")));
			food.setFoodName(c.getString(c.getColumnIndex("foodName")));
			food.setCostPrice(c.getFloat(c.getColumnIndex("costPrice")));
			food.setPresentPrice(c.getFloat(c.getColumnIndex("presentPrice")));
			food.setIntroduction(c.getString(c.getColumnIndex("introduction")));
			food.setPicture(c.getString(c.getColumnIndex("picture")));
			food.setFoodCategoryId(c.getInt(c.getColumnIndex("foodCategoryID")));
			foodList.add(food);
		}
		c.close();
		return foodList;
	}

	/**
	 * ͨ����ƷId�Ų��Ҹò�Ʒ������
	 * 
	 * @param foodId
	 *            ��ƷId��
	 * @return foodName ���ز�Ʒ����
	 */
	public String getFoodName(int foodId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_food", null, "foodID=" + foodId, null, null,
				null, null);
		c.moveToNext();
		return c.getString(c.getColumnIndex("foodName"));
	}

	/**
	 * ͨ���ؼ��ֲ��Ҳ�Ʒ������ؼ�������ϵĲ�Ʒ��Ϣ��
	 * 
	 * @param foodkey
	 *            �ؼ���
	 * @return foodList ����һ����Ʒ�б�
	 */
	public List<Food> findByfoodkey(String foodkey) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_food", null, "foodName like '%" + foodkey
				+ "%'", null, null, null, null);
		List<Food> foodList = new ArrayList<Food>();
		while (c.moveToNext()) {
			Food food = new Food();
			food.set_id(c.getInt(c.getColumnIndex("foodID")));
			food.setFoodName(c.getString(c.getColumnIndex("foodName")));
			food.setCostPrice(c.getFloat(c.getColumnIndex("costPrice")));
			food.setPresentPrice(c.getFloat(c.getColumnIndex("presentPrice")));
			food.setIntroduction(c.getString(c.getColumnIndex("introduction")));
			food.setPicture(c.getString(c.getColumnIndex("picture")));
			food.setFoodCategoryId(c.getInt(c.getColumnIndex("foodCategoryID")));
			foodList.add(food);
		}
		c.close();
		return foodList;
	}

}
