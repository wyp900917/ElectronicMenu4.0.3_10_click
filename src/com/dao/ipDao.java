package com.dao;

import java.util.List;

import android.content.Context;

import com.model.IP;
/**
 * IP��ַ��Ϣ�ӿڣ��ýӿ��ж������йز�ѯ�Լ����·�����IP��ַ�ʹ�ӡ��IP��ַ�ķ�����
 * @author ping
 *
 */
public interface ipDao {
	/**
	 * ���ҷ�����IP��ַ
	 * @return IP ���ط�����IP��ַ����
	 */
	public IP findServerIp(Context context);
	/**
	 * �������д�ӡ��IP��ַ
	 * @return IPList ����һ����ӡ��IP��ַ�б�
	 */
	public List<IP> findAllPrinterIp();
	/**
	 * �޸ķ�������IP��ַ
	 * @param serverip ����һ��������IP��ַ����
	 */
	public void updateServerIp(IP serverip);
	/**
	 * �޸Ĵ�ӡ����IP��ַ
	 * @param printerip ����һ����ӡ����IP��ַ����
	 */
	public void updatePrinterIP(IP printerip);

}
