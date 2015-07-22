package com.cookapp.cookapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.cookapp.cookapp.R;
import com.cookapp.cookapp.model.CompetitiveProductsModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CompetitiveProductsActivity extends Activity {

	ListView listProducts;
	
	List<CompetitiveProductsModel> listComProModels=new ArrayList<CompetitiveProductsModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_competitiveproducts);
		
		initView();
		
	}
	
	void initView(){
		
		listProducts=(ListView)findViewById(R.id.list_competitiveproducts_Acti);
		
		listProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				Intent intent=new Intent(CompetitiveProductsActivity.this,CompetitiveProductsDetialActivity.class);
				intent.putExtra("url",listComProModels.get(position).linkUrl);
			}
		});
		
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return listComProModels.size();
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
				
				contentView=LayoutInflater.from(CompetitiveProductsActivity.this).inflate(R.layout.item_competitiveproducts,null);
				
				myViewHolder.imageProducts=(ImageView)contentView.findViewById(R.id.itemimag_compro_comproActi);
				
				//myViewHolder.imageProducts.setImageBitmap(bm);
				
				myViewHolder.texTitle=(TextView)contentView.findViewById(R.id.itemtextitle_compro_comproActi);
				
				myViewHolder.texProducts.setText(listComProModels.get(position).introduce);
				
				myViewHolder.texProducts=(TextView)contentView.findViewById(R.id.itemtexintro_compro_comproActi);
				
				myViewHolder.texProducts.setText(listComProModels.get(position).title);
				
				contentView.setTag(myViewHolder);
				
			}else{
				
				myViewHolder=(MyViewHolder)contentView.getTag();
				
			}
			
			return contentView;
		}
		
	}
	
	class MyViewHolder{
		ImageView imageProducts;
		TextView texProducts;
		TextView texTitle;
	}

}
