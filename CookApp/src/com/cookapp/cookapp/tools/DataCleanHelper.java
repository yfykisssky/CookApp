package com.cookapp.cookapp.tools;

import java.io.File;
import java.text.DecimalFormat;

import android.content.Context;
import android.os.Environment;

public class DataCleanHelper {

	//清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
	public static void cleanInternalCache(Context context) {
		deleteFilesByDirectory(context.getCacheDir().toString());
	}

	public static long getSizeInternalCache(Context context) {
		return getSizeFilesByDirectory(context.getCacheDir().toString());
	}

	//清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
	public static void cleanDatabases(Context context) {
		deleteFilesByDirectory("/data/data/"+ context.getPackageName() + "/databases");
	}
	
	public static long getSizeDatabases(Context context) {
		return getSizeFilesByDirectory("/data/data/"+ context.getPackageName() + "/databases");
	}

	//清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
	public static void cleanSharedPreference(Context context) {
		deleteFilesByDirectory("/data/data/"+ context.getPackageName() + "/shared_prefs");
	}
	
	public static long getSizeSharedPreference(Context context) {
		return getSizeFilesByDirectory("/data/data/"+ context.getPackageName() + "/shared_prefs");
	}

	// 按名字清除本应用数据库 
	public static void cleanDatabaseByName(Context context, String dbName) {
		context.deleteDatabase(dbName);
	}

	// 清除/data/data/com.xxx.xxx/files下的内容 
	public static void cleanFiles(Context context) {
		deleteFilesByDirectory(context.getFilesDir().toString());
	}
	
	public static long getSizeFiles(Context context) {
		return getSizeFilesByDirectory(context.getFilesDir().toString());
	}

	//清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
	public static void cleanExternalCache(Context context) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir().toString());
		}
	}
	
	public static long getSizeExternalCache(Context context) {
		
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return getSizeFilesByDirectory(context.getExternalCacheDir().toString());
		}
		
		return 0;		

	}

	//清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(filePath);
	}

	public static long getSizeCustomCache(String filePath) {
		return getSizeFilesByDirectory(filePath);
	}
	
	//清除本应用所有的数据 
	public static void cleanApplicationData(Context context, String... filepath) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		cleanDatabases(context);
		cleanSharedPreference(context);
		cleanFiles(context);
		for (String filePath : filepath) {
			cleanCustomCache(filePath);
		}
	}
	
	//清除本应用所有的数据 
	public static long getSizeApplicationData(Context context, String... filepath) {
		
		long size=
		getSizeInternalCache(context)+
		getSizeExternalCache(context)+
		getSizeDatabases(context)+
		getSizeSharedPreference(context)+
		getSizeFiles(context);
		for (String filePath : filepath) {
			size+=getSizeCustomCache(filePath);
		}
		return size;
		
	}

	//删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
	private static void deleteFilesByDirectory(String url) {
		
		File directory=new File(url);
		
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
		
	}

	private static long getSizeFilesByDirectory(String url) {
		
		File directory=new File(url);
		
		long size=0;
		
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				size+=item.length();
			}
		}
		return size;
	}
	
	public static String getChaceSize(Context context){
		
		long size=DataCleanHelper.getSizeApplicationData(context,"");

		return getDataSize(size);
		
	}

	public static String getDataSize(long size){
		DecimalFormat formater = new DecimalFormat("####.00");
		if(size<1024){
			return size+"bytes";
		}else if(size<1024*1024){
			float kbsize = size/1024f;  
			return formater.format(kbsize)+"KB";
		}else if(size<1024*1024*1024){
			float mbsize = size/1024f/1024f;  
			return formater.format(mbsize)+"MB";
		}else if(size<1024*1024*1024*1024){
			float gbsize = size/1024f/1024f/1024f;  
			return formater.format(gbsize)+"GB";
		}else{
			return "size: error";
		}
	}
}
