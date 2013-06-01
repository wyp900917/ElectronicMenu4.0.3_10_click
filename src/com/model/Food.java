package com.model;

import java.io.Serializable;
/**
 * ��Ʒ�࣬��װ�˲�Ʒ��������Ϣ��
 * @author ping
 *
 */
public class Food implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String foodName;
	
	private float costPrice;
	
	private float presentPrice;
	
	private String introduction;
	
	private String picture;
	
	private int foodCategoryId;
	/**
	 * ��ȡ��ƷId
	 * @return id ��ƷId
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ���ò�ƷId
	 * @param _id ��ƷId
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ��Ʒ����
	 * @return foodName ��Ʒ����
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * ��ȡ��Ʒ����
	 * @param foodName ��Ʒ����
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * ��ȡ��Ʒԭ��
	 * @return costPrice ��Ʒԭ��
	 */
	public float getCostPrice() {
		return costPrice;
	}
	/**
	 * ���ò�Ʒԭ��
	 * @param costPrice ��Ʒԭ��
	 */
	public void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * ��ȡ��Ʒ�ּ�
	 * @return presentPrice ��Ʒ�ּ�
	 */
	public float getPresentPrice() {
		return presentPrice;
	}
	/**
	 * ���ò�Ʒ�ּ�
	 * @param presentPrice ��Ʒ�ּ�
	 */
	public void setPresentPrice(float presentPrice) {
		this.presentPrice = presentPrice;
	}
	/**
	 * ��ȡ��Ʒ��ϸ����
	 * @return introduction ��Ʒ��ϸ����
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * ���ò�Ʒ��ϸ�Ӻ�
	 * @param introduction ��Ʒ��ϸ����
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * ��ȡ��ƷͼƬ����·��
	 * @return picture ��ƷͼƬ����·��
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * ���ò�ƷͼƬ����·��
	 * @param picture ��ƷͼƬ����·��
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * ��ȡ��Ʒ����Id
	 * @return foodCategoryId ��Ʒ����Id
	 */
	public int getFoodCategoryId() {
		return foodCategoryId;
	}
	/**
	 * ���ò�Ʒ����Id
	 * @param foodCategoryId ��Ʒ����Id
	 */
	public void setFoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}

}
