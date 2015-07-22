package com.cookapp.cookapp.tools;

import org.json.JSONException;
import org.json.JSONObject;
import com.cookapp.cookapp.activity.HomeActivity;
import cn.jpush.android.api.JPushInterface;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class JPushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		MyLog.e("", "receive" + intent.getAction());

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
		}else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

		} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

		} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

			Intent i = new Intent(context,HomeActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		} else {
			MyLog.e("", "Unhandled intent - " + intent.getAction());
		}
	}

	// 打印所有的 intent extra 数据
		private static String printBundle(Bundle bundle) {
			StringBuilder sb = new StringBuilder();
			for (String key : bundle.keySet()) {
				if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
					sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
				}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
					sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
				} 
				else {
					sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
				}
			}
			return sb.toString();
		}
		
		//send msg to MainActivity
		private void processCustomMessage(Context context, Bundle bundle) {
/*			if (HomeActivity.isForeground) {*/
				String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
				String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
				Intent msgIntent = new Intent(HomeActivity.MESSAGE_RECEIVED_ACTION);
				msgIntent.putExtra(HomeActivity.KEY_MESSAGE, message);
					try {
						JSONObject extraJson = new JSONObject(extras);
						if (null != extraJson && extraJson.length() > 0) {
							msgIntent.putExtra(HomeActivity.KEY_EXTRAS, extras);
						}
					} catch (JSONException e) {

					}
				
				context.sendBroadcast(msgIntent);
			}
	/*	}*/
	
}
