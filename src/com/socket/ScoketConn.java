package com.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.FoodOrder;
import com.model.Table;
/**
 * 该类是将Socket通信所传递的字符流封装成类的对象进行传递，使用ObjectOutputStream和ObjectInputStream流传递实例对象
 * @author ping
 *
 */
public class ScoketConn {
	/**
	 * 该方法是将一个整形数值封装成一个对象，然后通过ObjectOutputStream流写入到字节流中，最后使用Socket进行传递
	 * @param id 一个整形数值
	 * @return byte[] 返回一个字节数组
	 */
	public static byte[] TabletoBytes(Integer id) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	/**
	 * 该方法是将一个FoodOrder类的List列表进行封装，通过ObjectOutputStream流写入到字节流中，然后用Socket进行传递
	 * @param foodorderlist foodorder对象列表
	 * @return byte[] 返回一个字节数组
	 */
	public static byte[] FoodOrdertoBytes(List<FoodOrder> foodorderlist) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(foodorderlist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	/**
	 * 该方法是将一个Table类的对象进行封装，通过ObjectOutputStream流写入到一个字节流中，然后使用Socket进行传递
	 * @param table 一个Table对象实例
	 * @return byte[] 返回一个字节数组
	 */
	public static byte[] TabletoBytes(Table table) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(table);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}
	/**
	 * 该方法是将Socket通信所得到的字节流转换成Table类的一个实例对象列表
	 * @param bs Socket通信所接收到的一个字节流数组
	 * @return table 返回一个Table类的对象实例列表
	 */
	@SuppressWarnings("unchecked")
	public static List<Table> TablegetBytes(byte[] bs) {
		List<Table> table = new ArrayList<Table>();
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bs);
			ObjectInputStream in = new ObjectInputStream(byteIn);
			table = (List<Table>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	
	public static int IntgetBytes(byte[] bs) {
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bs);
			ObjectInputStream in = new ObjectInputStream(byteIn);
			Integer orderId = (Integer) in.readObject();
			return orderId.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
