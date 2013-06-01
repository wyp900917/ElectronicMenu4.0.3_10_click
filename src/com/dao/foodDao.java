package com.dao;

import java.util.List;

import android.content.Context;

import com.model.Food;
/**
 * ��Ʒ��Ϣ�ӿڣ��ýӿڶ����˹��ڲ�Ʒ��Ĳ���������
 * @author ping
 *
 */
public interface foodDao {
	/**
	 * �������в�Ʒ��Ϣ������һ����Ʒ�б�
	 * @return foodList ���в�Ʒ��Ϣ���б�
	 */
	public List<Food> findAll();
	/**
	 * ͨ����ƷId���Ҳ�Ʒ��Ϣ��������һ����Ʒ����
	 * @param foodId ��ƷId��
	 * @return food ����һ����Ʒ����
	 */
	public Food findFoodById(int foodId, Context context);
	/**
	 * ͨ����Ʒ����Id�Ų��Ҹò�Ʒ���������������в�Ʒ��Ϣ��
	 * @param categoryId ��Ʒ����Id��
	 * @return foodList ����һ����Ʒ�б�
	 */
	public List<Food> findBycategoryId(int categoryId);
	/**
	 * ͨ����ƷId�Ų��Ҹò�Ʒ������
	 * @param foodId ��ƷId��
	 * @return foodName ���ز�Ʒ����
	 */
	public String getFoodName(int foodId);
	/**
	 * ͨ���ؼ��ֲ��Ҳ�Ʒ������ؼ�������ϵĲ�Ʒ��Ϣ��
	 * @param foodkey �ؼ���
	 * @return foodList ����һ����Ʒ�б�
	 */
	public List<Food> findByfoodkey(String foodkey);

}
