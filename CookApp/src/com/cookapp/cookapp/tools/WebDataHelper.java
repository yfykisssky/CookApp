package com.cookapp.cookapp.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WebDataHelper {

	public static void getPostData(String url,String keys[],String values[],final int numMark,Handler handler){

		HttpClient httpClient;
		httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> valuesPost= new ArrayList<NameValuePair>();

		for(int b=0;b<keys.length;b++){
			valuesPost.add(new BasicNameValuePair(keys[b],values[b]));
		}

		try {
			
			UrlEncodedFormEntity uentity = new UrlEncodedFormEntity(valuesPost, "UTF-8");

			httpPost.setEntity(uentity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();

			String body="";

			if(httpResponse.getStatusLine().getStatusCode() == 200)   
			{  
				body = EntityUtils.toString(entity,"UTF-8");
			}  

			Message msg=new Message();
			msg.what=numMark;

			Bundle bundle=new Bundle();
			bundle.putString("responseResult",body);

			msg.setData(bundle);

			handler.sendMessage(msg);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void getGetData(String url,String keys[],String values[],final int numMark,Handler handler){

		String getString="";

		for(int h=0;h<keys.length;h++){
			getString+="&"+keys[h]+"="+values[h];
		}

		url+="?"+getString.substring(1);

		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);

			String body="";

			if(httpResponse.getStatusLine().getStatusCode() == 200)   
			{  
				body= EntityUtils.toString(httpResponse.getEntity());  
			}

			Message msg=new Message();
			msg.what=numMark;

			Bundle bundle=new Bundle();
			bundle.putString("responseResult",body);

			msg.setData(bundle);

			handler.sendMessage(msg);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  

	}

}
