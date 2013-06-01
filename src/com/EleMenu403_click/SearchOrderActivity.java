package com.EleMenu403_click;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.Impl.ipDaoImpl;
import com.dao.Impl.orderDaoImpl;
import com.dao.Impl.orderDetailDaoImpl;
import com.model.FoodOrder;
import com.model.IP;
import com.model.Order;
import com.socket.ScoketConn;

/**
 * 订单列表展示页面，服务员可以在此页面中对相应订单进行下单、删除或续点操作
 * 
 * @author ping
 * 
 */
public class SearchOrderActivity extends Activity {

	private Button btn_back = null;
	/**
	 * ListView控件的数据适配器对象，使用单例模式
	 */
	private ListViewAdapter listviewAdapter;
	/**
	 * 定义一个订单列表，存放查询到的所有订单对象
	 */
	private List<Order> orderlists = new ArrayList<Order>();
	/**
	 * 当前的服务员Id号，该Id号是从上一个页面用参数传递而来
	 */
	private int presentWaiterId;

	// 声明一个服务器IP地址和端口的变量
	private String serverIP;
	private int serverPort;

	/**
	 * 首次创建SearchOrderActivity调用该方法，绘制Activity中的控件对象
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderlist);
		// 获取服务员Id号
		presentWaiterId = getIntent().getIntExtra("waiterId", 0);
		// 通过该Id号从数据库查询所有的订单
		orderlists = new orderDaoImpl(this).findOrderByWaiterId(
				presentWaiterId, this);

		btn_back = (Button) findViewById(R.id.orderlistBack);
		btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SearchOrderActivity.this.finish();
			}
		});
		// 通过控件Id获取ListView控件对象
		ListView listview = (ListView) findViewById(R.id.list1);
		// 新建一个ListViewAdapter对象
		listviewAdapter = new ListViewAdapter(this);
		// 将新建的ListViewAdapter对象绑定到ListView控件上
		listview.setAdapter(listviewAdapter);
		// 设置ListView的背景颜色为白色
		listview.setCacheColorHint(Color.WHITE);
	}

	private void ResultDialog() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.xiandanresult);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		word.setText("订单删除成功！");
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}

	private void showComfirmDialog(final int selectId) {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.clarm);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		Button cancle = (Button) window.findViewById(R.id.btn_cancel);
		word.setText("确定删除订单吗？");
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new orderDetailDaoImpl(SearchOrderActivity.this)
						.deleteOrderFood(orderlists.get(selectId).get_id());
				new orderDaoImpl(SearchOrderActivity.this)
						.deleteOrder(orderlists.get(selectId).get_id());
				orderlists.remove(orderlists.get(selectId));
				SearchOrderActivity.this.listviewAdapter.notifyDataSetChanged();
				ResultDialog();
				dlg.dismiss();
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}

	// ListView数据适配器类，从BaseAdapter类继承
	class ListViewAdapter extends BaseAdapter {
		private LayoutInflater listContainer;

		public boolean isEnabled(int position) {
			return false;
		}

		// 定义每一个ListView项的控件组类，声明每一个项包含的所有的控件
		public final class orderlistviewItem {
			public TextView tv_orderId;
			public TextView tv_tableId;
			public TextView tv_waiterId;
			public TextView tv_totalPrice;
			public TextView tv_state;
			public TextView tv_time;
			public Button btn_continue;
			public Button btn_delete;
		}

		private orderlistviewItem lvitem;

		public ListViewAdapter(Context context) {
			this.listContainer = LayoutInflater.from(context);
		}

		public int getCount() {
			return orderlists.size();
		}

		public Object getItem(int position) {
			return orderlists.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		// 数据适配器的获取数据对象方法，通过该方法可以获取需要的数据，从而进行展出
		public View getView(int position, View convertView, ViewGroup parent) {
			final int selectID = position;
			if (convertView == null) {
				lvitem = new orderlistviewItem();
				// 设置布局
				convertView = listContainer.inflate(R.layout.orderlvitem, null);
				// 通过控件Id获取控件对象
				lvitem.tv_orderId = (TextView) convertView
						.findViewById(R.id.Tv_orderid);
				lvitem.tv_tableId = (TextView) convertView
						.findViewById(R.id.Tv_tableid);
				lvitem.tv_waiterId = (TextView) convertView
						.findViewById(R.id.Tv_waiterid);
				lvitem.tv_totalPrice = (TextView) convertView
						.findViewById(R.id.Tv_totalprice);
				lvitem.tv_state = (TextView) convertView
						.findViewById(R.id.Tv_orderstate);
				lvitem.tv_time = (TextView) convertView
						.findViewById(R.id.Tv_time);
				lvitem.btn_continue = (Button) convertView
						.findViewById(R.id.btn_orderlistcontinue);
				lvitem.btn_delete = (Button) convertView
						.findViewById(R.id.btn_orderlistdelete);
				convertView.setTag(lvitem);
			} else {
				lvitem = (orderlistviewItem) convertView.getTag();
			}
			Order itemInfo = orderlists.get(position);
			if (itemInfo != null) {
				lvitem.tv_orderId.setText("" + itemInfo.get_id());
				lvitem.tv_tableId.setText("" + itemInfo.getTableId());
				lvitem.tv_waiterId.setText("" + itemInfo.getWaiterId());
				lvitem.tv_totalPrice.setText("" + itemInfo.getTotalPrice());
				lvitem.tv_time.setText(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(itemInfo.getTime()));
				// 根据菜品的状态设定不同的控件操作
				if (itemInfo.getOrderState() == 0) {
					// 如果菜品状态为未下单，则将状态栏中填写未下单，并显示下单和删除按钮，为下单个删除按钮添加事件监听器
					lvitem.tv_state.setText("未下单");
					lvitem.btn_delete.setVisibility(View.VISIBLE);
					lvitem.btn_delete.setOnClickListener(new deleteListener(
							selectID));
				} else {
					// 如果菜品的状态为已下单，则不显示下单个删除按钮，将状态栏中填写已下单
					lvitem.btn_delete.setVisibility(View.INVISIBLE);
					lvitem.tv_state.setText("已下单");
				}
				// 为续点按钮添加事件监听器
				lvitem.btn_continue
						.setOnClickListener(new continueDiancaiListener(
								selectID));
			}
			return convertView;
		}
	}

	/**
	 * Socket通信的创建方法
	 * 
	 * @param id
	 *            一个通信协议数字，不同数字代表不同的传输内容
	 * @param foodorders
	 *            传递的下单菜品信息列表
	 */
	public boolean create(int id, List<FoodOrder> foodorders) {
		// 查询数据库获取服务器IP地址
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		// 如果没有服务器IP地址，则提示错误信息
		if (serverip == null) {
			Toast.makeText(SearchOrderActivity.this, "未配置服务器IP！",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		// 得到IP地址和端口号
		serverIP = serverip.getIPpart1() + "." + serverip.getIPpart2() + "."
				+ serverip.getIPpart3() + "." + serverip.getIPpart4();
		serverPort = serverip.getPort();
		// 建立Socket连接，并开始传递数据
		try {
			Socket server = new Socket(serverIP, serverPort);
			OutputStream out = server.getOutputStream();
			byte[] bs = ScoketConn.TabletoBytes(id);
			out.write(bs);
			if (foodorders != null) {
				byte[] bs2 = ScoketConn.FoodOrdertoBytes(foodorders);
				out.write(bs2);
			}
			return true;

		} catch (UnknownHostException e) {
			e.printStackTrace();
			Toast.makeText(SearchOrderActivity.this, "未知的服务器IP！",
					Toast.LENGTH_SHORT).show();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(SearchOrderActivity.this, "未连接上服务器！！！！",
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	// 删除按钮触发的处理事件方法
	private class deleteListener implements OnClickListener {
		private int selectId;

		public deleteListener(int selectId) {
			this.selectId = selectId;
		}

		public void onClick(View v) {
			/*
			 * new AlertDialog.Builder(SearchOrderActivity.this) .setTitle("提示")
			 * .setMessage("确定删除订单吗？") .setPositiveButton(R.string.commit, new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int which) { new
			 * orderDaoImpl(SearchOrderActivity.this)
			 * .deleteOrder(orderlists.get( selectId).get_id());
			 * orderlists.remove(orderlists.get(selectId));
			 * SearchOrderActivity.this.listviewAdapter .notifyDataSetChanged();
			 * Toast.makeText(SearchOrderActivity.this, "订单删除成功！",
			 * Toast.LENGTH_LONG) .show(); }
			 * }).setNegativeButton(R.string.cancle, null).show();
			 */
			showComfirmDialog(selectId);
		}
	}

	// 续点按钮点击触发的方法处理
	private class continueDiancaiListener implements OnClickListener {
		private int selectId;

		public continueDiancaiListener(int selectId) {
			this.selectId = selectId;
		}

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(SearchOrderActivity.this, IndexActivity.class);
			intent.putExtra("PresentOrder", orderlists.get(selectId));
			startActivity(intent);
			SearchOrderActivity.this.finish();
		}
	}
}
