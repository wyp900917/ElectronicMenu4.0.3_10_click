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
 * 菜品详细页面，查看菜品的详细信息，同时可以进行点菜操作
 * 
 * @author ping
 * 
 */
public class DetailActivity extends Activity {
	/**
	 * 代表推荐菜的静态常量，默认值为1
	 */
	private static final int RECOMMEND = 1;
	/**
	 * 代表特色菜的静态常量，默认值为2
	 */
	private static final int SPECIAL = 2;
	/**
	 * 代表冷菜的静态常量，默认值为3
	 */
	private static final int COOL = 3;
	/**
	 * 代表热菜的静态常量，默认值为4
	 */
	private static final int HOT = 4;
	/**
	 * 代表海鲜菜的静态常量，默认值为5
	 */
	private static final int SEAFOOD = 5;
	/**
	 * 代表火锅类的静态常量，默认值为6
	 */
	private static final int HOTPOT = 6;
	/**
	 * 代表汤类的静态常量，默认值为7
	 */
	private static final int SOUP = 7;
	/**
	 * 代表酒水类的静态常量，默认值为8
	 */
	private static final int WINE = 8;
	/**
	 * 缺省的菜品图片的保存路径；当菜品图片查询不到时，就用该图片代替
	 */
	/*
	 * private static final String DEFAULTPICTURE =
	 * "/mnt/sdcard/default/default.png";
	 */
	// 定义菜品种类按钮控件对象
	private Button btn_cool = null;
	private Button btn_hot = null;
	private Button btn_seafood = null;
	private Button btn_hotpot = null;
	private Button btn_soup = null;
	private Button btn_recommend = null;
	private Button btn_special = null;
	private Button btn_wine = null;

	// 定义搜索按钮和呼叫服务按钮控件对象
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

	// 当前的订单对象，该订单对象从开台页面获得
	private Order presentOrder;
	// 当前已点菜品的列表
	private List<ChoosedFood> choosedfoodList = new ArrayList<ChoosedFood>();// 已点菜列表，初始化为空

	private int foodId;
	private Boolean flag;
	private Boolean flag_xiangxi;
	private myApplication myapplication;
	ScrollView hsv;

	/**
	 * 首次创建DetailActivity调用该方法，绘制Activity中的控件对象
	 */
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		myapplication = (myApplication) getApplication();
		// 获取Activity传递的参数
		Intent intent = getIntent();
		Bundle bd = intent.getExtras();
		// 获取已点菜品列表
		choosedfoodList = (List<ChoosedFood>) (getIntent()
				.getSerializableExtra("choosedfoodList"));
		// 获取当前订单对象
		presentOrder = (Order) getIntent().getSerializableExtra("Order");
		foodId = bd.getInt("foodID");
		flag = bd.getBoolean("flag");
		foodDao fooddao = new foodDaoImpl(this);
		// 通过传递的菜品Id号查询数据库中对应的菜品信息
		Food food = fooddao.findFoodById(foodId, this);
		// 通过控件Id获取控件对象
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
		tv_price.setText(food.getPresentPrice() + "元/份");
		image = (ImageView) findViewById(R.id.bigimage);
		tv_introduction = (TextView) findViewById(R.id.Tv_introduction);
		tv_introduction.setText(food.getIntroduction());
		btn_diancai = (Button) findViewById(R.id.btn_diancai);
		btn_back = (Button) findViewById(R.id.detail_back);
		btn_xiangxi = (Button) findViewById(R.id.btn_dil_xiangxi);
		layout = (RelativeLayout) findViewById(R.id.ll_detail);
		// 显示图片
		BitmapFactory.Options options = new BitmapFactory.Options();
		Bitmap bm = BitmapFactory.decodeFile(food.getPicture(), options);
		if (bm == null) {
			bm = BitmapFactory.decodeFile(myapplication.getPicturepath()
					+ myapplication.getDefaultpic() + "default.png", options);
		}
		image.setImageBitmap(bm);
		// 为按钮添加监听器
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
		btn_diancai.setText("点     菜");
		btn_back.setText("返     回");
		btn_xiangxi.setText("详     细");
		setFlag_xiangxi(true);
		if (flag) {
			btn_diancai.setTextColor(Color.GRAY);
			btn_diancai.setClickable(false);
		} else
			btn_diancai.setOnClickListener(new btn_diancaiClickListener());
		btn_back.setOnClickListener(new btn_backClickListener());
		btn_xiangxi.setOnClickListener(new btn_xiangxiClickListener());
	}

	// 已点菜品按钮和结束点菜按钮点击触发的事件
	private class btn_yidianOnClickListener implements OnClickListener {
		public void onClick(View v) {
			// v.setBackgroundColor(Color.BLUE);
			Intent intent = new Intent();
			intent.setClass(DetailActivity.this, FoodlistActivity.class);
			// 将当前已选菜品列表和订单对象传递到下一个Activity
			intent.putExtra("choosedfoodList",
					(Serializable) DetailActivity.this.choosedfoodList);
			intent.putExtra("Order", presentOrder);
			startActivity(intent);
		}
	}

	// 详细按钮点击触发的事件处理方法
	private class btn_xiangxiClickListener implements OnClickListener {
		public void onClick(View v) {
			if (getFlag_xiangxi()) {
				btn_xiangxi.setText("隐     藏");
				layout.setVisibility(View.VISIBLE);
				setFlag_xiangxi(false);
			} else {
				btn_xiangxi.setText("详     细");
				layout.setVisibility(View.INVISIBLE);
				setFlag_xiangxi(true);
			}
		}
	}

	// 点菜按钮点击触发的事件处理方法
	private class btn_diancaiClickListener implements OnClickListener {

		public void onClick(View v) {
			Intent intent = getIntent();
			Bundle bd = new Bundle();
			bd.putInt("foodID", foodId);
			intent.putExtras(bd);
			// 返回到MainActivity进行处理
			DetailActivity.this.setResult(0, intent);
			DetailActivity.this.finish();
		}

	}

	// 返回按钮点击触发的事件处理方法
	private class btn_backClickListener implements OnClickListener {

		public void onClick(View v) {
			DetailActivity.this.setResult(3, null);
			DetailActivity.this.finish();
		}

	}

	// 搜索按钮点击触发的事件处理方法
	private class btn_searchOnClickListener implements OnClickListener {
		public void onClick(View v) {
			DetailActivity.this.setResult(2, null);
			DetailActivity.this.finish();
		}
	}

	// 菜品种类按钮点击触发的事件处理方法
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
	 * // 呼叫服务按钮点击触发的事件处理方法 private class btn_callserverListener implements
	 * OnClickListener {
	 * 
	 * public void onClick(View v) { Toast.makeText(DetailActivity.this,
	 * "服务请求已发出！", Toast.LENGTH_SHORT) .show(); }
	 * 
	 * }
	 */

	// 页面返回事件处理
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
