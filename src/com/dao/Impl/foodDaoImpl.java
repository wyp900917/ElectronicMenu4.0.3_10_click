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
 * 菜品接口实现类，该类实现了菜品接口中定义的所有方法
 * <p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * 
 * @author ping
 * 
 */
public class foodDaoImpl extends DatabaseHelper implements foodDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * 
	 * @param context
	 */
	public foodDaoImpl(Context context) {
		super(context);
	}

	/**
	 * 查找所有菜品信息，返回一个菜品列表
	 * 
	 * @return foodList 所有菜品信息的列表
	 */
	public List<Food> findAll() {
		SQLiteDatabase db = openDatabase();
		String sql = "select * from tb_food";
		db.execSQL(sql);
		return null;
	}

	/**
	 * 通过菜品Id查找菜品信息，并返回一个菜品对象
	 * 
	 * @param foodId
	 *            菜品Id号
	 * @return food 返回一个菜品对象
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
			Toast.makeText(context, "数据库查询失败！！！", Toast.LENGTH_SHORT).show();
			sqlexception.printStackTrace();
		}
		return food;
	}

	/**
	 * 通过菜品种类Id号查找该菜品种类所包含的所有菜品信息。
	 * 
	 * @param categoryId
	 *            菜品种类Id号
	 * @return foodList 返回一个菜品列表
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
	 * 通过菜品Id号查找该菜品的名称
	 * 
	 * @param foodId
	 *            菜品Id号
	 * @return foodName 返回菜品名称
	 */
	public String getFoodName(int foodId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_food", null, "foodID=" + foodId, null, null,
				null, null);
		c.moveToNext();
		return c.getString(c.getColumnIndex("foodName"));
	}

	/**
	 * 通过关键字查找菜品名称与关键字相符合的菜品信息。
	 * 
	 * @param foodkey
	 *            关键字
	 * @return foodList 返回一个菜品列表
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
