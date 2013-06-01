package com.model;

import java.io.Serializable;
import java.util.Date;
/**
 * �µ���Ʒ��Ϣ�࣬�Ǵ��ݸ��������µ���Ʒ��Ϣ�ķ�װ�ࣻ�����˶���ID����̨ID������ID����ƷID������������ܼۡ���ƷҪ����Ϣ�Լ��µ�ʱ�䡣
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
	 * ��ȡ����Id��
	 * @return orderId ����Id��
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * ���ö���Id��
	 * @param orderId ����Id��
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	 * @return waterId ���񹤺�Id 
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
	 * ��ȡ��ƷId��
	 * @return foodId ��ƷId��
	 */
	public int getFoodId() {
		return foodId;
	}
	/**
	 * ���ò�ƷId�� 
	 * @param foodId ��ƷId��
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	/**
	 * ��ȡ��Ʒ�������
	 * @return foodNum ��Ʒ�������
	 */
	public int getFoodNum() {
		return foodNum;
	}
	/**
	 * ���ò�Ʒ�������
	 * @param foodNum ��Ʒ�������
	 */
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	/**
	 * ��ȡ�����ܼ�
	 * @return totalPrice �����ܼ�
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * ���ö����ܼ�
	 * @param totalPrice �����ܼ�
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * ��ȡ��ƷҪ����Ϣ
	 * @return foodRequest ��ƷҪ����Ϣ
	 */
	public String getFoodRequest() {
		return foodRequest;
	}
	/**
	 * ���ò�ƷҪ����Ϣ
	 * @param foodRequest ��ƷҪ����Ϣ
	 */
	public void setFoodRequest(String foodRequest) {
		this.foodRequest = foodRequest;
	}
	/**
	 * ��ȡ�����µ�ʱ��
	 * @return xiandanTime �µ�ʱ��
	 */
	public Date getXiadanTime() {
		return xiadanTime;
	}
	/**
	 * ���ö����µ�ʱ��
	 * @param xiadanTime �����µ�ʱ��
	 */
	public void setXiadanTime(Date xiadanTime) {
		this.xiadanTime = xiadanTime;
	}

}
