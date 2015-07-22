package com.cookapp.cookapp.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.cookapp.cookapp.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class GetFileHelper {

	public static String getUrlFileName(String urlPath){
		String result = urlPath.substring(urlPath.lastIndexOf('/')+1); 
		return result;
	}

	public static Uri getDownLoadFileURI(String urlPath,String filePath,String MD5Str) throws Exception {

		File file = new File(filePath);

		if (MD5Str!=null&&file.exists()&&!MD5Check.getFileMD5String(file).equals(MD5Str)) {

			return Uri.fromFile(file);

		} else {
			// 从网络上获取图片
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			if (conn.getResponseCode() == 200) {

				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
				// 返回一个URI对象
				return Uri.fromFile(file);
			}
		}
		return null;
	}

	public static Bitmap getImageBitmap(String urlPath,String filePath,String MD5Str) throws Exception {

		Bitmap bitmap=null;

		File file = new File(filePath);

		if (MD5Str!=null&&file.exists()&&!MD5Check.getFileMD5String(file).equals(MD5Str)) {

			InputStream is =new FileInputStream(file);

			bitmap = BitmapFactory.decodeStream(is);

			return bitmap;
		} else {
			// 从网络上获取图片
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			if (conn.getResponseCode() == 200) {

				InputStream is = conn.getInputStream();
				// 将InputStream转换成Bitmap
				bitmap = BitmapFactory.decodeStream(is);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
				// 返回一个URI对象
				return bitmap;
			}
		}
		return bitmap;
	}

	static boolean updateContiune=false;

	public static void updateVersionFile(final String urlPath,final Context context){

		final String filePath=context.getFilesDir().toString()+"file.apk";
		MyLog.e("path",filePath);

		final Dialog downloadDialog=new Dialog(context);
		downloadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		downloadDialog.setContentView(R.layout.dialog_update_file);
		
		WindowManager.LayoutParams params =downloadDialog.getWindow().getAttributes();
		params.width = (int) (APPHelper.getWindowWidth(context)* 0.7);  
		params.height = (int) (APPHelper.getWindowHeight(context) * 0.2);  
		downloadDialog.getWindow().setAttributes(params); 

		final ProgressBar prograssBar=(ProgressBar)downloadDialog.findViewById(R.id.prograss_percent_downloadfile);

		prograssBar.setMax(100);

		final TextView texPercent=(TextView)downloadDialog.findViewById(R.id.tex_percent_downloadfile);
		Button cancleBnt=(Button)downloadDialog.findViewById(R.id.bnt_percent_downloadfile);

		cancleBnt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				updateContiune=false;

				File file=new File(filePath);

				if(file.exists()){
					file.delete();
				}

				downloadDialog.dismiss();
			}
		});

		final Handler UIUpdateHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				int percent=msg.what;

				prograssBar.setProgress(percent);
				texPercent.setText(String.valueOf(percent)+"%");

			}

		};

		downloadDialog.show();

		updateContiune=true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				File file = new File(filePath);

				try {

					URL url = new URL(urlPath);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestProperty("Accept-Encoding", "identity"); 
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					if (conn.getResponseCode() == 200) {

						InputStream is = conn.getInputStream();

						int fileLength=conn.getContentLength();

						FileOutputStream fos = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						int len = 0;
						long total=0;
						int percent=0;
						while (((len = is.read(buffer))!=-1)&&updateContiune) {
							total+=len;
							percent=(int)total*100/fileLength;
							fos.write(buffer, 0, len);

							Message msg=new Message();
							msg.what=percent;
							UIUpdateHandler.sendMessage(msg);		
						}

						is.close();
						fos.close();

						downloadDialog.dismiss();

						openApk(context,filePath);

					}	
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private static void openApk(Context context,String url) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = manager.getPackageArchiveInfo(url, PackageManager.GET_ACTIVITIES);
		if (info != null) {
			Intent intent = manager.getLaunchIntentForPackage(info.applicationInfo.packageName);
			context.startActivity(intent);
		}
	}

}
