/**
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

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


public  class LuRuHuoDan extends Activity implements View.OnClickListener{
	

	private Intent intent_get;	
	private String code;
	private String tobuy;
	private String name;
	private String area ;
	private String producter;
	private String date ;
	private String quality;
	private String price;


	
	private Bundle bundle_chuandi;
	
	private TextView textView_luruhuodantanchu_shangpinbianhao;
	private TextView textView_luruhuodantanchu_shangpinming;
	private TextView textView_luruhuodantanchu_baozhiqi;
	private TextView textView_luruhuodantanchu_chandi;
	private TextView textView_luruhuodantanchu_shengchanshang;
	private EditText editText_luruhuodantanchu_jinhuojiage;
	private TextView textView_luruhuodantanchu_shengchanriqi;

	
	
	GoodsService  goodsService;
	SailService sailService ;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.luruhuodantanchu);
		
		Button button1 = (Button)findViewById(R.id.button_luruhuodantanchu_cancel); 
  	    Button button2 = (Button)findViewById(R.id.button_luruhuodantanchu_delete); 
		Button button3 = (Button)findViewById(R.id.button_luruhuodantanchu_baocunxiugai); 

		button1.setOnClickListener(LuRuHuoDan.this);  
	        button1.setTag(1);  
	    button2.setOnClickListener(LuRuHuoDan.this);  
	        button2.setTag(2); 
	    button3.setOnClickListener(LuRuHuoDan.this);  
	        button3.setTag(3); 
	        
	        goodsService= new GoodsService(this);
	        
	        intent_get = this.getIntent();
			bundle_chuandi = intent_get.getExtras();
			code = bundle_chuandi.getString("code");
			tobuy = bundle_chuandi.getString("tobuy");
			
			Cursor	cursor =goodsService.getGoodsCursorScrollData_code(code, 0, 20);
			cursor.moveToFirst();


			 name = cursor.getString(2);
			 area = cursor.getString(cursor.getColumnIndex("area"));
			 producter= cursor.getString(cursor.getColumnIndex("producter"));
			 date = cursor.getString(cursor.getColumnIndex("date"));
			 quality = cursor.getString(cursor.getColumnIndex("quality"));
			 price= cursor.getString(cursor.getColumnIndex("price"));
		
			
		textView_luruhuodantanchu_shangpinbianhao=(TextView)findViewById(R.id.textView_luruhuodantanchu_shangpinbianhao);
		textView_luruhuodantanchu_shangpinming=(TextView)findViewById(R.id.textView_luruhuodantanchu_shangpinming);
		textView_luruhuodantanchu_baozhiqi=(TextView)findViewById(R.id.textView_luruhuodantanchu_baozhiqi);
		textView_luruhuodantanchu_chandi=(TextView)findViewById(R.id.textView_luruhuodantanchu_chandi);
		textView_luruhuodantanchu_shengchanshang=(TextView)findViewById(R.id.textView_luruhuodantanchu_shengchanshang);
		editText_luruhuodantanchu_jinhuojiage=(EditText)findViewById(R.id.editText_luruhuodantanchu_jinhuojiage);
		textView_luruhuodantanchu_shengchanriqi=(TextView)findViewById(R.id.textView_luruhuodantanchu_shengchanriqi);
		

		
		textView_luruhuodantanchu_shangpinbianhao.setText(code);
		textView_luruhuodantanchu_shangpinming.setText(name);
		textView_luruhuodantanchu_baozhiqi.setText(quality);
		textView_luruhuodantanchu_chandi.setText(area);
		textView_luruhuodantanchu_shengchanshang.setText(producter);
		editText_luruhuodantanchu_jinhuojiage.setText(price);
		textView_luruhuodantanchu_shengchanriqi.setText(date);

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
	    			
	    				db.execSQL("delete from buy_register where goods_id=?", new Object[] {code});
	    			
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
	        	if("".equals(editText_luruhuodantanchu_jinhuojiage.getText().toString().trim()))
	          		  Toast.makeText(getApplicationContext(), "销售价格不能为空！",
	       				     Toast.LENGTH_SHORT).show();
	    			else
	    			{
	        	android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update buy_register set product_price=? where goods_id=?",
						new Object[] {editText_luruhuodantanchu_jinhuojiage.getText().toString(), code});
				db.execSQL("update goods set bid=? where code=?",
						new Object[] {editText_luruhuodantanchu_jinhuojiage.getText().toString(), code});
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
		Intent intent = new Intent(LuRuHuoDan.this, CaiGouActivity.class); 
		startActivity(intent);
		LuRuHuoDan.this.finish(); 
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
			intent.setClass( LuRuHuoDan.this,CaiGouActivity.class);	
			startActivity(intent);
			LuRuHuoDan.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
}
