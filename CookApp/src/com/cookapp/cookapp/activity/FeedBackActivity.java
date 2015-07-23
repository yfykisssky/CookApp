package com.cookapp.cookapp.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cookapp.cookapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class FeedBackActivity extends Activity implements OnClickListener{

	ListView feedBacList;

	EditText messageEdit;

	Button commitBnt;
	
	List<Map<String,String>> messageList=new ArrayList<Map<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
	}

	void initView(){

		feedBacList=(ListView)findViewById(R.id.feedback_list_feedActi);

		messageEdit=(EditText)findViewById(R.id.message_edit_feedActi);

		commitBnt=(Button)findViewById(R.id.commit_bnt_feedActi);

		commitBnt.setOnClickListener(this);

	}


	class MyMessageList extends BaseAdapter{

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
		public View getView(int position, View contentView, ViewGroup arg2) {
			return null;
		}

	}


	@Override
	public void onClick(View id) {
		switch(id.getId()){
		case R.id.commit_bnt_feedActi:
			break;
		}

	}
}
