package com.xwh.whatsapi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private LinearLayout mLinearLayout;
	private Button mButton;

	private int[] mGuideID = new int[]{R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3, R.drawable.guide_4};

	private List<ImageView> mImages = new ArrayList<ImageView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mLinearLayout = (LinearLayout) findViewById(R.id.director);
		mButton = (Button) findViewById(R.id.btn_begin);

		mViewPager.setAdapter(new PagerAdapter() {
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView imageView = new ImageView(GuideActivity.this);
				imageView.setImageResource(mGuideID[position]);
				imageView.setScaleType(ScaleType.CENTER_CROP);
				container.addView(imageView);
				mImages.add(imageView);
				return imageView;
			}
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mImages.get(position));
			}
			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}
			@Override
			public int getCount() {
				return mGuideID.length;
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				int count = mLinearLayout.getChildCount();
				if(position == mGuideID.length-1){
					mButton.setVisibility(View.VISIBLE);
				}else{
					mButton.setVisibility(View.INVISIBLE);
				}
				for (int i = 0; i < count; i++) {
					ImageView imageView = (ImageView) mLinearLayout
							.getChildAt(i);
					if (i == position) {
						imageView
								.setBackgroundResource(R.drawable.guide_director_on);
					} else {
						imageView
								.setBackgroundResource(R.drawable.guide_director_off);
					}
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GuideActivity.this,MainActivity.class);
				startActivity(intent);
				GuideActivity.this.finish();
			}
		});
	}
	
}
