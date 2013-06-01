package com.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.orderDetailDao;
import com.db.DatabaseHelper;
import com.model.OrderDetail;
/**
 * 订单明细接口实现类，该类实现了订单明细接口中定义的所有方法；
 * 该类还继承了DatabaseHelper类，从而可以调用DatabaseHelper类中的打开数据库的方法
 * @author ping
 *
 */
public class orderDetailDaoImpl extends DatabaseHelper implements
		orderDetailDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * @param context
	 */
	public orderDetailDaoImpl(Context context) {
		super(context);
	}
	/**
	 * 添加一个订单明细方法的实现，将传递的orderDetail插入数据库表中
	 * @param ordertal 一个订单明细的实例对象
	 */
	public void addFood(OrderDetail ordertal) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodID", ordertal.getFoodId());
		val.put("orderID", ordertal.getOrderId());
		val.put("foodNum", ordertal.getFoodNum());
		val.put("foodState", ordertal.getFoodState());
		val.put("foodPrice", ordertal.getFoodPrice());
		val.put("foodRequest", ordertal.getFoodRequest());
		db.insert("tb_orderDetail", null, val);
	}
	/**
	 * 删除一个订单明细，根据菜品Id、订单Id和菜品状态将已点的菜品信息删除
	 * @param foodId 菜品Id
	 * @param orderId 订单Id
	 * @param foodstate 菜品状态
	 */
	public void deleteFood(int foodId, int orderId, int foodstate) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_orderDetail where foodID=" + foodId
				+ " and orderID=" + orderId + " and foodState=" + foodstate;
		db.execSQL(sql);
	}
	/**
	 * 更新订单明细表中菜品的数量，根据菜品Id和订单Id查找到对应的菜品信息，再将购买数量修改为传递过来的参数num的值
	 * @param foodId 菜品Id
	 * @param orderId 订单Id
	 * @param num 需要修改的菜品数量值
	 */
	public void updatefoodNum(int foodId, int orderId, int num) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodNum", num);
		db.update("tb_orderDetail", val, "foodID=" + foodId + " and orderID="
				+ orderId + " and foodState=0", null);
	}
	/**
	 * 查找指定订单号的菜品明细信息，根据订单Id号和菜品状态可以查找该订单中所有未下单的菜品信息以及所有已下单的菜品信息
	 * @param orderId 订单Id号
	 * @param foodState 菜品状态
	 */
	public List<OrderDetail> findDetailByorderId(int orderId, int foodState) {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_orderDetail", null, "orderID=" + orderId
				+ " and foodState=" + foodState, null, null, null, null);
		List<OrderDetail> orderdetaillist = new ArrayList<OrderDetail>();
		while (c.moveToNext()) {
			OrderDetail orderdal = new OrderDetail();
			orderdal.setFoodId(c.getInt(c.getColumnIndex("foodID")));
			orderdal.setOrderId(orderId);
			orderdal.setFoodNum(c.getInt(c.getColumnIndex("foodNum")));
			orderdal.setFoodState(c.getInt(c.getColumnIndex("foodState")));
			orderdal.setFoodPrice(c.getFloat(c.getColumnIndex("foodPrice")));
			orderdal.setFoodRequest(c.getString(c.getColumnIndex("foodRequest")));
			orderdetaillist.add(orderdal);
		}
		return orderdetaillist;
	}
	/**
	 * 更新订单中的菜品状态
	 * @param orderId 订单Id号
	 */
	public void updateFoodState(int orderId) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodState", 1);
		db.update("tb_orderDetail", val, "orderID=" + orderId, null);
	}
	/**
	 * 添加菜品要求信息，根据菜品Id号、订单Id号查询到对应的已点菜品信息，将传递的菜品要求信息插入到订单明细表中
	 * @param orderId 订单Id号
	 * @param foodId 菜品Id号
	 * @param request 菜品要求信息
	 */
	public void addfoodRequest(int orderId, int foodId, String request) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodRequest", request);
		db.update("tb_orderDetail", val, "orderID=" + orderId + " and foodID="
				+ foodId + " and foodState=0", null);
	}
	/**
	 * 通过订单Id号删除该订单所选的菜品
	 * @param orderId 订单Id号
	 */
	public void deleteOrderFood(int orderId) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_orderDetail where orderID = " + orderId;
		db.execSQL(sql);
	}

}
