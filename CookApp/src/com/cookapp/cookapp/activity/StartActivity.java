package com.cookapp.cookapp.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.contants.Contants;
import com.cookapp.cookapp.tools.SharedPreferencesHelper;
import com.umeng.analytics.MobclickAgent;

public class StartActivity extends Activity {

	String UM_TAG="";

	ImageView startPageImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		//友盟
		MobclickAgent.setDebugMode(true);
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.setSessionContinueMillis(1000);
		MobclickAgent.updateOnlineConfig(this);
		//友盟
		
		Bitmap bitmap=checkStartImage();
		
		if(bitmap!=null){
			startPageImage.setImageBitmap(bitmap);
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Intent intent=null;
				
				if(SharedPreferencesHelper.readSharedPreferences(Contants.xmlFileName,"logined",StartActivity.this).equals("")){
					SharedPreferencesHelper.writeSharedPreferences(Contants.xmlFileName,"logined","logined",StartActivity.this);
					intent=new Intent(StartActivity.this,GuideActivity.class);
				}else{
					intent=new Intent(StartActivity.this,HomeActivity.class);
				}
								
				startActivity(intent);
				
				finish();
				
			}
		}).start();

	}

	Bitmap checkStartImage(){
		
		File file=new File(Contants.startPageFilePath);
		
		if(file.exists()){

			try {
				InputStream is= new FileInputStream(file);
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				is.close();
				return bitmap;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;	
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
}
