package com.xwh.whatsapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
/**
 * Ö÷activity
 * @author xwh
 *
 */
public class MainActivity extends Activity {

	ImageButton btn_hellowords;
	ImageButton btn_zhaiyan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_hellowords = (ImageButton) findViewById(R.id.btn_hellowords);
		btn_zhaiyan = (ImageButton) findViewById(R.id.btn_zhaiyan);
		
		btn_hellowords.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, HellowordsActivity.class);
				startActivity(intent);
			}
		});
		
		btn_zhaiyan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ZhaiyanActivity.class);
				startActivity(intent);
			}
		});
	}
}
