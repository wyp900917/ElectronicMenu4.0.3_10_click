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
 * ��Activity�����ô�ӡ��IP��ַ�Ͷ˿ڣ����ҿ������Ƿ����ô�ӡ��
 * @author ping
 *
 */
public class printerConfigerActivity extends Activity {
	/**
	 * ����һ��ȫ��Application����ʵ��
	 */
	private myApplication myapplication;
	/**
	 * EditText ��ӡ��1IP��ַ�ĵ�һ����
	 */
	private EditText printIp1_part1 = null;
	/**
	 * EditText ��ӡ��1IP��ַ�ĵڶ�����
	 */
	private EditText printIp1_part2 = null;
	/**
	 * EditText ��ӡ��1IP��ַ�ĵ�������
	 */
	private EditText printIp1_part3 = null;
	/**
	 * EditText ��ӡ��1IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp1_part4 = null;
	
	/**
	 * EditText ��ӡ��2IP��ַ�ĵ�һ����
	 */
	private EditText printIp2_part1 = null;
	/**
	 * EditText ��ӡ��2IP��ַ�ĵڶ�����
	 */
	private EditText printIp2_part2 = null;
	/**
	 * EditText ��ӡ��2IP��ַ�ĵ�������
	 */
	private EditText printIp2_part3 = null;
	/**
	 * EditText ��ӡ��2IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp2_part4 = null;
	
	/**
	 * EditText ��ӡ��3IP��ַ�ĵ�һ����
	 */
	private EditText printIp3_part1 = null;
	/**
	 * EditText ��ӡ��3IP��ַ�ĵڶ�����
	 */
	private EditText printIp3_part2 = null;
	/**
	 * EditText ��ӡ��3IP��ַ�ĵ�������
	 */
	private EditText printIp3_part3 = null;
	/**
	 * EditText ��ӡ��3IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp3_part4 = null;
	
	/**
	 * EditText ��ӡ��4IP��ַ�ĵ�һ����
	 */
	private EditText printIp4_part1 = null;
	/**
	 * EditText ��ӡ��4IP��ַ�ĵڶ�����
	 */
	private EditText printIp4_part2 = null;
	/**
	 * EditText ��ӡ��4IP��ַ�ĵ�������
	 */
	private EditText printIp4_part3 = null;
	/**
	 * EditText ��ӡ��4IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp4_part4 = null;
	
	/**
	 * EditText ��ӡ��5IP��ַ�ĵ�һ����
	 */
	private EditText printIp5_part1 = null;
	/**
	 * EditText ��ӡ��5IP��ַ�ĵڶ�����
	 */
	private EditText printIp5_part2 = null;
	/**
	 * EditText ��ӡ��5IP��ַ�ĵ�������
	 */
	private EditText printIp5_part3 = null;
	/**
	 * EditText ��ӡ��5IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp5_part4 = null;
	
	/**
	 * EditText ��ӡ��6IP��ַ�ĵ�һ����
	 */
	private EditText printIp6_part1 = null;
	/**
	 * EditText ��ӡ��6IP��ַ�ĵڶ�����
	 */
	private EditText printIp6_part2 = null;
	/**
	 * EditText ��ӡ��6IP��ַ�ĵ�������
	 */
	private EditText printIp6_part3 = null;
	/**
	 * EditText ��ӡ��6IP��ַ�ĵ��Ĳ���
	 */
	private EditText printIp6_part4 = null;

	/**
	 * EditText ��ӡ��1�˿�
	 */
	private EditText printIp1_port = null;
	/**
	 * EditText ��ӡ��2�˿�
	 */
	private EditText printIp2_port = null;
	/**
	 * EditText ��ӡ��3�˿�
	 */
	private EditText printIp3_port = null;
	/**
	 * EditText ��ӡ��4�˿�
	 */
	private EditText printIp4_port = null;
	/**
	 * EditText ��ӡ��5�˿�
	 */
	private EditText printIp5_port = null;
	/**
	 * EditText ��ӡ��6�˿�
	 */
	private EditText printIp6_port = null;

	/**
	 * CheckBox ��ӡ��1�Ƿ�����ѡ���
	 */
	private CheckBox printCb1 = null;
	/**
	 * CheckBox ��ӡ��2�Ƿ�����ѡ���
	 */
	private CheckBox printCb2 = null;
	/**
	 * CheckBox ��ӡ��3�Ƿ�����ѡ���
	 */
	private CheckBox printCb3 = null;
	/**
	 * CheckBox ��ӡ��4�Ƿ�����ѡ���
	 */
	private CheckBox printCb4 = null;
	/**
	 * CheckBox ��ӡ��5�Ƿ�����ѡ���
	 */
	private CheckBox printCb5 = null;
	/**
	 * CheckBox ��ӡ��6�Ƿ�����ѡ���
	 */
	private CheckBox printCb6 = null;

	private Button btn_printerCommit = null;
	private Button btn_printerBack = null;
	/**
	 * IpDao��ʵ��������ģʽ
	 */
	private ipDao ipdao;
	/**
	 * һ��IP�����б�ʵ��
	 */
	private List<IP> printerIplist;
	/**
	 * ��д��Activity���������а���ʱ���ø÷������������ǶԷ��ذ�������д�������·��ؼ�ʱ��ת��HomeActivity
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
	 * ����printerConfigerActivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.priterconfiger);
		//��ȡȫ��Application����
		myapplication = (myApplication) getApplication();
		
		ipdao = new ipDaoImpl(this);
		printerIplist = ipdao.findAllPrinterIp();
		//ͨ���ؼ�Id��ȡ�ؼ�����
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
		//���ϵͳ����Ϊʹ�÷���������Ĭ��Ϊ�����ô�ӡ��
		if(myapplication.getIsHaveServer() == 0) {
			printCb1.setChecked(true);
			printCb2.setChecked(true);
			printCb3.setChecked(true);
			printCb4.setChecked(true);
			printCb5.setChecked(true);
			printCb6.setChecked(true);
		}
		//�ô����ݿ��ѯ������������ʼ����ӡ��IP��ַ�Ͷ˿�
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
		//Ϊȷ����ť����¼�������
		btn_printerCommit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(printerConfigerActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//Ϊ���ذ�ť����¼�������
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
