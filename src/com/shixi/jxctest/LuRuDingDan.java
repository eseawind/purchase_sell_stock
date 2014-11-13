package com.shixi.jxctest;

import com.shixi.service.GoodsService;
import com.shixi.service.SailService;
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


public  class LuRuDingDan extends Activity implements View.OnClickListener{
	

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
	
	private TextView textView_shangpinxinxi_shangpinbianhao;
	private TextView textView_shangpinxinxi_shangpinming;
	private TextView textView_shangpinxinxi_baozhiqi;
	private TextView textView_shangpinxinxi_chandi;
	private TextView textView_shangpinxinxi_shengchanshang;
	private EditText EditText_shangpinxinxi_xiaoshoujiage;
	private TextView textView_shangpinxinxi_shengchanriqi;
	private TextView textView_shangpinxinxi_goumaishuliang;
	
	
	private GoodsService  goodsService;
	private StorageService storageService;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.lurudingdantanchu);
		
		Button button1 = (Button)findViewById(R.id.button_shangpinxinxi_cancel); 
  	    Button button2 = (Button)findViewById(R.id.button_shangpinxinxi_delete); 
		Button button3 = (Button)findViewById(R.id.button_shangpinxinxi_baocunxiugai); 

		button1.setOnClickListener(LuRuDingDan.this);  
	        button1.setTag(1);  
	    button2.setOnClickListener(LuRuDingDan.this);  
	        button2.setTag(2); 
	    button3.setOnClickListener(LuRuDingDan.this);  
	        button3.setTag(3); 
	        
	        goodsService= new GoodsService(this);
	        storageService= new StorageService(this);
	        
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
		
			
		textView_shangpinxinxi_shangpinbianhao=(TextView)findViewById(R.id.textView_shangpinxinxi_shangpinbianhao);
		textView_shangpinxinxi_shangpinming=(TextView)findViewById(R.id.textView_shangpinxinxi_shangpinming);
		textView_shangpinxinxi_baozhiqi=(TextView)findViewById(R.id.textView_shangpinxinxi_baozhiqi);
		textView_shangpinxinxi_chandi=(TextView)findViewById(R.id.textView_shangpinxinxi_chandi);
		textView_shangpinxinxi_shengchanshang=(TextView)findViewById(R.id.textView_shangpinxinxi_shengchanshang);
		EditText_shangpinxinxi_xiaoshoujiage=(EditText)findViewById(R.id.editText_shangpinxinxi_xiaoshoujiage);
		textView_shangpinxinxi_shengchanriqi=(TextView)findViewById(R.id.textView_shangpinxinxi_shengchanriqi);
		textView_shangpinxinxi_goumaishuliang=(TextView)findViewById(R.id.textView_shangpinxinxi_goumaishuliang);

		
		textView_shangpinxinxi_shangpinbianhao.setText(code);
		textView_shangpinxinxi_shangpinming.setText(name);
		textView_shangpinxinxi_baozhiqi.setText(quality);
		textView_shangpinxinxi_chandi.setText(area);
		textView_shangpinxinxi_shengchanshang.setText(producter);
		EditText_shangpinxinxi_xiaoshoujiage.setText(price);
		textView_shangpinxinxi_shengchanriqi.setText(date);
		textView_shangpinxinxi_goumaishuliang.setText(purchase_quantity);

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
	    			
	    				db.execSQL("delete from sail_register where code=?", new Object[] {code});
	    			
	    				//在仓库中加上相应的数量
	    				Cursor cursor =storageService.getCursorScrollData_code(code, 0, 20);   	   
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
	        case 3:       //保存响应函数
	        	if("".equals(EditText_shangpinxinxi_xiaoshoujiage.getText().toString().trim()))
	          		  Toast.makeText(getApplicationContext(), "销售价格不能为空！",
	       				     Toast.LENGTH_SHORT).show();
	    			else
	    			{
	        	android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update buy_register set product_price=? where goods_id=?",
						new Object[] {EditText_shangpinxinxi_xiaoshoujiage.getText().toString(), code});
				db.execSQL("update goods set price=? where code=?",
						new Object[] {EditText_shangpinxinxi_xiaoshoujiage.getText().toString(), code});
				Toast.makeText(getApplicationContext(), "保存成功！",
   				     Toast.LENGTH_SHORT).show();
				back();
	    			}
	        	break;
	        default :  
	            break;  
	        }
	}
	
	public void back()
	{
		Intent intent = new Intent(LuRuDingDan.this, XiaoShouActivity.class); 
		startActivity(intent);
		LuRuDingDan.this.finish(); 
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
			intent.setClass( LuRuDingDan.this,XiaoShouActivity.class);	
			startActivity(intent);
			LuRuDingDan.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
