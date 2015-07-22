package com.cookapp.cookapp.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;

public class APPHelper {

	public static String getVersion(Context context) {
		try {
			PackageManager manager =context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			final String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getIntVersion(Context context){
		
		return Integer.parseInt(getVersion(context).replaceAll(".",""));
		
	}
	
	public static int getWindowWidth(Context context){
		
		return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
		
	}
	
	public static int getWindowHeight(Context context){
		
		return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
		
	}

}
