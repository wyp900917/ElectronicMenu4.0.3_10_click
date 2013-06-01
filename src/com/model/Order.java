package com.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 订单类，该类主要是封装了订单的相关信息，包含订单ID号、桌台ID号、服务工号ID、菜品总价、订单状态以及下单时间信息。
 * @author ping
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int _id;

	private int tableId;

	private int waiterId;

	private float totalPrice;

	private int orderState;// 1表示未结帐；0表示已结账

	private Date time;
	/**
	 * 获取订单Id号
	 * @return id 订单Id号
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * 设置订单Id号
	 * @param _id 订单Id号
	 */
	public void set_id(int _id) {
		this._id = _id;
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
	 * @return waiterId 服务工号Id
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
	 * 获取菜品总价
	 * @return totalPrice 菜品总价
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置菜品总价
	 * @param totalPrice 菜品总价
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取订单状态
	 * @return orderState 订单状态
	 */
	public int getOrderState() {
		return orderState;
	}
	/**
	 * 设置订单状态
	 * @param orderState 订单状态
	 */
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取下单时间
	 * @return time 下单时间
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * 设置下单时间
	 * @param time 下单时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}

}
