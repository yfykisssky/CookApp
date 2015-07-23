package com.cookapp.cookapp.activity;

import java.io.Serializable;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.contants.WebContants;
import com.cookapp.cookapp.model.UserMessage;
import com.cookapp.cookapp.tools.MD5Helper;
import com.cookapp.cookapp.tools.WebDataHelper;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
	
	TextView bntRegister;
	
	TextView bntPhoneQuickLogin;
	
	TextView bntFindBackPswd;
	
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mContext=this;
		
		initView();
		
	}

	void initView(){

		loginNameEdi=(EditText)findViewById(R.id.name_edit_loginAci);

		loginPasswordEdi=(EditText)findViewById(R.id.pswd_edit_loginAci);

		loginBnt=(Button)findViewById(R.id.login_bnt_loginAci);

		loginBnt.setOnClickListener(this);
		
		bntBack=(ImageView)findViewById(R.id.image_back_loginActi);
		
		bntBack.setOnClickListener(this);
		
		bntRegister=(TextView)findViewById(R.id.register_tex_loginActi);
		
		bntRegister.setOnClickListener(this);
		
		bntPhoneQuickLogin=(TextView)findViewById(R.id.phone_login_tex_loginActi);
		
		bntPhoneQuickLogin.setOnClickListener(this);
		
		bntFindBackPswd=(TextView)findViewById(R.id.findback_password_loginActi);
		
		bntFindBackPswd.setOnClickListener(this);

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
			
			String userName=loginNameEdi.getText().toString();

			String password=loginPasswordEdi.getText().toString();
			
			if(userName.equals("")){
				Toast.makeText(mContext,getResources().getString(R.string.login_user_null),Toast.LENGTH_LONG).show();
				return;
			}
			
			if(password.equals("")){
				Toast.makeText(mContext,getResources().getString(R.string.login_pswd_null),Toast.LENGTH_LONG).show();
				return;
			}
			
			checkLoginFromInternet(userName,password);
			
			break;
		case R.id.image_back_loginActi:
			finish();
			break;
		case R.id.register_tex_loginActi:
			startActivity(new Intent(this,RegisterActivity.class));
			break;
		case R.id.phone_login_tex_loginActi:
			startActivity(new Intent(this,PhoneLoginActivity.class));
			break;
		case R.id.findback_password_loginActi:
			startActivity(new Intent(this,FindBackPswdActivity.class));
			break;
		}
	}
	
	@SuppressLint("HandlerLeak") 
	void checkLoginFromInternet(String userName,String password){
		
		String paswdMD5=MD5Helper.stringToMD5(password);
		
		WebDataHelper.getPostData(WebContants.UPDATE_VERSION_URL,new String[]{userName},
				new String[]{paswdMD5},100,new Handler(){

					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);

						setLoginSuccess();
						
						finish();
						
					}
			
			
		});
	}
	
	void setLoginSuccess(){
		
		UserMessage userMessage=new UserMessage();
		
		Bundle bundle=new Bundle();
		
		bundle.putSerializable("userMessage",(Serializable)userMessage);
		
		Intent intent=new Intent();
		
		intent.putExtras(bundle);
		
		setResult(1);
	}

	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode,int resultCode, Intent data) {
		if(requestCode == Constants.REQUEST_API) {
			if(resultCode == Constants.RESULT_LOGIN) {
				mTencent.handleLoginData(data, loginListener);
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
