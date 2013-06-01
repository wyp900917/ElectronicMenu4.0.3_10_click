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
 * �����б�չʾҳ�棬����Ա�����ڴ�ҳ���ж���Ӧ���������µ���ɾ�����������
 * 
 * @author ping
 * 
 */
public class SearchOrderActivity extends Activity {

	private Button btn_back = null;
	/**
	 * ListView�ؼ�����������������ʹ�õ���ģʽ
	 */
	private ListViewAdapter listviewAdapter;
	/**
	 * ����һ�������б���Ų�ѯ�������ж�������
	 */
	private List<Order> orderlists = new ArrayList<Order>();
	/**
	 * ��ǰ�ķ���ԱId�ţ���Id���Ǵ���һ��ҳ���ò������ݶ���
	 */
	private int presentWaiterId;

	// ����һ��������IP��ַ�Ͷ˿ڵı���
	private String serverIP;
	private int serverPort;

	/**
	 * �״δ���SearchOrderActivity���ø÷���������Activity�еĿؼ�����
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderlist);
		// ��ȡ����ԱId��
		presentWaiterId = getIntent().getIntExtra("waiterId", 0);
		// ͨ����Id�Ŵ����ݿ��ѯ���еĶ���
		orderlists = new orderDaoImpl(this).findOrderByWaiterId(
				presentWaiterId, this);

		btn_back = (Button) findViewById(R.id.orderlistBack);
		btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SearchOrderActivity.this.finish();
			}
		});
		// ͨ���ؼ�Id��ȡListView�ؼ�����
		ListView listview = (ListView) findViewById(R.id.list1);
		// �½�һ��ListViewAdapter����
		listviewAdapter = new ListViewAdapter(this);
		// ���½���ListViewAdapter����󶨵�ListView�ؼ���
		listview.setAdapter(listviewAdapter);
		// ����ListView�ı�����ɫΪ��ɫ
		listview.setCacheColorHint(Color.WHITE);
	}

	private void ResultDialog() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.xiandanresult);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		word.setText("����ɾ���ɹ���");
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
		word.setText("ȷ��ɾ��������");
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

	// ListView�����������࣬��BaseAdapter��̳�
	class ListViewAdapter extends BaseAdapter {
		private LayoutInflater listContainer;

		public boolean isEnabled(int position) {
			return false;
		}

		// ����ÿһ��ListView��Ŀؼ����࣬����ÿһ������������еĿؼ�
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

		// �����������Ļ�ȡ���ݶ��󷽷���ͨ���÷������Ի�ȡ��Ҫ�����ݣ��Ӷ�����չ��
		public View getView(int position, View convertView, ViewGroup parent) {
			final int selectID = position;
			if (convertView == null) {
				lvitem = new orderlistviewItem();
				// ���ò���
				convertView = listContainer.inflate(R.layout.orderlvitem, null);
				// ͨ���ؼ�Id��ȡ�ؼ�����
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
				// ���ݲ�Ʒ��״̬�趨��ͬ�Ŀؼ�����
				if (itemInfo.getOrderState() == 0) {
					// �����Ʒ״̬Ϊδ�µ�����״̬������дδ�µ�������ʾ�µ���ɾ����ť��Ϊ�µ���ɾ����ť����¼�������
					lvitem.tv_state.setText("δ�µ�");
					lvitem.btn_delete.setVisibility(View.VISIBLE);
					lvitem.btn_delete.setOnClickListener(new deleteListener(
							selectID));
				} else {
					// �����Ʒ��״̬Ϊ���µ�������ʾ�µ���ɾ����ť����״̬������д���µ�
					lvitem.btn_delete.setVisibility(View.INVISIBLE);
					lvitem.tv_state.setText("���µ�");
				}
				// Ϊ���㰴ť����¼�������
				lvitem.btn_continue
						.setOnClickListener(new continueDiancaiListener(
								selectID));
			}
			return convertView;
		}
	}

	/**
	 * Socketͨ�ŵĴ�������
	 * 
	 * @param id
	 *            һ��ͨ��Э�����֣���ͬ���ִ���ͬ�Ĵ�������
	 * @param foodorders
	 *            ���ݵ��µ���Ʒ��Ϣ�б�
	 */
	public boolean create(int id, List<FoodOrder> foodorders) {
		// ��ѯ���ݿ��ȡ������IP��ַ
		IP serverip = new ipDaoImpl(this).findServerIp(this);
		// ���û�з�����IP��ַ������ʾ������Ϣ
		if (serverip == null) {
			Toast.makeText(SearchOrderActivity.this, "δ���÷�����IP��",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		// �õ�IP��ַ�Ͷ˿ں�
		serverIP = serverip.getIPpart1() + "." + serverip.getIPpart2() + "."
				+ serverip.getIPpart3() + "." + serverip.getIPpart4();
		serverPort = serverip.getPort();
		// ����Socket���ӣ�����ʼ��������
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
			Toast.makeText(SearchOrderActivity.this, "δ֪�ķ�����IP��",
					Toast.LENGTH_SHORT).show();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(SearchOrderActivity.this, "δ�����Ϸ�������������",
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	// ɾ����ť�����Ĵ����¼�����
	private class deleteListener implements OnClickListener {
		private int selectId;

		public deleteListener(int selectId) {
			this.selectId = selectId;
		}

		public void onClick(View v) {
			/*
			 * new AlertDialog.Builder(SearchOrderActivity.this) .setTitle("��ʾ")
			 * .setMessage("ȷ��ɾ��������") .setPositiveButton(R.string.commit, new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int which) { new
			 * orderDaoImpl(SearchOrderActivity.this)
			 * .deleteOrder(orderlists.get( selectId).get_id());
			 * orderlists.remove(orderlists.get(selectId));
			 * SearchOrderActivity.this.listviewAdapter .notifyDataSetChanged();
			 * Toast.makeText(SearchOrderActivity.this, "����ɾ���ɹ���",
			 * Toast.LENGTH_LONG) .show(); }
			 * }).setNegativeButton(R.string.cancle, null).show();
			 */
			showComfirmDialog(selectId);
		}
	}

	// ���㰴ť��������ķ�������
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
