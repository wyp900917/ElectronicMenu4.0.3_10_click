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
 * IP地址接口实现类，该类实现了IP地址接口中定义的所有方法
 * <p>
 * 该类还继承了DatabaseHelper类，可以直接调用DatabaseHelper类中的打开数据库方法
 * 
 * @author ping
 * 
 */
public class ipDaoImpl extends DatabaseHelper implements ipDao {
	/**
	 * 构造方法，调用DatabaseHelper的构造方法，并且将context参数传递给父类
	 * 
	 * @param context
	 */
	public ipDaoImpl(Context context) {
		super(context);
	}

	/**
	 * 查找服务器IP地址
	 * 
	 * @return IP 返回服务器IP地址对象
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
				// 将IP地址字符串拆分成四部分，以“。”进行分割
				String[] ip = c.getString(c.getColumnIndex("IP")).split("\\.");
				serverIp.setIPpart1(Integer.valueOf(ip[0]));
				serverIp.setIPpart2(Integer.valueOf(ip[1]));
				serverIp.setIPpart3(Integer.valueOf(ip[2]));
				serverIp.setIPpart4(Integer.valueOf(ip[3]));
				serverIp.setPort(c.getInt(c.getColumnIndex("port")));
			}
			c.close();
		} catch (SQLiteException e) {
			Toast.makeText(context, "查询数据库服务器失败！！！", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return serverIp;
	}

	/**
	 * 查找所有打印机IP地址
	 * 
	 * @return IPList 返回一个打印机IP地址列表
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
	 * 修改服务器的IP地址
	 * 
	 * @param serverip
	 *            传递一个服务器IP地址对象
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
	 * 修改打印机的IP地址
	 * 
	 * @param printerip
	 *            传递一个打印机的IP地址对象
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
