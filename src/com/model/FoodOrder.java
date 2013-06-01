package com.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 下单菜品信息类，是传递给服务器下单菜品信息的封装类；包含了订单ID、桌台ID、服务ID、菜品ID、点菜数量、总价、菜品要求信息以及下单时间。
 * @author ping
 *
 */
public class FoodOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	
	private int tableId;
	
	private int waiterId;
	
	private int foodId;
	
	private int foodNum;
	
	private float totalPrice;
	
	private String foodRequest;
	
	private Date xiadanTime;
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
	 * 获取桌台Id号
	 * @return tableId 桌台Id号
	 */
	public int getTableId() {
		return tableId;
	}
	/**
	 * 设置桌台Id号
	 * @param tableId 桌台Id号
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	/**
	 * 获取服务工号Id
	 * @return waterId 服务工号Id 
	 */
	public int getWaiterId() {
		return waiterId;
	}
	/**
	 * 设置服务工号Id
	 * @param waiterId 服务工号Id
	 */
	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}
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
	 * 获取订单总价
	 * @return totalPrice 订单总价
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置订单总价
	 * @param totalPrice 订单总价
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取菜品要求信息
	 * @return foodRequest 菜品要求信息
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
	/**
	 * 获取订单下单时间
	 * @return xiandanTime 下单时间
	 */
	public Date getXiadanTime() {
		return xiadanTime;
	}
	/**
	 * 设置订单下单时间
	 * @param xiadanTime 订单下单时间
	 */
	public void setXiadanTime(Date xiadanTime) {
		this.xiadanTime = xiadanTime;
	}

}
