package com.cookapp.cookapp.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

	public static void writeSharedPreferences(String XMLName,String key,String msg,Context context){
		//实例化SharedPreferences对象（第一步）
		SharedPreferences mySharedPreferences=context.getSharedPreferences(XMLName,Activity.MODE_PRIVATE);
		//实例化SharedPreferences.Editor对象（第二步）
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		//用putString的方法保存数据
		editor.putString(key,msg);
		//提交当前数据
		editor.commit(); 
	}
	
	public static String readSharedPreferences(String XMLName,String key,Context context){
		//同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
		SharedPreferences sharedPreferences=context.getSharedPreferences(XMLName,Activity.MODE_PRIVATE);
		// 使用getString方法获得value，注意第2个参数是value的默认值
		return sharedPreferences.getString(key, "");
	}
	
}
