package com.EleMenu403_click;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dao.ipDao;
import com.dao.Impl.ipDaoImpl;
import com.EleMenu403_click.R;
import com.model.IP;
/**
 * 该Activity是用来配置服务器IP地址和服务器端口
 * @author ping
 *
 */
public class serverConfigerActivity extends Activity {
	/**
	 * 定义的IP地址第一部分EditText控件
	 */
	private EditText serverIp_1 = null;
	/**
	 * 定义的IP地址第二部分EditText控件
	 */
	private EditText serverIp_2 = null;
	/**
	 * 定义的IP地址第三部分EditText控件
	 */
	private EditText serverIp_3 = null;
	/**
	 * 定义的IP地址第四部分EditText控件
	 */
	private EditText serverIp_4 = null;
	/**
	 * 定义的端口EditText控件
	 */
	private EditText serverPort = null;

	private Button btn_servercommit = null;
	private Button btn_serverBack = null;
	/**
	 * IpDao的实例，单例模式
	 */
	private ipDao ipdao;
	/**
	 * 一个IP对象实例
	 */
	private IP serverIp;
	/**
	 * 重写的Activity方法，当有按键时调用该方法，本方法是对返回按键的重写，当按下返回键时跳转到HomeActivity
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			Intent intent = new Intent();
			intent.setClass(serverConfigerActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	/**
	 * 构造serverConfigerActivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serverconfiger);
		/**
		 * 先查询数据库是否存在ServerIp
		 */
		ipdao = new ipDaoImpl(this);
		serverIp = ipdao.findServerIp(this);
		/**
		 * 通过控件Id获取控件对象
		 */
		serverIp_1 = (EditText) findViewById(R.id.Et_serverIP_1);
		serverIp_2 = (EditText) findViewById(R.id.Et_serverIP_2);
		serverIp_3 = (EditText) findViewById(R.id.Et_serverIP_3);
		serverIp_4 = (EditText) findViewById(R.id.Et_serverIP_4);
		serverPort = (EditText) findViewById(R.id.Ev_serverport);
		/**
		 * 如果存在ServerIp则初始化IP控件和端口控件
		 */
		if (serverIp != null) {
			serverIp_1.setText("" + serverIp.getIPpart1());
			serverIp_2.setText("" + serverIp.getIPpart2());
			serverIp_3.setText("" + serverIp.getIPpart3());
			serverIp_4.setText("" + serverIp.getIPpart4());
			serverPort.setText("" + serverIp.getPort());
		}

		btn_servercommit = (Button) findViewById(R.id.btn_serverconfigerCommit);
		btn_serverBack = (Button) findViewById(R.id.btn_serverback);

		btn_servercommit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (serverIp_1.getText().toString().equals("")
						|| Integer.valueOf(serverIp_1.getText().toString()) >= 255
						|| serverIp_2.getText().toString().equals("")
						|| Integer.valueOf(serverIp_2.getText().toString()) > 255
						|| Integer.valueOf(serverIp_3.getText().toString()) > 255
						|| serverIp_3.getText().toString().equals("")
						|| Integer.valueOf(serverIp_4.getText().toString()) > 255
						|| serverIp_4.getText().toString().equals("")) {
					Toast.makeText(serverConfigerActivity.this,
							"对不起，您输入的IP地址有误,请重新输入！！", Toast.LENGTH_SHORT)
							.show();
					serverIp_1.setText("");
					serverIp_2.setText("");
					serverIp_3.setText("");
					serverIp_4.setText("");
				} else {
					if (serverIp == null) {
						serverIp = new IP();
						serverIp.set_id(1);
					}
					serverIp.setIPpart1(Integer.valueOf(serverIp_1.getText()
							.toString()));
					serverIp.setIPpart2(Integer.valueOf(serverIp_2.getText()
							.toString()));
					serverIp.setIPpart3(Integer.valueOf(serverIp_3.getText()
							.toString()));
					serverIp.setIPpart4(Integer.valueOf(serverIp_4.getText()
							.toString()));
					serverIp.setPort(Integer.valueOf(serverPort.getText()
							.toString()));
					//根据控件的值更新ServerIP和端口，并写入数据库
					ipdao.updateServerIp(serverIp);
					try {
						Intent intent = new Intent();
						intent.setClass(serverConfigerActivity.this,
								HomeActivity.class);
						startActivity(intent);
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(serverConfigerActivity.this, "抱歉，Activity页面跳转失败，请重新运行程序！！！", Toast.LENGTH_SHORT).show();
					} finally {
						finish();
					}
				}
			}
		});

		btn_serverBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(serverConfigerActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
