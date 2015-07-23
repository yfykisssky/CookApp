package com.cookapp.cookapp.fragment;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.activity.CodeCaptureActivity;
import com.cookapp.cookapp.model.ProductsModel;
import com.cookapp.cookapp.tools.AsyncImageLoader;
import com.cookapp.cookapp.tools.AsyncImageLoader.ImageCallback;
import com.cookapp.cookapp.tools.FileHelper;
import com.cookapp.cookapp.view.SlideShowView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.umeng.analytics.MobclickAgent;

public class MainFragment extends Fragment implements OnClickListener{

	String UM_TAG="";

	View view;

	AsyncImageLoader asyncImageLoader;

	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;

	List<ProductsModel> listProducts=new ArrayList<ProductsModel>();

	SlideShowView slideShowView;

	ListView imageList;
	MyImageListAdapter myImageListAdapter;

	ImageView scanBnt;

	Context mContext;

	Fragment mFragment;

	final int  SCANNIN_GREQUEST_CODE=1;
	
	String IMAGE_DOWNLOAD_PATH="";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view=inflater.inflate(R.layout.fragment_main,null);

		mContext=this.getActivity();

		mFragment=this;

		initView();

		asyncImageLoader=new AsyncImageLoader();

		String urlPath="http://img1.cache.netease.com/catchpic/2/2B/2B2752B4C13FE832D7839FD7075FCC78.jpg";

		String filePath=this.getActivity().getFilesDir()+"//"+FileHelper.getUrlFileName(urlPath);

		try {
			((ImageView)view.findViewById(R.id.image)).setImageBitmap(FileHelper.getImageBitmap(urlPath, filePath,""));
		} catch (Exception e) {
			e.printStackTrace();
		};

		return view;
	}

	
	void setTestData(){
		
		for(int v=0;v<50;v++){
			
			ProductsModel productsModel=new ProductsModel();
			productsModel.imageLinkUrl="";
			productsModel.title="123";
			
		}
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

		myImageListAdapter=new MyImageListAdapter();

		imageList.setAdapter(myImageListAdapter);

		scanBnt=(ImageView)view.findViewById(R.id.scan_bnt_mainFrag);

		scanBnt.setOnClickListener(this);

	}

	class MyImageListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return listProducts.size();
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
		public View getView(int position, View contentView, ViewGroup arg2) {

			MyViewHolder myViewHolder=new MyViewHolder();

			if(contentView==null){

				contentView=LayoutInflater.from(mContext).inflate(R.layout.item_list_main_fragment,null);

				myViewHolder.leftTex=(TextView)contentView.findViewById(R.id.item_tex_left_main_frag);

				myViewHolder.leftImage=(ImageView)contentView.findViewById(R.id.item_img_left_main_frag);

				myViewHolder.rightTex=(TextView)contentView.findViewById(R.id.item_tex_right_main_frag);

				myViewHolder.rightImage=(ImageView)contentView.findViewById(R.id.item_img_right_main_frag);
				
				myViewHolder.leftTex.setText(listProducts.get(position*2-1).title);
				
				myViewHolder.rightTex.setText(listProducts.get(position*2).title);
				
				final ImageView newLeftImage=myViewHolder.leftImage;
				
				final ImageView newRightImage=myViewHolder.rightImage;
				
				asyncImageLoader.DrawableloadImage(listProducts.get(position*2-1).imageLinkUrl,IMAGE_DOWNLOAD_PATH,new ImageCallback() {

					@Override
					public void imageLoaded(Bitmap bitmap) {
						newLeftImage.setImageBitmap(bitmap);
					}
				});
				
				asyncImageLoader.DrawableloadImage(listProducts.get(position*2).imageLinkUrl,IMAGE_DOWNLOAD_PATH,new ImageCallback() {

					@Override
					public void imageLoaded(Bitmap bitmap) {
						newRightImage.setImageBitmap(bitmap);
					}
				});				

			}else{
				myViewHolder=(MyViewHolder)contentView.getTag();
			}
			
			return contentView;
		}

	}

	class MyViewHolder{

		ImageView leftImage;
		TextView leftTex;

		ImageView rightImage;
		TextView rightTex;
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
