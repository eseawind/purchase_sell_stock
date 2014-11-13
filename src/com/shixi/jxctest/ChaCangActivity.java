package com.shixi.jxctest;

import com.shixi.service.SailService;
import com.shixi.service.StorageService;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public  class ChaCangActivity extends Activity {
	

	private Intent intent_get;
	private Integer ID;	
	private String goodsid;
	private String name;
	private String area ;
	private String producter;
	private String date ;
	private String quality;
	private String bid ;
	private String price;
	private Integer storage_quantity; 
	private String operator_name;
	private String storage_time; 
	private Bundle bundle_getcinfo;
	private TextView textView_chacang_tanchu_kucunhao;
	private TextView textView_chacang_tanchu_shangpinbianhao;
	private TextView textView_chacang_tanchu_shangpinming;
	private TextView textView_chacang_tanchu_baozhiqi;
	private TextView textView_chacang_tanchu_chandi;
	private TextView textView_chacang_tanchu_jinhuojiage;
	private TextView textView_chacang_tanchu_shengchanshang;
	private TextView textView_chacang_tanchu_xiaoshoujiage;
	private TextView textView_chacang_tanchu_shengchanriqi;
	private TextView textView_chacang_tanchu_kucunliang; 
	private TextView textView_chacang_tanchu_caozuoyuan;
	private TextView textView_chacang_tanchu_rucangshijian; 
	EditText edit_tuicang_tuicangshu;

	
	SailService sailService ;
	StorageService storageService;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.chacangshow);
	 
	        storageService= new StorageService(this); 
	        
	        
	        intent_get = this.getIntent();
			bundle_getcinfo = intent_get.getExtras();
			ID = bundle_getcinfo.getInt("id");
			goodsid= bundle_getcinfo.getString("goodsid");
			name = bundle_getcinfo.getString("name");
			area = bundle_getcinfo.getString("area");
			producter = bundle_getcinfo.getString("producter");
			date = bundle_getcinfo.getString("date");
			quality = bundle_getcinfo.getString("quality");
			bid = bundle_getcinfo.getString("bid");
			price = bundle_getcinfo.getString("price");
			storage_quantity = bundle_getcinfo.getInt("storage_quantity"); 
			operator_name = bundle_getcinfo.getString("operator_name");
			storage_time = bundle_getcinfo.getString("storage_time");
	        
			
			textView_chacang_tanchu_kucunhao=(TextView)findViewById(R.id.textView_chacang_tanchu_kucunhao);
			textView_chacang_tanchu_shangpinbianhao=(TextView)findViewById(R.id.textView_chacang_tanchu_shangpinbianhao);
			textView_chacang_tanchu_shangpinming=(TextView)findViewById(R.id.textView_chacang_tanchu_shangpinming);
			textView_chacang_tanchu_baozhiqi=(TextView)findViewById(R.id.textView_chacang_tanchu_baozhiqi);
			textView_chacang_tanchu_chandi=(TextView)findViewById(R.id.textView_chacang_tanchu_chandi);
			textView_chacang_tanchu_jinhuojiage=(TextView)findViewById(R.id.textView_chacang_tanchu_jinhuojiage);
			textView_chacang_tanchu_shengchanshang=(TextView)findViewById(R.id.textView_chacang_tanchu_shengchanshang);
			textView_chacang_tanchu_xiaoshoujiage=(TextView)findViewById(R.id.textView_chacang_tanchu_xiaoshoujiage);
			textView_chacang_tanchu_shengchanriqi=(TextView)findViewById(R.id.textView_chacang_tanchu_shengchanriqi);
			textView_chacang_tanchu_kucunliang=(TextView)findViewById(R.id.textView_chacang_tanchu_kucunliang);
		    textView_chacang_tanchu_caozuoyuan=(TextView)findViewById(R.id.textView_chacang_tanchu_cangzuoyuan);
			textView_chacang_tanchu_rucangshijian=(TextView)findViewById(R.id.textView_chacang_tanchu_rucangshijian); 
			
			textView_chacang_tanchu_kucunhao.setText(String.valueOf(ID));
			textView_chacang_tanchu_shangpinbianhao.setText(goodsid);;
			textView_chacang_tanchu_shangpinming.setText(name);
			textView_chacang_tanchu_chandi.setText(area);
			textView_chacang_tanchu_shengchanshang.setText(producter);
			textView_chacang_tanchu_shengchanriqi.setText(date);
			textView_chacang_tanchu_baozhiqi.setText(quality);
			textView_chacang_tanchu_jinhuojiage.setText(bid);
			textView_chacang_tanchu_xiaoshoujiage.setText(price);
			textView_chacang_tanchu_kucunliang.setText(String.valueOf(storage_quantity)); 
			textView_chacang_tanchu_caozuoyuan.setText(operator_name);
			textView_chacang_tanchu_rucangshijian.setText(storage_time);
			
	
	}

	
	
	
	public void back()
	{
		Intent intent = new Intent(ChaCangActivity.this, CangKuActivity.class); 
		startActivity(intent);
		ChaCangActivity.this.finish(); 
	}
	/****************************************************************/	
	  @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.actionbar, menu);
			actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg));
			
			actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
			
			return true;
		}
		
		@Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	        // TODO Auto-generated method stub
			Intent intent = new Intent(); 
			intent.setClass(ChaCangActivity.this,CangKuActivity.class);	
			startActivity(intent);
			ChaCangActivity.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
