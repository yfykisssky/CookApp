package com.cookapp.cookapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import com.cookapp.cookapp.R;
import com.cookapp.cookapp.activity.CodeCaptureActivity;
import com.cookapp.cookapp.tools.GetFileHelper;
import com.cookapp.cookapp.view.SlideShowView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.umeng.analytics.MobclickAgent;

public class MainFragment extends Fragment implements OnClickListener{

	String UM_TAG="";

	View view;
	
	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	
	SlideShowView slideShowView;
	
	ListView imageList;
	
	ImageView scanBnt;
	
	final int  SCANNIN_GREQUEST_CODE=1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_main,null);

		initView();
		
		String urlPath="http://img1.cache.netease.com/catchpic/2/2B/2B2752B4C13FE832D7839FD7075FCC78.jpg";
		
		String filePath=this.getActivity().getFilesDir()+"//"+GetFileHelper.getUrlFileName(urlPath);
		
		try {
			((ImageView)view.findViewById(R.id.image)).setImageBitmap(GetFileHelper.getImageBitmap(urlPath, filePath,""));
		} catch (Exception e) {
			e.printStackTrace();
		};
		
		return view;
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

	void initView() {
		
		mPullRefreshScrollView= (PullToRefreshScrollView)view.findViewById(R.id.pull_refresh_scrollview_mainFrag);
		
		mScrollView=mPullRefreshScrollView.getRefreshableView();
		
		mPullRefreshScrollView.setMode(Mode.BOTH);
		
		mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				mPullRefreshScrollView.onRefreshComplete();
			}
			
		});
		
		slideShowView=(SlideShowView)view.findViewById(R.id.slideShowView_mainFrag);
		
		imageList=(ListView)view.findViewById(R.id.image_list_mainFrag);
		
		MyImageListAdapter myImageListAdapter=new MyImageListAdapter();
		
		imageList.setAdapter(myImageListAdapter);
		
		scanBnt=(ImageView)view.findViewById(R.id.scan_bnt_mainFrag);
		
		scanBnt.setOnClickListener(this);
		
	}
	
	class MyImageListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View contentView, ViewGroup arg2) {
			return null;
		}
		
	}

	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.scan_bnt_mainFrag:
			scanCode();
			break;
		}
	}
	
	void scanCode(){

		Intent intent = new Intent();
		intent.setClass(this.getActivity(), CodeCaptureActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent,SCANNIN_GREQUEST_CODE);

	}

	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
		/*	if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();

				MyLog.e("scan",bundle.getString("result"));

				//显示扫描到的内容
				mTextView.setText(bundle.getString("result"));
				//显示
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}*/
			break;
		}
	}	

}
