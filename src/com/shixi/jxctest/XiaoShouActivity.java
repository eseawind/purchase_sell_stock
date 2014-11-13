package com.shixi.jxctest;
import java.util.Calendar;

import com.shixi.service.GoodsService;
import com.shixi.service.SailService;
import com.shixi.service.SailServiceRegister;
import com.shixi.service.StorageService;
import com.shixi.autocompleteadater.AutoCompleteAdater_code;
import com.shixi.autocompleteadater.AutoCompleteAdater_tuicang;
import com.shixi.jxctest.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class XiaoShouActivity extends Activity implements View.OnClickListener{



	/**************录入订单控件声明*************************/
	private AutoCompleteTextView  editText_shangpinbianhao;
	private EditText edit_product_name;
	private EditText edit_product_price;
	private EditText edit_product_kucunliang;
	private EditText edit_purchase_quantity;
	private EditText edit_customer_name;
	private EditText edit_customer_pay;
	private EditText edit_caozuoyuan;
	
	private TextView textview_dingdanhao;
	private TextView textview_luru_jine;
	private ListView listView_luru;
	/**************查询控件声明*************************/
	private EditText edit_chaxun_dingdanhao;
	private EditText edit_chaxun_caozuoyuan;
	private EditText edit_chaxun_kehuming;
	private EditText edit_chaxun_jiaoyishijian;
	
	private ListView listView_chaxun;
	/**************退单控件声明*************************/
	private TableLayout view_xinzeng;
	private AutoCompleteTextView  edit_xinzeng_shangpinbianhao;
	private EditText edit_xinzeng_shangpinming;
	private EditText edit_xinzeng_jiage;
	private EditText edit_xinzeng_kuncunliang;
	private EditText edit_xinzeng_goumailiang;
	
	private EditText edit_tuidan_dingdanhao;
	private EditText edit__tuidan_fukuan;
	private TextView textview_tuidan_dingdanhao;
	private TextView textview_tuidan_kehuming;
	private TextView textview_tuidan_jiaoyishijian;
	private TextView textview_tuidan_zongjia;
	private TextView textview_tuidan_qiankuan;
	
	private ListView listView_tuidan;
	/**************欠款控件声明*************************/
	private ListView listView_qiankuan;
	private TextView textview_qiankuan_zonge;
	/**************系统变量声明*************************/
	private Calendar system_time;
	private int result_year;
	private int result_month;
	private int result_day;
	private int result_hour;
	private int result_minute;

	private SailService sailService; 
	private SailServiceRegister sailService_register; 
	private StorageService storageService;
	private GoodsService  goodsService;
	private Integer ID;

	public int i=0;
	public static boolean jine_flag=false;
	public static boolean show_flag=false;
	public static boolean xinzeng_flag=false;
	public static int  tabshow_flag=0;
	public static String tosail_tuidan;
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.sail);  
	        // 获取TabHost对象  
	        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  
	        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
	        tabHost.setup();  
	        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("录入订单",  
	                getResources().getDrawable(R.drawable.banzi)).setContent(  
	                R.id.view1));  
	        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("退单处理")  
	                .setContent(R.id.view2));  
	        
	        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("查询订单")  
	                .setContent(R.id.view3));  
	  	       
	        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("欠款显示")  
	                .setContent(R.id.view4));  
	        	        
	        
	       
	        Button button1 = (Button)findViewById(R.id.button_lurudingdan);  
	        Button button2 = (Button)findViewById(R.id.button_tijiaodingdan);  
	        Button button3 = (Button)findViewById(R.id.button_lurutuidan_ok);  
	        Button button4 = (Button)findViewById(R.id.button_chaxun_ok);  
	        Button button5 = (Button)findViewById(R.id.button_tuidan_shanchudingdan);  
	        Button button6 = (Button)findViewById(R.id.button_tuidan_xiugai);
	        Button button7 = (Button)findViewById(R.id.button_qiankuanxianshi_shuaxin);
	        Button button8 = (Button)findViewById(R.id.button_xiaoshou_tuidan_xinzengshangpin);
	        
	        editText_shangpinbianhao = (AutoCompleteTextView)findViewById(R.id.editText_shangpinbianhao);
	        edit_product_name = (EditText)findViewById(R.id.edit_shangpinming);
	    	edit_product_price = (EditText)findViewById(R.id.edit_shangpinshoujia);
	    	edit_product_kucunliang= (EditText)findViewById(R.id.edit_kucunshuliang);
	    	edit_purchase_quantity = (EditText)findViewById(R.id.edit_goumaishuliang);
	        edit_customer_name = (EditText)findViewById(R.id.edit_kehuming);
	        edit_customer_pay = (EditText)findViewById(R.id.edit_fukuan);
	        edit_caozuoyuan = (EditText)findViewById(R.id.edit_caozuoyuan);
	        textview_dingdanhao=(TextView)findViewById(R.id.view_showdingdanhao);
	        textview_luru_jine=(TextView)findViewById(R.id.textview_luru_showjine);
	        
	         edit_chaxun_dingdanhao= (EditText)findViewById(R.id.edit_chaxun_dingdanhao);
	    	 edit_chaxun_caozuoyuan= (EditText)findViewById(R.id.edit_chaxun_caozuoyuan);
	    	 edit_chaxun_kehuming= (EditText)findViewById(R.id.edit_chaxun_kehuming);
	    	 edit_chaxun_jiaoyishijian= (EditText)findViewById(R.id.edit_chaxun_riqi);
	        
				        
	         edit_tuidan_dingdanhao= (EditText)findViewById(R.id.edit_shurutuidanhao);
	         edit__tuidan_fukuan= (EditText)findViewById(R.id.edit__tuidan_fukuan);
	    	 textview_tuidan_dingdanhao=(TextView)findViewById(R.id.textView_tudan_dingdanhao);
	    	 textview_tuidan_kehuming=(TextView)findViewById(R.id.textView_tuidan_kehuming);
	    	 textview_tuidan_jiaoyishijian=(TextView)findViewById(R.id.textview_tuidan_jiaoyishijian);
	    	 textview_tuidan_zongjia=(TextView)findViewById(R.id.textView_tuidan_zongjia);
	    	 textview_tuidan_qiankuan=(TextView)findViewById(R.id.textView_tuidan_qiankuan);
	        
	    	 textview_qiankuan_zonge=(TextView)findViewById(R.id.textView_qiankuan_zonge);
	        button1.setOnClickListener(this);  
	        button1.setTag(1);  
	        button2.setOnClickListener(this);  
	        button2.setTag(2);  
	        button3.setOnClickListener(this);  
	        button3.setTag(3);  
	        button4.setOnClickListener(this);  
	        button4.setTag(4);
	        button5.setOnClickListener(this);  
	        button5.setTag(5); 
	        button6.setOnClickListener(this);  
	        button6.setTag(6); 
	        button7.setOnClickListener(this);  
	        button7.setTag(7); 
	        button8.setOnClickListener(this);  
	        button8.setTag(8); 
	     
	        listView_chaxun = (ListView)findViewById(R.id.listView_chaxun);
	        listView_luru = (ListView)findViewById(R.id.listView_lurudingdan);
	        listView_tuidan = (ListView)findViewById(R.id.listView_tuidan);
	        listView_qiankuan= (ListView)findViewById(R.id.listView_qiankuan);
			
	    	sailService = new SailService(this);
	    	sailService_register = new SailServiceRegister(this);
	    	storageService= new StorageService(this);
	    	goodsService= new GoodsService(this);
	    	
	    	
	        edit_product_name.setText("");
			edit_product_price.setText("");
			edit_purchase_quantity.setText("");
			edit_customer_name.setText("");
			edit_customer_pay.setText("");
			
			edit_chaxun_dingdanhao.setText("");
			edit_chaxun_caozuoyuan.setText("");
			edit_chaxun_kehuming.setText("");
		    edit_chaxun_jiaoyishijian.setText("");
		    edit__tuidan_fukuan.setText("");
		  
/*****************初始化操作***************************************************/			
		    Cursor cursor = sailService.getCursorScrollData_chaxun(null,null,null,null, 0, 20);   //创建数据库
		    
			if(show_flag)show_luru();
			switch(tabshow_flag)
			{
			case 0:
				
				tabHost.setCurrentTab(0);
				Cursor cursor0 = sailService_register.getCursorScrollData_tosail(GetLastId(), 0, 20);
				  
				   if(cursor0.moveToFirst() == true)                 //当cursor为空时				     				
				   {
					   jine_flag=true;
					   show_luru();
				   }
				   else
					   show_luru_clear();
				
			    break;
			case 1:
				tabHost.setCurrentTab(1);
				
				
				break;
			case 2:
				tabHost.setCurrentTab(2);
				Cursor cursor2 = sailService.getCursorScrollData_chaxun(edit_chaxun_dingdanhao.getText().toString(),edit_chaxun_caozuoyuan.getText().toString(),edit_chaxun_kehuming.getText().toString(),edit_chaxun_jiaoyishijian.getText().toString(), 0, 20);
				  
				   if(cursor2.moveToFirst() == true)                 //当cursor为空时
				     
				   {
				        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, cursor2,
				    		new String[] {"_id","customer_name", "operator_name", "trade_time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
				    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
				            listView_chaxun.setAdapter(adapter); 
				   }
				break;
			case 3:
				
				tabHost.setCurrentTab(3);
				show_qiankuan();
				break;
			
			default:break;
			
			}
		
			
			AutoCompleteAdater_tuicang cursorAdapter = new AutoCompleteAdater_tuicang(this, android.R.layout.simple_dropdown_item_1line, null, "goodsid", android.R.id.text1,1);
			editText_shangpinbianhao.setThreshold(1);
			editText_shangpinbianhao.setAdapter(cursorAdapter);
			
			
/********************************************************************/				
			listView_chaxun.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Bundle bundle_chuandi = new Bundle();
		        	ListView lView = (ListView)parent;
					Cursor cursor = (Cursor)lView.getItemAtPosition(position);
					
					ID = cursor.getInt(cursor.getColumnIndex("_id"));					    	   				    					
					String customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
					String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
					String trade_time = cursor.getString(cursor.getColumnIndex("trade_time"));				
					String customer_pay = cursor.getString(cursor.getColumnIndex("customer_pay"));
					String customer_debt = cursor.getString(cursor.getColumnIndex("customer_debt"));
					
					bundle_chuandi.putInt("id", ID);
					bundle_chuandi.putString("customer_name", customer_name);
					bundle_chuandi.putString("operator_name", operator_name);
					bundle_chuandi.putString("customer_pay", customer_pay);
					bundle_chuandi.putString("trade_time", trade_time);
					bundle_chuandi.putString("customer_debt", customer_debt);
					
					tabshow_flag=2;
					Intent intent = new Intent(); 
					intent.putExtras(bundle_chuandi);
					intent.setClass(XiaoShouActivity.this, ChaXunDingDan.class);
					startActivity(intent);
					XiaoShouActivity.this.finish();
					
					
				}
				
								
			});
/********************************************************************/	 
			
			listView_luru.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Bundle bundle_chuandi = new Bundle();
		        	ListView lView = (ListView)parent;
					Cursor cursor = (Cursor)lView.getItemAtPosition(position);
				   				    					
				   	String code = cursor.getString(cursor.getColumnIndex("code"));
					String purchase_quantity= cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			    	String tosail= cursor.getString(cursor.getColumnIndex("tosail"));
				
					bundle_chuandi.putString("code", code);
					bundle_chuandi.putString("purchase_quantity", purchase_quantity);
					bundle_chuandi.putString("tosail", tosail);
					tabshow_flag=0;
					Intent intent = new Intent(); 
					intent.putExtras(bundle_chuandi);
					intent.setClass(XiaoShouActivity.this, LuRuDingDan.class);
					startActivity(intent);
					XiaoShouActivity.this.finish();					
					
				}
				
								
			});
			
			listView_tuidan.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Bundle bundle_chuandi = new Bundle();
		        	ListView lView = (ListView)parent;
					Cursor cursor = (Cursor)lView.getItemAtPosition(position);
					
				 	String code = cursor.getString(cursor.getColumnIndex("code"));
					String purchase_quantity= cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			    	String tosail= cursor.getString(cursor.getColumnIndex("tosail"));
				
					bundle_chuandi.putString("code", code);
					bundle_chuandi.putString("purchase_quantity", purchase_quantity);
					bundle_chuandi.putString("tosail", tosail);
					
					Intent intent = new Intent(); 
					intent.putExtras(bundle_chuandi);
					intent.setClass(XiaoShouActivity.this, TuiDanChuLi.class);
					tabshow_flag=1;
					startActivity(intent);
					XiaoShouActivity.this.finish();
					//cursor.close(); 
					
				}
				
								
			});	
			listView_qiankuan.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Bundle bundle_chuandi = new Bundle();
		        	ListView lView = (ListView)parent;
					Cursor cursor = (Cursor)lView.getItemAtPosition(position);
					
					ID = cursor.getInt(cursor.getColumnIndex("_id"));					    				    					
					String customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
					String customer_pay = cursor.getString(cursor.getColumnIndex("customer_pay"));
					String customer_debt = cursor.getString(cursor.getColumnIndex("customer_debt"));
					
					bundle_chuandi.putInt("id", ID);	
					bundle_chuandi.putString("customer_name", customer_name);
					bundle_chuandi.putString("customer_pay", customer_pay);
					bundle_chuandi.putString("customer_debt", customer_debt);
					
					Intent intent = new Intent(); 
					intent.putExtras(bundle_chuandi);
					intent.setClass(XiaoShouActivity.this, QianKuanXianShi.class);	
					tabshow_flag=3;
					startActivity(intent);
					XiaoShouActivity.this.finish();
					
					
				}
				
								
			});	
			   
			/**************************商品编号自动填充框光标移走时监听相应******************************************/
			editText_shangpinbianhao.setOnFocusChangeListener(new OnFocusChangeListener() {  
			       @Override  
			       public void onFocusChange(View v, boolean hasFocus) {  
			           AutoCompleteTextView view = (AutoCompleteTextView) v;  
			           if (!hasFocus) {  
			                   view.showDropDown();  
			                   Cursor cursor =storageService.getCursorScrollData_code( editText_shangpinbianhao.getText().toString(), 0, 20);   	   
								 if( cursor.moveToFirst()==false)
								 {
									    edit_product_name.setText(null);
					    				edit_product_price.setText(null);
					    				edit_product_kucunliang.setText(null);
										Toast.makeText(getApplicationContext(), "仓库中不存在该商品！",
						       				     Toast.LENGTH_SHORT).show();
								 }
									else if("".equals(editText_shangpinbianhao.getText().toString().trim()))
					          		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
					       				     Toast.LENGTH_SHORT).show();
					    			else
					    			{
					    				edit_product_name.setText(cursor.getString(2));
					    				edit_product_price.setText(cursor.getString(8));
					    				edit_product_kucunliang.setText(cursor.getString(9));
					    			}
					    	   
			      			
			           }  
			       }  
			   });  
			   /************************商品编号自动填充item选择时相应********************************************/
			editText_shangpinbianhao.setOnItemClickListener(new OnItemClickListener() 
			   { 

			       @Override 
			       public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
			               long arg3) 
			       { 
			    	  
			    	   Cursor cursor =storageService.getCursorScrollData_code( editText_shangpinbianhao.getText().toString(), 0, 20);   	   
						if( cursor.moveToFirst()==false)
						{
						    edit_product_name.setText(null);
	    				    edit_product_price.setText(null);
	    				    edit_product_kucunliang.setText(null);
							Toast.makeText(getApplicationContext(), "仓库中不存在该商品！",
			       				     Toast.LENGTH_SHORT).show();
						}
						else if("".equals(editText_shangpinbianhao.getText().toString().trim()))
			          		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
			       				     Toast.LENGTH_SHORT).show();
			    			else
			    			{
			    				edit_product_name.setText(cursor.getString(2));
			    				edit_product_price.setText(cursor.getString(8));
			    				edit_product_kucunliang.setText(cursor.getString(9));
			    			}
			    	   
			    	   
			    	   
			    			}
			    	   
			    	   
			            
			        
			   }); 
			   
			 
			 /********************************************************************/			
/********************************************************************/
	}
	
	
    public void onClick(View v){  
    	 
        int tag = (Integer) v.getTag();  
        switch(tag){  
        case 1: 
        	if("".equals(editText_shangpinbianhao.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
   				     Toast.LENGTH_SHORT).show();    	     
         	  else if("".equals(edit_purchase_quantity.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "购买数量不能为空！",
       				     Toast.LENGTH_SHORT).show();
          	  else 
          	  {
          		Cursor cursor =sailService_register.getCursorScrollData_codeandtosail(editText_shangpinbianhao.getText().toString(),String.valueOf(GetLastId()), 0, 20) ;  	   
      			if(cursor.moveToFirst()==true)	
      			{
      				String purchase_quantity=cursor.getString(4);
      				Integer a =Integer.valueOf(purchase_quantity)+Integer.valueOf(edit_purchase_quantity.getText().toString());
      				android.database.sqlite.SQLiteDatabase db;
      				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
      				db.execSQL("update sail_register set  purchase_quantity=? where code=?",
      						new Object[] {String.valueOf(a),editText_shangpinbianhao.getText().toString()});
      	      	}
      			else
      			{
        	android.database.sqlite.SQLiteDatabase db;
			db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
			db.execSQL("insert into sail_register(code,product_name, product_price, purchase_quantity,tosail) values( ?, ?, ?, ?, ?)",
					new Object[] {editText_shangpinbianhao.getText().toString(),edit_product_name.getText().toString(),edit_product_price.getText().toString(), 
					edit_purchase_quantity.getText().toString(),String.valueOf(GetLastId())});
      			}
			
			//在仓库中减去相应的数量
			cursor =storageService.getCursorScrollData_code( editText_shangpinbianhao.getText().toString(), 0, 20);   	   
  			cursor.moveToFirst();	   	
  			Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
  			Integer a= Integer.valueOf(edit_purchase_quantity.getText().toString());
		   	storage_quantity=storage_quantity-a;
    		storageService.update_storage_quantity(storage_quantity,editText_shangpinbianhao.getText().toString());
			
			show_flag=true;
			jine_flag=true;
			show_luru();
      			
          	  }		
      	
            break;  
        case 2:  
        	
        	if("".equals(edit_customer_name.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "客户名不能为空！",
     				     Toast.LENGTH_SHORT).show();  
        	else if("".equals(edit_customer_pay.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "客户付款不能为空！",
  				     Toast.LENGTH_SHORT).show();  
     	    else if("".equals(edit_caozuoyuan.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "操作员不能为空！",
  				     Toast.LENGTH_SHORT).show();  
     	    else
        	{
            /***************************得到系统时间*******************************/ 
        	system_time =Calendar.getInstance();        
           	 result_year=system_time.get(Calendar.YEAR);
        	 result_month=system_time.get(Calendar.MONTH);
        	 result_day=system_time.get(Calendar.DAY_OF_MONTH);
        	 result_hour=system_time.get(Calendar.HOUR);
        	 result_minute=system_time.get(Calendar.MINUTE);
        	/*******************************************************************/
        	 float a=stringToFloat(textview_luru_jine.getText().toString());
        	 float b=stringToFloat(edit_customer_pay.getText().toString());
        	 float c=a-b;
     			android.database.sqlite.SQLiteDatabase db_sail;
    			db_sail = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
    			db_sail.execSQL("insert into sail(customer_name,operator_name,customer_pay,trade_time,customer_debt) values( ?, ?, ?, ?, ?)",
    					new Object[] {  edit_customer_name.getText().toString(), edit_caozuoyuan.getText().toString(),
            			edit_customer_pay.getText().toString(),  result_year+"年"+result_month+"月"+result_day+"日"+ result_hour+"时"+result_minute+"分",String.valueOf(c)});
    					
            			Toast.makeText(getApplicationContext(), "提交成功！",
            				     Toast.LENGTH_SHORT).show();
            			show_luru_clear();  //清屏函数   	   
     			        	    		        		       			
        	}	
            break;  
        case 3:
        	
        	if("".equals(edit_tuidan_dingdanhao.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "输入不能为空！",
   				     Toast.LENGTH_SHORT).show();
      	  else
        	show_tuidan();
     
            break;  
        case 4:  
        	  
        	show_chaxun();
        
            break;  
        case 5:
        	if(xinzeng_flag)
        	{	
        	shanchudingdan();
        	show_tuidan_clear();
        	}
        	else
        	{
        		Toast.makeText(getApplicationContext(), "无法删除订单！",
      				     Toast.LENGTH_SHORT).show();
        	}
            break; 
        
       case 6:  
    	   if("".equals(edit_tuidan_dingdanhao.getText().toString().trim()))
       		  Toast.makeText(getApplicationContext(), "无法修改！",
    				     Toast.LENGTH_SHORT).show();
       	  else
       		  xiugaifukuan();
    	     
    	      break; 
       case 7:  
       
    	   show_qiankuan();
    	
           break;    
       case 8:  
    	   if(xinzeng_flag)       	
    	   addshangpin();
    	   else       	
       		Toast.makeText(getApplicationContext(), "无法新增商品！",
     				     Toast.LENGTH_SHORT).show();
           break;  
            
        default :  
            break;  
        }  
    } 	
/****************************************************************/	
    /**
	 * 将数据库和ListView绑定并显示在Tab1或Tab3中
	 * xianshi_listview1与Tab3绑定，查询
	 * xianshi_listview2与Tab1绑定，录入
	 * xianshi_listview3与Tab2绑定，退单
	 */
	
    private void show_luru() {
    	show_luru_clear();  //清屏函数   	
    
		Cursor cursor =sailService_register.getCursorScrollData_tosail(GetLastId(), 0, 20);	
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview2, cursor,
				new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    	
	    
		textview_dingdanhao.setText(""+GetLastId());	
		listView_luru.setAdapter(adapter);
		cursor.moveToFirst();
	
/**********计算金额**********************/
		String price;
		String quantity;
		Float i=(float) 0;
		int j=0;
		Float k=(float) 0;
	  	 price=cursor.getString(3);
	     quantity=cursor.getString(4);	
	     i = stringToFloat(price);
	     j = Integer.parseInt(quantity);
	     k =k+i*j;
		try{
			while(cursor.moveToNext()==true)
			{
			 price=cursor.getString(3);
			 quantity=cursor.getString(4);	
			 i = stringToFloat(price);
		     j = Integer.parseInt(quantity);
		     k =k+i*j;
			}
		     if(jine_flag)				 		
		 			textview_luru_jine.setText(String.valueOf(k));
		 		else
		 		    textview_luru_jine.setText(null);			     
		              }catch(Exception e){
		               return;
		              }	
    
	/*******************************************/
	}
	
    private void show_chaxun() {
    	
    	
        Cursor cursor = sailService.getCursorScrollData_chaxun(edit_chaxun_dingdanhao.getText().toString(),edit_chaxun_kehuming.getText().toString(),edit_chaxun_caozuoyuan.getText().toString(),edit_chaxun_jiaoyishijian.getText().toString(), 0, 20);
  
   if(cursor.moveToFirst() == false)                 //当cursor为空时
   {
	   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, null,
	    		new String[] {"_id","customer_name", "operator_name", "trade_time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	            listView_chaxun.setAdapter(adapter); 
	   Toast.makeText(getApplicationContext(), "不存在订单",
			     Toast.LENGTH_SHORT).show();
   }
 
   else
     
   {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, cursor,
    		new String[] {"_id","customer_name", "operator_name", "trade_time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            listView_chaxun.setAdapter(adapter); 
   }
    }
    private void show_tuidan() {    	
    	show_tuidan_clear() ;   //清屏函数
    	
    	Float fukuan=(float) 0;
    	Float qiankuan=(float) 0;
		 Cursor cursor = sailService.getCursorScrollData_tuidan(edit_tuidan_dingdanhao.getText().toString(), 0, 20);		 
		 if(cursor.moveToFirst() == false)                                //当cursor为空时
				   Toast.makeText(getApplicationContext(), "不存在该订单",
						     Toast.LENGTH_SHORT).show();
		    else
		    {
		   
			cursor.moveToFirst();
			tosail_tuidan=cursor.getString(0);
			textview_tuidan_dingdanhao.setText(cursor.getString(0));
			textview_tuidan_kehuming.setText(cursor.getString(1));
			textview_tuidan_jiaoyishijian.setText(cursor.getString(4));
			edit__tuidan_fukuan.setText(cursor.getString(3));
			fukuan=stringToFloat(cursor.getString(3));
			cursor.close();
			
			cursor =sailService_register.getCursorScrollData_tosail(Integer.parseInt(edit_tuidan_dingdanhao.getText().toString()), 0, 20);				
		
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xiaoshoutuidan_listview, cursor,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity}, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);							
			listView_tuidan.setAdapter(adapter);
			 xinzeng_flag=true;
		    cursor =sailService_register.getCursorScrollData_tosail(Integer.parseInt(edit_tuidan_dingdanhao.getText().toString()), 0, 20);
			cursor.moveToFirst();
	/**********计算金额**********************/
			String price;
			String quantity;
			Float i=(float) 0;
			int j=0;
			Float k=(float) 0;
		  	 price=cursor.getString(3);
		     quantity=cursor.getString(4);	
		     i = stringToFloat(price);
		     j = Integer.parseInt(quantity);
		     k =k+i*j;
			try{
				while(cursor.moveToNext()==true)
				{
				 price=cursor.getString(3);
				 quantity=cursor.getString(4);	
				 i = stringToFloat(price);
			     j = Integer.parseInt(quantity);
			     k =k+i*j;
			     
				}
			     
			    	 textview_tuidan_zongjia.setText(String.valueOf(k));
			         qiankuan=k-fukuan;
			         textview_tuidan_qiankuan.setText(String.valueOf(qiankuan));
			    		     
			              }catch(Exception e){
			               return;
			              }			
		/*******************************************/
		       }
		   
	}
 	public void show_qiankuan()
 	{
 		Float k=(float)0;
 		Float i=(float)0;
 		String qiankuan;
 		Cursor cursor = sailService.getCursorScrollData_qiankuan( 0, 20);
 		if(cursor.moveToFirst() == false)                                //当cursor为空时
			   Toast.makeText(getApplicationContext(), "没有客户欠款！",
					     Toast.LENGTH_SHORT).show();
		 
		   else
		   {
 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview4, cursor,
				new String[] {"_id", "customer_name", "customer_debt", "operator_name", "trade_time"}, new int[] {R.id.qiankuan_dingdanhao, R.id.qiankuan_kehuming, R.id.qiankuan_shangqian, R.id.qiankuan_caozuoyuan, R.id.qiankuan_tradetime}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);							
 		listView_qiankuan.setAdapter(adapter);
 		k=stringToFloat(cursor.getString(5));
 		try{
			while(cursor.moveToNext()==true)
			{
			 qiankuan=cursor.getString(5);		
			 i = stringToFloat(qiankuan);
		     k =k+i;		     
			}
			textview_qiankuan_zonge.setText(String.valueOf(k));		     
		              }catch(Exception e){
		               return;
		              }			
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
	  public int GetLastId() {
		  android.database.sqlite.SQLiteDatabase db;
		  db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
	       Cursor cursor = db.rawQuery("select * from sail",null);
	     if(cursor.moveToLast()==true)
	     {
	       String id1=cursor.getString(0);	
	       
	    	     int u = Integer.parseInt(id1);
	    	u++;              
      
	      
	       return u;  
	     }
	     else
	    	 return 1;
	      
	   }

/*************************清空显示函数***************************************/	
	  private void show_luru_clear() {
			
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview2, null,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity }, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
					listView_luru.setAdapter(adapter);
					
					textview_luru_jine.setText(null);
					textview_dingdanhao.setText(null);	
					edit_product_name.setText(null);
                    edit_product_price.setText(null);
					edit_purchase_quantity.setText(null);
					edit_customer_name.setText(null);
                    edit_caozuoyuan.setText(null);
        			edit_customer_pay.setText(null);
        			editText_shangpinbianhao.setText(null);
        			edit_product_kucunliang.setText(null);
		}

	  private void show_tuidan_clear() {
		  
		  SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xiaoshoutuidan_listview, null,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity }, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
					listView_tuidan.setAdapter(adapter);		
	    textview_tuidan_dingdanhao.setText(null);
		textview_tuidan_kehuming.setText(null);
		textview_tuidan_jiaoyishijian.setText(null);
		textview_tuidan_zongjia.setText(null);
		textview_tuidan_qiankuan.setText(null);
		edit__tuidan_fukuan.setText(null);
	 
	  }
/************************字符String转换成Float类型****************************************/
	  public static float stringToFloat(String floatstr) {
		  Float floatee;
		  floatee = Float.valueOf(floatstr);
		  return floatee.floatValue();
		  }
/****************************************************************/
	  private void  xiugaifukuan()
	  {
		  if("".equals(edit_tuidan_dingdanhao.getText().toString().trim()))
     		  Toast.makeText(getApplicationContext(), "请输入退单号！",
  				     Toast.LENGTH_SHORT).show();
		  else
		  {
		  Cursor cursor = sailService.getCursorScrollData_tuidan(edit_tuidan_dingdanhao.getText().toString(), 0, 20);			
		if("".equals(edit__tuidan_fukuan.getText().toString().trim()))
     		  Toast.makeText(getApplicationContext(), "输入不能为空！",
  				     Toast.LENGTH_SHORT).show();
     	  else
			      
			   { 
				   cursor.moveToFirst();
				   sailService.update_pay(edit__tuidan_fukuan.getText().toString(), Integer.parseInt(cursor.getString(0)));
				   Toast.makeText(getApplicationContext(), "修改成功！",
		  				     Toast.LENGTH_SHORT).show();
				   show_tuidan();
			   }
	  
		  }
	  }
	  
/****************************************************************/
	  private void  shanchudingdan()
	  {
			Cursor cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail_tuidan), 0, 20);
			cursor.moveToFirst();
			String code;
			String purchase_quantity;
			int i=2;
			code=cursor.getString(1);
			purchase_quantity=cursor.getString(4);
			//找到listview中第一个商品，在库存中增加相应的数量
			cursor =storageService.getCursorScrollData_code( code, 0, 20);   	   
			cursor.moveToFirst();	
			Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
			Integer a= Integer.valueOf(purchase_quantity);
		   	storage_quantity=storage_quantity+a;
			storageService.update_storage_quantity(storage_quantity,code);
			
		    cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail_tuidan), 0, 20);
			cursor.moveToFirst();
			try{
				while(cursor.moveToNext()==true)   //当cursor不为空时，表示listview还有其他商品
				{
					//找到listview中下一个商品，在库存中增加相应的数量
					code=cursor.getString(1);
					purchase_quantity=cursor.getString(4);
					
					cursor =storageService.getCursorScrollData_code( code, 0, 20);   	   
					cursor.moveToFirst();	
					 storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
					 a= Integer.valueOf(purchase_quantity);
				   	storage_quantity=storage_quantity-a;
					storageService.update_storage_quantity(storage_quantity,code);
					
				    cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail_tuidan), 0, 20);				
				    cursor.moveToPosition(i++);
				}
			     		    		     
			              }catch(Exception e){
			               return;
			              }			
		 
		 android.database.sqlite.SQLiteDatabase db;
		 db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);	    			
	     db.execSQL("delete from sail_register where tosail=?", new Object[] {tosail_tuidan});
	     db.execSQL("delete from sail where buyid=?", new Object[] {tosail_tuidan});  
	     show_tuidan_clear();
	     Toast.makeText(getApplicationContext(), "删除成功！",
			     Toast.LENGTH_SHORT).show();
	 }

	  /****************************************************************/
	  public void addshangpin()
	  {
	 	 
	 	 
	 	 
	 	 view_xinzeng=(TableLayout)getLayoutInflater().inflate(R.layout.xinzengshangpin, null);
	 	 edit_xinzeng_shangpinbianhao= (AutoCompleteTextView )view_xinzeng.findViewById(R.id.edit_xinzeng_shangpinbianhao);
	 	 edit_xinzeng_shangpinming= (EditText)view_xinzeng.findViewById(R.id.edit_xinzeng_shangpinming);
	  	 edit_xinzeng_jiage= (EditText)view_xinzeng.findViewById(R.id.edit_xinzeng_jiage);
	  	 edit_xinzeng_goumailiang= (EditText)view_xinzeng.findViewById(R.id.edit_xinzeng_goumailiang);
	  	 edit_xinzeng_kuncunliang= (EditText)view_xinzeng.findViewById(R.id.edit_xinzeng_kuncunliang);
	  	
	  	
	  	AutoCompleteAdater_code cursorAdapter_code = new AutoCompleteAdater_code(this, android.R.layout.simple_dropdown_item_1line, null, "code", android.R.id.text1,1);
	   	 edit_xinzeng_shangpinbianhao.setThreshold(1);
	   	 edit_xinzeng_shangpinbianhao.setAdapter(cursorAdapter_code);
	   	 
	 	 new AlertDialog.Builder(this).setTitle("新增商品") 
	 	     .setView(view_xinzeng)
	 	    .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
	 	 
	 	        @Override 
	 	        public void onClick(DialogInterface dialog, int which) { 
	 	        // 点击“确认”后的操作 
	 	        	
	 	       	 
	 	        	if("".equals(edit_xinzeng_shangpinbianhao.getText().toString().trim()))
	 	        	{
	 	       		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
	 	    				     Toast.LENGTH_SHORT).show();
	 	        	addshangpin();
	 	        	}
	 	       	  else if("".equals(edit_xinzeng_goumailiang.getText().toString().trim()))
	 	        	{
	 	       		  Toast.makeText(getApplicationContext(), "购买数量不能为空！",
	 	    				     Toast.LENGTH_SHORT).show();
	 	        	addshangpin();
	 	        	}
	 	       	  else
	 	  			      
	 	  			   { 
	 	       		  
	 	       	Cursor cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail_tuidan), 0, 20);  //判断新增商品在原来货单中是否存在	       	
	 	       	if(cursor.moveToNext()==false)    //如果订单中没有，则插入
	 	       	{
	 	       	android.database.sqlite.SQLiteDatabase db1;
	 		   	db1 = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);		   
	 		   	db1.execSQL("insert into sail_register(code,product_name, product_price, purchase_quantity,tosail) values( ?, ?, ?, ?, ?)",
	 		   			new Object[] {edit_xinzeng_shangpinbianhao.getText().toString(),edit_xinzeng_shangpinming.getText().toString(), 
	 		   			edit_xinzeng_jiage.getText().toString(),edit_xinzeng_goumailiang.getText().toString(),tosail_tuidan});
	 	       	}
	 	       	else                              //若存在，则更新购买数量
	 	       	{
	 	       		cursor =sailService_register.getCursorScrollData_tosail(Integer.valueOf(tosail_tuidan), 0, 20);
	 	       		cursor.moveToFirst();
	 	       		Integer a= Integer.valueOf(edit_xinzeng_goumailiang.getText().toString());
	 	       		Integer purchase_quantity= cursor.getInt(cursor.getColumnIndex("purchase_quantity"))+a;
	 	        	sailService_register.update_purchase_quantity(purchase_quantity,edit_xinzeng_shangpinbianhao.getText().toString(),tosail_tuidan);
	 	       		
	 	       	}
	 	       	//从仓库中减去相应数量
	 		    cursor =storageService.getCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);   	   
	   			cursor.moveToFirst();	   	
	   			Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
	   			Integer a= Integer.valueOf(edit_xinzeng_goumailiang.getText().toString());
	 		   	storage_quantity=storage_quantity-a;
	     		storageService.update_storage_quantity(storage_quantity,edit_xinzeng_shangpinbianhao.getText().toString());
	   		
	     		show_tuidan();
	 	  			   }
	 	        }
	 	    }) 
	 	    .setNegativeButton("取消", new DialogInterface.OnClickListener() { 
	 	 
	 	        @Override 
	 	        public void onClick(DialogInterface dialog, int which) { 
	 	        // 点击“返回”后的操作,这里不设置没有任何操作 
	 	        } 
	 	    }).show(); 
	 	 /**************************新增商品中AlertDlalog商品编号自动填充框光标移走时监听相应******************************************/
	 	 edit_xinzeng_shangpinbianhao.setOnFocusChangeListener(new OnFocusChangeListener() {  
	 	       @Override  
	 	       public void onFocusChange(View v, boolean hasFocus) {  
	 	           AutoCompleteTextView view = (AutoCompleteTextView) v;  
	 	           if (!hasFocus) {  
	 	                   view.showDropDown();  
	 	                Cursor cursor =goodsService.getGoodsCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);   	   
	 	      			cursor.moveToFirst();
	 	      			if("".equals(edit_xinzeng_shangpinbianhao.getText().toString().trim()))
	 	            		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
	 	         				     Toast.LENGTH_SHORT).show();
	 	      			else 
	 	      			{
	 	      				edit_xinzeng_shangpinming.setText(cursor.getString(2));
	 	    			 	edit_xinzeng_jiage.setText(cursor.getString(7));
	 	    			 	cursor =storageService.getCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);
	 	    			 	cursor.moveToFirst();
	 	    			 	edit_xinzeng_kuncunliang.setText(cursor.getString(9));
	 	      			
	 	      			}
	 	           }  
	 	       }  
	 	   });  
	 	 /************************新增商品中AlertDlalog商品编号自动填充item选择时相应********************************************/
	 	   edit_xinzeng_shangpinbianhao.setOnItemClickListener(new OnItemClickListener() 
	 	   { 

	 	       @Override 
	 	       public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
	 	               long arg3) 
	 	       { 
	 	           
	 	    	   Cursor cursor =goodsService.getGoodsCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);   	   
	 				 cursor.moveToFirst();
	 					if("".equals(edit_xinzeng_shangpinbianhao.getText().toString().trim()))
	 	          		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
	 	       				     Toast.LENGTH_SHORT).show();
	 	    			else
	 	    			{
	 	    				 
	 	    			 	edit_xinzeng_shangpinming.setText(cursor.getString(2));
	 	    			 	edit_xinzeng_jiage.setText(cursor.getString(7));
	 	    			 	cursor =storageService.getCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);
	 	    			 	cursor.moveToFirst();
	 	    			 	edit_xinzeng_kuncunliang.setText(cursor.getString(9));
	 	    			}
	 	    	   
	 	    	   
	 	            
	 	       } 
	 	   }); 
	 /********************************************************************/ 
	 	 
	 	
	  }	
/****************************************************************/
}
