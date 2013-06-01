package com.EleMenu403_click;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.dao.ipDao;
import com.dao.Impl.ipDaoImpl;
import com.EleMenu403_click.R;
import com.model.IP;
/**
 * 该Activity是设置打印机IP地址和端口，并且可设置是否启用打印机
 * @author ping
 *
 */
public class printerConfigerActivity extends Activity {
	/**
	 * 声明一个全局Application对象实例
	 */
	private myApplication myapplication;
	/**
	 * EditText 打印机1IP地址的第一部分
	 */
	private EditText printIp1_part1 = null;
	/**
	 * EditText 打印机1IP地址的第二部分
	 */
	private EditText printIp1_part2 = null;
	/**
	 * EditText 打印机1IP地址的第三部分
	 */
	private EditText printIp1_part3 = null;
	/**
	 * EditText 打印机1IP地址的第四部分
	 */
	private EditText printIp1_part4 = null;
	
	/**
	 * EditText 打印机2IP地址的第一部分
	 */
	private EditText printIp2_part1 = null;
	/**
	 * EditText 打印机2IP地址的第二部分
	 */
	private EditText printIp2_part2 = null;
	/**
	 * EditText 打印机2IP地址的第三部分
	 */
	private EditText printIp2_part3 = null;
	/**
	 * EditText 打印机2IP地址的第四部分
	 */
	private EditText printIp2_part4 = null;
	
	/**
	 * EditText 打印机3IP地址的第一部分
	 */
	private EditText printIp3_part1 = null;
	/**
	 * EditText 打印机3IP地址的第二部分
	 */
	private EditText printIp3_part2 = null;
	/**
	 * EditText 打印机3IP地址的第三部分
	 */
	private EditText printIp3_part3 = null;
	/**
	 * EditText 打印机3IP地址的第四部分
	 */
	private EditText printIp3_part4 = null;
	
	/**
	 * EditText 打印机4IP地址的第一部分
	 */
	private EditText printIp4_part1 = null;
	/**
	 * EditText 打印机4IP地址的第二部分
	 */
	private EditText printIp4_part2 = null;
	/**
	 * EditText 打印机4IP地址的第三部分
	 */
	private EditText printIp4_part3 = null;
	/**
	 * EditText 打印机4IP地址的第四部分
	 */
	private EditText printIp4_part4 = null;
	
	/**
	 * EditText 打印机5IP地址的第一部分
	 */
	private EditText printIp5_part1 = null;
	/**
	 * EditText 打印机5IP地址的第二部分
	 */
	private EditText printIp5_part2 = null;
	/**
	 * EditText 打印机5IP地址的第三部分
	 */
	private EditText printIp5_part3 = null;
	/**
	 * EditText 打印机5IP地址的第四部分
	 */
	private EditText printIp5_part4 = null;
	
	/**
	 * EditText 打印机6IP地址的第一部分
	 */
	private EditText printIp6_part1 = null;
	/**
	 * EditText 打印机6IP地址的第二部分
	 */
	private EditText printIp6_part2 = null;
	/**
	 * EditText 打印机6IP地址的第三部分
	 */
	private EditText printIp6_part3 = null;
	/**
	 * EditText 打印机6IP地址的第四部分
	 */
	private EditText printIp6_part4 = null;

	/**
	 * EditText 打印机1端口
	 */
	private EditText printIp1_port = null;
	/**
	 * EditText 打印机2端口
	 */
	private EditText printIp2_port = null;
	/**
	 * EditText 打印机3端口
	 */
	private EditText printIp3_port = null;
	/**
	 * EditText 打印机4端口
	 */
	private EditText printIp4_port = null;
	/**
	 * EditText 打印机5端口
	 */
	private EditText printIp5_port = null;
	/**
	 * EditText 打印机6端口
	 */
	private EditText printIp6_port = null;

	/**
	 * CheckBox 打印机1是否启用选择框
	 */
	private CheckBox printCb1 = null;
	/**
	 * CheckBox 打印机2是否启用选择框
	 */
	private CheckBox printCb2 = null;
	/**
	 * CheckBox 打印机3是否启用选择框
	 */
	private CheckBox printCb3 = null;
	/**
	 * CheckBox 打印机4是否启用选择框
	 */
	private CheckBox printCb4 = null;
	/**
	 * CheckBox 打印机5是否启用选择框
	 */
	private CheckBox printCb5 = null;
	/**
	 * CheckBox 打印机6是否启用选择框
	 */
	private CheckBox printCb6 = null;

	private Button btn_printerCommit = null;
	private Button btn_printerBack = null;
	/**
	 * IpDao的实例，单例模式
	 */
	private ipDao ipdao;
	/**
	 * 一个IP对象列表实例
	 */
	private List<IP> printerIplist;
	/**
	 * 重写的Activity方法，当有按键时调用该方法，本方法是对返回按键的重写，当按下返回键时跳转到HomeActivity
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			Intent intent = new Intent();
			intent.setClass(printerConfigerActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 构造printerConfigerActivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.priterconfiger);
		//获取全局Application对象
		myapplication = (myApplication) getApplication();
		
		ipdao = new ipDaoImpl(this);
		printerIplist = ipdao.findAllPrinterIp();
		//通过控件Id获取控件对象
		printIp1_part1 = (EditText) findViewById(R.id.Et_printerIP_1);
		printIp1_part2 = (EditText) findViewById(R.id.Et_printerIP_2);
		printIp1_part3 = (EditText) findViewById(R.id.Et_printerIP_3);
		printIp1_part4 = (EditText) findViewById(R.id.Et_printerIP_4);
		printIp1_port = (EditText) findViewById(R.id.Et_printerport1);
		printCb1 = (CheckBox) findViewById(R.id.Cb_box1);

		printIp2_part1 = (EditText) findViewById(R.id.Et_printerIP2_1);
		printIp2_part2 = (EditText) findViewById(R.id.Et_printerIP2_2);
		printIp2_part3 = (EditText) findViewById(R.id.Et_printerIP2_3);
		printIp2_part4 = (EditText) findViewById(R.id.Et_printerIP2_4);
		printIp2_port = (EditText) findViewById(R.id.Et_printerport2);
		printCb2 = (CheckBox) findViewById(R.id.Cb_box2);

		printIp3_part1 = (EditText) findViewById(R.id.Et_printerIP3_1);
		printIp3_part2 = (EditText) findViewById(R.id.Et_printerIP3_2);
		printIp3_part3 = (EditText) findViewById(R.id.Et_printerIP3_3);
		printIp3_part4 = (EditText) findViewById(R.id.Et_printerIP3_4);
		printIp3_port = (EditText) findViewById(R.id.Et_printerport3);
		printCb3 = (CheckBox) findViewById(R.id.Cb_box3);

		printIp4_part1 = (EditText) findViewById(R.id.Et_printerIP4_1);
		printIp4_part2 = (EditText) findViewById(R.id.Et_printerIP4_2);
		printIp4_part3 = (EditText) findViewById(R.id.Et_printerIP4_3);
		printIp4_part4 = (EditText) findViewById(R.id.Et_printerIP4_4);
		printIp4_port = (EditText) findViewById(R.id.Et_printerport4);
		printCb4 = (CheckBox) findViewById(R.id.Cb_box4);

		printIp5_part1 = (EditText) findViewById(R.id.Et_printerIP5_1);
		printIp5_part2 = (EditText) findViewById(R.id.Et_printerIP5_2);
		printIp5_part3 = (EditText) findViewById(R.id.Et_printerIP5_3);
		printIp5_part4 = (EditText) findViewById(R.id.Et_printerIP5_4);
		printIp5_port = (EditText) findViewById(R.id.Et_printerport5);
		printCb5 = (CheckBox) findViewById(R.id.Cb_box5);

		printIp6_part1 = (EditText) findViewById(R.id.Et_printerIP6_1);
		printIp6_part2 = (EditText) findViewById(R.id.Et_printerIP6_2);
		printIp6_part3 = (EditText) findViewById(R.id.Et_printerIP6_3);
		printIp6_part4 = (EditText) findViewById(R.id.Et_printerIP6_4);
		printIp6_port = (EditText) findViewById(R.id.Et_printerport6);
		printCb6 = (CheckBox) findViewById(R.id.Cb_box6);
		//如果系统设置为使用服务器，则默认为不启用打印机
		if(myapplication.getIsHaveServer() == 0) {
			printCb1.setChecked(true);
			printCb2.setChecked(true);
			printCb3.setChecked(true);
			printCb4.setChecked(true);
			printCb5.setChecked(true);
			printCb6.setChecked(true);
		}
		//用从数据库查询到的数据来初始化打印机IP地址和端口
		for (int i = 0; i < printerIplist.size(); i++) {
			switch (printerIplist.get(i).get_id()) {
			case 1: {
				printIp1_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp1_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp1_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp1_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp1_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			case 2: {
				printIp2_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp2_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp2_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp2_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp2_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			case 3: {
				printIp3_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp3_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp3_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp3_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp3_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			case 4: {
				printIp4_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp4_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp4_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp4_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp4_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			case 5: {
				printIp5_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp5_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp5_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp5_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp5_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			case 6: {
				printIp6_part1.setText("" + printerIplist.get(i).getIPpart1());
				printIp6_part2.setText("" + printerIplist.get(i).getIPpart2());
				printIp6_part3.setText("" + printerIplist.get(i).getIPpart3());
				printIp6_part4.setText("" + printerIplist.get(i).getIPpart4());
				printIp6_port.setText("" + printerIplist.get(i).getPort());
				break;
			}
			default:
				break;
			}
		}

		btn_printerCommit = (Button) findViewById(R.id.btn_printerCommit);
		btn_printerBack = (Button) findViewById(R.id.btn_printerback);
		//为确定按钮添加事件监听器
		btn_printerCommit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(printerConfigerActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//为返回按钮添加事件监听器
		btn_printerBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(printerConfigerActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
