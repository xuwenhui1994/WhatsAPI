package com.xwh.whatsapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * 一语倾心 hellowords API key：79c0afa47f80011c1e8a07b6a09efb5d
 * 
 * @author xwh
 *
 */

public class HellowordsActivity extends Activity {

	ImageView img_show;

	Bitmap mBitmap = null;

	Timer mTimer;

	ProgressDialog mProgressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_hellowords);

		img_show = (ImageView) findViewById(R.id.img_show);

		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(HellowordsActivity.this);
			mProgressDialog.setTitle("请等待");
			mProgressDialog.setMessage("正在拼命加载中...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		final Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					if (mBitmap == null) {
						Toast.makeText(HellowordsActivity.this, "网络连接异常",
								Toast.LENGTH_SHORT).show();
					} else {
						if (mProgressDialog != null) {
							mProgressDialog.dismiss();
						}
						img_show.setImageBitmap(mBitmap);
					}
				}
			};
		};

		mTimer = new Timer();

		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				String uri = "http://hello.api.235dns.com/api.php?code=png&key=79c0afa47f80011c1e8a07b6a09efb5d";

				HttpGet get = new HttpGet(uri);
				HttpClient client = new DefaultHttpClient();

				try {
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					mBitmap = BitmapFactory.decodeStream(is);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 5000);
	}

	@Override
	protected void onDestroy() {
		mTimer.cancel();
		super.onDestroy();
	}
}
