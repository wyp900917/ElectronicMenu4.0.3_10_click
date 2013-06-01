package com.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.dao.ipDao;
import com.db.DatabaseHelper;
import com.model.IP;

/**
 * IP��ַ�ӿ�ʵ���࣬����ʵ����IP��ַ�ӿ��ж�������з���
 * <p>
 * ���໹�̳���DatabaseHelper�࣬����ֱ�ӵ���DatabaseHelper���еĴ����ݿⷽ��
 * 
 * @author ping
 * 
 */
public class ipDaoImpl extends DatabaseHelper implements ipDao {
	/**
	 * ���췽��������DatabaseHelper�Ĺ��췽�������ҽ�context�������ݸ�����
	 * 
	 * @param context
	 */
	public ipDaoImpl(Context context) {
		super(context);
	}

	/**
	 * ���ҷ�����IP��ַ
	 * 
	 * @return IP ���ط�����IP��ַ����
	 */
	public IP findServerIp(Context context) {
		IP serverIp = null;
		try {
			SQLiteDatabase db = openDatabase();
			Cursor c = db.query("tb_serverIP", null, null, null, null, null,
					null);
			if (c.moveToNext()) {
				serverIp = new IP();
				serverIp.set_id(c.getInt(c.getColumnIndex("serverId")));
				// ��IP��ַ�ַ�����ֳ��Ĳ��֣��ԡ��������зָ�
				String[] ip = c.getString(c.getColumnIndex("IP")).split("\\.");
				serverIp.setIPpart1(Integer.valueOf(ip[0]));
				serverIp.setIPpart2(Integer.valueOf(ip[1]));
				serverIp.setIPpart3(Integer.valueOf(ip[2]));
				serverIp.setIPpart4(Integer.valueOf(ip[3]));
				serverIp.setPort(c.getInt(c.getColumnIndex("port")));
			}
			c.close();
		} catch (SQLiteException e) {
			Toast.makeText(context, "��ѯ���ݿ������ʧ�ܣ�����", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return serverIp;
	}

	/**
	 * �������д�ӡ��IP��ַ
	 * 
	 * @return IPList ����һ����ӡ��IP��ַ�б�
	 */
	public List<IP> findAllPrinterIp() {
		SQLiteDatabase db = openDatabase();
		Cursor c = db.query("tb_printIP", null, null, null, null, null, null);
		List<IP> printerIplist = new ArrayList<IP>();
		;
		while (c.moveToNext()) {
			IP printIp = new IP();
			printIp.set_id(c.getInt(c.getColumnIndex("printerId")));
			String[] ip = c.getString(c.getColumnIndex("IP")).split("\\.");
			printIp.setIPpart1(Integer.valueOf(ip[0]));
			printIp.setIPpart2(Integer.valueOf(ip[1]));
			printIp.setIPpart3(Integer.valueOf(ip[2]));
			printIp.setIPpart4(Integer.valueOf(ip[3]));
			printIp.setPort(c.getInt(c.getColumnIndex("port")));
			printerIplist.add(printIp);
		}
		return printerIplist;
	}

	/**
	 * �޸ķ�������IP��ַ
	 * 
	 * @param serverip
	 *            ����һ��������IP��ַ����
	 */
	public void updateServerIp(IP serverip) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("IP", serverip.getIPpart1() + "." + serverip.getIPpart2() + "."
				+ serverip.getIPpart3() + "." + serverip.getIPpart4());
		val.put("port", serverip.getPort());
		db.update("tb_serverIP", val, "serverId=" + serverip.get_id(), null);
	}

	/**
	 * �޸Ĵ�ӡ����IP��ַ
	 * 
	 * @param printerip
	 *            ����һ����ӡ����IP��ַ����
	 */
	public void updatePrinterIP(IP printerip) {
		SQLiteDatabase db = openDatabase();
		ContentValues val = new ContentValues();
		val.put("IP", printerip.getIPpart1() + "." + printerip.getIPpart2()
				+ "." + printerip.getIPpart3() + "." + printerip.getIPpart4());
		val.put("port", printerip.getPort());
		db.update("tb_printIP", val, "printerId=" + printerip.get_id(), null);
	}

}
