package com.model;

import java.io.Serializable;
/**
 * �����Ʒ��Ϣ�࣬������Ҫ�Ǳ��浱ǰ�ѵ��Ʒ����Ϣ����װ�˲�Ʒ������Ϣ��������ƷID����Ʒ���ơ��۸񡢵����������Ʒ״̬�Լ��Բ�ƷҪ����Ϣ��
 * @author ping
 *
 */
public class ChoosedFood implements Serializable {
	private static final long serialVersionUID = 1L;

	private int foodId;

	private String foodName;

	private float costprice;
	
	private float presentprice;

	private int foodNum;

	private int state;// 0��ʾδ�µ���1��ʾ���µ�
	
	private String requset;
	/**
	 * ��ȡ��ƷId
	 * @return foodId ��ƷId
	 */
	public int getFoodId() {
		return foodId;
	}
	/**
	 * ���ò�ƷId
	 * @param foodId ��ƷId
	 */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	/**
	 * ��ȡ��Ʒ����
	 * @return foodName ��Ʒ����
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * ���ò�Ʒ����
	 * @param foodName ��Ʒ����
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * ��ȡ��Ʒ����
	 * @return price ��Ʒ����
	 */
	public float getCostprice() {
		return costprice;
	}
	/**
	 * ���ò�Ʒԭ��
	 * @param price ��Ʒԭ��
	 */
	public void setCostprice(float costprice) {
		this.costprice = costprice;
	}
	/**
	 * ���ò�Ʒ�ּ�
	 * @return presentPrice ��Ʒ�ּ�
	 */
	public float getPresentprice() {
		return presentprice;
	}
	/**
	 * ��ȡ��Ʒ�ּ�
	 * @param presentprice ��Ʒ�ּ�
	 */
	public void setPresentprice(float presentprice) {
		this.presentprice = presentprice;
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
	 * ��ȡ��Ʒ״̬
	 * @return state ��Ʒ״̬
	 */
	public int getState() {
		return state;
	}
	/**
	 * ���ò�Ʒ״̬
	 * @param state ��Ʒ״̬
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * ��ȡ��ƷҪ��
	 * @return request ��ƷҪ��
	 */
	public String getRequset() {
		return requset;
	}
	/**
	 * ���ò�ƷҪ��
	 * @param requset ��ƷҪ��
	 */
	public void setRequset(String requset) {
		this.requset = requset;
	}

}
