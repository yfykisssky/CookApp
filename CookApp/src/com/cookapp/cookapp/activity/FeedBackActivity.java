package com.cookapp.cookapp.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cookapp.cookapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FeedBackActivity extends Activity implements OnClickListener{

	PullToRefreshListView pullToRefreshListView;
	
	ListView feedBacList;

	EditText messageEdit;

	Button commitBnt;

	List<Map<String,String>> messageList=new ArrayList<Map<String,String>>();

	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		mContext=this;

		initView();

	}

	void initView(){

		pullToRefreshListView=(PullToRefreshListView)findViewById(R.id.feedback_list_feedActi);
		
		pullToRefreshListView.setMode(Mode.PULL_FROM_START);
		
		pullToRefreshListView.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				
			}
			
		});
		
		feedBacList=pullToRefreshListView.getRefreshableView();
		
		MyMessageAdapter myMessageAdapter=new MyMessageAdapter();
		
		feedBacList.setAdapter(myMessageAdapter);

		messageEdit=(EditText)findViewById(R.id.message_edit_feedActi);

		commitBnt=(Button)findViewById(R.id.commit_bnt_feedActi);

		commitBnt.setOnClickListener(this);

	}


	class MyMessageAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return messageList.size();
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
				
				if(messageList.get(position).containsKey("")){
					
					contentView=RelativeLayout.inflate(mContext,R.layout.item_message_feedback_service,null);

					myViewHolder.imageView=(ImageView)contentView.findViewById(R.id.item_image_service_feedBackActi);

					myViewHolder.texView=(TextView)contentView.findViewById(R.id.item_tex_service_feedBackActi);
					
				}else{
					
					contentView=RelativeLayout.inflate(mContext,R.layout.item_message_feedback_user,null);

					myViewHolder.imageView=(ImageView)contentView.findViewById(R.id.item_image_user_feedBackActi);

					myViewHolder.texView=(TextView)contentView.findViewById(R.id.item_tex_user_feedBackActi);
					
				}
				
				myViewHolder.imageView.setImageBitmap(null);
				
				myViewHolder.texView.setText(messageList.get(position).get(""));

				contentView.setTag(myViewHolder);

			}else{
				myViewHolder=(MyViewHolder)contentView.getTag();
			}

			return contentView;
		}

	}

	class MyViewHolder{
		ImageView imageView;
		TextView texView;
	}


	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.commit_bnt_feedActi:
			break;
		}

	}
}
