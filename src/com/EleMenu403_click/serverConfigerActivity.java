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
 * ��Activity���������÷�����IP��ַ�ͷ������˿�
 * @author ping
 *
 */
public class serverConfigerActivity extends Activity {
	/**
	 * �����IP��ַ��һ����EditText�ؼ�
	 */
	private EditText serverIp_1 = null;
	/**
	 * �����IP��ַ�ڶ�����EditText�ؼ�
	 */
	private EditText serverIp_2 = null;
	/**
	 * �����IP��ַ��������EditText�ؼ�
	 */
	private EditText serverIp_3 = null;
	/**
	 * �����IP��ַ���Ĳ���EditText�ؼ�
	 */
	private EditText serverIp_4 = null;
	/**
	 * ����Ķ˿�EditText�ؼ�
	 */
	private EditText serverPort = null;

	private Button btn_servercommit = null;
	private Button btn_serverBack = null;
	/**
	 * IpDao��ʵ��������ģʽ
	 */
	private ipDao ipdao;
	/**
	 * һ��IP����ʵ��
	 */
	private IP serverIp;
	/**
	 * ��д��Activity���������а���ʱ���ø÷������������ǶԷ��ذ�������д�������·��ؼ�ʱ��ת��HomeActivity
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
	 * ����serverConfigerActivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serverconfiger);
		/**
		 * �Ȳ�ѯ���ݿ��Ƿ����ServerIp
		 */
		ipdao = new ipDaoImpl(this);
		serverIp = ipdao.findServerIp(this);
		/**
		 * ͨ���ؼ�Id��ȡ�ؼ�����
		 */
		serverIp_1 = (EditText) findViewById(R.id.Et_serverIP_1);
		serverIp_2 = (EditText) findViewById(R.id.Et_serverIP_2);
		serverIp_3 = (EditText) findViewById(R.id.Et_serverIP_3);
		serverIp_4 = (EditText) findViewById(R.id.Et_serverIP_4);
		serverPort = (EditText) findViewById(R.id.Ev_serverport);
		/**
		 * �������ServerIp���ʼ��IP�ؼ��Ͷ˿ڿؼ�
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
							"�Բ����������IP��ַ����,���������룡��", Toast.LENGTH_SHORT)
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
					//���ݿؼ���ֵ����ServerIP�Ͷ˿ڣ���д�����ݿ�
					ipdao.updateServerIp(serverIp);
					try {
						Intent intent = new Intent();
						intent.setClass(serverConfigerActivity.this,
								HomeActivity.class);
						startActivity(intent);
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(serverConfigerActivity.this, "��Ǹ��Activityҳ����תʧ�ܣ����������г��򣡣���", Toast.LENGTH_SHORT).show();
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
