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
 * �����ǽ�Socketͨ�������ݵ��ַ�����װ����Ķ�����д��ݣ�ʹ��ObjectOutputStream��ObjectInputStream������ʵ������
 * @author ping
 *
 */
public class ScoketConn {
	/**
	 * �÷����ǽ�һ��������ֵ��װ��һ������Ȼ��ͨ��ObjectOutputStream��д�뵽�ֽ����У����ʹ��Socket���д���
	 * @param id һ��������ֵ
	 * @return byte[] ����һ���ֽ�����
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
	 * �÷����ǽ�һ��FoodOrder���List�б���з�װ��ͨ��ObjectOutputStream��д�뵽�ֽ����У�Ȼ����Socket���д���
	 * @param foodorderlist foodorder�����б�
	 * @return byte[] ����һ���ֽ�����
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
	 * �÷����ǽ�һ��Table��Ķ�����з�װ��ͨ��ObjectOutputStream��д�뵽һ���ֽ����У�Ȼ��ʹ��Socket���д���
	 * @param table һ��Table����ʵ��
	 * @return byte[] ����һ���ֽ�����
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
	 * �÷����ǽ�Socketͨ�����õ����ֽ���ת����Table���һ��ʵ�������б�
	 * @param bs Socketͨ�������յ���һ���ֽ�������
	 * @return table ����һ��Table��Ķ���ʵ���б�
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
