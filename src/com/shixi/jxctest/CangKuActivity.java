package com.shixi.jxctest;
import com.shixi.autocompleteadater.AutoCompleteAdater_code;
import com.shixi.autocompleteadater.AutoCompleteAdater_tuicang;
import com.shixi.jxctest.R;
import com.shixi.service.DBOpenHelper;
import com.shixi.service.StorageService;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CangKuActivity extends Activity implements View.OnClickListener{

	/**************退仓控件声明*************************/
	private AutoCompleteTextView  edit_tuicang_shangpinbianhao;
	private ListView listView_cangku_tuicang;
	/**************查询控件声明*************************/
	private EditText edit_chaxun_kucunhao;
	private EditText edit_chaxun_shangpinming;
	private EditText edit_chaxun_caozuoyuan;	
	private EditText edit_chaxun_jincangshijian;
	private ListView listView_cangku_chaxun;

	/**************低保质期控件声明*************************/
	
	private ListView listView_cangku_dibaozhiqi;
	
	
	/**************低仓控件声明****************************/
	private ListView listView_cangku_dicang;
	/**************系统变量声明****************************/
	private Integer ID;
	public static int  tabshow_flag=0;
	private DBOpenHelper dbOpenHelper;
	ActionBar actionBar;
	StorageService storageService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/		
		 setContentView(R.layout.cangku);  
	        // 获取TabHost对象  
	        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  
	        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
	        tabHost.setup();  
	        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("货品退仓",  
	                getResources().getDrawable(R.drawable.banzi)).setContent(  
	                R.id.cangku_view1));  
	        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("仓库查询")  
	                .setContent(R.id.cangku_view3));  
	        
	        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("低保质期")  
	                .setContent(R.id.cangku_view2));  
	  	       
	        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("低仓显示")  
	                .setContent(R.id.cangku_view4));  
	        	        
	        
	        edit_tuicang_shangpinbianhao= (AutoCompleteTextView)findViewById(R.id.edit_tuicang_shangpinbianma);
	    	edit_chaxun_kucunhao= (EditText)findViewById(R.id.edit_chacang_kucunhao);
	    	edit_chaxun_shangpinming= (EditText)findViewById(R.id.edit_chacang_shangpinming);
	    	edit_chaxun_caozuoyuan= (EditText)findViewById(R.id.edit_chacang_caozuoyuan);
	    	edit_chaxun_jincangshijian= (EditText)findViewById(R.id.edit_chacang_time);
	    	listView_cangku_tuicang= (ListView)findViewById(R.id.listView_tuicang);
	    	listView_cangku_chaxun= (ListView)findViewById(R.id.listView_chacang);
	    	listView_cangku_dibaozhiqi= (ListView)findViewById(R.id.listView_dibaozhiqi);
	    	listView_cangku_dicang= (ListView)findViewById(R.id.listView_dicang);
	    	
	    	
	        
	    	Button button1 = (Button)findViewById(R.id.button_tuicang_ok);  
	        Button button2 = (Button)findViewById(R.id.button_chacang_ok);  
	        Button button3 = (Button)findViewById(R.id.button_dibaozhiqi_shuaxin);  
	        Button button4 = (Button)findViewById(R.id.button_dicang_shuaxin);  
	           	
	
	        button1.setOnClickListener(this);  
            button1.setTag(1);  
            button2.setOnClickListener(this);  
	        button2.setTag(2);  
	        button3.setOnClickListener(this);  
	        button3.setTag(3);  
	        button4.setOnClickListener(this);  
	        button4.setTag(4);
	      
	        storageService= new StorageService(this);
	        /********************************************************************/	      
	        listView_cangku_tuicang.setOnItemClickListener(new OnItemClickListener()
	    	{

	    		@Override
	    		public void onItemClick(AdapterView<?> parent, View view,
	    				int position, long id) {
	    			// TODO Auto-generated method stub
	    			Bundle bundle_chuandi = new Bundle();
	             	ListView lView = (ListView)parent;
	    			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
	    			
	    			 ID = cursor.getInt(cursor.getColumnIndex("_id"));	
	    			 String goodsid= cursor.getString(cursor.getColumnIndex("goodsid"));
	    			 String name= cursor.getString(cursor.getColumnIndex("name"));
	    			 String area = cursor.getString(cursor.getColumnIndex("area"));
	    			 String producter= cursor.getString(cursor.getColumnIndex("producter"));
	    			 String date = cursor.getString(cursor.getColumnIndex("date"));
	    			 String quality = cursor.getString(cursor.getColumnIndex("quality"));
	    			 String bid = cursor.getString(cursor.getColumnIndex("bid"));
	    			 String price= cursor.getString(cursor.getColumnIndex("price"));
	    			 Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity"));	
	    			 String operator_name= cursor.getString(cursor.getColumnIndex("operator_name"));
	    			 String storage_time= cursor.getString(cursor.getColumnIndex("storage_time")); 
	    							    
	    		   	
	    			  
	    			bundle_chuandi.putInt("id", ID);
	    			bundle_chuandi.putString("goodsid", goodsid);
	    			bundle_chuandi.putString("name", name);
	    			bundle_chuandi.putString("area", area);
	    			bundle_chuandi.putString("producter", producter);
	    			bundle_chuandi.putString("date", date);
	    			bundle_chuandi.putString("quality", quality);
	    			bundle_chuandi.putString("bid", bid);
	    			bundle_chuandi.putString("price", price);
	    			bundle_chuandi.putInt("storage_quantity", storage_quantity);
	    			bundle_chuandi.putString("operator_name", operator_name);
	    			bundle_chuandi.putString("storage_time", storage_time);
	    			
	    			Intent intent = new Intent(); 
	    			intent.putExtras(bundle_chuandi);
	    			intent.setClass(CangKuActivity.this, TuiCangActivity.class);
	    			tabshow_flag=0;
	    			startActivity(intent);
	    			CangKuActivity.this.finish();
	    		
	    			
	    		}
	    	});
	        /********************************************************************/	      
	        listView_cangku_chaxun.setOnItemClickListener(new OnItemClickListener()
	    	{

	    		@Override
	    		public void onItemClick(AdapterView<?> parent, View view,
	    				int position, long id) {
	    			// TODO Auto-generated method stub
	    			Bundle bundle_chuandi = new Bundle();
	             	ListView lView = (ListView)parent;
	    			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
	    			
	    			 ID = cursor.getInt(cursor.getColumnIndex("_id"));	
	    			 String goodsid= cursor.getString(cursor.getColumnIndex("goodsid"));
	    			 String name= cursor.getString(cursor.getColumnIndex("name"));
	    			 String area = cursor.getString(cursor.getColumnIndex("area"));
	    			 String producter= cursor.getString(cursor.getColumnIndex("producter"));
	    			 String date = cursor.getString(cursor.getColumnIndex("date"));
	    			 String quality = cursor.getString(cursor.getColumnIndex("quality"));
	    			 String bid = cursor.getString(cursor.getColumnIndex("bid"));
	    			 String price= cursor.getString(cursor.getColumnIndex("price"));
	    			 Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity"));	
	    			 String operator_name= cursor.getString(cursor.getColumnIndex("operator_name"));
	    			 String storage_time= cursor.getString(cursor.getColumnIndex("storage_time")); 
	    							    
	    		   	
	    			  
	    			bundle_chuandi.putInt("id", ID);
	    			bundle_chuandi.putString("goodsid", goodsid);
	    			bundle_chuandi.putString("name", name);
	    			bundle_chuandi.putString("area", area);
	    			bundle_chuandi.putString("producter", producter);
	    			bundle_chuandi.putString("date", date);
	    			bundle_chuandi.putString("quality", quality);
	    			bundle_chuandi.putString("bid", bid);
	    			bundle_chuandi.putString("price", price);
	    			bundle_chuandi.putInt("storage_quantity", storage_quantity);
	    			bundle_chuandi.putString("operator_name", operator_name);
	    			bundle_chuandi.putString("storage_time", storage_time);
	    			
	    			Intent intent = new Intent(); 
	    			intent.putExtras(bundle_chuandi);
	    			intent.setClass(CangKuActivity.this, ChaCangActivity.class);
	    			tabshow_flag=1;
	    			startActivity(intent);
	    			CangKuActivity.this.finish();
	    		
	    			
	    		}
	    	});
/********************************************************************/
	        listView_cangku_dibaozhiqi.setOnItemClickListener(new OnItemClickListener()
	    	{

	    		@Override
	    		public void onItemClick(AdapterView<?> parent, View view,
	    				int position, long id) {
	    			// TODO Auto-generated method stub
	    			Bundle bundle_chuandi = new Bundle();
	             	ListView lView = (ListView)parent;
	    			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
	    			
	    			 ID = cursor.getInt(cursor.getColumnIndex("_id"));	
	    			 String goodsid= cursor.getString(cursor.getColumnIndex("goodsid"));
	    			 String name= cursor.getString(cursor.getColumnIndex("name"));
	    			 String area = cursor.getString(cursor.getColumnIndex("area"));
	    			 String producter= cursor.getString(cursor.getColumnIndex("producter"));
	    			 String date = cursor.getString(cursor.getColumnIndex("date"));
	    			 String quality = cursor.getString(cursor.getColumnIndex("quality"));
	    			 String bid = cursor.getString(cursor.getColumnIndex("bid"));
	    			 String price= cursor.getString(cursor.getColumnIndex("price"));
	    			 Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity"));	
	    			 String operator_name= cursor.getString(cursor.getColumnIndex("operator_name"));
	    			 String storage_time= cursor.getString(cursor.getColumnIndex("storage_time")); 
	    							    
	    		   	
	    			  
	    			bundle_chuandi.putInt("id", ID);
	    			bundle_chuandi.putString("goodsid", goodsid);
	    			bundle_chuandi.putString("name", name);
	    			bundle_chuandi.putString("area", area);
	    			bundle_chuandi.putString("producter", producter);
	    			bundle_chuandi.putString("date", date);
	    			bundle_chuandi.putString("quality", quality);
	    			bundle_chuandi.putString("bid", bid);
	    			bundle_chuandi.putString("price", price);
	    			bundle_chuandi.putInt("storage_quantity", storage_quantity);
	    			bundle_chuandi.putString("operator_name", operator_name);
	    			bundle_chuandi.putString("storage_time", storage_time);
	    			
	    			Intent intent = new Intent(); 
	    			intent.putExtras(bundle_chuandi);
	    			intent.setClass(CangKuActivity.this, ChaCangActivity.class);
	    			tabshow_flag=1;
	    			startActivity(intent);
	    			CangKuActivity.this.finish();
	    		
	    			
	    		}
	    	});     
	        
	        
/********************************************************************/
	        listView_cangku_dicang.setOnItemClickListener(new OnItemClickListener()
	    	{

	    		@Override
	    		public void onItemClick(AdapterView<?> parent, View view,
	    				int position, long id) {
	    			// TODO Auto-generated method stub
	    			Bundle bundle_chuandi = new Bundle();
	             	ListView lView = (ListView)parent;
	    			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
	    			
	    			 ID = cursor.getInt(cursor.getColumnIndex("_id"));	
	    			 String goodsid= cursor.getString(cursor.getColumnIndex("goodsid"));
	    			 String name= cursor.getString(cursor.getColumnIndex("name"));
	    			 String area = cursor.getString(cursor.getColumnIndex("area"));
	    			 String producter= cursor.getString(cursor.getColumnIndex("producter"));
	    			 String date = cursor.getString(cursor.getColumnIndex("date"));
	    			 String quality = cursor.getString(cursor.getColumnIndex("quality"));
	    			 String bid = cursor.getString(cursor.getColumnIndex("bid"));
	    			 String price= cursor.getString(cursor.getColumnIndex("price"));
	    			 Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity"));	
	    			 String operator_name= cursor.getString(cursor.getColumnIndex("operator_name"));
	    			 String storage_time= cursor.getString(cursor.getColumnIndex("storage_time")); 
	    							    
	    		   	
	    			  
	    			bundle_chuandi.putInt("id", ID);
	    			bundle_chuandi.putString("goodsid", goodsid);
	    			bundle_chuandi.putString("name", name);
	    			bundle_chuandi.putString("area", area);
	    			bundle_chuandi.putString("producter", producter);
	    			bundle_chuandi.putString("date", date);
	    			bundle_chuandi.putString("quality", quality);
	    			bundle_chuandi.putString("bid", bid);
	    			bundle_chuandi.putString("price", price);
	    			bundle_chuandi.putInt("storage_quantity", storage_quantity);
	    			bundle_chuandi.putString("operator_name", operator_name);
	    			bundle_chuandi.putString("storage_time", storage_time);
	    			
	    			Intent intent = new Intent(); 
	    			intent.putExtras(bundle_chuandi);
	    			intent.setClass(CangKuActivity.this, ChaCangActivity.class);
	    			tabshow_flag=1;
	    			startActivity(intent);
	    			CangKuActivity.this.finish();
	    		
	    			
	    		}
	    	});     
	        
/*****************初始化操作***************************************************/			
		 		    
			
			switch(tabshow_flag)
			{
			case 0:
				tabHost.setCurrentTab(0);
			    break;
			case 1:
				tabHost.setCurrentTab(1);
							
				break;
			case 2:
				tabHost.setCurrentTab(2);
				
				break;
			case 3:
				
				tabHost.setCurrentTab(3);
				
				break;
			
			default:break;
			
			}
			
			AutoCompleteAdater_tuicang cursorAdapter = new AutoCompleteAdater_tuicang(this, android.R.layout.simple_dropdown_item_1line, null, "goodsid", android.R.id.text1,1);
			edit_tuicang_shangpinbianhao.setThreshold(1);
			edit_tuicang_shangpinbianhao.setAdapter(cursorAdapter);   
/********************************************************************/	    	
	}
	
	
    public void onClick(View v){  
    	 
        int tag = (Integer) v.getTag();  
        switch(tag){  
        case 1: 
        	if("".equals(edit_tuicang_shangpinbianhao.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "输入不能为空！",
   				     Toast.LENGTH_SHORT).show();
      	  else
        	show_cangku_tuicang();	
        	
        	
            break;  
        case 2:  
        	 show_cangku_chaxun();
       
            break;  
        case 3:
        	
        	show_cangku_dibaozhiqi();
        	 
            break;  
        case 4:  
        	
        	show_cangku_dicang();
        	
            break;  
         
            
        default :  
            break;  
        }  
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
          if(item.getItemId() == android.R.id.home)
          {
              finish();
              return true;
          }
          return super.onOptionsItemSelected(item);
      }

/****************************************************************/	
  	 public void show_cangku_chaxun()
  	 {
  		 Cursor cursor = storageService.getCursorScrollData_chaxun(edit_chaxun_kucunhao.getText().toString(),
  				 edit_chaxun_shangpinming.getText().toString(),edit_chaxun_caozuoyuan.getText().toString(),
  				 edit_chaxun_jincangshijian.getText().toString(), 0, 20);
  		 if(cursor.moveToFirst() == false)                 //当cursor为空时
  	   {
  		   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.cangkushow, null,
  				 new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
  				 new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian, 
  				   R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
   	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
  		    listView_cangku_chaxun.setAdapter(adapter); 
  		   Toast.makeText(getApplicationContext(), "不存在订单",
  				     Toast.LENGTH_SHORT).show();
  	   }
  	 
  	   else
  	   {
  	        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.cangkushow, cursor,
  	    		new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
  	    		new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian,
  	        		R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
  	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
  	      listView_cangku_chaxun.setAdapter(adapter); 
  	          
  	   }
  	  
  	 }
/****************************************************************/
  	public void	show_cangku_tuicang()
  	{
  		Cursor cursor = storageService.getCursorScrollData_code(edit_tuicang_shangpinbianhao.getText().toString(),0,20);
  		 if(cursor.moveToFirst() == false)                                //当cursor为空时
  		 {
			   Toast.makeText(getApplicationContext(), "仓库不存在该商品！",
					     Toast.LENGTH_SHORT).show();
			   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.tuicangshow, null,
		  	    		new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
		  	    		new int[] {R.id.cangku_tuicang_kucunhao, R.id.cangku_tuicang_shangpinming,
		  				R.id.cangku_tuicang_shangqian, R.id.cangku_tuicang_caozuoyuan, R.id.cangku_tuicang_tradetime}, 
		  	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		  		
		  		listView_cangku_tuicang.setAdapter(adapter); 
  		 }
	    else
	    {
  		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.tuicangshow, cursor,
  	    		new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
  	    		new int[] {R.id.cangku_tuicang_kucunhao, R.id.cangku_tuicang_shangpinming,
  				R.id.cangku_tuicang_shangqian, R.id.cangku_tuicang_caozuoyuan, R.id.cangku_tuicang_tradetime}, 
  	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
  		
  		listView_cangku_tuicang.setAdapter(adapter); 
	    }
  	}
 /****************************************************************/
  	public void	show_cangku_dicang()
  	{
  		Cursor cursor = storageService.getCursorScrollData_dicang(0,20);
  		 if(cursor.moveToFirst() == false)                                //当cursor为空时
  		 {
			   Toast.makeText(getApplicationContext(), "仓库无低仓商品！",
					     Toast.LENGTH_SHORT).show();
  		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.dicang, null,
  	    		new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
  	    		new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian, 
  				R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
  	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
  		listView_cangku_dicang.setAdapter(adapter); 
  	}
	    else
	    {
  		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.dicang, cursor,
  	    		new String[] {"_id","name", "storage_quantity","operator_name", "storage_time"}, 
  	    		new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian, 
  				R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
  	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
  		listView_cangku_dicang.setAdapter(adapter); 
	    }
  	}
/****************************************************************/
  public void	show_cangku_dibaozhiqi()
  {
	  Cursor cursor = storageService.getCursorScrollData_dibaozhiqi(0,20);
	  if(cursor.moveToFirst() == false)                                //当cursor为空时
	  {
		   Toast.makeText(getApplicationContext(), "无低保质期商品！",
				     Toast.LENGTH_SHORT).show();
	  SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.dicang, null,
	    		new String[] {"_id","name", "storage_quantity","date", "quality"}, 
	    		new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian, 
				R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  
	  listView_cangku_dibaozhiqi.setAdapter(adapter); 
	  }
   else
   {
	  SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.dicang, cursor,
	    		new String[] {"_id","name", "storage_quantity","date", "quality"}, 
	    		new int[] {R.id.cangku_show_kucunhao, R.id.cangku_show_shangpinming, R.id.cangku_show_shangqian, 
				R.id.cangku_show_caozuoyuan, R.id.cangku_show_tradetime}, 
	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  
	  listView_cangku_dibaozhiqi.setAdapter(adapter); 
   }
  }
/****************************************************************/
}
