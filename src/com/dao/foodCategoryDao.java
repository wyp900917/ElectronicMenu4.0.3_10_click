package com.dao;

import java.util.List;

import android.content.Context;

import com.model.FoodCategory;
/**
 * 菜品种类接口，该接口定义了有关菜品种类表的操作方法。
 * @author ping
 *
 */
public interface foodCategoryDao {
	/**
	 * 查找所有菜品种类信息，返回一个菜品种类列表
	 * @return FoodCategoryList 所有菜品种类的List列表
	 */
	public List<FoodCategory> findAll();
	/**
	 * 通过菜品种类的Id查找该菜品种类的名称
	 * @param categoryId 菜品种类Id
	 * @return CategoryName 菜品种类名称
	 */
	public String findCategoryName(int categoryId, Context context);
	
}
