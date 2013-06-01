package com.dao;

import java.util.List;

import com.model.Table;
/**
 * ��̨�ӿڣ��ýӿ��ж����˶���̨��������йط���
 * @author ping
 *
 */
public interface tableDao {
	/**
	 * ͨ����̨Id�Ų�����̨������Ϣ
	 * @param tableId ��̨Id��
	 * @return table ����һ����̨������Ϣ
	 */
	//ͨ����̨�Ų�ѯ
	public Table findTableById(int tableId);
	/**
	 * ͨ����̨��״̬�������и�״̬����̨��Ϣ
	 * @param state ��̨״̬��Ϣ
	 * @return tableList ����һ����̨�б�
	 */
	//ͨ����̨��״̬��ѯ
	public List<Table> findTableByState(int state);
	/**
	 * �������е���̨��Ϣ
	 * @return tableList �������е���̨��Ϣ�б�
	 */
	//��ѯ������̨
	public List<Table> findAllTable();
	/**
	 * ͨ��������Id�Ų��Ҹô��������е���̨��Ϣ
	 * @param roomId ����Id��
	 * @return tableList ���ظô��������е���̨��Ϣ�б�
	 */
	//ͨ������Ų�ѯ
	public List<Table> findTableByroomId(int roomId);
	/**
	 * ������̨,�޸���̨��ص�����
	 * @param table ����һ����̨����
	 */
	//������̨��Ϣ
	public void update(Table table);
	/**
	 * ͨ����̨Id�����޸ĸ���̨��״̬
	 * @param tableId ��̨Id��
	 * @param state ��Ҫ�޸ĵ���̨״̬
	 */
	public void updateState(int tableId,int state);
	/**
	 * ɾ�����е���̨��Ϣ
	 */
	public void deleteAllTable();

}
