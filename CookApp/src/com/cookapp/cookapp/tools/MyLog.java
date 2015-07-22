package com.cookapp.cookapp.tools;

import android.util.Log;

public class MyLog {

	final static boolean DEBUG=true;

	public static void e(String title,String message){

		if(DEBUG){
			Log.e(title,message);
		}

	}

	public static void v(String title,String message){

		if(DEBUG){
			Log.v(title,message);
		}

	}

	public static void i(String title,String message){

		if(DEBUG){
			Log.i(title,message);
		}

	}

	public static void d(String title,String message){

		if(DEBUG){
			Log.d(title,message);
		}

	}
	
	public static void w(String title,String message){

		if(DEBUG){
			Log.w(title,message);
		}

	}
	
	public static void syso(String message){

		if(DEBUG){
			System.out.println(message);
		}

	}
}
