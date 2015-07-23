package com.cookapp.cookapp.view;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.cookapp.cookapp.tools.AsyncImageLoader;
import com.cookapp.cookapp.tools.AsyncImageLoader.ImageCallback;

public class SlideShowView extends ViewPager{

	Context mContext;

	MyAdapter myAdapter;

	MyPageChangeListener myPageChangeListener;
	
	AsyncImageLoader asyncImageLoader=new AsyncImageLoader();

	List<ImageView> imageList=new ArrayList<ImageView>();

	int time=0;

	Handler handler=new Handler();

	Runnable runnable;

	public SlideShowView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext=context;

		myAdapter=new MyAdapter();

		myPageChangeListener=new MyPageChangeListener();

		this.setAdapter(myAdapter);

		this.setOnPageChangeListener(myPageChangeListener);

	}

	public void setImageList(List<String> imagePath,String downLoadPath){

		for(int r=0;r<imagePath.size();r++){
			
			final ImageView imageView=new ImageView(mContext);
			
			try {
				
				asyncImageLoader.loadImage(imagePath.get(r),downLoadPath,new ImageCallback() {
					
					@Override
					public void imageLoaded(Bitmap bitmap) {
						imageView.setImageBitmap(bitmap);
					}
				});
			
				imageList.add(imageView);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.postInvalidate();

	}

	public void setTransTime(final int time){

		this.time=time;

		if(time!=0&&imageList.size()>0){
			runnable=new Runnable() {

				@Override
				public void run() {
					handler.postDelayed(this,time*1000);
				}
			};
		}
	}

	void stopTrans(){
		if(time!=0&&imageList.size()>0){
			handler.removeCallbacks(runnable);
		}
	}

	void setHeightPercent(int x,int y){
		
		LayoutParams params=new LayoutParams();
		params.width=this.getWidth();
		params.height=this.getWidth()*x/y;

		this.setLayoutParams(params);
	}

	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return imageList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}

		@Override  
		public void destroyItem(ViewGroup container, int position, Object object) {  
			container.removeView(imageList.get(position % imageList.size()));  
		}  

		@Override  
		public Object instantiateItem(ViewGroup container, int position) {  
			container.addView(imageList.get(position %imageList.size()));  
			return imageList.get(position % imageList.size());  
		}  

	}

	class MyPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {

		}

	}

}
