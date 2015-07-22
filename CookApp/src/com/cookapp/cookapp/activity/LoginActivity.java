package com.cookapp.cookapp.activity;

import com.cookapp.cookapp.R;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LoginActivity extends Activity implements OnClickListener{

	EditText loginNameEdi;

	EditText loginPasswordEdi;

	Button loginBnt;

	ImageButton loginQQBnt;

	ImageButton loginWXBnt;

	ImageButton loginWOBnt;
	
	Tencent	mTencent;
	
	IUiListener loginListener;
	
	ImageView bntBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initView();
		
	}

	void initView(){

		loginNameEdi=(EditText)findViewById(R.id.name_edit_loginAci);

		loginPasswordEdi=(EditText)findViewById(R.id.pswd_edit_loginAci);

		loginBnt=(Button)findViewById(R.id.login_bnt_loginAci);

		loginBnt.setOnClickListener(this);
		
		bntBack=(ImageView)findViewById(R.id.image_back_loginActi);
		
		bntBack.setOnClickListener(this);

	}

	void QQLogin()
	{
		mTencent = Tencent.createInstance("", this.getApplicationContext());

		loginListener=new IUiListener() {

			@Override
			public void onError(UiError arg0) {

			}

			@Override
			public void onComplete(Object arg0) {

			}

			@Override
			public void onCancel() {

			}
		};

	/*	if (!mTencent.isSessionValid())
		{
			mTencent.login(this,"get_user_info,add_t",loginListener);
		}*/

		//mTencent.logout(this);
	} 

	void WeiXinLogin(){

	}

	void WeiBoLogin(){

	}

	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.login_bnt_loginAci:
			break;
		case R.id.image_back_loginActi:
			finish();
			break;
		}
	}

	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == Constants.REQUEST_API) {
			if(resultCode == Constants.RESULT_LOGIN) {
				mTencent.handleLoginData(data, loginListener);
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
