package com.dao;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.model.Order;
/**
 * ������Ϣ�ӿڣ��ýӿڶ����˶Զ���������һϵ�з����Ͳ�����
 * @author ping
 *
 */
public interface orderDao {
	/**
	 * ����һ��������Ϣ
	 * @param order ����һ����Ҫ�����Ķ�������
	 * @return long ����һ���������ж��Ƿ������ɹ�
	 */
	public long addOrder(Order order);
	/**
	 * ͨ������Id�Ų��Ҹö�����Ϣ
	 * @param orderId ����Id��
	 * @return order ����һ����������
	 */
	// ͨ�������Ų�ѯ
	public Order findOrderById(int orderId);
	/**
	 * �������еĶ�����Ϣ
	 * @return orderList ����һ��������Ϣ�б�
	 */
	// ��ѯ���ж���
	public List<Order> findAll();
	/**
	 * ͨ������ԱId�Ų��Ҷ����������з��񹤺��Ǹ÷���Id�Ķ������ҳ���
	 * @param waiterId ����ԱId��
	 * @return orderList ����һ�������б�
	 */
	public List<Order> findOrderByWaiterId(int waiterId, Context context);
	/**
	 * �����ݵĶ��������µ�����
	 * @param order ��Ҫ�µ��Ķ�������
	 */
	public void xiadan(Order order);
	/**
	 * ͨ������Id��ɾ���ö�����Ϣ
	 * @param orderId ����Id��
	 */
	public void deleteOrder(int orderId);
	/**
	 * ͨ���������޸ĸö������ܼ���Ϣ
	 * @param orderId ����Id��
	 * @param totalPrice ��Ҫ�޸ĵ��ܼ۲���
	 */
	public void updateTotalprice(int orderId, float totalPrice);
	/**
	 * ���ص�ǰ��󶩵�Id
	 * 
	 * @return orderId ���Ķ���Id��
	 */
	public int maxId();
	/**
	 * ���ݶ��������ö������µ�ʱ��
	 * 
	 * @param orderId ����Id��
	 * @param time �������µ�ʱ��
	 */
	public void setOrderTime(int orderId, Date time);

}
