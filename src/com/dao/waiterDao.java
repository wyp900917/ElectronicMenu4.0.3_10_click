package com.dao;

import java.util.List;

import com.model.Waiter;
/**
 * ����Ա�ӿڣ��ýӿ��ж����˶Է���Ա��Ϣ���в������йط���
 * @author ping
 *
 */
public interface waiterDao {
	/**
	 * ͨ������ID���ҷ���Ա����
	 * @param writerId ����Id��
	 * @return ����һ������Ա����
	 */
	public Waiter findWriterById(int writerId);
	/**
	 * ���ҷ���Ա�������еķ���Ա��Ϣ
	 * @return ���ط���Ա��Ϣ�б�
	 */
	public List<Waiter> findAllWaiter();
	/**
	 * ͨ������Id�Ų��Ҹ÷���Ա������
	 * @param waiterId ����Id��
	 * @return ���ظ÷���Id�ŵ�����
	 */
	public String checkPassword(int waiterId);

}
