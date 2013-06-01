package com.dao;

import java.util.List;

import com.model.OrderDetail;
/**
 * 订单明细接口，该接口定义对订单中的菜品进行各种操作的方法
 * @author ping
 *
 */
public interface orderDetailDao {
	/**
	 * 添加一个订单明细，在订单中新增一个菜品信息
	 * @param ordertal 传递一个订单明细对象
	 */
	public void addFood(OrderDetail ordertal);
	/**
	 * 删除一个订单明细，通过订单Id号、菜品Id号以及菜品状态查找该订单中的菜品并且将其删除
	 * @param foodId 菜品Id号
	 * @param orderId 订单Id号
	 * @param foodstate 菜品状态
	 */
	public void deleteFood(int foodId, int orderId, int foodstate);
	/**
	 * 通过订单Id号和菜品Id号将订单明细表中对应的菜品数量需改为传递的参数值num
	 * @param foodId 菜品Id号
	 * @param orderId 订单Id号
	 * @param num 所需要修改的菜品数量
	 */
	public void updatefoodNum(int foodId, int orderId, int num);
	/**
	 * 通过订单Id号以及菜品状态查找订单明细表中所有符合参数要求的订单明细的菜品信息
	 * @param orderId 订单Id号
	 * @param foodState 菜品状态
	 * @return orderDetailList 返回一个订单明细列表
	 */
	public List<OrderDetail> findDetailByorderId(int orderId, int foodState);
	/**
	 * 通过订单Id号修改该订单的状态，即将该订单的状态修改为已下单
	 * @param orderId 订单Id号
	 */
	public void updateFoodState(int orderId);
	/**
	 * 通过订单Id号、菜品Id号为该菜品添加所传递的要求信息request
	 * @param orderId 订单Id号
	 * @param foodId 菜品Id号
	 * @param request 菜品要求信息
	 */
	public void addfoodRequest(int orderId, int foodId, String request);
	/**
	 * 通过订单Id号删除该订单所选的菜品
	 * @param orderId 订单Id号
	 */
	public void deleteOrderFood(int orderId);

}
