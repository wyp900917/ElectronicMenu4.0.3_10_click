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
 * 菜品种类接口实现类，该类实现了菜品种类接口中定义的所有方法
 * <p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * 
 * @author ping
 * 
 */
public class foodCategoryDaoImpl extends DatabaseHelper implements
		foodCategoryDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
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
	 * 查询所有菜品种类信息
	 */
	public List<FoodCategory> findAll() {
		return null;
	}

	/**
	 * 通过菜品种类的Id查找该菜品种类的名称
	 * 
	 * @param categoryId
	 *            菜品种类Id
	 * @return CategoryName 菜品种类名称
	 */
	public String findCategoryName(int categoryId, Context context) {
		SQLiteDatabase db = openDatabase();
		Cursor c = null;
		try {
			c = db.query("tb_foodCategory", null, "foodCategory="
					+ categoryId, null, null, null, null);
		} catch (SQLiteException sqlexception) {
			Toast.makeText(context, "数据库查询失败！！！", Toast.LENGTH_SHORT).show();
			sqlexception.printStackTrace();
		}
		c.moveToFirst();
		String categoryName = c.getString(c.getColumnIndex("categoryName"));
		c.close();
		return categoryName;
	}

}
