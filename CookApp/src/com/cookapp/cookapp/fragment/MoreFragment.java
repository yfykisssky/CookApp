package com.cookapp.cookapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.activity.AboutActivity;
import com.cookapp.cookapp.activity.CompetitiveProductsActivity;
import com.cookapp.cookapp.activity.FeedBackActivity;
import com.cookapp.cookapp.contants.Contants;
import com.cookapp.cookapp.contants.WebContants;
import com.cookapp.cookapp.tools.APPHelper;
import com.cookapp.cookapp.tools.DataCleanHelper;
import com.cookapp.cookapp.tools.FileHelper;
import com.cookapp.cookapp.tools.GetNetwork;
import com.cookapp.cookapp.tools.SharedPreferencesHelper;
import com.cookapp.cookapp.tools.WebDataHelper;

public class MoreFragment extends Fragment implements OnClickListener{

	View view;

	TextView chaceTex;

	Context mContext;
	
	TextView texVersion;
	
	ImageView imgeCanUpdate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_more,null);

		mContext=this.getActivity();
		
		initView();
		
		return view;
	}

	void initView(){

		//((RelativeLayout)view.findViewById(R.id.saveweb_rela_MoreFrag)).setOnClickListener(this);
		//((RelativeLayout)view.findViewById(R.id.messageremind_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.sharesetting_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.invitefriends_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.clearsave_rela_MoreFrag)).setOnClickListener(this);

		((RelativeLayout)view.findViewById(R.id.feedback_rela_MoreFrag)).setOnClickListener(this);
		//((RelativeLayout)view.findViewById(R.id.diagnoseweb_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.about_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.competitiveproducts_rela_MoreFrag)).setOnClickListener(this);
		((RelativeLayout)view.findViewById(R.id.update_rela_MoreFrag)).setOnClickListener(this);

		chaceTex=(TextView)view.findViewById(R.id.tex_cache_moreFrag);

		chaceTex.setText(String.valueOf(DataCleanHelper.getChaceSize(mContext)));
		
		texVersion=(TextView)view.findViewById(R.id.update_tex_MoreFrag);
		
		texVersion.setText("V"+APPHelper.getVersion(mContext));
		
		imgeCanUpdate=(ImageView)view.findViewById(R.id.update_image_MoreFrag);

	}

	@Override
	public void onClick(View id) {
		switch(id.getId()){
		/*case R.id.saveweb_rela_MoreFrag:
			break;*/
	/*	case R.id.messageremind_rela_MoreFrag:
			break;*/
		case R.id.sharesetting_rela_MoreFrag:
			break;
		case R.id.invitefriends_rela_MoreFrag:
			break;
		case R.id.clearsave_rela_MoreFrag:
			DataCleanHelper.cleanApplicationData(mContext,"");
			chaceTex.setText(String.valueOf(DataCleanHelper.getChaceSize(mContext)));
			break;
		case R.id.feedback_rela_MoreFrag:
			startActivity(new Intent(mContext,FeedBackActivity.class));
			break;
		case R.id.diagnoseweb_rela_MoreFrag:

			if(GetNetwork.isNetworkConnected(mContext)){

			}else{
				int type=GetNetwork.getConnectedType(mContext);
				switch(type){

				}
			}

			break;
		case R.id.about_rela_MoreFrag:
			startActivity(new Intent(mContext,AboutActivity.class));
			break;
		case R.id.competitiveproducts_rela_MoreFrag:
			startActivity(new Intent(mContext,CompetitiveProductsActivity.class));
			break;
		case R.id.update_rela_MoreFrag:

			FileHelper.updateVersionFile("http://gdown.baidu.com/data/wisegame/89f2cc358ae029db/baidushoujiweishi_2150.apk",mContext);

			break;
		}
	}

	void checkUpdate(){

		if(!SharedPreferencesHelper.readSharedPreferences(Contants.xmlFileName,"canupdate",mContext).equals("")){
			imgeCanUpdate.setVisibility(View.VISIBLE);
		}else{
			WebDataHelper.getPostData(WebContants.UPDATE_VERSION_URL,new String[]{},new String[]{},100,myWebHandler);
		}

	}

	Handler myWebHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String responseText=msg.getData().getString("responseResult").toString();
			switch(msg.what){
			case 100:

				APPHelper.getIntVersion(mContext);

				SharedPreferencesHelper.writeSharedPreferences(Contants.xmlFileName,"canupdate",APPHelper.getVersion(mContext),mContext);

			/*	imgeCanUpdate.setVisibility(View.VISIBLE);
				imgeCanUpdate.setVisibility(View.INVISIBLE);*/
				
				break;
			}
		}

	};
}
