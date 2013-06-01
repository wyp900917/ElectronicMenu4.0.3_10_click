package com.dao;

import java.util.List;

import android.content.Context;

import com.model.Food;
/**
 * 菜品信息接口，该接口定义了关于菜品表的操作方法。
 * @author ping
 *
 */
public interface foodDao {
	/**
	 * 查找所有菜品信息，返回一个菜品列表
	 * @return foodList 所有菜品信息的列表
	 */
	public List<Food> findAll();
	/**
	 * 通过菜品Id查找菜品信息，并返回一个菜品对象
	 * @param foodId 菜品Id号
	 * @return food 返回一个菜品对象
	 */
	public Food findFoodById(int foodId, Context context);
	/**
	 * 通过菜品种类Id号查找该菜品种类所包含的所有菜品信息。
	 * @param categoryId 菜品种类Id号
	 * @return foodList 返回一个菜品列表
	 */
	public List<Food> findBycategoryId(int categoryId);
	/**
	 * 通过菜品Id号查找该菜品的名称
	 * @param foodId 菜品Id号
	 * @return foodName 返回菜品名称
	 */
	public String getFoodName(int foodId);
	/**
	 * 通过关键字查找菜品名称与关键字相符合的菜品信息。
	 * @param foodkey 关键字
	 * @return foodList 返回一个菜品列表
	 */
	public List<Food> findByfoodkey(String foodkey);

}
