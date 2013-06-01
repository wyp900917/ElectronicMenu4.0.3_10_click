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
 * ������ϸ�ӿ�ʵ���࣬����ʵ���˶�����ϸ�ӿ��ж�������з�����
 * ���໹�̳���DatabaseHelper�࣬�Ӷ����Ե���DatabaseHelper���еĴ����ݿ�ķ���
 * @author ping
 *
 */
public class orderDetailDaoImpl extends DatabaseHelper implements
		orderDetailDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * @param context
	 */
	public orderDetailDaoImpl(Context context) {
		super(context);
	}
	/**
	 * ���һ��������ϸ������ʵ�֣������ݵ�orderDetail�������ݿ����
	 * @param ordertal һ��������ϸ��ʵ������
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
	 * ɾ��һ��������ϸ�����ݲ�ƷId������Id�Ͳ�Ʒ״̬���ѵ�Ĳ�Ʒ��Ϣɾ��
	 * @param foodId ��ƷId
	 * @param orderId ����Id
	 * @param foodstate ��Ʒ״̬
	 */
	public void deleteFood(int foodId, int orderId, int foodstate) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_orderDetail where foodID=" + foodId
				+ " and orderID=" + orderId + " and foodState=" + foodstate;
		db.execSQL(sql);
	}
	/**
	 * ���¶�����ϸ���в�Ʒ�����������ݲ�ƷId�Ͷ���Id���ҵ���Ӧ�Ĳ�Ʒ��Ϣ���ٽ����������޸�Ϊ���ݹ����Ĳ���num��ֵ
	 * @param foodId ��ƷId
	 * @param orderId ����Id
	 * @param num ��Ҫ�޸ĵĲ�Ʒ����ֵ
	 */
	public void updatefoodNum(int foodId, int orderId, int num) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodNum", num);
		db.update("tb_orderDetail", val, "foodID=" + foodId + " and orderID="
				+ orderId + " and foodState=0", null);
	}
	/**
	 * ����ָ�������ŵĲ�Ʒ��ϸ��Ϣ�����ݶ���Id�źͲ�Ʒ״̬���Բ��Ҹö���������δ�µ��Ĳ�Ʒ��Ϣ�Լ��������µ��Ĳ�Ʒ��Ϣ
	 * @param orderId ����Id��
	 * @param foodState ��Ʒ״̬
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
	 * ���¶����еĲ�Ʒ״̬
	 * @param orderId ����Id��
	 */
	public void updateFoodState(int orderId) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodState", 1);
		db.update("tb_orderDetail", val, "orderID=" + orderId, null);
	}
	/**
	 * ��Ӳ�ƷҪ����Ϣ�����ݲ�ƷId�š�����Id�Ų�ѯ����Ӧ���ѵ��Ʒ��Ϣ�������ݵĲ�ƷҪ����Ϣ���뵽������ϸ����
	 * @param orderId ����Id��
	 * @param foodId ��ƷId��
	 * @param request ��ƷҪ����Ϣ
	 */
	public void addfoodRequest(int orderId, int foodId, String request) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("foodRequest", request);
		db.update("tb_orderDetail", val, "orderID=" + orderId + " and foodID="
				+ foodId + " and foodState=0", null);
	}
	/**
	 * ͨ������Id��ɾ���ö�����ѡ�Ĳ�Ʒ
	 * @param orderId ����Id��
	 */
	public void deleteOrderFood(int orderId) {
		SQLiteDatabase db = openDatabase();
		String sql = "delete from tb_orderDetail where orderID = " + orderId;
		db.execSQL(sql);
	}

}
