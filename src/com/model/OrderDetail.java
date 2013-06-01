package com.model;

import java.io.Serializable;
/**
 * 订单明细类，封装了订单的明细信息，主要是订单的菜品信息，包含菜品ID号、订单ID号、菜品数量、菜品状态、菜品价格、菜品要求信息。
 * @author ping
 *
 */
public class OrderDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int foodId;
	
	private int orderId;
	
	private int foodNum;
	
	private int foodState;
	
	private float foodPrice;
	
	private String foodRequest;
	/**
	 * 获取菜品Id号
	 * @return foodId 菜品Id号
	 */
	public int getFoodId() {
		return foodId;
	}
	/**
	 * 设置菜品Id号
	 * @param foodId 菜品Id号
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	/**
	 * 获取订单Id号
	 * @return orderId 订单Id号
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * 设置订单Id号
	 * @param orderId 订单Id号
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取菜品数量
	 * @return foodNum 菜品数量
	 */
	public int getFoodNum() {
		return foodNum;
	}
	/**
	 * 设置菜品数量
	 * @param foodNum 菜品数量
	 */
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	/**
	 * 获取菜品状态
	 * @return foodState 菜品状态
	 */
	public int getFoodState() {
		return foodState;
	}
	/**
	 * 设置菜品状态
	 * @param foodState 菜品状态
	 */
	public void setFoodState(int foodState) {
		this.foodState = foodState;
	}
	/**
	 * 获取菜品价格
	 * @return foodPrice 菜品价格
	 */
	public float getFoodPrice() {
		return foodPrice;
	}
	/**
	 * 设置菜品价格
	 * @param foodPrice 菜品价格
	 */
	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}
	/**
	 * 获取菜品要求信息
	 * @return 菜品要求信息
	 */
	public String getFoodRequest() {
		return foodRequest;
	}
	/**
	 * 设置菜品要求信息
	 * @param foodRequest 菜品要求信息
	 */
	public void setFoodRequest(String foodRequest) {
		this.foodRequest = foodRequest;
	}

}
