package com.cookapp.cookapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.drawable.Drawable; 
import com.cookapp.cookapp.R;
import com.cookapp.cookapp.activity.LoginActivity;
import com.umeng.analytics.MobclickAgent;

public class PersionCenterFragment extends Fragment implements OnClickListener{

	String UM_TAG="";

	View view;
	
	Button loginBnt;
	
	Button myCollectionBnt;
	
	Button myMessageBnt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_persion_center,null);

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
		}	
	}

}
