package com.cookapp.cookapp.app;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {

	String JPushId="";
	
	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
	    JPushId=JPushInterface.getRegistrationID(this);
	}

	
	
}
