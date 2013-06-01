package com.model;

import java.io.Serializable;
/**
 * 菜品种类信息类，该类中封装了菜品种类的Id号以及菜品种类名称信息。
 * @author ping
 *
 */
public class FoodCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String categoryName;
	/**
	 * 获取菜品种类Id
	 * @return Id 菜品种类Id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置菜品种类Id
	 * @param _id 菜品种类Id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取菜品种类名称
	 * @return categoryName 菜品种类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置菜品种类名称
	 * @param categoryName 菜品种类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
