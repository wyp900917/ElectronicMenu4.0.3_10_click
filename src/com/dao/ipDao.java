package com.dao;

import java.util.List;

import android.content.Context;

import com.model.IP;
/**
 * IP地址信息接口，该接口中定义了有关查询以及更新服务器IP地址和打印机IP地址的方法。
 * @author ping
 *
 */
public interface ipDao {
	/**
	 * 查找服务器IP地址
	 * @return IP 返回服务器IP地址对象
	 */
	public IP findServerIp(Context context);
	/**
	 * 查找所有打印机IP地址
	 * @return IPList 返回一个打印机IP地址列表
	 */
	public List<IP> findAllPrinterIp();
	/**
	 * 修改服务器的IP地址
	 * @param serverip 传递一个服务器IP地址对象
	 */
	public void updateServerIp(IP serverip);
	/**
	 * 修改打印机的IP地址
	 * @param printerip 传递一个打印机的IP地址对象
	 */
	public void updatePrinterIP(IP printerip);

}
