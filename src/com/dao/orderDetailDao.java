package com.dao;

import java.util.List;

import com.model.OrderDetail;
/**
 * ������ϸ�ӿڣ��ýӿڶ���Զ����еĲ�Ʒ���и��ֲ����ķ���
 * @author ping
 *
 */
public interface orderDetailDao {
	/**
	 * ���һ��������ϸ���ڶ���������һ����Ʒ��Ϣ
	 * @param ordertal ����һ��������ϸ����
	 */
	public void addFood(OrderDetail ordertal);
	/**
	 * ɾ��һ��������ϸ��ͨ������Id�š���ƷId���Լ���Ʒ״̬���Ҹö����еĲ�Ʒ���ҽ���ɾ��
	 * @param foodId ��ƷId��
	 * @param orderId ����Id��
	 * @param foodstate ��Ʒ״̬
	 */
	public void deleteFood(int foodId, int orderId, int foodstate);
	/**
	 * ͨ������Id�źͲ�ƷId�Ž�������ϸ���ж�Ӧ�Ĳ�Ʒ�������Ϊ���ݵĲ���ֵnum
	 * @param foodId ��ƷId��
	 * @param orderId ����Id��
	 * @param num ����Ҫ�޸ĵĲ�Ʒ����
	 */
	public void updatefoodNum(int foodId, int orderId, int num);
	/**
	 * ͨ������Id���Լ���Ʒ״̬���Ҷ�����ϸ�������з��ϲ���Ҫ��Ķ�����ϸ�Ĳ�Ʒ��Ϣ
	 * @param orderId ����Id��
	 * @param foodState ��Ʒ״̬
	 * @return orderDetailList ����һ��������ϸ�б�
	 */
	public List<OrderDetail> findDetailByorderId(int orderId, int foodState);
	/**
	 * ͨ������Id���޸ĸö�����״̬�������ö�����״̬�޸�Ϊ���µ�
	 * @param orderId ����Id��
	 */
	public void updateFoodState(int orderId);
	/**
	 * ͨ������Id�š���ƷId��Ϊ�ò�Ʒ��������ݵ�Ҫ����Ϣrequest
	 * @param orderId ����Id��
	 * @param foodId ��ƷId��
	 * @param request ��ƷҪ����Ϣ
	 */
	public void addfoodRequest(int orderId, int foodId, String request);
	/**
	 * ͨ������Id��ɾ���ö�����ѡ�Ĳ�Ʒ
	 * @param orderId ����Id��
	 */
	public void deleteOrderFood(int orderId);

}
