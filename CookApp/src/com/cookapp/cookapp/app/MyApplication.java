package com.cookapp.cookapp.app;

import com.cookapp.cookapp.model.UserMessage;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {

	String JPushId="";
	
	UserMessage userMessage;
	
	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(true);
	    JPushInterface.init(this);
	    JPushId=JPushInterface.getRegistrationID(this);
	}

}
