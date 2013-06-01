package com.EleMenu403_click;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.EleMenu403_click.R;
import com.registe.test;

public class RegisteActivity extends Activity {
	
	SharedPreferences settings;
	SharedPreferences.Editor editor;

	private EditText register_1 = null;
	private EditText register_2 = null;
	private EditText register_3 = null;
	private EditText register_4 = null;

	private Button registe_commit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences("Register_Info", 0);
		String name = settings.getString("Register", "");
		if (name.equals("true")) {
			Intent intent = new Intent();
			intent.setClass(RegisteActivity.this, HomeActivity.class);
			startActivity(intent);
			RegisteActivity.this.finish();
		}
		setContentView(R.layout.register);
		register_1 = (EditText) findViewById(R.id.Et_register_1);
		register_2 = (EditText) findViewById(R.id.Et_register_2);
		register_3 = (EditText) findViewById(R.id.Et_register_3);
		register_4 = (EditText) findViewById(R.id.Et_register_4);

		registe_commit = (Button) findViewById(R.id.btn_registerCommit);

		registe_commit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				commit();
			}
		});
	}
	
	private void commit() {
		String register_text = "";
		register_text += register_1.getText().toString();
		register_text += register_2.getText().toString();
		register_text += register_3.getText().toString();
		register_text += register_4.getText().toString();
		//System.out.println("--------" + register_text);
		if (register_text.length() != 16) {
			Toast.makeText(RegisteActivity.this,
					"对不起，您输入的注册码格式不正确，请重新输入！", Toast.LENGTH_SHORT)
					.show();
		}
		else {
			if(test.check(register_text.toCharArray())) {
				editor = settings.edit();
				editor.putString("Register", "true");
				editor.commit();
				Toast.makeText(RegisteActivity.this, "恭喜您，注册成功！！！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(RegisteActivity.this, HomeActivity.class);
				startActivity(intent);
				RegisteActivity.this.finish();
			}
			else {
				Toast.makeText(RegisteActivity.this,
						"对不起，注册码不正确，请重新输入！", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

}
