package com.model;

import java.io.Serializable;
/**
 * 所点菜品信息类，该类主要是保存当前已点菜品的信息，封装了菜品基本信息，包含菜品ID、菜品名称、价格、点菜数量、菜品状态以及对菜品要求信息。
 * @author ping
 *
 */
public class ChoosedFood implements Serializable {
	private static final long serialVersionUID = 1L;

	private int foodId;

	private String foodName;

	private float costprice;
	
	private float presentprice;

	private int foodNum;

	private int state;// 0表示未下单，1表示已下单
	
	private String requset;
	/**
	 * 获取菜品Id
	 * @return foodId 菜品Id
	 */
	public int getFoodId() {
		return foodId;
	}
	/**
	 * 设置菜品Id
	 * @param foodId 菜品Id
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	/**
	 * 获取菜品名称
	 * @return foodName 菜品名称
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * 设置菜品名称
	 * @param foodName 菜品名称
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * 获取菜品单价
	 * @return price 菜品单价
	 */
	public float getCostprice() {
		return costprice;
	}
	/**
	 * 设置菜品原价
	 * @param price 菜品原价
	 */
	public void setCostprice(float costprice) {
		this.costprice = costprice;
	}
	/**
	 * 设置菜品现价
	 * @return presentPrice 菜品现价
	 */
	public float getPresentprice() {
		return presentprice;
	}
	/**
	 * 获取菜品现价
	 * @param presentprice 菜品现价
	 */
	public void setPresentprice(float presentprice) {
		this.presentprice = presentprice;
	}
	/**
	 * 获取菜品点菜数量
	 * @return foodNum 菜品点菜数量
	 */
	public int getFoodNum() {
		return foodNum;
	}
	/**
	 * 设置菜品点菜数量
	 * @param foodNum 菜品点菜数量
	 */
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	/**
	 * 获取菜品状态
	 * @return state 菜品状态
	 */
	public int getState() {
		return state;
	}
	/**
	 * 设置菜品状态
	 * @param state 菜品状态
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * 获取菜品要求
	 * @return request 菜品要求
	 */
	public String getRequset() {
		return requset;
	}
	/**
	 * 设置菜品要求
	 * @param requset 菜品要求
	 */
	public void setRequset(String requset) {
		this.requset = requset;
	}

}
