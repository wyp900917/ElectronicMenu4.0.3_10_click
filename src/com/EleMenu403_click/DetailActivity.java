package com.EleMenu403_click;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dao.foodDao;
import com.dao.Impl.foodDaoImpl;
import com.EleMenu403_click.R;
import com.model.ChoosedFood;
import com.model.Food;
import com.model.Order;

/**
 * ��Ʒ��ϸҳ�棬�鿴��Ʒ����ϸ��Ϣ��ͬʱ���Խ��е�˲���
 * 
 * @author ping
 * 
 */
public class DetailActivity extends Activity {
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
	/**
	 * ȱʡ�Ĳ�ƷͼƬ�ı���·��������ƷͼƬ��ѯ����ʱ�����ø�ͼƬ����
	 */
	/*
	 * private static final String DEFAULTPICTURE =
	 * "/mnt/sdcard/default/default.png";
	 */
	// �����Ʒ���ఴť�ؼ�����
	private Button btn_cool = null;
	private Button btn_hot = null;
	private Button btn_seafood = null;
	private Button btn_hotpot = null;
	private Button btn_soup = null;
	private Button btn_recommend = null;
	private Button btn_special = null;
	private Button btn_wine = null;

	// ����������ť�ͺ��з���ť�ؼ�����
	private Button btn_search = null;
	// private Button btn_callserver = null;

	private TextView tv_name = null;
	private TextView tv_price = null;
	private TextView tv_introduction = null;
	private ImageView image = null;
	private RelativeLayout layout = null;
	private Button btn_diancai = null;
	private Button btn_back = null;
	private Button btn_xiangxi = null;

	private Button btn_yidian = null;
	private Button btn_jieshu = null;

	// ��ǰ�Ķ������󣬸ö�������ӿ�̨ҳ����
	private Order presentOrder;
	// ��ǰ�ѵ��Ʒ���б�
	private List<ChoosedFood> choosedfoodList = new ArrayList<ChoosedFood>();// �ѵ���б���ʼ��Ϊ��

	private int foodId;
	private Boolean flag;
	private Boolean flag_xiangxi;
	private myApplication myapplication;
	ScrollView hsv;

	/**
	 * �״δ���DetailActivity���ø÷���������Activity�еĿؼ�����
	 */
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		myapplication = (myApplication) getApplication();
		// ��ȡActivity���ݵĲ���
		Intent intent = getIntent();
		Bundle bd = intent.getExtras();
		// ��ȡ�ѵ��Ʒ�б�
		choosedfoodList = (List<ChoosedFood>) (getIntent()
				.getSerializableExtra("choosedfoodList"));
		// ��ȡ��ǰ��������
		presentOrder = (Order) getIntent().getSerializableExtra("Order");
		foodId = bd.getInt("foodID");
		flag = bd.getBoolean("flag");
		foodDao fooddao = new foodDaoImpl(this);
		// ͨ�����ݵĲ�ƷId�Ų�ѯ���ݿ��ж�Ӧ�Ĳ�Ʒ��Ϣ
		Food food = fooddao.findFoodById(foodId, this);
		// ͨ���ؼ�Id��ȡ�ؼ�����
		btn_cool = (Button) findViewById(R.id.btn_cool);
		btn_hot = (Button) findViewById(R.id.btn_hot);
		btn_seafood = (Button) findViewById(R.id.btn_seafood);
		btn_hotpot = (Button) findViewById(R.id.btn_hotpot);
		btn_soup = (Button) findViewById(R.id.btn_soup);
		btn_recommend = (Button) findViewById(R.id.btn_recommend);
		btn_special = (Button) findViewById(R.id.btn_special);
		btn_wine = (Button) findViewById(R.id.btn_wine);

		btn_search = (Button) findViewById(R.id.search);
		// btn_callserver = (Button) findViewById(R.id.Btn_callserver);
		btn_yidian = (Button) findViewById(R.id.btn_yixuan);
		btn_jieshu = (Button) findViewById(R.id.btn_jieshu);

		tv_name = (TextView) findViewById(R.id.Tv_name);
		tv_price = (TextView) findViewById(R.id.Tv_price);
		tv_name.setText(food.getFoodName());
		tv_price.setText(food.getPresentPrice() + "Ԫ/��");
		image = (ImageView) findViewById(R.id.bigimage);
		tv_introduction = (TextView) findViewById(R.id.Tv_introduction);
		tv_introduction.setText(food.getIntroduction());
		btn_diancai = (Button) findViewById(R.id.btn_diancai);
		btn_back = (Button) findViewById(R.id.detail_back);
		btn_xiangxi = (Button) findViewById(R.id.btn_dil_xiangxi);
		layout = (RelativeLayout) findViewById(R.id.ll_detail);
		// ��ʾͼƬ
		BitmapFactory.Options options = new BitmapFactory.Options();
		Bitmap bm = BitmapFactory.decodeFile(food.getPicture(), options);
		if (bm == null) {
			bm = BitmapFactory.decodeFile(myapplication.getPicturepath()
					+ myapplication.getDefaultpic() + "default.png", options);
		}
		image.setImageBitmap(bm);
		// Ϊ��ť��Ӽ�����
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

		hsv = (ScrollView) findViewById(R.id.horizonMenu);

		// btn_callserver.setOnClickListener(new btn_callserverListener());
		btn_search.setOnClickListener(new btn_searchOnClickListener());
		btn_yidian.setOnClickListener(new btn_yidianOnClickListener());
		btn_jieshu.setOnClickListener(new btn_yidianOnClickListener());
		btn_diancai.setText("��     ��");
		btn_back.setText("��     ��");
		btn_xiangxi.setText("��     ϸ");
		setFlag_xiangxi(true);
		if (flag) {
			btn_diancai.setTextColor(Color.GRAY);
			btn_diancai.setClickable(false);
		} else
			btn_diancai.setOnClickListener(new btn_diancaiClickListener());
		btn_back.setOnClickListener(new btn_backClickListener());
		btn_xiangxi.setOnClickListener(new btn_xiangxiClickListener());
	}

	// �ѵ��Ʒ��ť�ͽ�����˰�ť����������¼�
	private class btn_yidianOnClickListener implements OnClickListener {
		public void onClick(View v) {
			// v.setBackgroundColor(Color.BLUE);
			Intent intent = new Intent();
			intent.setClass(DetailActivity.this, FoodlistActivity.class);
			// ����ǰ��ѡ��Ʒ�б�Ͷ������󴫵ݵ���һ��Activity
			intent.putExtra("choosedfoodList",
					(Serializable) DetailActivity.this.choosedfoodList);
			intent.putExtra("Order", presentOrder);
			startActivity(intent);
		}
	}

	// ��ϸ��ť����������¼�������
	private class btn_xiangxiClickListener implements OnClickListener {
		public void onClick(View v) {
			if (getFlag_xiangxi()) {
				btn_xiangxi.setText("��     ��");
				layout.setVisibility(View.VISIBLE);
				setFlag_xiangxi(false);
			} else {
				btn_xiangxi.setText("��     ϸ");
				layout.setVisibility(View.INVISIBLE);
				setFlag_xiangxi(true);
			}
		}
	}

	// ��˰�ť����������¼�������
	private class btn_diancaiClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = getIntent();
			Bundle bd = new Bundle();
			bd.putInt("foodID", foodId);
			intent.putExtras(bd);
			// ���ص�MainActivity���д���
			DetailActivity.this.setResult(0, intent);
			DetailActivity.this.finish();
		}

	}

	// ���ذ�ť����������¼�������
	private class btn_backClickListener implements OnClickListener {

		public void onClick(View v) {
			DetailActivity.this.setResult(3, null);
			DetailActivity.this.finish();
		}

	}

	// ������ť����������¼�������
	private class btn_searchOnClickListener implements OnClickListener {
		public void onClick(View v) {
			DetailActivity.this.setResult(2, null);
			DetailActivity.this.finish();
		}
	}

	// ��Ʒ���ఴť����������¼�������
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
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case HOT: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", HOT);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case HOTPOT: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", HOTPOT);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case SEAFOOD: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SEAFOOD);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case SOUP: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SOUP);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case RECOMMEND: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", RECOMMEND);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case SPECIAL: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", SPECIAL);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			case WINE: {
				Intent intent = getIntent();
				Bundle bd = new Bundle();
				bd.putInt("foodCategoryId", WINE);
				intent.putExtras(bd);
				DetailActivity.this.setResult(1, intent);
				DetailActivity.this.finish();
				break;
			}
			default:
				break;
			}
		}
	}

	/*
	 * // ���з���ť����������¼������� private class btn_callserverListener implements
	 * OnClickListener {
	 * 
	 * public void onClick(View v) { Toast.makeText(DetailActivity.this,
	 * "���������ѷ�����", Toast.LENGTH_SHORT) .show(); }
	 * 
	 * }
	 */

	// ҳ�淵���¼�����
	protected void onResume() {
		DetailActivity.this.setResult(2, null);
		super.onResume();
	}

	public Boolean getFlag_xiangxi() {
		return flag_xiangxi;
	}

	public void setFlag_xiangxi(Boolean flag_xiangxi) {
		this.flag_xiangxi = flag_xiangxi;
	}

}
