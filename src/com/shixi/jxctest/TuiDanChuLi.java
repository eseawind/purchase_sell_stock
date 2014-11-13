package com.shixi.jxctest;

import com.shixi.service.GoodsService;
import com.shixi.service.SailService;
import com.shixi.service.SailServiceRegister;
import com.shixi.service.StorageService;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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


public  class TuiDanChuLi extends Activity implements View.OnClickListener{
	

	private Intent intent_get;	
	private String code;
	private String tosail;
	private String name;
	private String area ;
	private String producter;
	private String date ;
	private String quality;
	private String price;
	private String purchase_quantity;

	
	private Bundle bundle_chuandi;
	
	private TextView textView_tuidanchuli_shangpinbianhao;
	private TextView textView_tuidanchuli_shangpinming;
	private TextView textView_tuidanchuli_baozhiqi;
	private TextView textView_tuidanchuli_chandi;
	private TextView textView_tuidanchuli_shengchanshang;
	private TextView textView_tuidanchuli_xiaoshoujiage;
	private TextView textView_tuidanchuli_shengchanriqi;
	private TextView textView_tuidanchuli_goumaishuliang;
	
	
	private GoodsService  goodsService;
	private StorageService storageService;
	private SailServiceRegister sailService_register; 
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.tuidanchuli_show);
		
		Button button1 = (Button)findViewById(R.id.button_tuidanchuli_cancel); 
  	    Button button2 = (Button)findViewById(R.id.button_tuidanchuli_delete); 
		

		button1.setOnClickListener(TuiDanChuLi.this);  
	        button1.setTag(1);  
	    button2.setOnClickListener(TuiDanChuLi.this);  
	        button2.setTag(2); 

	        
	        goodsService= new GoodsService(this);
	        storageService= new StorageService(this);
	        sailService_register = new SailServiceRegister(this);
	        
	        intent_get = this.getIntent();
			bundle_chuandi = intent_get.getExtras();
			code = bundle_chuandi.getString("code");
			purchase_quantity= bundle_chuandi.getString("purchase_quantity");
			tosail = bundle_chuandi.getString("tosail");
			
			Cursor	cursor =goodsService.getGoodsCursorScrollData_code(code, 0, 20);
			cursor.moveToFirst();


			 name = cursor.getString(2);
			 area = cursor.getString(cursor.getColumnIndex("area"));
			 producter= cursor.getString(cursor.getColumnIndex("producter"));
			 date = cursor.getString(cursor.getColumnIndex("date"));
			 quality = cursor.getString(cursor.getColumnIndex("quality"));
			 price= cursor.getString(cursor.getColumnIndex("price"));
		
			
		textView_tuidanchuli_shangpinbianhao=(TextView)findViewById(R.id.textView_tuidanchuli_shangpinbianhao);
		textView_tuidanchuli_shangpinming=(TextView)findViewById(R.id.textView_tuidanchuli_shangpinming);
		textView_tuidanchuli_baozhiqi=(TextView)findViewById(R.id.textView_tuidanchuli_baozhiqi);
		textView_tuidanchuli_chandi=(TextView)findViewById(R.id.textView_tuidanchuli_chandi);
		textView_tuidanchuli_shengchanshang=(TextView)findViewById(R.id.textView_tuidanchuli_shengchanshang);
		textView_tuidanchuli_xiaoshoujiage=(TextView)findViewById(R.id.textView_tuidanchuli_xiaoshoujiage);
		textView_tuidanchuli_shengchanriqi=(TextView)findViewById(R.id.textView_tuidanchuli_shengchanriqi);
		textView_tuidanchuli_goumaishuliang=(TextView)findViewById(R.id.textView_tuidanchuli_goumaishuliang);

		
		textView_tuidanchuli_shangpinbianhao.setText(code);
		textView_tuidanchuli_shangpinming.setText(name);
		textView_tuidanchuli_baozhiqi.setText(quality);
		textView_tuidanchuli_chandi.setText(area);
		textView_tuidanchuli_shengchanshang.setText(producter);
		textView_tuidanchuli_xiaoshoujiage.setText(price);
		textView_tuidanchuli_shengchanriqi.setText(date);
		textView_tuidanchuli_goumaishuliang.setText(purchase_quantity);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		 int tag = (Integer) v.getTag();  
	        switch(tag){  
	        case 1:                 //返回响应函数
	        	
	        	back();
	        	break;
	        case 2:                //删除响应函数
	        	
	       	new AlertDialog.Builder(this).setTitle("确认删除吗？") 	    	    
	    	    .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	    	 
	    	        @Override 
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	        // 点击“确认”后的操作 
	    	        	
	    	        	android.database.sqlite.SQLiteDatabase db;
	    				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);	    			
	    			
	    				db.execSQL("delete from sail_register where code=? and tosail=?", new Object[] {code,tosail});
	    				Cursor  cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail), 0, 20);				
	    				if(cursor.moveToFirst()==false)
	    				{
	    					db.execSQL("delete from sail where sailid=?", new Object[] {tosail});
	    				}
	    				//在仓库中加上相应的数量
	    			    cursor =storageService.getCursorScrollData_code(code, 0, 20);   	   
	    	  			cursor.moveToFirst();	   	
	    	  			Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
	    	  			Integer a= Integer.valueOf(purchase_quantity);
	    			   	storage_quantity=storage_quantity+a;
	    	    		storageService.update_storage_quantity(storage_quantity,code);
	    				
	    				
	    				Toast.makeText(getApplicationContext(), "删除成功！",
	        				     Toast.LENGTH_SHORT).show();
	    				back();
	    	        } 
	    	    }) 
	    	    .setNegativeButton("返回", new DialogInterface.OnClickListener() { 
	    	 
	    	        @Override 
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	        // 点击“返回”后的操作,这里不设置没有任何操作 
	    	        } 
	    	    }).show(); 
					
	        	break;
	      
	        default :  
	            break;  
	        }
	}
	
	public void back()
	{
		Intent intent = new Intent(TuiDanChuLi.this, XiaoShouActivity.class); 
		startActivity(intent);
		TuiDanChuLi.this.finish(); 
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
			intent.setClass( TuiDanChuLi.this,XiaoShouActivity.class);	
			startActivity(intent);
			TuiDanChuLi.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
