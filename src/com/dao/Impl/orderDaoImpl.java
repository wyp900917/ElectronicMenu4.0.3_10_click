package com.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.dao.orderDao;
import com.db.DatabaseHelper;
import com.model.Order;

/**
 * 订单接口实现类，该类实现了订单接口中定义的所有方法
 * <p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * 
 * @author ping
 * 
 */
public class orderDaoImpl extends DatabaseHelper implements orderDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * 
	 * @param context
	 */
	public orderDaoImpl(Context context) {
		super(context);
	}

	/**
	 * 通过订单Id号查找该订单信息
	 * 
	 * @param orderId
	 *            订单Id号
	 * @return order 返回一个订单对象
	 */
	public Order findOrderById(int orderId) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_order", null, "orderID=" + orderId, null, null,
				null, null);
		if (c.moveToNext()) {
			Order order = new Order();
			order.set_id(c.getInt(c.getColumnIndex("orderID")));
			order.setTableId(c.getInt(c.getColumnIndex("tableID")));
			order.setWaiterId(c.getInt(c.getColumnIndex("waiterID")));
			order.setTotalPrice(c.getFloat(c.getColumnIndex("totalPrice")));
			order.setOrderState(c.getInt(c.getColumnIndex("orderState")));
			try {
				order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(c.getString(c.getColumnIndex("time"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return order;
		} else
			return null;
	}

	/**
	 * 查找所有的订单信息
	 * 
	 * @return orderList 返回一个订单信息列表
	 */
	public List<Order> findAll() {
		return null;
	}

	/**
	 * 通过服务员Id号查找订单，将所有服务工号是该服务Id的订单查找出来
	 * 
	 * @param waiterId
	 *            服务员Id号
	 * @return orderList 返回一个订单列表
	 */
	public List<Order> findOrderByWaiterId(int waiterId, Context context) {
		String presentdate = "'"
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "'";
		List<Order> orderList = null;
		try {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_order", null, "waiterID=" + waiterId
				+ " and substr(time,1,10)=" + presentdate, null, null, null,
				"time DESC");
		orderList = new ArrayList<Order>();
		while (c.moveToNext()) {
			Order order = new Order();
			order.set_id(c.getInt(c.getColumnIndex("orderID")));
			order.setTableId(c.getInt(c.getColumnIndex("tableID")));
			order.setWaiterId(c.getInt(c.getColumnIndex("waiterID")));
			order.setTotalPrice(c.getFloat(c.getColumnIndex("totalPrice")));
			order.setOrderState(c.getInt(c.getColumnIndex("orderState")));
			try {
				order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(c.getString(c.getColumnIndex("time"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			orderList.add(order);
		}
		c.close(); 
		} catch(SQLiteException e) {
			Toast.makeText(context, "查询数据库失败！！！", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 新增一个订单信息，并插入到订单数据库表中
	 * 
	 * @param order
	 *            传递一个将要新增的订单对象
	 * @return long 返回一个长整型判断是否新增成功
	 */
	public long addOrder(Order order) {
		SQLiteDatabase db = openDatabase();
		ContentValues values = new ContentValues();
		values.put("orderID", order.get_id());
		values.put("tableID", order.getTableId());
		values.put("waiterID", order.getWaiterId());
		values.put("totalPrice", order.getTotalPrice());
		values.put("orderState", order.getOrderState());
		values.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(order.getTime()));
		return db.insert("tb_order", null, values);
	}

	/**
	 * 将传递的订单进行下单操作
	 * 
	 * @param order
	 *            将要下单的订单对象
	 */
	public void xiadan(Order order) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("tableID", order.getTableId());
		val.put("waiterID", order.getWaiterId());
		val.put("totalPrice", order.getTotalPrice());
		val.put("orderState", order.getOrderState());
		val.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(order.getTime()));
		db.update("tb_order", val, "orderID=" + order.get_id(), null);
	}

	/**
	 * 通过订单Id号删除该订单信息
	 * 
	 * @param orderId
	 *            订单Id号
	 */
	public void deleteOrder(int orderId) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_order where orderID = " + orderId;
		db.execSQL(sql);
	}

	/**
	 * 通过订单号修改该订单的总价信息
	 * 
	 * @param orderId
	 *            订单Id号
	 * @param totalprice
	 *            需要修改的总价参数
	 */
	public void updateTotalprice(int orderId, float totalprice) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("totalPrice", totalprice);
		db.update("tb_order", val, "orderID=" + orderId, null);
	}
	/**
	 * 返回当前最大订单Id
	 * 
	 * @return orderId 最大的订单Id号
	 */
	public int maxId() {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_order", new String[]{"max(orderID)"}, null, null, null, null,null);
		c.moveToNext();
		return c.getInt(c.getColumnIndex("max(orderID)"));
	}
	/**
	 * 根据订单号设置订单的下单时间
	 * 
	 * @param orderId 订单Id号
	 * @param time 订单的下单时间
	 */
	public void setOrderTime(int orderId, Date time) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(time));
		db.update("tb_order", val, "orderID=" + orderId, null);
	}

}
