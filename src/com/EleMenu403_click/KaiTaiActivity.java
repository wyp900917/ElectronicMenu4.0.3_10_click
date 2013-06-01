package com.EleMenu403_click;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dao.roomDao;
import com.dao.tableDao;
import com.dao.Impl.ipDaoImpl;
import com.dao.Impl.orderDaoImpl;
import com.dao.Impl.roomDaoImpl;
import com.dao.Impl.tableDaoImpl;
import com.dao.Impl.waiterDaoImpl;
import com.model.IP;
import com.model.Order;
import com.model.Room;
import com.model.Table;
import com.socket.ScoketConn;

/**
 * ��̨ҳ��,����ѡ������鿴��������̨��Ϣ������ѡ��һ��������̨���п�̨������Ҳ����ѡ��һ��æµ��̨�޸���״̬
 * 
 * @author ping
 * 
 */
public class KaiTaiActivity extends Activity {
	/**
	 * ����ȫ��Application����
	 */
	private myApplication myapplication;
	// �������ذ�ť�ؼ�����
	private Button btn_kaitaiBack = null;
	// ����GridView�ؼ���������չʾ��̨��Ϣ
	private GridView gridview;
	// ����Spinner�ؼ���������ѡ�����
	private Spinner spinner;
	private String serverIP;
	private int serverPort;
	ArrayAdapter<String> adapter;
	List<Room> roomlist = new ArrayList<Room>();
	List<Table> alltablelist = new ArrayList<Table>();
	List<Table> tablelist = new ArrayList<Table>();

	private View layout = null;
	private AlertDialog temp = null;

	public AlertDialog getTemp() {
		return temp;
	}

	public void setTemp(AlertDialog temp) {
		this.temp = temp;
	}

	/**
	 * ������⵱ǰ�����Ƿ����
	 * 
	 * @param context
	 */
	private boolean isConnect(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * �״δ���KaiTaiActivityʱ���ø÷���������ҳ�沼��
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaitai);
		// ��ȡȫ��Application����
		myapplication = (myApplication) getApplication();
		btn_kaitaiBack = (Button) findViewById(R.id.btn_kaitaiBack);
		btn_kaitaiBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				KaiTaiActivity.this.finish();
			}
		});

		// ��ѯ���еĴ�����Ϣ
		roomDao roomdao = new roomDaoImpl(this);
		roomlist = roomdao.findAll();
		// ͨ���ؼ�Id��ȡSpinner�ؼ�����
		spinner = (Spinner) findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		// Sets the layout resource to create the drop down views.
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for (Room room : roomlist) {
			adapter.add(room.getRoomName());
		}
		spinner.setAdapter(adapter);
		// Register a callback to be invoked when an item in this AdapterView
		// has been selected.
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (myapplication.getIsHaveServer() == 1) {
					tablelist = create(roomlist.get(arg2).get_id());
					if (tablelist.size() == 0) {
						Room presentRoom = roomlist.get(arg2);
						tablelist = new tableDaoImpl(KaiTaiActivity.this)
								.findTableByroomId(presentRoom.get_id());
					}
				} else {
					Room presentRoom = roomlist.get(arg2);
					tablelist = new tableDaoImpl(KaiTaiActivity.this)
							.findTableByroomId(presentRoom.get_id());
				}
				showTables(tablelist);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		if (!isConnect(this)) {
			Toast.makeText(KaiTaiActivity.this, "��ǰ�������Ӳ����ã�",
					Toast.LENGTH_SHORT).show();
		} else {
			if (myapplication.getIsHaveServer() == 1) {
				alltablelist = create(0);
			}
			if (alltablelist.size() == 0) {
				// Toast.makeText(KaiTaiActivity.this, "��ȡ������̨��Ϣʧ�ܣ���",
				// Toast.LENGTH_SHORT).show();
			} else {
				tableDao tabledao = new tableDaoImpl(this);
				tabledao.deleteAllTable();
				for (int i = 0; i < alltablelist.size(); i++) {
					tabledao.update(alltablelist.get(i));
				}
			}
		}
	}

	/**
	 * ����Socketһ�����ӣ�����һ�������š�������������øô�����������̨��Ϣ
	 * 
	 * @param roomId
	 *            ����Id��
	 * @return tablelist ����һ����̨�б�
	 */
	private List<Table> create(int roomId) {
		// ��ȡ������IP��ַ
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		if (serverip == null) {
			Toast.makeText(KaiTaiActivity.this, "δ���÷�����IP��", Toast.LENGTH_SHORT)
					.show();
			return tablelist;
		}
		serverIP = serverip.getIPpart1() + "." + serverip.getIPpart2() + "."
				+ serverip.getIPpart3() + "." + serverip.getIPpart4();
		serverPort = serverip.getPort();
		List<Table> tablelist = new ArrayList<Table>();
		try {
			Socket server = new Socket(serverIP, serverPort);
			OutputStream out = server.getOutputStream();
			byte[] bs = ScoketConn.TabletoBytes(roomId);
			out.write(bs);
			InputStream in = server.getInputStream();
			byte[] bin = new byte[2000];
			in.read(bin);
			tablelist = ScoketConn.TablegetBytes(bin);
			server.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "δ֪�ķ�����IP��", Toast.LENGTH_SHORT)
					.show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "δ�����Ϸ�������������",
					Toast.LENGTH_SHORT).show();
		}
		/*
		 * Table t=new Table(); t.set_id(3); t.setPeopleNum(5); t.setRoomId(5);
		 * t.setState(0); tablelist.add(t);
		 */
		return tablelist;

	}

	/**
	 * ����Socketһ�����ӣ�����һ����̨�š�����������뿪̨�������̨״̬
	 * 
	 * @param tableId
	 *            ��̨Id��
	 * 
	 */
	private int socketFortable(int tableId) {
		// ��ȡ������IP��ַ
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		if (serverip == null) {
			Toast.makeText(KaiTaiActivity.this, "δ���÷�����IP��", Toast.LENGTH_SHORT)
					.show();
		}
		serverIP = serverip.getIPpart1() + "." + serverip.getIPpart2() + "."
				+ serverip.getIPpart3() + "." + serverip.getIPpart4();
		serverPort = serverip.getPort();
		try {
			Socket server = new Socket(serverIP, serverPort);
			OutputStream out = server.getOutputStream();
			byte[] bs = ScoketConn.TabletoBytes(tableId);
			out.write(bs);

			InputStream in = server.getInputStream();
			DataInputStream datain = new DataInputStream(in);
			// byte[] bin = new byte[4];
			int data = datain.readInt();
			server.close();
			return data;
			/*
			 * in.read(bin); int orderId = ScoketConn.IntgetBytes(bin);
			 * 
			 * return orderId;
			 */

		} catch (UnknownHostException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "δ֪�ķ�����IP��", Toast.LENGTH_SHORT)
					.show();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "δ�����Ϸ�������������",
					Toast.LENGTH_SHORT).show();
			return 0;
		}
	}

	/**
	 * ͨ������GridView��չʾ��̨��Ϣ
	 * 
	 * @param tablelist
	 *            ��̨��Ϣ�б�
	 */
	private void showTables(List<Table> tablelist) {
		// ͨ���ؼ�Id��ȡGridView�ؼ�����
		gridview = (GridView) findViewById(R.id.gridview);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		int tableImage;
		for (int i = 0; i < tablelist.size(); i++) {
			if (tablelist.get(i).getState() == 0) {
				tableImage = R.drawable.bg_tablen;
			} else if (tablelist.get(i).getState() == 1) {
				tableImage = R.drawable.bg_tabley;
			} else {
				tableImage = R.drawable.bg_tabley;
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tableId", tablelist.get(i).get_id());
			map.put("ItemImage", tableImage);
			map.put("peopleNum", "������" + tablelist.get(i).getPeopleNum() + "��");
			list.add(map);
		}
		// ����һ����������������ȡ������Ϣ
		SimpleAdapter saImageItems = new SimpleAdapter(this, list,
				R.layout.gvitem, new String[] { "tableId", "ItemImage",
						"peopleNum" }, new int[] { R.id.Tv_tableId,
						R.id.ItemImage, R.id.Tv_peopleNum });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> item = (HashMap<String, Object>) arg0
						.getItemAtPosition(arg2);
				// showMyDialog((Integer) item.get("tableId")).show();
				KaiTaiActivity.this.setTemp(showMyDialog((Integer) item
						.get("tableId")));
				KaiTaiActivity.this.getTemp().setView(layout, 0, 0, 0, 0);
				KaiTaiActivity.this.getTemp().show();
			}

		});
	}

	/**
	 * ������̨��������ʾ�Ի���
	 * 
	 * @param tableid
	 *            ��̨Id��
	 * @return ����һ����ʾ�Ի������
	 */
	private AlertDialog showMyDialog(final int tableid) {
		AlertDialog.Builder builder = new Builder(KaiTaiActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		int state = -1;
		int peoNum = 0;
		for (int i = 0; i < tablelist.size(); i++) {
			if (tablelist.get(i).get_id() == tableid) {
				state = tablelist.get(i).getState();
				peoNum = tablelist.get(i).getPeopleNum();
				break;
			}
		}
		// �����ѡ����̨�ǿ�����̨������ʾ���빤�ź�������п�̨
		if (state == 0) {
			// ���ò����ļ�
			layout = inflater.inflate(R.layout.kaitaidialog,
					(ViewGroup) findViewById(R.id.kaitai_ll));
			// ͨ���ؼ�Id��ȡ�ؼ�����
			final EditText tableId = (EditText) layout
					.findViewById(R.id.Et_tableId);
			final EditText waiterId = (EditText) layout
					.findViewById(R.id.Et_waiterNum);
			final EditText password = (EditText) layout
					.findViewById(R.id.Et_password);
			final EditText peopleNum = (EditText) layout
					.findViewById(R.id.Et_peopleNum);
			waiterId.setText("1");
			peopleNum.setText(peoNum+"");

			// ��button
			final Button positiveButton = (Button) layout
					.findViewById(R.id.btn_ok);
			final Button negativeButton = (Button) layout
					.findViewById(R.id.btn_cancel);

			tableId.setText("��̨" + tableid);
			tableId.setFocusable(false);
			// builder.setView(layout);

			positiveButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (waiterId.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "���񹤺�Ϊ�գ���",
								Toast.LENGTH_SHORT).show();
					} else if (password.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "����Ϊ�գ���",
								Toast.LENGTH_SHORT).show();
					} else if (peopleNum.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "��������Ϊ�գ���",
								Toast.LENGTH_SHORT).show();
					} else {
						int waiterid = Integer.valueOf(waiterId.getText()
								.toString());
						String psw = password.getText().toString();
						String pass = new waiterDaoImpl(KaiTaiActivity.this)
								.checkPassword(waiterid);
						int peoplenum = Integer.valueOf(peopleNum.getText()
								.toString());
						if (pass == null) {
							Toast.makeText(KaiTaiActivity.this, "���񹤺Ų����ڣ���",
									Toast.LENGTH_SHORT).show();
							waiterId.setText("");
						} else if (!(pass.equals(psw))) {
							Toast.makeText(KaiTaiActivity.this, "����������󣡣�",
									Toast.LENGTH_SHORT).show();
							password.setText("");
						} else if (peoplenum < 1) {
							Toast.makeText(KaiTaiActivity.this, "�����������󣡣�",
									Toast.LENGTH_SHORT).show();
							peopleNum.setText("");
						} else {
							// ���ʹ�÷��������������������һ����̨����
							if (myapplication.getIsHaveServer() == 1) {
								int orderId = socketFortable(1000 + tableid);
								if (orderId != 0) {
									Order order = new Order();
									order.setTableId(tableid);
									order.setWaiterId(waiterid);
									order.setTotalPrice(0);
									order.setOrderState(0);
									order.setTime(new Date());
									order.set_id(orderId);
									new orderDaoImpl(KaiTaiActivity.this)
											.addOrder(order);
									new tableDaoImpl(KaiTaiActivity.this)
											.updateState(tableid, 1);
									Toast.makeText(KaiTaiActivity.this,
											"��̨�ɹ���������", Toast.LENGTH_LONG)
											.show();
									Intent intent = new Intent();
									intent.putExtra("PresentOrder", order);
									intent.setClass(KaiTaiActivity.this,
											IndexActivity.class);
									startActivity(intent);
									finish();
								} else if (orderId == 0) {
									Toast.makeText(KaiTaiActivity.this,
											"��̨ʧ�ܣ���̨�ѱ�ռ�ã�����", Toast.LENGTH_LONG)
											.show();
									for (int i = 0; i < tablelist.size(); i++) {
										if (tablelist.get(i).get_id() == tableid) {
											tablelist.get(i).setState(1);
											break;
										}
									}
									new tableDaoImpl(KaiTaiActivity.this)
											.updateState(tableid, 1);
									showTables(tablelist);
								}
							} else {
								Order order = new Order();
								order.setTableId(tableid);
								order.setWaiterId(waiterid);
								order.setTotalPrice(0);
								order.setOrderState(0);
								order.setTime(new Date());
								order.set_id(new orderDaoImpl(
										KaiTaiActivity.this).maxId() + 1);
								new orderDaoImpl(KaiTaiActivity.this)
										.addOrder(order);
								new tableDaoImpl(KaiTaiActivity.this)
										.updateState(tableid, 1);
								Toast.makeText(KaiTaiActivity.this, "��̨�ɹ���������",
										Toast.LENGTH_LONG).show();
								Intent intent = new Intent();
								intent.putExtra("PresentOrder", order);
								intent.setClass(KaiTaiActivity.this,
										IndexActivity.class);
								startActivity(intent);
								finish();
							}
							KaiTaiActivity.this.getTemp().dismiss();
						}
					}
				}

			});

			negativeButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					KaiTaiActivity.this.getTemp().dismiss();
				}

			});
		}
		// �����ѡ����̨��æµ״̬������ʾ�Ƿ���̨״̬�޸�Ϊ����״̬
		if (state == 1) {
			layout = inflater.inflate(R.layout.deletedialog,
					(ViewGroup) findViewById(R.id.delete_ll));
			final EditText delwaiter_num = (EditText) layout
					.findViewById(R.id.Et_delwaiterNum);
			final EditText delpsw = (EditText) layout
					.findViewById(R.id.Et_delpassword);

			final Button positiveButton = (Button) layout
					.findViewById(R.id.btn_ok);
			final Button negativeButton = (Button) layout
					.findViewById(R.id.btn_cancel);

			// builder.setView(layout);

			// �������
			positiveButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (delwaiter_num.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "���񹤺�Ϊ�գ���",
								Toast.LENGTH_SHORT).show();
					} else if (delpsw.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "����Ϊ�գ���",
								Toast.LENGTH_SHORT).show();
					} else {
						int waiterid = Integer.valueOf(delwaiter_num.getText()
								.toString());
						String psw = delpsw.getText().toString();
						String pass = new waiterDaoImpl(KaiTaiActivity.this)
								.checkPassword(waiterid);
						if (pass == null) {
							Toast.makeText(KaiTaiActivity.this, "���񹤺Ų����ڣ���",
									Toast.LENGTH_SHORT).show();
							delwaiter_num.setText("");
						} else if (!(pass.equals(psw))) {
							Toast.makeText(KaiTaiActivity.this, "����������󣡣�",
									Toast.LENGTH_SHORT).show();
							delpsw.setText("");
						} else {
							if (myapplication.getIsHaveServer() == 1) {
								if (socketFortable(2000 + tableid) != 0) {
									new tableDaoImpl(KaiTaiActivity.this)
											.updateState(tableid, 0);
									for (int i = 0; i < tablelist.size(); i++) {
										if (tablelist.get(i).get_id() == tableid) {
											tablelist.get(i).setState(0);
											break;
										}
									}
									showTables(tablelist);
								}
							} else {
								new tableDaoImpl(KaiTaiActivity.this)
										.updateState(tableid, 0);
								for (int i = 0; i < tablelist.size(); i++) {
									if (tablelist.get(i).get_id() == tableid) {
										tablelist.get(i).setState(0);
										break;
									}
								}
								showTables(tablelist);
							}
							// ��ȡ���Ի���
							KaiTaiActivity.this.getTemp().dismiss();
							//
						}
					}
				}

			});
			negativeButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					KaiTaiActivity.this.getTemp().dismiss();
				}

			});
		}
		return builder.create();

	}
}
