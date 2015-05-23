package com.xwh.whatsapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
/**
 * ��������activity���ж��Ƿ���ν���Ӧ�ã����ǣ�����ת������activity��������ת����activity��
 * 
 * @author xwh
 *
 */

public class SplashActivity extends Activity {

	// SharedPreferences�ļ���
	public static final String SHAREDPREFERENCES_NAME = "WhatsAPI_Pref";
	// ��Ҫ��ȡ��֤���ַ�����
	public static final String KEY_GUIDE_ACTIVITY = "guide_activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Log.d("TAG", "SplashActivity");
		//�ж��Ƿ���ν���Ӧ��
		if (isFirstEnter(SplashActivity.this)) {
			Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
			startActivity(intent);
			SplashActivity.this.finish();
		}else{
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);
			SplashActivity.this.finish();
		}
	}

	private boolean isFirstEnter(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		Boolean flag = preferences.getBoolean(KEY_GUIDE_ACTIVITY, true);
		if (flag) {
			return true;
		} else {
			return false;
		}
	}
}
