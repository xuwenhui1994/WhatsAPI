package com.xwh.whatsapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
/**
 * ��activity
 * 
 * @author xwh
 *
 */
public class MainActivity extends Activity {

	// ����������ת��ImageButton
	private ImageButton btn_hellowords;
	private ImageButton btn_zhaiyan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_hellowords = (ImageButton) findViewById(R.id.btn_hellowords);
		btn_zhaiyan = (ImageButton) findViewById(R.id.btn_zhaiyan);

		// �ر�����ҳ
		closeGuide(MainActivity.this);

		// �ֱ����õ���¼��ļ�����
		btn_hellowords.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						HellowordsActivity.class);
				startActivity(intent);
			}
		});
		btn_zhaiyan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ZhaiyanActivity.class);
				startActivity(intent);
			}
		});
	}

	private void closeGuide(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				SplashActivity.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(SplashActivity.KEY_GUIDE_ACTIVITY, false);
		editor.commit();
	}

	// ���η��ؼ��˳�����
	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
