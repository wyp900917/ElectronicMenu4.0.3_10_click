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
 * �����ӿ�ʵ���࣬����ʵ���˶����ӿ��ж�������з���
 * <p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * 
 * @author ping
 * 
 */
public class orderDaoImpl extends DatabaseHelper implements orderDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * 
	 * @param context
	 */
	public orderDaoImpl(Context context) {
		super(context);
	}

	/**
	 * ͨ������Id�Ų��Ҹö�����Ϣ
	 * 
	 * @param orderId
	 *            ����Id��
	 * @return order ����һ����������
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
	 * �������еĶ�����Ϣ
	 * 
	 * @return orderList ����һ��������Ϣ�б�
	 */
	public List<Order> findAll() {
		return null;
	}

	/**
	 * ͨ������ԱId�Ų��Ҷ����������з��񹤺��Ǹ÷���Id�Ķ������ҳ���
	 * 
	 * @param waiterId
	 *            ����ԱId��
	 * @return orderList ����һ�������б�
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
			Toast.makeText(context, "��ѯ���ݿ�ʧ�ܣ�����", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * ����һ��������Ϣ�������뵽�������ݿ����
	 * 
	 * @param order
	 *            ����һ����Ҫ�����Ķ�������
	 * @return long ����һ���������ж��Ƿ������ɹ�
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
	 * �����ݵĶ��������µ�����
	 * 
	 * @param order
	 *            ��Ҫ�µ��Ķ�������
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
	 * ͨ������Id��ɾ���ö�����Ϣ
	 * 
	 * @param orderId
	 *            ����Id��
	 */
	public void deleteOrder(int orderId) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_order where orderID = " + orderId;
		db.execSQL(sql);
	}

	/**
	 * ͨ���������޸ĸö������ܼ���Ϣ
	 * 
	 * @param orderId
	 *            ����Id��
	 * @param totalprice
	 *            ��Ҫ�޸ĵ��ܼ۲���
	 */
	public void updateTotalprice(int orderId, float totalprice) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("totalPrice", totalprice);
		db.update("tb_order", val, "orderID=" + orderId, null);
	}
	/**
	 * ���ص�ǰ��󶩵�Id
	 * 
	 * @return orderId ���Ķ���Id��
	 */
	public int maxId() {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_order", new String[]{"max(orderID)"}, null, null, null, null,null);
		c.moveToNext();
		return c.getInt(c.getColumnIndex("max(orderID)"));
	}
	/**
	 * ���ݶ��������ö������µ�ʱ��
	 * 
	 * @param orderId ����Id��
	 * @param time �������µ�ʱ��
	 */
	public void setOrderTime(int orderId, Date time) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(time));
		db.update("tb_order", val, "orderID=" + orderId, null);
	}

}
