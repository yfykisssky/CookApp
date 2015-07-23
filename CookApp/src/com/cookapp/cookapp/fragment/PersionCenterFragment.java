package com.cookapp.cookapp.fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable; 

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.activity.ClipImageActivity;
import com.cookapp.cookapp.activity.LoginActivity;
import com.cookapp.cookapp.view.MyDialog;
import com.umeng.analytics.MobclickAgent;

public class PersionCenterFragment extends Fragment implements OnClickListener{

	String UM_TAG="";

	View view;

	Button loginBnt;

	Button myCollectionBnt;

	Button myMessageBnt;

	Button signInBnt;

	ImageView headImage;

	TextView integralTex;

	Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_persion_center,null);

		mContext=this.getActivity();

		initView();

		return view;
	}

	void initView(){

		loginBnt=(Button)view.findViewById(R.id.login_per_cen_Frag);
		loginBnt.setOnClickListener(this);

		myMessageBnt=(Button)view.findViewById(R.id.mymessage_bnt_percen_Frag);
		myMessageBnt.setOnClickListener(this);

		myCollectionBnt=(Button)view.findViewById(R.id.mycollection_bnt_percen_Frag);
		myCollectionBnt.setOnClickListener(this);

		signInBnt=(Button)view.findViewById(R.id.sign_in_bnt_per_cen_Frag);
		signInBnt.setOnClickListener(this);

		headImage=(ImageView)view.findViewById(R.id.head_iamge_cen_Frag);
		headImage.setOnClickListener(this);

		integralTex=(TextView)view.findViewById(R.id.integral_tex_per_cen_Frag);

	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(UM_TAG);
		MobclickAgent.onResume(this.getActivity());
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(UM_TAG);
		MobclickAgent.onPause(this.getActivity());
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View id) {
		switch(id.getId()){
		
		case R.id.login_per_cen_Frag:
			Intent intent=new Intent(this.getActivity(),LoginActivity.class);
			startActivity(intent);
			break;
			
		case R.id.mymessage_bnt_percen_Frag:
			myMessageBnt.setBackground(this.getResources().getDrawable(R.drawable.background_halftopradius_red));
			myMessageBnt.setTextColor(getResources().getColor(R.color.white));
			myCollectionBnt.setBackground(this.getResources().getDrawable(R.drawable.background_halftopradius_white));
			myCollectionBnt.setTextColor(getResources().getColor(R.color.black));
			break;
			
		case R.id.mycollection_bnt_percen_Frag:
			myMessageBnt.setBackground(this.getResources().getDrawable(R.drawable.background_halftopradius_white));
			myMessageBnt.setTextColor(getResources().getColor(R.color.black));
			myCollectionBnt.setBackground(this.getResources().getDrawable(R.drawable.background_halftopradius_red));
			myCollectionBnt.setTextColor(getResources().getColor(R.color.white));
			break;
			
		case R.id.sign_in_bnt_per_cen_Frag:
			break;
			
		case R.id.head_iamge_cen_Frag:

			final MyDialog downPopWindow=new MyDialog(mContext);
			
			downPopWindow.showDownPopWindow(R.layout.dialog_headchange_percent_center);

			Button choicePhotographBnt=(Button)downPopWindow.findViewById(R.id.choice_photograph_bnt_dialog);
			
			choicePhotographBnt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					Intent intent=new Intent(mContext,ClipImageActivity.class);
					intent.putExtra("getImageKind",0);
					startActivityForResult(intent,0);
				}
			});

			Button getFromAlbumBnt=(Button)downPopWindow.findViewById(R.id.get_from_album_bnt_dialog);

			getFromAlbumBnt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					Intent intent=new Intent(mContext,ClipImageActivity.class);
					intent.putExtra("getImageKind",1);
					startActivityForResult(intent,0);

				}
			});

			Button cancleBnt=(Button)downPopWindow.findViewById(R.id.cancle_bnt_dialog);

			cancleBnt.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					downPopWindow.dismiss();
				}
			});

			break;
		}	
	}

}
