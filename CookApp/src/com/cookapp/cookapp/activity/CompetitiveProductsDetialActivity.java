package com.cookapp.cookapp.activity;

import com.cookapp.cookapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class CompetitiveProductsDetialActivity extends Activity {

	WebView webView;
	
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_competitiveproductsdetial);
		
		initView();
		
		Intent intent=getIntent();
		
		url=intent.getStringExtra("url");
		
		webView.loadUrl(url);
		
	}
	
	void initView(){
		
		webView=(WebView)findViewById(R.id.webview_competitiveproductsdetialActi);
		
	}

}
