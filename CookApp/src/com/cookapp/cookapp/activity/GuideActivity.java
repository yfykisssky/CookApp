package com.cookapp.cookapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cookapp.cookapp.R;
import com.umeng.analytics.MobclickAgent;

public class GuideActivity extends Activity implements OnClickListener{

	String UM_TAG="";

	ViewPager viewPager;

	Button buttonIn;

	TextView texIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		initView();

	}

	void initView(){

		viewPager=(ViewPager)findViewById(R.id.viewPager_guideActi);

		List<View> views=new ArrayList<View>();

		View guideView1=LayoutInflater.from(this).inflate(R.layout.fragment_guide1,null);
		views.add(guideView1);

		View guideView2=LayoutInflater.from(this).inflate(R.layout.fragment_guide2,null);
		views.add(guideView2);

		View guideView3=LayoutInflater.from(this).inflate(R.layout.fragment_guide3,null);
		views.add(guideView3);

		View guideView4=LayoutInflater.from(this).inflate(R.layout.fragment_guide4,null);
		views.add(guideView4);

		buttonIn=(Button)guideView4.findViewById(R.id.buttonIn_guideActi);

		buttonIn.setOnClickListener(this);

		texIn=(TextView)guideView1.findViewById(R.id.textIn_guide1Frag);

		texIn.setOnClickListener(this);

		viewPager.setAdapter(new MyPageAdapter(views));

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		viewPager.setCurrentItem(0);
		
	}

	class MyPageAdapter extends PagerAdapter{

		List<View> views;

		MyPageAdapter(List<View> views){
			this.views=views;
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override  
		public Object instantiateItem(View arg0, int arg1) {  

			((ViewPager) arg0).addView(views.get(arg1), 0);  

			return views.get(arg1);  
		}  

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return (arg0 == arg1);
		}

		@Override  
		public void destroyItem(View arg0, int arg1, Object arg2) {  
			((ViewPager) arg0).removeView(views.get(arg1));       
		}  

	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(UM_TAG);
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(UM_TAG);
		MobclickAgent.onPause(this);
	}

	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.buttonIn_guideActi:
			startActivity(new Intent(GuideActivity.this,HomeActivity.class));
			finish();
			break;
		case R.id.textIn_guide1Frag:
			startActivity(new Intent(GuideActivity.this,HomeActivity.class));
			finish();
			break;
		}
	}

}
