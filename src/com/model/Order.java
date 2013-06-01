package com.model;

import java.io.Serializable;
import java.util.Date;
/**
 * �����࣬������Ҫ�Ƿ�װ�˶����������Ϣ����������ID�š���̨ID�š����񹤺�ID����Ʒ�ܼۡ�����״̬�Լ��µ�ʱ����Ϣ��
 * @author ping
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int _id;

	private int tableId;

	private int waiterId;

	private float totalPrice;

	private int orderState;// 1��ʾδ���ʣ�0��ʾ�ѽ���

	private Date time;
	/**
	 * ��ȡ����Id��
	 * @return id ����Id��
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ���ö���Id��
	 * @param _id ����Id��
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ��̨Id��
	 * @return tableId ��̨Id��
	 */
	public int getTableId() {
		return tableId;
	}
	/**
	 * ������̨Id��
	 * @param tableId ��̨Id��
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	/**
	 * ��ȡ���񹤺�Id
	 * @return waiterId ���񹤺�Id
	 */
	public int getWaiterId() {
		return waiterId;
	}
	/**
	 * ���÷��񹤺�Id
	 * @param waiterId ���񹤺�Id
	 */
	public void setWaiterId(int waiterId) {
		this.waiterId = waiterId;
	}
	/**
	 * ��ȡ��Ʒ�ܼ�
	 * @return totalPrice ��Ʒ�ܼ�
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * ���ò�Ʒ�ܼ�
	 * @param totalPrice ��Ʒ�ܼ�
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * ��ȡ����״̬
	 * @return orderState ����״̬
	 */
	public int getOrderState() {
		return orderState;
	}
	/**
	 * ���ö���״̬
	 * @param orderState ����״̬
	 */
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	/**
	 * ��ȡ�µ�ʱ��
	 * @return time �µ�ʱ��
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * �����µ�ʱ��
	 * @param time �µ�ʱ��
	 */
	public void setTime(Date time) {
		this.time = time;
	}

}
