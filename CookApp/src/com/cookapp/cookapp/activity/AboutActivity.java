package com.cookapp.cookapp.activity;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.tools.APPHelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {

	TextView texVersion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		initView();
		
		texVersion.setText("V"+APPHelper.getVersion(this));
		
	}
	
	void initView(){
		
		texVersion=(TextView)findViewById(R.id.tex_logo_aboutActi);
		
	}

}
