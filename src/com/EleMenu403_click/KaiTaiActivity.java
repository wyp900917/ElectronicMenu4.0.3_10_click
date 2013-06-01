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
 * 开台页面,可以选择大厅查看大厅的桌台信息，可以选择一个空闲桌台进行开台操作，也可以选择一个忙碌桌台修改其状态
 * 
 * @author ping
 * 
 */
public class KaiTaiActivity extends Activity {
	/**
	 * 声明全局Application对象
	 */
	private myApplication myapplication;
	// 声明返回按钮控件对象
	private Button btn_kaitaiBack = null;
	// 声明GridView控件对象，用来展示桌台信息
	private GridView gridview;
	// 声明Spinner控件对象。用来选择大厅
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
	 * 用来检测当前网络是否可用
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
	 * 首次创建KaiTaiActivity时调用该方法，绘制页面布局
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaitai);
		// 获取全局Application对象
		myapplication = (myApplication) getApplication();
		btn_kaitaiBack = (Button) findViewById(R.id.btn_kaitaiBack);
		btn_kaitaiBack.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				KaiTaiActivity.this.finish();
			}
		});

		// 查询所有的大厅信息
		roomDao roomdao = new roomDaoImpl(this);
		roomlist = roomdao.findAll();
		// 通过控件Id获取Spinner控件对象
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
			Toast.makeText(KaiTaiActivity.this, "当前网络连接不可用！",
					Toast.LENGTH_SHORT).show();
		} else {
			if (myapplication.getIsHaveServer() == 1) {
				alltablelist = create(0);
			}
			if (alltablelist.size() == 0) {
				// Toast.makeText(KaiTaiActivity.this, "获取所有桌台信息失败！！",
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
	 * 创建Socket一个连接，传递一个大厅号。向服务器申请获得该大厅的最新桌台信息
	 * 
	 * @param roomId
	 *            大厅Id号
	 * @return tablelist 返回一个桌台列表
	 */
	private List<Table> create(int roomId) {
		// 获取服务器IP地址
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		if (serverip == null) {
			Toast.makeText(KaiTaiActivity.this, "未配置服务器IP！", Toast.LENGTH_SHORT)
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
			Toast.makeText(KaiTaiActivity.this, "未知的服务器IP！", Toast.LENGTH_SHORT)
					.show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "未连接上服务器！！！！",
					Toast.LENGTH_SHORT).show();
		}
		/*
		 * Table t=new Table(); t.set_id(3); t.setPeopleNum(5); t.setRoomId(5);
		 * t.setState(0); tablelist.add(t);
		 */
		return tablelist;

	}

	/**
	 * 创建Socket一个连接，传递一个桌台号。向服务器申请开台或更改桌台状态
	 * 
	 * @param tableId
	 *            桌台Id号
	 * 
	 */
	private int socketFortable(int tableId) {
		// 获取服务器IP地址
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		if (serverip == null) {
			Toast.makeText(KaiTaiActivity.this, "未配置服务器IP！", Toast.LENGTH_SHORT)
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
			Toast.makeText(KaiTaiActivity.this, "未知的服务器IP！", Toast.LENGTH_SHORT)
					.show();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(KaiTaiActivity.this, "未连接上服务器！！！！",
					Toast.LENGTH_SHORT).show();
			return 0;
		}
	}

	/**
	 * 通过绘制GridView来展示桌台信息
	 * 
	 * @param tablelist
	 *            桌台信息列表
	 */
	private void showTables(List<Table> tablelist) {
		// 通过控件Id获取GridView控件对象
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
			map.put("peopleNum", "人数：" + tablelist.get(i).getPeopleNum() + "人");
			list.add(map);
		}
		// 创建一个简单适配器类来获取数据信息
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
	 * 创建开台操作的提示对话框
	 * 
	 * @param tableid
	 *            桌台Id号
	 * @return 返回一个提示对话框对象
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
		// 如果所选的桌台是空闲桌台，则提示输入工号和密码进行开台
		if (state == 0) {
			// 设置布局文件
			layout = inflater.inflate(R.layout.kaitaidialog,
					(ViewGroup) findViewById(R.id.kaitai_ll));
			// 通过控件Id获取控件对象
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

			// 加button
			final Button positiveButton = (Button) layout
					.findViewById(R.id.btn_ok);
			final Button negativeButton = (Button) layout
					.findViewById(R.id.btn_cancel);

			tableId.setText("桌台" + tableid);
			tableId.setFocusable(false);
			// builder.setView(layout);

			positiveButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (waiterId.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "服务工号为空！！",
								Toast.LENGTH_SHORT).show();
					} else if (password.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "密码为空！！",
								Toast.LENGTH_SHORT).show();
					} else if (peopleNum.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "入座人数为空！！",
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
							Toast.makeText(KaiTaiActivity.this, "服务工号不存在！！",
									Toast.LENGTH_SHORT).show();
							waiterId.setText("");
						} else if (!(pass.equals(psw))) {
							Toast.makeText(KaiTaiActivity.this, "服务密码错误！！",
									Toast.LENGTH_SHORT).show();
							password.setText("");
						} else if (peoplenum < 1) {
							Toast.makeText(KaiTaiActivity.this, "入座人数错误！！",
									Toast.LENGTH_SHORT).show();
							peopleNum.setText("");
						} else {
							// 如果使用服务器，则向服务器发送一个开台请求
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
											"开台成功啦！！！", Toast.LENGTH_LONG)
											.show();
									Intent intent = new Intent();
									intent.putExtra("PresentOrder", order);
									intent.setClass(KaiTaiActivity.this,
											IndexActivity.class);
									startActivity(intent);
									finish();
								} else if (orderId == 0) {
									Toast.makeText(KaiTaiActivity.this,
											"开台失败，桌台已被占用！！！", Toast.LENGTH_LONG)
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
								Toast.makeText(KaiTaiActivity.this, "开台成功啦！！！",
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
		// 如果所选的桌台是忙碌状态，则提示是否将桌台状态修改为空闲状态
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

			// 添加内容
			positiveButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (delwaiter_num.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "服务工号为空！！",
								Toast.LENGTH_SHORT).show();
					} else if (delpsw.getText().toString().trim().equals("")) {
						Toast.makeText(KaiTaiActivity.this, "密码为空！！",
								Toast.LENGTH_SHORT).show();
					} else {
						int waiterid = Integer.valueOf(delwaiter_num.getText()
								.toString());
						String psw = delpsw.getText().toString();
						String pass = new waiterDaoImpl(KaiTaiActivity.this)
								.checkPassword(waiterid);
						if (pass == null) {
							Toast.makeText(KaiTaiActivity.this, "服务工号不存在！！",
									Toast.LENGTH_SHORT).show();
							delwaiter_num.setText("");
						} else if (!(pass.equals(psw))) {
							Toast.makeText(KaiTaiActivity.this, "服务密码错误！！",
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
							// 加取消对话框
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
