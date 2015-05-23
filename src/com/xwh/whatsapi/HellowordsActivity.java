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
 * һ������ hellowords API key��79c0afa47f80011c1e8a07b6a09efb5d
 * 
 * @author xwh
 *
 */

public class HellowordsActivity extends Activity {

	// ͼƬչʾ�ؼ�
	private ImageView img_show;
	// ����API�������ϻ�ȡ��λͼ
	private Bitmap mBitmap = null;
	// ��ʱ��
	private Timer mTimer = null;
	// �������Ի���
	private ProgressDialog mProgressDialog = null;
	// ��Ϣͨ��
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_hellowords);

		img_show = (ImageView) findViewById(R.id.img_show);

		// ��ʼ���������Ի���
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(HellowordsActivity.this);
			mProgressDialog.setTitle("��ȴ�");
			mProgressDialog.setMessage("����ƴ��������...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		// �������̵߳���Ϣ
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					if (mBitmap == null) {
						if (mProgressDialog != null) {
							mProgressDialog.dismiss();
						}
						Toast.makeText(HellowordsActivity.this, "���������쳣",
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

	// �����ʱ
	@Override
	protected void onStart() {
		// ��ʼ����ʱ��
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

	// �ֹͣʱ
	@Override
	protected void onStop() {
		mTimer.cancel();
		super.onStop();
	}

}
