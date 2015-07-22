package com.cookapp.cookapp.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cookapp.cookapp.R;
import com.cookapp.cookapp.fragment.MainFragment;
import com.cookapp.cookapp.fragment.MoreFragment;
import com.cookapp.cookapp.fragment.PersionCenterFragment;
import com.cookapp.cookapp.fragment.RadomScanFragment;
import com.cookapp.cookapp.tools.MyLog;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("CutPasteId") 
public class HomeActivity extends FragmentActivity implements OnClickListener{

	String UM_TAG="";

	FragmentManager fragMent;

	RelativeLayout bntMainFrag;

	RelativeLayout bntRadscaFrag;

	RelativeLayout bntPerCenFrag;

	RelativeLayout bntMoreFrag;

	final int  SCANNIN_GREQUEST_CODE=1;

	ImageView mainBackImage,radomScanImage,persionCenBackImage,moreBackImage;

	TextView mainBackTex,radomScanTex,persionCenBackTex,moreBackTex;

	int colorChoice;

	int colorUnChoice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initView();

	}

	void scanCode(){

		Intent intent = new Intent();
		intent.setClass(HomeActivity.this, CodeCaptureActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent,SCANNIN_GREQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();

				MyLog.e("scan",bundle.getString("result"));

				/*//显示扫描到的内容
				mTextView.setText(bundle.getString("result"));
				//显示
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));*/
			}
			break;
		}
	}	

	/*	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
		}
	}*/

	void initView(){

		colorChoice=getResources().getColor(R.color.back_red);

		colorUnChoice=getResources().getColor(R.color.dark_gray);
			
		fragMent=getSupportFragmentManager();

		bntMainFrag=(RelativeLayout)findViewById(R.id.relative_layout_main_homeActi);
		
		bntRadscaFrag=(RelativeLayout)findViewById(R.id.relative_layout_radom_scan_homeActi);

		bntPerCenFrag=(RelativeLayout)findViewById(R.id.relative_layout_persion_cen_homeActi);

		bntMoreFrag=(RelativeLayout)findViewById(R.id.relative_layout_more_homeActi);
	
		bntMainFrag.setOnClickListener(this);
		
		bntRadscaFrag.setOnClickListener(this);

		bntPerCenFrag.setOnClickListener(this);

		bntMoreFrag.setOnClickListener(this);

		mainBackImage=(ImageView)findViewById(R.id.main_image_back_homeActi);
		radomScanImage=(ImageView)findViewById(R.id.radsca_image_back_homeActi);
		persionCenBackImage=(ImageView)findViewById(R.id.perscen_image_back_homeActi);
		moreBackImage=(ImageView)findViewById(R.id.more_image_back_homeActi);

		mainBackTex=(TextView)findViewById(R.id.main_tex_back_homeActi);
		radomScanTex=(TextView)findViewById(R.id.radsca_tex_back_homeActi);
		persionCenBackTex=(TextView)findViewById(R.id.perscen_tex_back_homeActi);
		moreBackTex=(TextView)findViewById(R.id.more_tex_back_homeActi);

		showPage(0);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	void showPage(int position){

		Fragment fragment=null;

		switch(position){
		case 0:
			fragment=new MainFragment();

			/*			mainBackImage.setBackgroundResource(R.drawable.ic_down);
			persionCenBackImage.setBackgroundResource(R.drawable.ic_down);
			moreBackImage.setBackgroundResource(R.drawable.ic_down);*/

			mainBackTex.setTextColor(colorChoice);
			radomScanTex.setTextColor(colorUnChoice);
			persionCenBackTex.setTextColor(colorUnChoice);
			moreBackTex.setTextColor(colorUnChoice);

			break;
		case 1:
			fragment=new RadomScanFragment();
			
			/*	mainBackImage.setBackgroundResource(R.drawable.ic_down);
			persionCenBackImage.setBackgroundResource(R.drawable.ic_down);
			moreBackImage.setBackgroundResource(R.drawable.ic_down);
			 */
		
			mainBackTex.setTextColor(colorUnChoice);
			radomScanTex.setTextColor(colorChoice);
			persionCenBackTex.setTextColor(colorUnChoice);
			moreBackTex.setTextColor(colorUnChoice);
			break;
		case 2:
			fragment=new PersionCenterFragment();

			/*			mainBackImage.setBackgroundResource(R.drawable.ic_down);
			persionCenBackImage.setBackgroundResource(R.drawable.ic_down);
			moreBackImage.setBackgroundResource(R.drawable.ic_down);*/

			mainBackTex.setTextColor(colorUnChoice);
			radomScanTex.setTextColor(colorUnChoice);
			persionCenBackTex.setTextColor(colorChoice);
			moreBackTex.setTextColor(colorUnChoice);
			break;
		case 3:
			fragment=new MoreFragment();

			/*		mainBackImage.setBackgroundResource(R.drawable.ic_down);
			persionCenBackImage.setBackgroundResource(R.drawable.ic_down);
			moreBackImage.setBackgroundResource(R.drawable.ic_down);*/

			mainBackTex.setTextColor(colorUnChoice);
			radomScanTex.setTextColor(colorUnChoice);
			persionCenBackTex.setTextColor(colorUnChoice);
			moreBackTex.setTextColor(colorChoice);
			break;
		}

		FragmentTransaction fragTran=fragMent.beginTransaction();
		fragTran.replace(R.id.fragment_layout_homeActi,fragment);
		fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragTran.commit();
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(UM_TAG);
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(UM_TAG);
		MobclickAgent.onPause(this);
	}

	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.relative_layout_main_homeActi:
			showPage(0);
			break;
		case R.id.relative_layout_radom_scan_homeActi:
			showPage(1);
			break;
		case R.id.relative_layout_persion_cen_homeActi:
			showPage(2);
			break;
		case R.id.relative_layout_more_homeActi:
			showPage(3);
			break;
		}
	}

	//for receive customer msg from jpush server
	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";

	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
			}
		}
	}

}
