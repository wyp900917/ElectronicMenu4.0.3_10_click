package com.model;

import java.io.Serializable;
/**
 * ������ϸ�࣬��װ�˶�������ϸ��Ϣ����Ҫ�Ƕ����Ĳ�Ʒ��Ϣ��������ƷID�š�����ID�š���Ʒ��������Ʒ״̬����Ʒ�۸񡢲�ƷҪ����Ϣ��
 * @author ping
 *
 */
public class OrderDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int foodId;
	
	private int orderId;
	
	private int foodNum;
	
	private int foodState;
	
	private float foodPrice;
	
	private String foodRequest;
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
	 * ��ȡ��Ʒ����
	 * @return foodNum ��Ʒ����
	 */
	public int getFoodNum() {
		return foodNum;
	}
	/**
	 * ���ò�Ʒ����
	 * @param foodNum ��Ʒ����
	 */
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	/**
	 * ��ȡ��Ʒ״̬
	 * @return foodState ��Ʒ״̬
	 */
	public int getFoodState() {
		return foodState;
	}
	/**
	 * ���ò�Ʒ״̬
	 * @param foodState ��Ʒ״̬
	 */
	public void setFoodState(int foodState) {
		this.foodState = foodState;
	}
	/**
	 * ��ȡ��Ʒ�۸�
	 * @return foodPrice ��Ʒ�۸�
	 */
	public float getFoodPrice() {
		return foodPrice;
	}
	/**
	 * ���ò�Ʒ�۸�
	 * @param foodPrice ��Ʒ�۸�
	 */
	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}
	/**
	 * ��ȡ��ƷҪ����Ϣ
	 * @return ��ƷҪ����Ϣ
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

}
