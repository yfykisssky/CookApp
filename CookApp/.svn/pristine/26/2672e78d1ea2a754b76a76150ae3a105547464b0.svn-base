package com.cookapp.cookapp.activity;

import com.cookapp.cookapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MessageListActivity extends Activity {

	ListView msgList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagelist);
		
	}

	void initView(){
		
		msgList=(ListView)findViewById(R.id.msglist_list_msglist);
		
		MyMsgListAdapter myMsgListAdapter=new MyMsgListAdapter();
		
		msgList.setAdapter(myMsgListAdapter);
		
	}
	
	
	class MyMsgListAdapter extends BaseAdapter{

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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
		
	}
}
