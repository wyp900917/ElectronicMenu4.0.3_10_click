package com.model;

import java.io.Serializable;
/**
 * ��Ʒ������Ϣ�࣬�����з�װ�˲�Ʒ�����Id���Լ���Ʒ����������Ϣ��
 * @author ping
 *
 */
public class FoodCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int _id;
	
	private String categoryName;
	/**
	 * ��ȡ��Ʒ����Id
	 * @return Id ��Ʒ����Id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * ���ò�Ʒ����Id
	 * @param _id ��Ʒ����Id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * ��ȡ��Ʒ��������
	 * @return categoryName ��Ʒ��������
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * ���ò�Ʒ��������
	 * @param categoryName ��Ʒ��������
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
