package com.xwh.whatsapi;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
/**
 * լ��API http://zyfree.acman.cn/
 * 
 * @author xwh
 *
 */

public class ZhaiyanActivity extends Activity {

	// ������ʾ�ؼ�
	private TextView txt_show;
	// ����API�������ϻ�ȡ������
	private String result = "";
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
		setContentView(R.layout.activity_zhaiyan);

		txt_show = (TextView) findViewById(R.id.txt_show);

		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(ZhaiyanActivity.this);
			mProgressDialog.setTitle("��ȴ�");
			mProgressDialog.setMessage("����ƴ��������...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0x123) {
					if (result.equals("")) {
						if (mProgressDialog != null) {
							mProgressDialog.dismiss();
						}
						Toast.makeText(ZhaiyanActivity.this, "���������쳣",
								Toast.LENGTH_SHORT).show();
					} else {
						if (mProgressDialog != null) {
							mProgressDialog.dismiss();
						}
						String text = "";
						try {
							JSONObject jsonObject = new JSONObject(result);
							text = jsonObject.getString("zhaiyan");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						txt_show.setText(text);
					}
				}
			}
		};
	}

	// �����ʱ
	@Override
	protected void onStart() {
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				String uri = "http://zyfree1.acman.cn/";
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(uri);

				try {
					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					result = EntityUtils.toString(entity);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (ParseException e) {
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
		super.onDestroy();
	}
}
