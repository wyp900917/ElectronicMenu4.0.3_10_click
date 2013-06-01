package com.EleMenu403_click;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
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
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.Impl.foodDaoImpl;
import com.dao.Impl.ipDaoImpl;
import com.dao.Impl.orderDaoImpl;
import com.dao.Impl.orderDetailDaoImpl;
import com.dao.Impl.waiterDaoImpl;
import com.EleMenu403_click.R;
import com.model.ChoosedFood;
import com.model.FoodOrder;
import com.model.IP;
import com.model.Order;
import com.model.OrderDetail;
import com.socket.ScoketConn;

/**
 * �ѵ��Ʒ�б�ҳ�棬���Բ鿴��ǰ�ѵ��Ʒ����Ϣ��Ҳ���Զ��ѵ��Ʒ������ز�����
 * <p>
 * δ�µ���Ʒ������ֱ�ӽ���ɾ����������������Ӳ�Ʒ�����Ͳ�ƷҪ��
 * <p>
 * ���µ���Ʒ��ֻ��ͨ������Ա����ɾ������������Ա��Ҫ���빤�ź�������ܽ���ɾ������
 * <p>
 * �µ�������Ҫ����Ա���У����빤�ź���������µ�
 * <p>
 * 
 * @author ping
 * 
 */
public class FoodlistActivity extends ListActivity {
	// ����ȫ��Application���󣬵���ģʽ
	private myApplication myapplication;
	/**
	 * �����Ƽ��˵ľ�̬������Ĭ��ֵΪ1
	 */
	private static final int RECOMMEND = 1;
	/**
	 * ������ɫ�˵ľ�̬������Ĭ��ֵΪ2
	 */
	private static final int SPECIAL = 2;
	/**
	 * ������˵ľ�̬������Ĭ��ֵΪ3
	 */
	private static final int COOL = 3;
	/**
	 * �����Ȳ˵ľ�̬������Ĭ��ֵΪ4
	 */
	private static final int HOT = 4;
	/**
	 * �����ʲ˵ľ�̬������Ĭ��ֵΪ5
	 */
	private static final int SEAFOOD = 5;
	/**
	 * ��������ľ�̬������Ĭ��ֵΪ6
	 */
	private static final int HOTPOT = 6;
	/**
	 * ��������ľ�̬������Ĭ��ֵΪ7
	 */
	private static final int SOUP = 7;
	/**
	 * �����ˮ��ľ�̬������Ĭ��ֵΪ8
	 */
	private static final int WINE = 8;
	// ��ǰѡ��Ĳ�Ʒ�б���δ�µ��Ĳ�Ʒ
	private List<ChoosedFood> choosedfoodList = new ArrayList<ChoosedFood>();
	// ���е���Ĳ�Ʒ�б������µ���δ�µ��Ĳ�Ʒ
	private List<ChoosedFood> allfoodList = new ArrayList<ChoosedFood>();
	// ԭ���ܼ�
	private float CosttotalPrice;
	// �ּ��ܼ�
	private float PresenttotalPrice;
	// �ܵĲ�Ʒ����
	private int totalFoodNum;
	// ����һ����������
	private Order order;
	// ����һ��������IP��ַ�Ͷ˿ڵı���
	private String serverIP;
	private int serverPort;
	// �����ؼ�����
	private Button btn_cool = null;
	private Button btn_hot = null;
	private Button btn_seafood = null;
	private Button btn_hotpot = null;
	private Button btn_soup = null;
	private Button btn_recommend = null;
	private Button btn_special = null;
	private Button btn_wine = null;

	private Button btn_forleft = null;
	private Button btn_forright = null;

	private Button btn_search = null;
	// private Button btn_callserver = null;
	private TextView tv_totalfoodNum;
	private TextView tv_CosttotalPrice;
	private TextView tv_PresenttotalPrice;
	private Button btn_back;
	private Button btn_clearall;
	private Button btn_xiadan;
	// ����ListViewAdapter����������
	ListViewAdapter listviewAdapter;

	HorizontalScrollView hsv;

	private AlertDialog temp;
	private View layout;

	/**
	 * �״δ���FoodlistActivity���ø÷���������Activity�еĿؼ�����
	 */
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foodlist);
		// ��ȡȫ��Application����
		myapplication = (myApplication) getApplication();
		// ��ȡ�ѵ��Ʒ�б�
		choosedfoodList = (List<ChoosedFood>) (getIntent()
				.getSerializableExtra("choosedfoodList"));
		// ��ȡ��ǰ��������
		order = (Order) getIntent().getSerializableExtra("Order");
		// ��ȡ��ǰ�����Ŷ�Ӧ�����µ��Ĳ�Ʒ�б�
		List<OrderDetail> xiadanfoodlist = new orderDetailDaoImpl(this)
				.findDetailByorderId(order.get_id(), 1);

		for (int i = 0; i < xiadanfoodlist.size(); i++) {
			boolean flag = true;
			ChoosedFood choosefood = new ChoosedFood();
			choosefood.setFoodId(xiadanfoodlist.get(i).getFoodId());
			choosefood.setFoodNum(xiadanfoodlist.get(i).getFoodNum());
			choosefood.setCostprice((new foodDaoImpl(FoodlistActivity.this)
					.findFoodById(xiadanfoodlist.get(i).getFoodId(), this))
					.getCostPrice());
			choosefood.setPresentprice(xiadanfoodlist.get(i).getFoodPrice());
			choosefood.setState(xiadanfoodlist.get(i).getFoodState());
			choosefood.setRequset(xiadanfoodlist.get(i).getFoodRequest());
			choosefood.setFoodName(new foodDaoImpl(this)
					.getFoodName(xiadanfoodlist.get(i).getFoodId()));
			for (int j = 0; j < allfoodList.size(); j++) {
				if (allfoodList.get(j).getFoodId() == choosefood.getFoodId()) {
					allfoodList.get(j).setFoodNum(
							allfoodList.get(j).getFoodNum()
									+ choosefood.getFoodNum());
					flag = false;
				}
			}
			if (flag) {
				allfoodList.add(choosefood);
			}
		}
		// ��ѡ��Ļ�δ�µ��Ĳ�Ʒ��Ϣ��ӵ����в�Ʒ��Ϣ�б���
		for (int i = 0; i < choosedfoodList.size(); i++) {
			allfoodList.add(choosedfoodList.get(i));
		}
		float costtotalprice = 0;
		float presenttotalprice = 0;
		int totalfoodNum = 0;
		// �����ܼۺ��ܵĲ�Ʒ����
		for (int i = 0; i < allfoodList.size(); i++) {
			costtotalprice += allfoodList.get(i).getCostprice()
					* allfoodList.get(i).getFoodNum();
			presenttotalprice += allfoodList.get(i).getPresentprice()
					* allfoodList.get(i).getFoodNum();
			totalfoodNum += allfoodList.get(i).getFoodNum();
		}
		setTotalFoodNum(totalfoodNum);
		setCosttotalPrice(costtotalprice);
		setPresenttotalPrice(presenttotalprice);
		// ͨ���ؼ�Id��ȡ�ؼ�����
		btn_cool = (Button) findViewById(R.id.fl_btn_cool);
		btn_hot = (Button) findViewById(R.id.fl_btn_hot);
		btn_seafood = (Button) findViewById(R.id.fl_btn_seafood);
		btn_hotpot = (Button) findViewById(R.id.fl_btn_hotpot);
		btn_soup = (Button) findViewById(R.id.fl_btn_soup);
		btn_recommend = (Button) findViewById(R.id.fl_btn_recommend);
		btn_special = (Button) findViewById(R.id.fl_btn_special);
		btn_wine = (Button) findViewById(R.id.fl_btn_wine);

		btn_forleft = (Button) findViewById(R.id.forleft);
		btn_forright = (Button) findViewById(R.id.forright);

		// btn_callserver = (Button) findViewById(R.id.fl_Btn_callserver);
		btn_search = (Button) findViewById(R.id.fl_search);

		tv_totalfoodNum = (TextView) findViewById(R.id.Tv_totalfoodNum);
		tv_CosttotalPrice = (TextView) findViewById(R.id.Tv_CostTotalprice);
		tv_PresenttotalPrice = (TextView) findViewById(R.id.Tv_PresentTotalprice);

		btn_back = (Button) findViewById(R.id.btn_fanhui);
		btn_clearall = (Button) findViewById(R.id.btn_clearall);
		btn_xiadan = (Button) findViewById(R.id.btn_xiadan);
		// Ϊ��ť�ؼ�����¼�������
		btn_cool.setOnClickListener(new btn_categoryOnClickListener(COOL));
		btn_hot.setOnClickListener(new btn_categoryOnClickListener(HOT));
		btn_seafood
				.setOnClickListener(new btn_categoryOnClickListener(SEAFOOD));
		btn_hotpot.setOnClickListener(new btn_categoryOnClickListener(HOTPOT));
		btn_soup.setOnClickListener(new btn_categoryOnClickListener(SOUP));
		btn_recommend.setOnClickListener(new btn_categoryOnClickListener(
				RECOMMEND));
		btn_special
				.setOnClickListener(new btn_categoryOnClickListener(SPECIAL));
		btn_wine.setOnClickListener(new btn_categoryOnClickListener(WINE));

		btn_forleft.setOnClickListener(new btn_forLeftOnclickListener(-1));
		btn_forright.setOnClickListener(new btn_forLeftOnclickListener(1));

		hsv = (HorizontalScrollView) findViewById(R.id.fl_horizonMenu);

		// btn_callserver.setOnClickListener(new btn_callserverListener());
		btn_search.setOnClickListener(new btn_searchOnClickListener());
		// ��ʼ���ܼۺͲ�Ʒ�����ܺ�
		setText();

		btn_back.setOnClickListener(new btn_backClickListener());
		// ��ȡListView�ؼ�����
		ListView listview = (ListView) findViewById(android.R.id.list);
		// ΪListView�ؼ�������������
		listviewAdapter = new ListViewAdapter(this);
		listview.setAdapter(listviewAdapter);
		listview.setCacheColorHint(Color.WHITE);
	}

	class btn_forLeftOnclickListener implements OnClickListener {

		private int flag = 0;

		public btn_forLeftOnclickListener(int flag) {
			this.flag = flag;
		}

		public void onClick(View v) {
			hsv.scrollBy(80 * flag, 0);
		}

	}

	/**
	 * �����ܼۺͲ�Ʒ�����ܺ͵Ŀؼ���ʾ���
	 */
	private void setText() {
		tv_totalfoodNum.setText(String.valueOf(getTotalFoodNum()));
		tv_CosttotalPrice.setText(String.valueOf(getCosttotalPrice()));
		tv_PresenttotalPrice.setText(String.valueOf(getPresenttotalPrice()));
		if (choosedfoodList.size() > 0) {
			btn_xiadan.setVisibility(View.VISIBLE);
			btn_clearall.setVisibility(View.VISIBLE);
			btn_xiadan.setOnClickListener(new xiadanClickListener());
			btn_clearall.setOnClickListener(new clearAllOnClickListener());
		} else {
			btn_xiadan.setVisibility(View.INVISIBLE);
			btn_clearall.setVisibility(View.INVISIBLE);
		}
	}

	// ���ذ�ť����¼�������
	private class btn_backClickListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = getIntent();
			intent.putExtra("choosedfoodList", (Serializable) choosedfoodList);
			FoodlistActivity.this.setResult(0, intent);
			FoodlistActivity.this.finish();
		}

	}

	// ��հ�ť����¼�������
	private class clearAllOnClickListener implements OnClickListener {
		public void onClick(View v) {
			showComfirmDialog(-1);
		}
	}

	private void clearAllComfirm() {
		for (int i = 0; i < choosedfoodList.size(); i++) {
			setTotalFoodNum(getTotalFoodNum()
					- choosedfoodList.get(i).getFoodNum());
			setCosttotalPrice(getCosttotalPrice()
					- choosedfoodList.get(i).getFoodNum()
					* choosedfoodList.get(i).getCostprice());
			setPresenttotalPrice(getPresenttotalPrice()
					- choosedfoodList.get(i).getFoodNum()
					* choosedfoodList.get(i).getPresentprice());
			new orderDetailDaoImpl(FoodlistActivity.this).deleteFood(
					choosedfoodList.get(i).getFoodId(), order.get_id(),
					choosedfoodList.get(i).getState());
			allfoodList.remove(choosedfoodList.get(i));
		}
		Iterator<ChoosedFood> sublist = choosedfoodList.iterator();
		while (sublist.hasNext()) {
			sublist.next();
			sublist.remove();
		}
		listviewAdapter.notifyDataSetChanged();
		setText();
	}

	// ������ť����¼�������
	private class btn_searchOnClickListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = getIntent();
			intent.putExtra("choosedfoodList", (Serializable) choosedfoodList);
			FoodlistActivity.this.setResult(2, intent);
			FoodlistActivity.this.finish();
		}
	}

	// �µ���ť����¼�������
	private class xiadanClickListener implements OnClickListener {
		public void onClick(View v) {
			temp = showMyDialog(0);
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		}
	}

	// �µ��¼�����ʾ�ĶԻ���
	private AlertDialog showMyDialog(final int flag) {
		AlertDialog.Builder builder = new Builder(FoodlistActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		// ���ò����ļ�
		layout = inflater.inflate(R.layout.deletedialog,
				(ViewGroup) findViewById(R.id.delete_ll));
		// ͨ���ؼ�Id��ȡ�ؼ�����
		final EditText delwaiter_num = (EditText) layout
				.findViewById(R.id.Et_delwaiterNum);
		final EditText delpsw = (EditText) layout
				.findViewById(R.id.Et_delpassword);
		final Button btn_ok = (Button) layout.findViewById(R.id.btn_ok);
		final Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancel);

		btn_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (delwaiter_num.getText().toString().trim().equals("")) {
					Toast.makeText(FoodlistActivity.this, "���񹤺�Ϊ�գ���",
							Toast.LENGTH_SHORT).show();
				} else if (delpsw.getText().toString().trim().equals("")) {
					Toast.makeText(FoodlistActivity.this, "����Ϊ�գ���",
							Toast.LENGTH_SHORT).show();
				} else {
					int waiterid = Integer.valueOf(delwaiter_num.getText()
							.toString());
					String psw = delpsw.getText().toString();
					String pass = new waiterDaoImpl(FoodlistActivity.this)
							.checkPassword(waiterid);
					if (pass == null) {
						Toast.makeText(FoodlistActivity.this, "���񹤺Ų����ڣ���",
								Toast.LENGTH_SHORT).show();
						delwaiter_num.setText("");
					} else if (!(pass.equals(psw))) {
						Toast.makeText(FoodlistActivity.this, "����������󣡣�",
								Toast.LENGTH_SHORT).show();
						delpsw.setText("");
					} else {
						showComfirmDialog(flag);
						temp.dismiss();
					}
				}
			}
		});
		btn_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				temp.dismiss();
			}
		});
		return builder.create();
	}

	private void showComfirmDialog(final int flag) {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.clarm);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		Button cancle = (Button) window.findViewById(R.id.btn_cancel);
		if (flag == -1) {
			word.setText("ȷ�����ȫ����");
			ok.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					clearAllComfirm();
					dlg.dismiss();
				}
			});
		} else if (flag == 0) {
			word.setText("ȷ���µ���");
			ok.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					xiadanComfirm();
					dlg.dismiss();
				}
			});
		} else {
			word.setText("ȷ��ɾ����");
			ok.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					shanchuComfirm(flag - 1);
					dlg.dismiss();
				}
			});
		}
		cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}
	
	private void xiadanResultDialog(final boolean flag) {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.xiandanresult);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		if(flag) {
			word.setText("��ϲ�����µ��ɹ���");
		} else {
			word.setText("�Բ����µ�ʧ�ܣ�������������...");
		}
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}

	private void xiadanComfirm() {
		List<OrderDetail> orderdals = new orderDetailDaoImpl(
				FoodlistActivity.this).findDetailByorderId(order.get_id(), 0);
		order.setTime(new Date());
		List<FoodOrder> foodorderlist = new ArrayList<FoodOrder>();
		for (int i = 0; i < orderdals.size(); i++) {
			FoodOrder foodorder = new FoodOrder();
			foodorder.setOrderId(order.get_id());
			foodorder.setTableId(order.getTableId());
			foodorder.setFoodId(orderdals.get(i).getFoodId());
			foodorder.setFoodNum(orderdals.get(i).getFoodNum());
			foodorder.setTotalPrice(PresenttotalPrice);
			foodorder.setWaiterId(order.getWaiterId());
			foodorder.setFoodRequest(orderdals.get(i).getFoodRequest());
			foodorder.setXiadanTime(order.getTime());
			foodorderlist.add(foodorder);
		}
		boolean success = true;
		// ���µ���Ʒ��Ϣͨ��Socketͨ�Ŵ��ݸ�������
		if (myapplication.getIsHaveServer() == 1) {
			success = create(10000, foodorderlist);
		}
		// ������ݳɹ������޸ı��ص����ݿ��е������Ϣ
		if (success) {
			if (order.getOrderState() == 0) {
				order.setOrderState(1);
				//order.setTime(new Date());
			}
			order.setTotalPrice(PresenttotalPrice);
			new orderDaoImpl(FoodlistActivity.this).xiadan(order);
			new orderDetailDaoImpl(FoodlistActivity.this).updateFoodState(order
					.get_id());
			xiadanResultDialog(true);
			int m = allfoodList.size() - choosedfoodList.size();
			int s = m;
			for (int i = 0; i < choosedfoodList.size(); i++) {
				boolean flag = true;
				for (int j = 0; j < m; j++) {
					if (allfoodList.get(j).getFoodId() == choosedfoodList
							.get(i).getFoodId()) {
						allfoodList.get(j).setFoodNum(
								allfoodList.get(j).getFoodNum()
										+ choosedfoodList.get(i).getFoodNum());
						allfoodList.remove(allfoodList.get(i + s));
						s--;
						flag = false;
					}
				}
				if (flag) {
					allfoodList.get(i + s).setState(1);
				}
			}
			Iterator<ChoosedFood> sublist = choosedfoodList.iterator();
			while (sublist.hasNext()) {
				sublist.next();
				sublist.remove();
			}
			listviewAdapter.notifyDataSetChanged();
			setText();
		} else {
			xiadanResultDialog(false);
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
			Toast.makeText(FoodlistActivity.this, "δ���÷�����IP��",
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
			Toast.makeText(FoodlistActivity.this, "δ֪�ķ�����IP��",
					Toast.LENGTH_SHORT).show();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(FoodlistActivity.this, "δ�����Ϸ�������������",
					Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	// ��Ʒ���ఴť���������
	private class btn_categoryOnClickListener implements OnClickListener {
		private int foodCategory = 0;

		public btn_categoryOnClickListener(int foodCategory) {
			this.foodCategory = foodCategory;
		}

		public void onClick(View v) {
			switch (foodCategory) {
			case COOL: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", COOL);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case HOT: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", HOT);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case HOTPOT: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", HOTPOT);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case SEAFOOD: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SEAFOOD);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case SOUP: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SOUP);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case RECOMMEND: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", RECOMMEND);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case SPECIAL: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SPECIAL);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			case WINE: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", WINE);
				intent.putExtras(bd);
				FoodlistActivity.this.setResult(1, intent);
				FoodlistActivity.this.finish();
				break;
			}
			default:
				break;
			}
		}
	}

	/*
	 * // ���з���ť��������� private class btn_callserverListener implements
	 * OnClickListener {
	 * 
	 * public void onClick(View v) { Toast.makeText(FoodlistActivity.this,
	 * "���������ѷ�����", Toast.LENGTH_SHORT).show(); }
	 * 
	 * }
	 */

	// �����������࣬�̳�BaseAdapter��ͨ���������������Ի�ȡ���ݽ���չʾ
	class ListViewAdapter extends BaseAdapter {
		private static final int DOWN = -1;
		private static final int UP = 1;

		private LayoutInflater listContainer;

		public boolean isEnabled(int position) {
			return false;
		}

		private listviewItem lvitem;

		// ������ListVIew��ÿһ�������������пؼ�����
		public final class listviewItem {
			public TextView tv_foodName;
			public TextView tv_request;
			public TextView tv_price;
			public Button btn_down;
			public TextView tv_foodNum;
			public Button btn_up;
			public TextView tv_total;
			public TextView tv_state;
			public Button btn_delete;
			public Button btn_addrequest;
		}

		public ListViewAdapter(Context context) {
			this.listContainer = LayoutInflater.from(context);
		}

		public int getCount() {
			return allfoodList.size();
		}

		public Object getItem(int position) {
			return allfoodList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		// �޸Ĳ�Ʒ�����Ĵ�����
		public void changeItem(int position, int which) {
			if (DOWN == which) {
				if (allfoodList.get(position).getFoodNum() == 1) {
					Toast.makeText(FoodlistActivity.this, "������Ϊ���٣���",
							Toast.LENGTH_SHORT).show();
				} else {
					allfoodList.get(position).setFoodNum(
							allfoodList.get(position).getFoodNum() - 1);
					setTotalFoodNum(getTotalFoodNum() - 1);
					setCosttotalPrice(getCosttotalPrice()
							- allfoodList.get(position).getCostprice());
					setPresenttotalPrice(getPresenttotalPrice()
							- allfoodList.get(position).getPresentprice());
					new orderDetailDaoImpl(FoodlistActivity.this)
							.updatefoodNum(allfoodList.get(position)
									.getFoodId(), order.get_id(), allfoodList
									.get(position).getFoodNum());
					notifyDataSetChanged();
					setText();
				}
			} else if (UP == which) {
				allfoodList.get(position).setFoodNum(
						allfoodList.get(position).getFoodNum() + 1);
				setTotalFoodNum(getTotalFoodNum() + 1);
				setCosttotalPrice(getCosttotalPrice()
						+ allfoodList.get(position).getCostprice());
				setPresenttotalPrice(getPresenttotalPrice()
						+ allfoodList.get(position).getPresentprice());
				new orderDetailDaoImpl(FoodlistActivity.this).updatefoodNum(
						allfoodList.get(position).getFoodId(), order.get_id(),
						allfoodList.get(position).getFoodNum());
				notifyDataSetChanged();
				setText();
			}
		}

		// �����������Ļ�ȡ���ݶ��󷽷���ͨ���÷������Ի�ȡ��Ҫ�����ݣ��Ӷ�����չ��
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final int selectID = position;
			// ��Ҫ�жϵ�ǰ��convertView�Ƿ�Ϊ�գ����Ϊ������Ҫ���¹���
			if (convertView == null) {
				lvitem = new listviewItem();
				convertView = listContainer.inflate(R.layout.lvitem, null);
				lvitem.tv_foodName = (TextView) convertView
						.findViewById(R.id.Tv_foodName);
				lvitem.tv_request = (TextView) convertView
						.findViewById(R.id.Tv_request);
				lvitem.tv_price = (TextView) convertView
						.findViewById(R.id.Tv_price);
				lvitem.btn_down = (Button) convertView
						.findViewById(R.id.btn_down);
				lvitem.tv_foodNum = (TextView) convertView
						.findViewById(R.id.Tv_foodNum);
				lvitem.btn_up = (Button) convertView.findViewById(R.id.btn_up);
				lvitem.tv_total = (TextView) convertView
						.findViewById(R.id.Tv_total);
				lvitem.tv_state = (TextView) convertView
						.findViewById(R.id.Tv_state);
				lvitem.btn_delete = (Button) convertView
						.findViewById(R.id.btn_del);
				lvitem.btn_addrequest = (Button) convertView
						.findViewById(R.id.btn_beizhu);
				convertView.setTag(lvitem);
			} else {
				lvitem = (listviewItem) convertView.getTag();
			}
			ChoosedFood itemInfo = allfoodList.get(position);
			if (itemInfo != null) {
				lvitem.tv_foodName.setText((String) itemInfo.getFoodName());
				lvitem.tv_request.setText(itemInfo.getRequset());
				lvitem.tv_price.setText("" + itemInfo.getPresentprice());
				lvitem.tv_foodNum.setText("" + itemInfo.getFoodNum());
				lvitem.tv_total.setText("" + itemInfo.getFoodNum()
						* itemInfo.getPresentprice());
				if (itemInfo.getState() == 0) {
					lvitem.tv_state.setText("δ�µ�");
					lvitem.btn_down.setVisibility(View.VISIBLE);
					lvitem.btn_up.setVisibility(View.VISIBLE);
					lvitem.btn_addrequest.setVisibility(View.VISIBLE);
					lvitem.btn_addrequest
							.setOnClickListener(new OnClickListener() {
								public void onClick(View v) {
									temp = showAddRequestDialog(position);
									temp.setView(layout, 0, 0, 0, 0);
									temp.show();
								}
							});
					lvitem.btn_down
							.setOnClickListener(new changeFoodNumListener(
									selectID, DOWN));
					lvitem.btn_up.setOnClickListener(new changeFoodNumListener(
							selectID, UP));
					lvitem.btn_delete.setOnClickListener(new deleteListener(
							selectID));
				} else {
					lvitem.tv_state.setText("���µ�");
					lvitem.btn_down.setVisibility(View.INVISIBLE);
					lvitem.btn_up.setVisibility(View.INVISIBLE);
					lvitem.btn_addrequest.setVisibility(View.INVISIBLE);
					lvitem.btn_delete.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							temp = showMyDialog(position + 1);
							temp.setView(layout, 0, 0, 0, 0);
							temp.show();
						}
					});
				}
			}
			return convertView;
		}

		// �޸Ĳ�Ʒ������ť��������
		class changeFoodNumListener implements OnClickListener {
			private int seleteID;
			private int which = 0;

			public changeFoodNumListener(int seleteId, int which) {
				this.seleteID = seleteId;
				this.which = which;
			}

			public void onClick(View v) {
				changeItem(this.seleteID, which);
			}
		}

		// ɾ����ť�����������
		class deleteListener implements OnClickListener {
			private int seleteID;

			public deleteListener(int seleteId) {
				this.seleteID = seleteId;
			}

			public void onClick(View v) {
				setTotalFoodNum(getTotalFoodNum()
						- allfoodList.get(seleteID).getFoodNum());
				setCosttotalPrice(getCosttotalPrice()
						- allfoodList.get(seleteID).getFoodNum()
						* allfoodList.get(seleteID).getCostprice());
				setPresenttotalPrice(getPresenttotalPrice()
						- allfoodList.get(seleteID).getFoodNum()
						* allfoodList.get(seleteID).getPresentprice());
				new orderDetailDaoImpl(FoodlistActivity.this).deleteFood(
						allfoodList.get(seleteID).getFoodId(), order.get_id(),
						allfoodList.get(seleteID).getState());
				choosedfoodList.remove(seleteID
						- (allfoodList.size() - choosedfoodList.size()));
				allfoodList.remove(allfoodList.get(seleteID));
				notifyDataSetChanged();
				setText();
			}

		}

	}

	// ��Ӳ�ƷҪ����ʾ�ĶԻ���
	private AlertDialog showAddRequestDialog(final int selectId) {
		AlertDialog.Builder builder = new Builder(FoodlistActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		// ���ò����ļ�
		layout = inflater.inflate(R.layout.addrequestdialog,
				(ViewGroup) findViewById(R.id.addrequest_ll));
		// ͨ���ؼ�Id��ȡ�ؼ�����
		final EditText foodrequest = (EditText) layout
				.findViewById(R.id.Et_request);
		final TextView tv_tips = (TextView) layout
				.findViewById(R.id.Tv_addtips);
		tv_tips.setText(R.string.addrequest);
		foodrequest.setText(allfoodList.get(selectId).getRequset());
		final Button btn_ok = (Button) layout.findViewById(R.id.btn_ok);
		final Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancel);

		btn_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				allfoodList.get(selectId).setRequset(
						foodrequest.getText().toString().trim());
				new orderDetailDaoImpl(FoodlistActivity.this).addfoodRequest(
						order.get_id(), allfoodList.get(selectId).getFoodId(),
						foodrequest.getText().toString().trim());
				FoodlistActivity.this.listviewAdapter.notifyDataSetChanged();
				temp.dismiss();
			}
		});
		btn_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				temp.dismiss();
			}
		});
		return builder.create();
	}

	private void shanchuComfirm(final int selectId) {
		setTotalFoodNum(getTotalFoodNum()
				- allfoodList.get(selectId).getFoodNum());
		setCosttotalPrice(getCosttotalPrice()
				- allfoodList.get(selectId).getFoodNum()
				* allfoodList.get(selectId).getCostprice());
		setPresenttotalPrice(getPresenttotalPrice()
				- allfoodList.get(selectId).getFoodNum()
				* allfoodList.get(selectId).getPresentprice());
		new orderDetailDaoImpl(FoodlistActivity.this).deleteFood(allfoodList
				.get(selectId).getFoodId(), order.get_id(),
				allfoodList.get(selectId).getState());
		new orderDaoImpl(FoodlistActivity.this).updateTotalprice(
				order.get_id(), PresenttotalPrice);
		allfoodList.remove(allfoodList.get(selectId));
		FoodlistActivity.this.listviewAdapter.notifyDataSetChanged();
		setText();
		Toast.makeText(FoodlistActivity.this, "ɾ���ɹ�����", Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * ��ȡ��Ʒ�����ܺ�
	 * 
	 * @return totalFoodNum ��Ʒ�����ܺ���Ϣ
	 */
	public int getTotalFoodNum() {
		return totalFoodNum;
	}

	/**
	 * ��ȡԭ���ܼ�
	 * 
	 * @return totalPrice ����ԭ���ܼ���Ϣ
	 */
	public float getCosttotalPrice() {
		return CosttotalPrice;
	}

	/**
	 * ����ԭ���ܼ�
	 * 
	 * @param totalPrice
	 *            ԭ���ܼ�
	 */
	public void setCosttotalPrice(float costtotalPrice) {
		CosttotalPrice = costtotalPrice;
	}

	/**
	 * ��ȡ�ּ��ܼ�
	 * 
	 * @return totalPrice �����ּ��ܼ���Ϣ
	 */
	public float getPresenttotalPrice() {
		return PresenttotalPrice;
	}

	/**
	 * �����ּ��ܼ�
	 * 
	 * @param totalPrice
	 *            �ּ��ܼ�
	 */
	public void setPresenttotalPrice(float presenttotalPrice) {
		PresenttotalPrice = presenttotalPrice;
	}

	/**
	 * ���ò�Ʒ�����ܺ�
	 * 
	 * @param totalFoodNum
	 *            ��Ʒ�����ܺ�
	 */
	public void setTotalFoodNum(int totalFoodNum) {
		this.totalFoodNum = totalFoodNum;
	}

	// ���ؼ�������
	protected void onResume() {
		Intent intent = getIntent();
		intent.putExtra("choosedfoodList", (Serializable) choosedfoodList);
		FoodlistActivity.this.setResult(0, intent);
		super.onResume();
	}
}
