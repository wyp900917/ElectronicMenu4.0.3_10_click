package com.EleMenu403_click;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dao.Impl.waiterDaoImpl;
import com.EleMenu403_click.R;

/**
 * 应用程序的第一个Activity，提供开台、更新菜单、配置以及订单查询功能选项
 * 
 * @author ping
 * 
 */
public class HomeActivity extends Activity {

	/**
	 * 全局Application对象
	 */
	private myApplication myapplication;
	// 声明控件对象
	private Button btn_kaitai = null;
	private Button btn_updateMenu = null;
	private Button btn_configer = null;
	private Button btn_searchorder = null;

	private final int READ_SIZE = 100;

	private AlertDialog temp;
	private View layout;

	/***
	 * HomeActivity的onCreate方法
	 */
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		// 获取全局Application对象
		myapplication = (myApplication) getApplication();
		// 通过控件Id获取控件对象
		btn_kaitai = (Button) findViewById(R.id.btn_kaitai);
		btn_updateMenu = (Button) findViewById(R.id.btn_updateMenu);
		btn_configer = (Button) findViewById(R.id.btn_configer);
		btn_searchorder = (Button) findViewById(R.id.btn_searchorder);
		// 给按钮控件添加事件监听器
		btn_kaitai.setOnClickListener(btn_kaitaiClickListener);
		btn_updateMenu.setOnClickListener(new btn_UpdateOnclickListener());
		btn_configer.setOnClickListener(new btn_ConfigOnclickListener());
		btn_searchorder.setOnClickListener(btn_searchorderClickListener);
	}

	/**
	 * 根据点击的按钮为HomeActivity创建一个对话框， 点击配置菜单按钮弹出配置Ip对话框
	 * 
	 */
	protected AlertDialog showDialogForConfig() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		layout = inflater.inflate(R.layout.configerdialog,
				(ViewGroup) findViewById(R.id.configer_ll));
		// builder.setView(layout);
		Button serverConfiger = (Button) layout
				.findViewById(R.id.btn_serverconfig);
		Button printerConfiger = (Button) layout
				.findViewById(R.id.btn_printerconfig);
		if (myapplication.getIsHaveServer() == 0) {
			serverConfiger.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(HomeActivity.this, R.string.no_server,
							Toast.LENGTH_LONG).show();
				}
			});
			printerConfiger.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this,
							printerConfigerActivity.class);
					startActivity(intent);
					finish();
				}
			});

		} else {
			serverConfiger.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this,
							serverConfigerActivity.class);
					startActivity(intent);
					finish();
				}
			});
			printerConfiger.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(HomeActivity.this, "有服务器，不需要配置打印机！！！",
							Toast.LENGTH_LONG).show();
				}
			});
		}
		return builder.create();
	}

	/**
	 * 根据点击的按钮为HomeActivity创建一个对话框， 点击更新菜单按钮弹出更新菜单对话框
	 * 
	 */
	/*
	 * protected AlertDialog showDialogForUpdate() { AlertDialog.Builder builder
	 * = new Builder(HomeActivity.this); LayoutInflater inflater =
	 * LayoutInflater.from(this); View layout =
	 * inflater.inflate(R.layout.updatemenu, (ViewGroup)
	 * findViewById(R.id.updateMenu_ll)); builder.setView(layout);
	 * builder.setPositiveButton(R.string.commit, new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int which) { dialog.dismiss(); } });
	 * builder.setNegativeButton(R.string.cancle, new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int which) { dialog.dismiss(); } }); return builder.create(); }
	 */

	private void copySQLFile() {
		File TFsqlfile = new File(myapplication.getTfsqlpath()
				+ myapplication.getDbname());
		if (!(new File("/mnt/sdcard/electronicMenu/").exists())) {
			new File("/mnt/sdcard/electronicMenu/").mkdir();
		}
		if (!(new File(myapplication.getSqlpath()).exists())) {
			new File(myapplication.getSqlpath()).mkdir();
		}
		File SDsqlfile = new File(myapplication.getSqlpath()
				+ myapplication.getDbname());
		try {
			FileInputStream fosfrom = new FileInputStream(TFsqlfile);
			FileOutputStream fosto = new FileOutputStream(SDsqlfile);
			byte bt[] = new byte[100];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); // 将内容写到新文件当中
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception e) {
			Toast.makeText(HomeActivity.this, "没有TF卡！！！", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void copyPictureFile() {
		String TFPicPath = myapplication.getTfpicturepath();
		String SDPicPath = myapplication.getPicturepath();
		if (!(new File(SDPicPath).exists())) {
			new File(SDPicPath).mkdir();
		}
		if (!new File(TFPicPath).exists()) {
			Toast.makeText(HomeActivity.this, "没有TF卡！！", Toast.LENGTH_LONG)
					.show();
			return;
		}
		File cools[] = new File(TFPicPath + myapplication.getCoolpic())
				.listFiles();
		File hots[] = new File(TFPicPath + myapplication.getHotpic())
				.listFiles();
		File recommends[] = new File(TFPicPath
				+ myapplication.getRecommendpic()).listFiles();
		File specials[] = new File(TFPicPath + myapplication.getSpecialpic())
				.listFiles();
		File seafoods[] = new File(TFPicPath + myapplication.getSeafoodpic())
				.listFiles();
		File soups[] = new File(TFPicPath + myapplication.getSouppic())
				.listFiles();
		File wines[] = new File(TFPicPath + myapplication.getWinepic())
				.listFiles();
		File hotpots[] = new File(TFPicPath + myapplication.getHotpotpic())
				.listFiles();
		File defaults[] = new File(TFPicPath + myapplication.getDefaultpic())
				.listFiles();
		if (!(new File(SDPicPath + myapplication.getCoolpic()).exists())) {
			new File(SDPicPath + myapplication.getCoolpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getHotpic()).exists())) {
			new File(SDPicPath + myapplication.getHotpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getRecommendpic()).exists())) {
			new File(SDPicPath + myapplication.getRecommendpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getSpecialpic()).exists())) {
			new File(SDPicPath + myapplication.getSpecialpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getSeafoodpic()).exists())) {
			new File(SDPicPath + myapplication.getSeafoodpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getHotpotpic()).exists())) {
			new File(SDPicPath + myapplication.getHotpotpic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getSouppic()).exists())) {
			new File(SDPicPath + myapplication.getSouppic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getWinepic()).exists())) {
			new File(SDPicPath + myapplication.getWinepic()).mkdir();
		}
		if (!(new File(SDPicPath + myapplication.getDefaultpic()).exists())) {
			new File(SDPicPath + myapplication.getDefaultpic()).mkdir();
		}
		for (File f : cools) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getCoolpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : hots) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getHotpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : recommends) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getRecommendpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : specials) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getSpecialpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : seafoods) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getSeafoodpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : hotpots) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getHotpotpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : soups) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getSouppic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : wines) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getWinepic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (File f : defaults) {
			try {
				FileInputStream fosfrom = new FileInputStream(f);
				FileOutputStream fosto = new FileOutputStream(SDPicPath
						+ myapplication.getDefaultpic() + f.getName());
				byte bt[] = new byte[READ_SIZE];
				int c;
				while ((c = fosfrom.read(bt)) > 0) {
					fosto.write(bt, 0, c); // 将内容写到新文件当中
				}
				fosfrom.close();
				fosto.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Toast.makeText(HomeActivity.this, "更新完成！", Toast.LENGTH_SHORT).show();
	}

	// 更新菜单按钮点击触发的事件
	private class btn_UpdateOnclickListener implements OnClickListener {
		public void onClick(View v) {
			showsearchDialog();
		}
	}
	
	private void updateComfirm() {
		copySQLFile();
		copyPictureFile();
	}

	private void showsearchDialog() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		window.setContentView(R.layout.clarm);
		TextView word = (TextView) window.findViewById(R.id.Tv_word);
		Button ok = (Button) window.findViewById(R.id.btn_ok);
		Button cancle = (Button) window.findViewById(R.id.btn_cancel);
		word.setText("点击确定开始更新...");
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				updateComfirm();
				dlg.dismiss();
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dlg.dismiss();
			}
		});
	}

	// 配置按钮点击触发的事件
	private class btn_ConfigOnclickListener implements OnClickListener {
		public void onClick(View v) {
			temp = showDialogForConfig();
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		}
	}

	// 开台按钮点击触发的事件，跳转到KaiTaiActivity
	private OnClickListener btn_kaitaiClickListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(HomeActivity.this, KaiTaiActivity.class);
			startActivity(intent);
		}

	};
	// 查询订单按钮点击触发的事件
	private OnClickListener btn_searchorderClickListener = new OnClickListener() {

		public void onClick(View v) {
			temp = showMyDialog();
			temp.setView(layout, 0, 0, 0, 0);
			temp.show();
		}

	};

	// 查询订单前需输入服务工号和密码，弹出对话框输入信息
	private AlertDialog showMyDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		layout = inflater.inflate(R.layout.deletedialog,
				(ViewGroup) findViewById(R.id.delete_ll));
		final EditText delwaiter_num = (EditText) layout
				.findViewById(R.id.Et_delwaiterNum);
		final EditText delpsw = (EditText) layout
				.findViewById(R.id.Et_delpassword);
		// builder.setView(layout);
		Button posbtn = (Button) layout.findViewById(R.id.btn_ok);
		Button negbtn = (Button) layout.findViewById(R.id.btn_cancel);

		posbtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (delwaiter_num.getText().toString().trim().equals("")) {
					Toast.makeText(HomeActivity.this, "服务工号为空！！",
							Toast.LENGTH_SHORT).show();
				} else if (delpsw.getText().toString().trim().equals("")) {
					Toast.makeText(HomeActivity.this, "密码为空！！",
							Toast.LENGTH_SHORT).show();
				} else {
					int waiterid = Integer.valueOf(delwaiter_num.getText()
							.toString());
					String psw = delpsw.getText().toString();
					String pass = new waiterDaoImpl(HomeActivity.this)
							.checkPassword(waiterid);
					if (pass == null) {
						Toast.makeText(HomeActivity.this, "服务工号不存在！！",
								Toast.LENGTH_SHORT).show();
						delwaiter_num.setText("");
					} else if (!(pass.equals(psw))) {
						Toast.makeText(HomeActivity.this, "服务密码错误！！",
								Toast.LENGTH_SHORT).show();
						delpsw.setText("");
					} else {
						Intent intent = new Intent();
						intent.setClass(HomeActivity.this,
								SearchOrderActivity.class);
						intent.putExtra("waiterId", Integer
								.valueOf(delwaiter_num.getText().toString()));
						startActivity(intent);
					}
				}
				temp.dismiss();
			}
		});

		negbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				temp.dismiss();
			}
		});
		return builder.create();

	}
}