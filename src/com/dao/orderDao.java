package com.dao;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.model.Order;
/**
 * 订单信息接口，该接口定义了对订单操作的一系列方法和操作。
 * @author ping
 *
 */
public interface orderDao {
	/**
	 * 新增一个订单信息
	 * @param order 传递一个将要新增的订单对象
	 * @return long 返回一个长整型判断是否新增成功
	 */
	public long addOrder(Order order);
	/**
	 * 通过订单Id号查找该订单信息
	 * @param orderId 订单Id号
	 * @return order 返回一个订单对象
	 */
	// 通过订单号查询
	public Order findOrderById(int orderId);
	/**
	 * 查找所有的订单信息
	 * @return orderList 返回一个订单信息列表
	 */
	// 查询所有订单
	public List<Order> findAll();
	/**
	 * 通过服务员Id号查找订单，将所有服务工号是该服务Id的订单查找出来
	 * @param waiterId 服务员Id号
	 * @return orderList 返回一个订单列表
	 */
	public List<Order> findOrderByWaiterId(int waiterId, Context context);
	/**
	 * 将传递的订单进行下单操作
	 * @param order 将要下单的订单对象
	 */
	public void xiadan(Order order);
	/**
	 * 通过订单Id号删除该订单信息
	 * @param orderId 订单Id号
	 */
	public void deleteOrder(int orderId);
	/**
	 * 通过订单号修改该订单的总价信息
	 * @param orderId 订单Id号
	 * @param totalPrice 需要修改的总价参数
	 */
	public void updateTotalprice(int orderId, float totalPrice);
	/**
	 * 返回当前最大订单Id
	 * 
	 * @return orderId 最大的订单Id号
	 */
	public int maxId();
	/**
	 * 根据订单号设置订单的下单时间
	 * 
	 * @param orderId 订单Id号
	 * @param time 订单的下单时间
	 */
	public void setOrderTime(int orderId, Date time);

}
