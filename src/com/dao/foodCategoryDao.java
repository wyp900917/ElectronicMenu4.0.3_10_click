package com.dao;

import java.util.List;

import android.content.Context;

import com.model.FoodCategory;
/**
 * ��Ʒ����ӿڣ��ýӿڶ������йز�Ʒ�����Ĳ���������
 * @author ping
 *
 */
public interface foodCategoryDao {
	/**
	 * �������в�Ʒ������Ϣ������һ����Ʒ�����б�
	 * @return FoodCategoryList ���в�Ʒ�����List�б�
	 */
	public List<FoodCategory> findAll();
	/**
	 * ͨ����Ʒ�����Id���Ҹò�Ʒ���������
	 * @param categoryId ��Ʒ����Id
	 * @return CategoryName ��Ʒ��������
	 */
	public String findCategoryName(int categoryId, Context context);
	
}
