package com.xwh.whatsapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
/**
 * 动画加载activity，判断是否初次进入应用，若是，则跳转到引导activity；否则跳转到主activity。
 * 
 * @author xwh
 *
 */

public class SplashActivity extends Activity {

	// SharedPreferences文件名
	public static final String SHAREDPREFERENCES_NAME = "WhatsAPI_Pref";
	// 需要读取验证的字符串名
	public static final String KEY_GUIDE_ACTIVITY = "guide_activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Log.d("TAG", "SplashActivity");
		//判断是否初次进入应用
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
