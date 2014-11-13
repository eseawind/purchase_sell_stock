package com.shixi.jxctest;

import com.shixi.service.BuyServiceRegister;
import com.shixi.service.GoodsService;
import com.shixi.service.SailService;

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


public  class TuiHuoChuLi extends Activity implements View.OnClickListener{
	

	private Intent intent_get;	
	private String code;
	private String name;
	private String area ;
	private String producter;
	private String date ;
	private String quality;
	private String price;
	private String purchase_quantity;
	private String tobuy;
	
	private Bundle bundle_chuandi;
	
	private TextView textView_tuihuochuli_shangpinbianhao;
	private TextView textView_tuihuochuli_shangpinming;
	private TextView textView_tuihuochuli_baozhiqi;
	private TextView textView_tuihuochuli_chandi;
	private TextView textView_tuihuochuli_shengchanshang;
	private TextView textView_tuihuochuli_xiaoshoujiage;
	private TextView textView_tuihuochuli_shengchanriqi;
	private TextView textView_tuihuochuli_goumaishuliang;
	
	
	GoodsService  goodsService;
	SailService sailService ;
	BuyServiceRegister buyService_register;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.tuihuochuli_show);
		
		Button button1 = (Button)findViewById(R.id.button_tuihuochuli_cancel); 
  	    Button button2 = (Button)findViewById(R.id.button_tuihuochuli_delete); 

		button1.setOnClickListener(TuiHuoChuLi.this);  
	        button1.setTag(1);  
	    button2.setOnClickListener(TuiHuoChuLi.this);  
	        button2.setTag(2); 
	   
	        
	        goodsService= new GoodsService(this);
	        buyService_register = new BuyServiceRegister(this);
	        
	        intent_get = this.getIntent();
			bundle_chuandi = intent_get.getExtras();
			code = bundle_chuandi.getString("code");
			tobuy = bundle_chuandi.getString("tobuy");
			purchase_quantity= bundle_chuandi.getString("purchase_quantity");
			
			Cursor	cursor =goodsService.getGoodsCursorScrollData_code(code, 0, 20);
			cursor.moveToFirst();


			 name = cursor.getString(2);
			 area = cursor.getString(cursor.getColumnIndex("area"));
			 producter= cursor.getString(cursor.getColumnIndex("producter"));
			 date = cursor.getString(cursor.getColumnIndex("date"));
			 quality = cursor.getString(cursor.getColumnIndex("quality"));
			 price= cursor.getString(cursor.getColumnIndex("price"));
		
			
		textView_tuihuochuli_shangpinbianhao=(TextView)findViewById(R.id.textView_tuihuochuli_shangpinbianhao);
		textView_tuihuochuli_shangpinming=(TextView)findViewById(R.id.textView_tuihuochuli_shangpinming);
		textView_tuihuochuli_baozhiqi=(TextView)findViewById(R.id.textView_tuihuochuli_baozhiqi);
		textView_tuihuochuli_chandi=(TextView)findViewById(R.id.textView_tuihuochuli_chandi);
		textView_tuihuochuli_shengchanshang=(TextView)findViewById(R.id.textView_tuihuochuli_shengchanshang);
		textView_tuihuochuli_xiaoshoujiage=(TextView)findViewById(R.id.textView_tuihuochuli_xiaoshoujiage);
		textView_tuihuochuli_shengchanriqi=(TextView)findViewById(R.id.textView_tuihuochuli_shengchanriqi);
		textView_tuihuochuli_goumaishuliang=(TextView)findViewById(R.id.textView_tuihuochuli_goumaishuliang);

		
		textView_tuihuochuli_shangpinbianhao.setText(code);
		textView_tuihuochuli_shangpinming.setText(name);
		textView_tuihuochuli_baozhiqi.setText(quality);
		textView_tuihuochuli_chandi.setText(area);
		textView_tuihuochuli_shengchanshang.setText(producter);
		textView_tuihuochuli_xiaoshoujiage.setText(price);
		textView_tuihuochuli_shengchanriqi.setText(date);
		textView_tuihuochuli_goumaishuliang.setText(purchase_quantity);
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
	    			
	    				db.execSQL("delete from buy_register where goods_id=? and tobuy=?", new Object[] {code,tobuy});
	    				Cursor  cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy), 0, 20);				
	    				if(cursor.moveToFirst()==false)
	    				{
	    					db.execSQL("delete from buy where buyid=?", new Object[] {tobuy});
	    				}
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
		Intent intent = new Intent(TuiHuoChuLi.this, CaiGouActivity.class); 
		startActivity(intent);
		TuiHuoChuLi.this.finish(); 
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
			intent.setClass( TuiHuoChuLi.this,CaiGouActivity.class);	
			startActivity(intent);
			TuiHuoChuLi.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
