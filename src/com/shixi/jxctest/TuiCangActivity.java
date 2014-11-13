package com.shixi.jxctest;

import com.shixi.service.SailService;
import com.shixi.service.StorageService;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public  class TuiCangActivity extends Activity implements View.OnClickListener{
	

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
	private TextView textView_tuicang_kucunhao;
	private TextView textView_tuicang_shangpinbianhao;
	private TextView textView_tuicang_shangpinming;
	private TextView textView_tuicang_baozhiqi;
	private TextView textView_tuicang_chandi;
	private TextView textView_tuicang_jinhuojiage;
	private TextView textView_tuicang_shengchanshang;
	private TextView textView_tuicang_xiaoshoujiage;
	private TextView textView_tuicang_shengchanriqi;
	private TextView textView_tuicang_kucunliang; 

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
		setContentView(R.layout.tuicang);
		
		Button button1 = (Button)findViewById(R.id.button_tuicang_ok); 
  	    Button button2 = (Button)findViewById(R.id.button_tuicang_cancel); 
		

		button1.setOnClickListener(TuiCangActivity.this);  
	        button1.setTag(1);  
	    button2.setOnClickListener(TuiCangActivity.this);  
	        button2.setTag(2); 
	 
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
	        
			
			textView_tuicang_kucunhao=(TextView)findViewById(R.id.textView_tuicang_kucunhao);
			textView_tuicang_shangpinbianhao=(TextView)findViewById(R.id.textView_tuicang_shangpinbianhao);
			textView_tuicang_shangpinming=(TextView)findViewById(R.id.textView_tuicang_shangpinming);
			textView_tuicang_baozhiqi=(TextView)findViewById(R.id.textView_tuicang_baozhiqi);
			textView_tuicang_chandi=(TextView)findViewById(R.id.textView_tuicang_chandi);
			textView_tuicang_jinhuojiage=(TextView)findViewById(R.id.textView_tuicang_jinhuojiage);
			textView_tuicang_shengchanshang=(TextView)findViewById(R.id.textView_tuicang_shengchanshang);
			textView_tuicang_xiaoshoujiage=(TextView)findViewById(R.id.textView_tuicang_xiaoshoujiage);
			textView_tuicang_shengchanriqi=(TextView)findViewById(R.id.textView_tuicang_shengchanriqi);
			textView_tuicang_kucunliang=(TextView)findViewById(R.id.textView_tuicang_kucunliang);
			
			textView_tuicang_kucunhao.setText(String.valueOf(ID));
			textView_tuicang_shangpinbianhao.setText(goodsid);;
			textView_tuicang_shangpinming.setText(name);
			textView_tuicang_chandi.setText(area);
			textView_tuicang_shengchanshang.setText(producter);
			textView_tuicang_shengchanriqi.setText(date);
			textView_tuicang_baozhiqi.setText(quality);
			textView_tuicang_jinhuojiage.setText(bid);
			textView_tuicang_xiaoshoujiage.setText(price);
			textView_tuicang_kucunliang.setText(String.valueOf(storage_quantity)); 
			
			edit_tuicang_tuicangshu = (EditText)findViewById(R.id.editText_tuicang_tuicangshu);
			
	
			
			
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		 int tag = (Integer) v.getTag();  
	        switch(tag){  
	        case 1:                 //退仓响应函数
	        	if("".equals(edit_tuicang_tuicangshu.getText().toString().trim()))
	        	{
	        		Toast.makeText(getApplicationContext(), "退仓商品数不能为空！",
       				     Toast.LENGTH_SHORT).show();
	        		
	        	}
	        	else
	        	{
	        	new AlertDialog.Builder(this).setTitle("确认退仓吗？") 	    	    
	    	    .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	    	 
	    	        @Override 
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	        // 点击“确认”后的操作 
	    	        	
	    	        	int a=Integer.parseInt(edit_tuicang_tuicangshu.getText().toString());
	    	        	
	    	        	if(a==storage_quantity)
	    	        	{
	    	        	android.database.sqlite.SQLiteDatabase db;
	    				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);	    			
	    			
	    				db.execSQL("delete from storage where storageid=?", new Object[] {ID});
	    			
	    				Toast.makeText(getApplicationContext(), "操作成功！",
	        				     Toast.LENGTH_SHORT).show();
	    				back();
	    	        	}
	        	else if(a<=storage_quantity)
	        	{
	        		storage_quantity=storage_quantity-a;
	        		storageService.update_storage_quantity(storage_quantity,goodsid);
	        		Toast.makeText(getApplicationContext(), "操作成功！",
       				     Toast.LENGTH_SHORT).show();
	        		back();
	        	}
	        	else
	        	{
	        		Toast.makeText(getApplicationContext(), "退仓商品数不能大于库存数！",
       				     Toast.LENGTH_SHORT).show();
	        		
	        	}
	    	        }
	    	     
	    	    }) 
	    	    .setNegativeButton("返回", new DialogInterface.OnClickListener() { 
	    	 
	    	        @Override 
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	        // 点击“返回”后的操作,这里不设置没有任何操作 
	    	        } 
	    	    }).show(); 
	        	}
	        	break;
	        case 2:                //删除响应函数
	        	
	       	
	       	back();	
	       	
	        	break;
	        
	        default :  
	            break;  
	        }
	}
	
	public void back()
	{
		Intent intent = new Intent(TuiCangActivity.this, CangKuActivity.class); 
		startActivity(intent);
		TuiCangActivity.this.finish(); 
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
			intent.setClass(TuiCangActivity.this,CangKuActivity.class);	
			startActivity(intent);
			TuiCangActivity.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
