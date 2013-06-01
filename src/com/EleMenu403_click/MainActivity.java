package com.EleMenu403_click;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.foodDao;
import com.dao.Impl.foodDaoImpl;
import com.dao.Impl.orderDetailDaoImpl;
import com.EleMenu403_click.R;
import com.model.ChoosedFood;
import com.model.Food;
import com.model.Order;
import com.model.OrderDetail;

/**
 * �����ҳ�棬չʾ��Ʒ��Ϣ���ṩ��ˡ�ɾ�˹��ܣ��鿴��Ʒ��ϸ��������Ʒ�����з����
 * 
 * @author ping
 * 
 */
public class MainActivity extends Activity {
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

	// private static final int SHANGYIYE = -1;
	// private static final int XIAYIYE = 1;

	private static final int DIANCAI = 1;
	//private static final int SHANCHU = -1;
	// ��Ʒ��Ϣչʾ���Gallery
	private Gallery mGallery;
	// �����Ʒ���ఴť�ؼ�����
	private Button btn_cool = null;
	private Button btn_hot = null;
	private Button btn_seafood = null;
	private Button btn_hotpot = null;
	private Button btn_soup = null;
	private Button btn_recommend = null;
	private Button btn_special = null;
	private Button btn_wine = null;
	// private Button btn_forleft = null;
	// private Button btn_forright = null;
	// ����������ť�ͺ��з���ť�ؼ�����
	private Button btn_search = null;
	//private Button btn_callserver = null;
	// ������һҳ����һҳ��ť�ؼ�����
	// private Button btn_shangyiye = null;
	// private Button btn_xiayiye = null;
	// �����ѵ��Ʒ��ť�ͽ�����˰�ť�ؼ�����
	private Button btn_yidian = null;
	private Button btn_jieshu = null;
	// ��ǰҳ���Ʒ��Ϣ���б�ÿһҳĬ��Ϊ4����Ʒ��Ϣ
	private List<List<Food>> listItems;
	// ��ǰ�ѵ��Ʒ���б�
	private List<ChoosedFood> choosedfoodList = new ArrayList<ChoosedFood>();// �ѵ���б���ʼ��Ϊ��
	// ��ǰѡ��Ĳ�Ʒ����ID��Ĭ��Ϊ1
	private int foodCategoryId;// ��ǰ��ʾ��Ʒ����ID,��ʼ��Ϊ1
	// ��ǰ�Ķ������󣬸ö�������ӿ�̨ҳ����
	private Order presentOrder;
	// gallery�����Ӧ����������������
	GalleryAdapter adapter;
	private myApplication myapplication;

	ScrollView hsv;
	
	private View layout;
	private AlertDialog temp;

	/**
	 * ��ȡ��Ʒ����ID
	 * 
	 * @return foodCategoryId ��Ʒ����ID
	 */
	public int getFoodCategoryId() {
		return foodCategoryId;
	}

	/**
	 * ���ò�Ʒ����ID
	 * 
	 * @param foodCategoryId
	 *            ��Ʒ����ID
	 * 
	 */
	public void setFoodCategoryId(int foodCategoryId) {
		this.foodCategoryId = foodCategoryId;
	}

	/**
	 * ��ȡ��Ʒ��Ϣlist�б�
	 * 
	 * @return listItems ��Ʒչʾ��Ϣ�б�
	 */
	public List<List<Food>> getListItems() {
		return listItems;
	}

	/**
	 * ���ò�Ʒ��Ϣlist�б�
	 * 
	 * @param listItems
	 *            ��Ʒչʾ��Ϣ�б�
	 * 
	 */
	public void setListItems(List<List<Food>> listItems) {
		this.listItems = listItems;
	}

	/**
	 * ����MainActivity
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myapplication = (myApplication) getApplication();
		// ��ȡ��һActivity���ݵ�Order����
		presentOrder = (Order) getIntent().getSerializableExtra("PresentOrder");
		// �����ݿ��л�ȡ��ǰ����������δ�µ��Ĳ�Ʒ��Ϣ
		List<OrderDetail> orderdetailLists = new orderDetailDaoImpl(this)
				.findDetailByorderId(presentOrder.get_id(), 0);
		// ����ѯ��������δ�µ��Ĳ�Ʒ��Ϣ��ӵ��ѵ��Ʒ�б���
		for (int i = 0; i < orderdetailLists.size(); i++) {
			ChoosedFood choosefood = new ChoosedFood();
			choosefood.setFoodId(orderdetailLists.get(i).getFoodId());
			choosefood.setFoodNum(orderdetailLists.get(i).getFoodNum());
			choosefood.setFoodName(new foodDaoImpl(this)
					.getFoodName(orderdetailLists.get(i).getFoodId()));
			choosefood.setPresentprice(orderdetailLists.get(i).getFoodPrice());
			choosefood.setCostprice(new foodDaoImpl(MainActivity.this)
					.findFoodById(orderdetailLists.get(i).getFoodId(), this)
					.getCostPrice());
			choosefood.setRequset(orderdetailLists.get(i).getFoodRequest());
			choosefood.setState(orderdetailLists.get(i).getFoodState());
			choosedfoodList.add(choosefood);
		}
		// ����Ĭ�ϵĲ�Ʒ����ΪRECOMMEND
		this.setFoodCategoryId(RECOMMEND);
		// ͨ���ؼ�Id��ȡ�ؼ�����
		btn_recommend = (Button) findViewById(R.id.btn_recommend);
		btn_special = (Button) findViewById(R.id.btn_special);
		btn_cool = (Button) findViewById(R.id.btn_cool);
		btn_hot = (Button) findViewById(R.id.btn_hot);
		btn_seafood = (Button) findViewById(R.id.btn_seafood);
		btn_hotpot = (Button) findViewById(R.id.btn_hotpot);
		btn_soup = (Button) findViewById(R.id.btn_soup);
		btn_wine = (Button) findViewById(R.id.btn_wine);

		// btn_forleft = (Button) findViewById(R.id.forleft);
		// btn_forright = (Button) findViewById(R.id.forright);

		btn_search = (Button) findViewById(R.id.search);
		//btn_callserver = (Button) findViewById(R.id.Btn_callserver);

		// btn_shangyiye = (Button) findViewById(R.id.btn_shangyiye);
		// btn_xiayiye = (Button) findViewById(R.id.btn_xiayiye);

		btn_yidian = (Button) findViewById(R.id.btn_yixuan);
		btn_jieshu = (Button) findViewById(R.id.btn_jieshu);

		hsv = (ScrollView) findViewById(R.id.horizonMenu);
		// Ϊ�ؼ��󶨼����¼���
		btn_recommend.setOnClickListener(new btn_categoryOnClickListener(
				RECOMMEND));
		btn_special
				.setOnClickListener(new btn_categoryOnClickListener(SPECIAL));
		btn_cool.setOnClickListener(new btn_categoryOnClickListener(COOL));
		btn_hot.setOnClickListener(new btn_categoryOnClickListener(HOT));
		btn_seafood
				.setOnClickListener(new btn_categoryOnClickListener(SEAFOOD));
		btn_hotpot.setOnClickListener(new btn_categoryOnClickListener(HOTPOT));
		btn_soup.setOnClickListener(new btn_categoryOnClickListener(SOUP));
		btn_wine.setOnClickListener(new btn_categoryOnClickListener(WINE));

		// btn_forleft.setOnClickListener(new btn_forLeftOnclickListener(-1));
		// btn_forright.setOnClickListener(new btn_forLeftOnclickListener(1));

		btn_search.setOnClickListener(new btn_searchOnClickListener());
		//btn_callserver.setOnClickListener(new btn_callserverListener());

		btn_yidian.setOnClickListener(new btn_yidianOnClickListener());
		btn_jieshu.setOnClickListener(new btn_yidianOnClickListener());

		getCategory();
		// btn_shangyiye.setOnClickListener(new
		// btn_fanyeOnClickListener(SHANGYIYE));
		// btn_xiayiye.setOnClickListener(new
		// btn_fanyeOnClickListener(XIAYIYE));
	}

	/*
	 * class btn_forLeftOnclickListener implements OnClickListener {
	 * 
	 * private int flag = 0;
	 * 
	 * public btn_forLeftOnclickListener(int flag) { this.flag = flag; }
	 * 
	 * public void onClick(View v) { hsv.scrollBy(60 * flag, 0); }
	 * 
	 * }
	 */

	/**
	 * ͨ����Ʒ����Id�Ų�ѯ�������Ӧ�����в�Ʒ��Ϣ, ���ҽ���Ʒ��Ϣlist�б���г�ʼ��
	 * <p>
	 * ����Gallery�ؼ���������������󶨵�Gallery�ؼ�������
	 */
	public void getCategory() {
		List<Food> foods = new foodDaoImpl(this)
				.findBycategoryId(getFoodCategoryId());
		setListItems(getfoodListItems(foods));
		init(getFoodCategoryId());
		// btn_jieshu.setBackgroundResource(R.drawable.btn_callserver);
		adapter = new GalleryAdapter(this);
		mGallery = (Gallery) findViewById(R.id.gallery);
		mGallery.setAdapter(adapter);
	}

	/**
	 * ����ѯ�������в�Ʒ��Ϣ���з���
	 * <p>
	 * ÿҳ��ʾ4����Ʒ��Ϣ
	 */
	public List<List<Food>> getfoodListItems(List<Food> foods) {
		List<List<Food>> listItems = new ArrayList<List<Food>>();
		List<Food> list = null;
		for (int i = 0; i < foods.size(); i++) {
			if (i % 6 == 0) {
				list = new ArrayList<Food>();
			}
			list.add(foods.get(i));
			if ((i + 1) % 6 == 0) {
				listItems.add(list);
			}
		}
		if (foods.size() % 6 != 0) {
			listItems.add(list);
		}
		return listItems;
	}

	// ��Ʒ���ఴť����������¼�
	// �����ĳһ����Ʒ�������ѯ�������Ӧ�����в�Ʒ��Ϣ
	// �������»���Gallery�������ʾ��ǰ�Ĳ�Ʒ��Ϣ
	private class btn_categoryOnClickListener implements OnClickListener {
		private int foodCategory = 0;

		public btn_categoryOnClickListener(int foodCategory) {
			this.foodCategory = foodCategory;
		}

		public void onClick(View v) {
			switch (foodCategory) {
			case RECOMMEND: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case SPECIAL: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case COOL: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case HOT: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case HOTPOT: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case SEAFOOD: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case SOUP: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			case WINE: {
				MainActivity.this.setFoodCategoryId(foodCategory);
				MainActivity.this.getCategory();
				MainActivity.this.adapter.notifyDataSetChanged();
				break;
			}
			default:
				break;
			}
		}
	}

	// ��ҳ��ť����������¼�
	/*
	 * private class btn_fanyeOnClickListener implements OnClickListener {
	 * private int fanye = 0;
	 * 
	 * public btn_fanyeOnClickListener(int fanye) { this.fanye = fanye; }
	 * 
	 * public void onClick(View v) { switch (fanye) { case SHANGYIYE: { if (0 ==
	 * mGallery.getSelectedItemPosition()) { Toast.makeText(MainActivity.this,
	 * "��ǰ���ǵ�һҳ��", Toast.LENGTH_LONG).show(); } else { // ����Galleryѡ�����Ϊ��ǰ�����1
	 * mGallery.setSelection(mGallery.getSelectedItemPosition() - 1); } break; }
	 * case XIAYIYE: { if ((getListItems().size() - 1) == mGallery
	 * .getSelectedItemPosition()) { Toast.makeText(MainActivity.this,
	 * "��ǰ�������һҳ��", Toast.LENGTH_LONG).show(); } else { // ����Galleryѡ�����Ϊ��ǰ�����1
	 * mGallery.setSelection(mGallery.getSelectedItemPosition() + 1); } break; }
	 * default: break; } } }
	 */

	// �ѵ��Ʒ��ť�ͽ�����˰�ť����������¼�
	private class btn_yidianOnClickListener implements OnClickListener {
		public void onClick(View v) {
			// v.setBackgroundColor(Color.BLUE);
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, FoodlistActivity.class);
			// ����ǰ��ѡ��Ʒ�б�Ͷ������󴫵ݵ���һ��Activity
			intent.putExtra("choosedfoodList",
					(Serializable) MainActivity.this.choosedfoodList);
			intent.putExtra("Order", presentOrder);
			startActivityForResult(intent, 1);
		}
	}

	// ������ť����������¼�
	private class btn_searchOnClickListener implements OnClickListener {
		public void onClick(View v) {
			temp = showSearchDialog();
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		}
	}

	// �����������ť�����ø÷������÷�������һ��AlertDialog����
	private AlertDialog showSearchDialog() {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		// ���ò����ļ�
		layout = inflater.inflate(R.layout.addrequestdialog,
				(ViewGroup) findViewById(R.id.addrequest_ll));
		// ͨ���ؼ�Id��ȡ�ؼ�����
		final TextView tv_tips = (TextView) layout.findViewById(R.id.Tv_addtips);
		tv_tips.setText(R.string.searchTips);
		final EditText foodkey = (EditText) layout
				.findViewById(R.id.Et_request);
		final Button btn_ok = (Button) layout.findViewById(R.id.btn_ok);
		final Button btn_cancle = (Button) layout.findViewById(R.id.btn_cancel);
		
		btn_ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				List<Food> foods = new foodDaoImpl(MainActivity.this)
						.findByfoodkey(foodkey.getText().toString().trim());
				setListItems(getfoodListItems(foods));
				init(0);
				MainActivity.this.adapter.notifyDataSetChanged();
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

	/*// ���з���ť����������¼�
	private class btn_callserverListener implements OnClickListener {

		public void onClick(View v) {
			Toast.makeText(MainActivity.this, "���������ѷ�����", Toast.LENGTH_SHORT)
					.show();
		}
	}*/

	// ��ʼ�����ò�Ʒ���ఴť��ɫ
	private void init(int which) {
		this.btn_recommend.setTextColor(Color.WHITE);
		this.btn_special.setTextColor(Color.WHITE);
		this.btn_cool.setTextColor(Color.WHITE);
		this.btn_hot.setTextColor(Color.WHITE);
		this.btn_seafood.setTextColor(Color.WHITE);
		this.btn_hotpot.setTextColor(Color.WHITE);
		this.btn_soup.setTextColor(Color.WHITE);
		this.btn_wine.setTextColor(Color.WHITE);
		switch (which) {
		case RECOMMEND: {
			this.btn_recommend.setTextColor(Color.BLUE);
			break;
		}
		case SPECIAL: {
			this.btn_special.setTextColor(Color.BLUE);
			break;
		}
		case COOL: {
			this.btn_cool.setTextColor(Color.BLUE);
			break;
		}
		case HOT: {
			this.btn_hot.setTextColor(Color.BLUE);
			break;
		}
		case SEAFOOD: {
			this.btn_seafood.setTextColor(Color.BLUE);
			break;
		}
		case HOTPOT: {
			this.btn_hotpot.setTextColor(Color.BLUE);
			break;
		}
		case SOUP: {
			this.btn_soup.setTextColor(Color.BLUE);
			break;
		}
		case WINE: {
			this.btn_wine.setTextColor(Color.BLUE);
			break;
		}
		default:
			break;
		}
	}

	// ���ඨ����ÿһ����Ʒչʾ����Ҫʹ�õĿؼ�����
	private final class ListItemView {
		public List<ImageView> bglist;
		public List<ImageView> imagelist;
		public List<TextView> titlelist;
		public List<TextView> costpricelist;
		public List<TextView> presentpricelist;
		public List<Button> diancailist;
		//public List<Button> shanchulist;
		public List<Button> detaillist;
	}

	// �����������࣬��BaseAdapter��̳�
	private class GalleryAdapter extends BaseAdapter {
		private LayoutInflater listContainer;

		public GalleryAdapter(Context context) {
			this.listContainer = LayoutInflater.from(context);
		}

		public int getCount() {
			return getListItems().size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		// �����������Ļ�ȡ���ݶ��󷽷���ͨ���÷������Ի�ȡ��Ҫ�����ݣ��Ӷ�����չ��
		@SuppressWarnings("deprecation")
		public View getView(int position, View convertView, ViewGroup parent) {
			final int selectID = position;
			ListItemView listItemView = null;

			if (convertView == null) {
				listItemView = new ListItemView();
				convertView = listContainer.inflate(R.layout.list_item, null);
				listItemView.bglist = new ArrayList<ImageView>();
				listItemView.imagelist = new ArrayList<ImageView>();
				listItemView.titlelist = new ArrayList<TextView>();
				listItemView.costpricelist = new ArrayList<TextView>();
				listItemView.presentpricelist = new ArrayList<TextView>();
				listItemView.detaillist = new ArrayList<Button>();
				listItemView.diancailist = new ArrayList<Button>();
				//listItemView.shanchulist = new ArrayList<Button>();
				// ͨ���ؼ�Id��ȡ�ؼ�����
				listItemView.bglist.add(0,
						(ImageView) convertView.findViewById(R.id.item_one));
				listItemView.bglist.add(1,
						(ImageView) convertView.findViewById(R.id.item_two));
				listItemView.bglist.add(2,
						(ImageView) convertView.findViewById(R.id.item_three));
				listItemView.bglist.add(3,
						(ImageView) convertView.findViewById(R.id.item_four));
				listItemView.bglist.add(4,
						(ImageView) convertView.findViewById(R.id.item_five));
				listItemView.bglist.add(5,
						(ImageView) convertView.findViewById(R.id.item_six));

				listItemView.imagelist.add(0,
						(ImageView) convertView.findViewById(R.id.imageitem));
				listItemView.imagelist.add(1,
						(ImageView) convertView.findViewById(R.id.imageitem2));
				listItemView.imagelist.add(2,
						(ImageView) convertView.findViewById(R.id.imageitem3));
				listItemView.imagelist.add(3,
						(ImageView) convertView.findViewById(R.id.imageitem4));
				listItemView.imagelist.add(4,
						(ImageView) convertView.findViewById(R.id.imageitem5));
				listItemView.imagelist.add(5,
						(ImageView) convertView.findViewById(R.id.imageitem6));

				listItemView.titlelist.add(0,
						(TextView) convertView.findViewById(R.id.title));
				listItemView.titlelist.add(1,
						(TextView) convertView.findViewById(R.id.title2));
				listItemView.titlelist.add(2,
						(TextView) convertView.findViewById(R.id.title3));
				listItemView.titlelist.add(3,
						(TextView) convertView.findViewById(R.id.title4));
				listItemView.titlelist.add(4,
						(TextView) convertView.findViewById(R.id.title5));
				listItemView.titlelist.add(5,
						(TextView) convertView.findViewById(R.id.title6));

				listItemView.costpricelist.add(0,
						(TextView) convertView.findViewById(R.id.costprice));
				listItemView.costpricelist.add(1,
						(TextView) convertView.findViewById(R.id.costprice2));
				listItemView.costpricelist.add(2,
						(TextView) convertView.findViewById(R.id.costprice3));
				listItemView.costpricelist.add(3,
						(TextView) convertView.findViewById(R.id.costprice4));
				listItemView.costpricelist.add(4,
						(TextView) convertView.findViewById(R.id.costprice5));
				listItemView.costpricelist.add(5,
						(TextView) convertView.findViewById(R.id.costprice6));

				listItemView.presentpricelist.add(0,
						(TextView) convertView.findViewById(R.id.presentprice));
				listItemView.presentpricelist
						.add(1, (TextView) convertView
								.findViewById(R.id.presentprice2));
				listItemView.presentpricelist
						.add(2, (TextView) convertView
								.findViewById(R.id.presentprice3));
				listItemView.presentpricelist
						.add(3, (TextView) convertView
								.findViewById(R.id.presentprice4));
				listItemView.presentpricelist
						.add(4, (TextView) convertView
								.findViewById(R.id.presentprice5));
				listItemView.presentpricelist
						.add(5, (TextView) convertView
								.findViewById(R.id.presentprice6));

				listItemView.diancailist.add(0,
						(Button) convertView.findViewById(R.id.diancai));
				listItemView.diancailist.add(1,
						(Button) convertView.findViewById(R.id.diancai2));
				listItemView.diancailist.add(2,
						(Button) convertView.findViewById(R.id.diancai3));
				listItemView.diancailist.add(3,
						(Button) convertView.findViewById(R.id.diancai4));
				listItemView.diancailist.add(4,
						(Button) convertView.findViewById(R.id.diancai5));
				listItemView.diancailist.add(5,
						(Button) convertView.findViewById(R.id.diancai6));

				/*listItemView.shanchulist.add(0,
						(Button) convertView.findViewById(R.id.shanchu));
				listItemView.shanchulist.add(1,
						(Button) convertView.findViewById(R.id.shanchu2));
				listItemView.shanchulist.add(2,
						(Button) convertView.findViewById(R.id.shanchu3));
				listItemView.shanchulist.add(3,
						(Button) convertView.findViewById(R.id.shanchu4));
				listItemView.shanchulist.add(4,
						(Button) convertView.findViewById(R.id.shanchu5));
				listItemView.shanchulist.add(5,
						(Button) convertView.findViewById(R.id.shanchu6));*/

				listItemView.detaillist.add(0,
						(Button) convertView.findViewById(R.id.detail));
				listItemView.detaillist.add(1,
						(Button) convertView.findViewById(R.id.detail2));
				listItemView.detaillist.add(2,
						(Button) convertView.findViewById(R.id.detail3));
				listItemView.detaillist.add(3,
						(Button) convertView.findViewById(R.id.detail4));
				listItemView.detaillist.add(4,
						(Button) convertView.findViewById(R.id.detail5));
				listItemView.detaillist.add(5,
						(Button) convertView.findViewById(R.id.detail6));

				convertView.setTag(listItemView);
			} else {
				listItemView = (ListItemView) convertView.getTag();
			}
			// ͨ��BitmapFactory���ͼƬ
			BitmapFactory.Options options = new BitmapFactory.Options();
			// �������ű���Ϊ2��1
			options.inSampleSize = 2;
			for (int i = 0; i < getListItems().get(position).size(); i++) {
				Bitmap bm = BitmapFactory.decodeFile((String) getListItems()
						.get(position).get(i).getPicture(), options);
				if (bm == null) {
					bm = BitmapFactory.decodeFile(myapplication
							.getPicturepath()
							+ myapplication.getDefaultpic()
							+ "default.png");
				}
				listItemView.bglist.get(i).setBackgroundResource(
						R.drawable.bg_diancai);
				listItemView.imagelist.get(i).setImageBitmap(bm);
				listItemView.imagelist.get(i).setOnClickListener(
						new DetailButtonOnClickListener(selectID, i));
				listItemView.titlelist.get(i).setText(
						(String) getListItems().get(position).get(i)
								.getFoodName());
				listItemView.costpricelist.get(i).setText(
						"ԭ�ۣ�"
								+ String.valueOf(getListItems().get(position)
										.get(i).getCostPrice()));
				listItemView.presentpricelist.get(i).setText(
						"�ּۣ�"
								+ String.valueOf(getListItems().get(position)
										.get(i).getPresentPrice()));
				listItemView.presentpricelist.get(i).setTextColor(Color.RED);
				listItemView.detaillist.get(i).setVisibility(View.VISIBLE);
				listItemView.detaillist.get(i).setText("��     ϸ");
				listItemView.detaillist.get(i).setOnClickListener(
						new DetailButtonOnClickListener(selectID, i));

				listItemView.diancailist.get(i).setVisibility(View.VISIBLE);
				listItemView.diancailist.get(i).setText("��     ��");
				//listItemView.shanchulist.get(i).setVisibility(View.VISIBLE);
				//listItemView.shanchulist.get(i).setText("ɾ     ��");
				int m;
				for (m = 0; m < choosedfoodList.size(); m++) {
					if (choosedfoodList.get(m).getFoodId() == (Integer) getListItems()
							.get(position).get(i).get_id()) {
						listItemView.diancailist.get(i)
								.setTextColor(Color.GRAY);
						listItemView.diancailist.get(i).setClickable(false);
						/*listItemView.shanchulist.get(i).setTextColor(
								Color.YELLOW);
						listItemView.shanchulist.get(i).setOnClickListener(
								new shanchuClickListener(selectID, i));*/
						break;
					}
				}
				if (m == choosedfoodList.size()) {
					listItemView.diancailist.get(i).setTextColor(Color.WHITE);
					//listItemView.shanchulist.get(i).setTextColor(Color.GRAY);
					//listItemView.shanchulist.get(i).setClickable(false);
					listItemView.diancailist.get(i).setOnClickListener(
							new diancaiClickListener(selectID, i));
				}
			}

			convertView.setLayoutParams(new Gallery.LayoutParams(
					Gallery.LayoutParams.FILL_PARENT,
					Gallery.LayoutParams.FILL_PARENT));
			return convertView;
		}

		/*// ɾ����ť����������¼�
		public class shanchuClickListener implements OnClickListener {
			private int selectId;
			private int item;

			public shanchuClickListener(int selectId, int item) {
				this.selectId = selectId;
				this.item = item;
			}

			public void onClick(View v) {
				int i;
				for (i = 0; i < choosedfoodList.size(); i++) {
					if (choosedfoodList.get(i).getFoodId() == getListItems()
							.get(selectId).get(item).get_id()) {
						break;
					}
				}
				new orderDetailDaoImpl(MainActivity.this).deleteFood(
						choosedfoodList.get(i).getFoodId(), presentOrder
								.get_id(), choosedfoodList.get(i).getState());
				choosedfoodList.remove(choosedfoodList.get(i));
				notifyDataSetChanged();
				showDialogInfo(selectId, item, SHANCHU);
			}
		}*/

		// ��˰�ť����������¼�
		public class diancaiClickListener implements OnClickListener {
			private int selectId;
			private int item;

			public diancaiClickListener(int selectId, int item) {
				this.selectId = selectId;
				this.item = item;
			}

			public void onClick(View v) {
				foodDao fooddao = new foodDaoImpl(MainActivity.this);
				Food food = fooddao.findFoodById(
						(Integer) getListItems().get(selectId).get(item)
								.get_id(), MainActivity.this);
				ChoosedFood choosefood = new ChoosedFood();
				choosefood.setFoodId(food.get_id());
				choosefood.setFoodName(food.getFoodName());
				choosefood.setFoodNum(1);
				choosefood.setPresentprice(food.getPresentPrice());
				choosefood.setCostprice(food.getCostPrice());
				choosefood.setState(0);
				choosedfoodList.add(choosefood);
				OrderDetail ordertal = new OrderDetail();
				ordertal.setFoodId(food.get_id());
				ordertal.setOrderId(presentOrder.get_id());
				ordertal.setFoodNum(1);
				ordertal.setFoodPrice(food.getPresentPrice());
				// ordertal.setFoodRequest("well");
				new orderDetailDaoImpl(MainActivity.this).addFood(ordertal);
				notifyDataSetChanged();
				showDialogInfo(selectId, item, DIANCAI);
			}
		}

		// ��ϸ��ť����������¼�
		public class DetailButtonOnClickListener implements OnClickListener {
			private int selectID;
			private int item;

			public DetailButtonOnClickListener(int selectID, int item) {
				this.selectID = selectID;
				this.item = item;
			}

			public void onClick(View v) {
				showDetailInfo(selectID, item);
			}

		}
	}

	// �����ϸ��ť�����ø÷���������Ʒ��ϸ��ȡ��������չʾ
	private void showDetailInfo(int clickID, int i) {
		Boolean flag = false;
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, DetailActivity.class);
		for (ChoosedFood choosefood : choosedfoodList) {
			if (choosefood.getFoodId() == getListItems().get(clickID).get(i)
					.get_id()) {
				flag = true;
				break;
			}
		}
		Bundle bd = new Bundle();
		bd.putBoolean("flag", flag);
		bd.putInt("foodID", (Integer) getListItems().get(clickID).get(i)
				.get_id());
		intent.putExtras(bd);
		intent.putExtra("choosedfoodList",
				(Serializable) MainActivity.this.choosedfoodList);
		intent.putExtra("Order", presentOrder);
		startActivityForResult(intent, 0);
	}

	// Called when an activity you launched exits, giving you the requestCode
	// you started it with, the resultCode it returned, and any additional data
	// from it.
	// The resultCode will be RESULT_CANCELED if the activity explicitly
	// returned that, didn't return any result, or crashed during its operation.
	// You will receive this call immediately before onResume() when your
	// activity is re-starting.
	@SuppressWarnings("unchecked")
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == 0 && resultCode == 0) {
			Bundle data = intent.getExtras();
			int foodId = data.getInt("foodID");
			Food food = new foodDaoImpl(MainActivity.this).findFoodById(foodId,
					this);
			ChoosedFood choosefood = new ChoosedFood();
			choosefood.setFoodId(food.get_id());
			choosefood.setFoodName(food.getFoodName());
			choosefood.setFoodNum(1);
			choosefood.setPresentprice(food.getPresentPrice());
			choosefood.setCostprice(food.getCostPrice());
			choosefood.setState(0);
			choosedfoodList.add(choosefood);
			OrderDetail ordertal = new OrderDetail();
			ordertal.setFoodId(food.get_id());
			ordertal.setOrderId(presentOrder.get_id());
			ordertal.setFoodNum(1);
			ordertal.setFoodPrice(food.getPresentPrice());
			new orderDetailDaoImpl(MainActivity.this).addFood(ordertal);
			Toast.makeText(this, food.getFoodName() + "��    ��˳ɹ���",
					Toast.LENGTH_LONG).show();
			MainActivity.this.adapter.notifyDataSetChanged();
		} else if (requestCode == 0 && resultCode == 1) {
			Bundle data = intent.getExtras();
			MainActivity.this.setFoodCategoryId(data.getInt("foodCategoryId"));
			MainActivity.this.getCategory();
			MainActivity.this.adapter.notifyDataSetChanged();
		} else if (requestCode == 0 && resultCode == 2) {
			temp= showSearchDialog();
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		} else if (requestCode == 1 && resultCode == 0) {
			choosedfoodList = (List<ChoosedFood>) intent
					.getSerializableExtra("choosedfoodList");
			MainActivity.this.adapter.notifyDataSetChanged();
		} else if (requestCode == 1 && resultCode == 1) {
			Bundle data = intent.getExtras();
			MainActivity.this.setFoodCategoryId(data.getInt("foodCategoryId"));
			choosedfoodList = (List<ChoosedFood>) intent
					.getSerializableExtra("choosedfoodList");
			MainActivity.this.getCategory();
			MainActivity.this.adapter.notifyDataSetChanged();
		} else if (requestCode == 1 && resultCode == 2) {
			choosedfoodList = (List<ChoosedFood>) intent
					.getSerializableExtra("choosedfoodList");
			temp= showSearchDialog();
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		}
	}

	// ����˻�ɾ�˳ɹ��󣬻�����Ӧ����ʾ��Ϣ���÷���������ʾ��ʾ��Ϣ
	private void showDialogInfo(int clickID, int i, int flag) {
		String msg;
		if (flag == DIANCAI) {
			msg = "��  ��˳ɹ���";
		} else {
			msg = "��  ɾ���ɹ���";
		}
		Toast.makeText(this,
				getListItems().get(clickID).get(i).getFoodName() + msg,
				Toast.LENGTH_LONG).show();
	}
}