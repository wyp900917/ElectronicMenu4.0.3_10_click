package com.model;

import java.io.Serializable;
/**
 * 菜品类，封装了菜品的所有信息。
 * @author ping
 *
 */
public class Food implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String foodName;
	
	private float costPrice;
	
	private float presentPrice;
	
	private String introduction;
	
	private String picture;
	
	private int foodCategoryId;
	/**
	 * 获取菜品Id
	 * @return id 菜品Id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置菜品Id
	 * @param _id 菜品Id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * 获取菜品名称
	 * @return foodName 菜品名称
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * 获取菜品名称
	 * @param foodName 菜品名称
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * 获取菜品原价
	 * @return costPrice 菜品原价
	 */
	public float getCostPrice() {
		return costPrice;
	}
	/**
	 * 设置菜品原价
	 * @param costPrice 菜品原价
	 */
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * 获取菜品现价
	 * @return presentPrice 菜品现价
	 */
	public float getPresentPrice() {
		return presentPrice;
	}
	/**
	 * 设置菜品现价
	 * @param presentPrice 菜品现价
	 */
	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}
	/**
	 * 获取菜品详细介绍
	 * @return introduction 菜品详细介绍
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置菜品详细接好
	 * @param introduction 菜品详细介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 获取菜品图片保存路径
	 * @return picture 菜品图片保存路径
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * 设置菜品图片保存路径
	 * @param picture 菜品图片保存路径
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取菜品种类Id
	 * @return foodCategoryId 菜品种类Id
	 */
	public int getFoodCategoryId() {
		return foodCategoryId;
	}
	/**
	 * 设置菜品种类Id
	 * @param foodCategoryId 菜品种类Id
	 */
	public void setFoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}

}
