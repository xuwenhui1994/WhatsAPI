package com.xwh.whatsapi;

import java.io.IOException;
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
import android.os.Message;
import android.util.Log;
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

	// 图片展示控件
	private ImageView img_show;
	// 调用API从网络上获取的位图
	private Bitmap mBitmap = null;
	// 计时器
	private Timer mTimer = null;
	// 进度条对话框
	private ProgressDialog mProgressDialog = null;
	// 消息通信
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_hellowords);

		img_show = (ImageView) findViewById(R.id.img_show);

		// 初始化进度条对话框
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(HellowordsActivity.this);
			mProgressDialog.setTitle("请等待");
			mProgressDialog.setMessage("正在拼命加载中...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		// 处理子线程的消息
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					if (mBitmap == null) {
						if (mProgressDialog != null) {
							mProgressDialog.dismiss();
						}
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
	}

	// 活动启动时
	@Override
	protected void onStart() {
		// 初始化计时器
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				String uri = "http://hello.api.235dns.com/api.php?code=png&key=79c0afa47f80011c1e8a07b6a09efb5d";
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(uri);

				try {
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					mBitmap = BitmapFactory.decodeStream(entity.getContent());
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
		super.onStart();
	}

	// 活动停止时
	@Override
	protected void onStop() {
		mTimer.cancel();
		super.onStop();
	}

}
