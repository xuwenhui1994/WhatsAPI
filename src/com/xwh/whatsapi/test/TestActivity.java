package com.xwh.whatsapi.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TAG", "onCreate");
	}

	@Override
	protected void onStart() {
		Log.d("TAG", "onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.d("TAG", "onResume");
		super.onResume();
	}
	@Override
	protected void onRestart() {
		Log.d("TAG", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onPause() {
		Log.d("TAG", "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d("TAG", "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
