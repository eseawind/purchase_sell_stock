/**
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

package com.shixi.jxctest;
import java.util.Calendar;


import com.shixi.autocompleteadater.AutoCompleteAdater_buyid;
import com.shixi.autocompleteadater.AutoCompleteAdater_buyname;
import com.shixi.autocompleteadater.AutoCompleteAdater_code;
import com.shixi.jxctest.R;
import com.shixi.service.BuyService;
import com.shixi.service.BuyServiceRegister;
import com.shixi.service.DBOpenHelper;
import com.shixi.service.GoodsService;
import com.shixi.service.SailServiceRegister;
import com.shixi.service.StorageService;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CaiGouActivity extends Activity implements View.OnClickListener{

	/**************录入货单控件声明*************************/
	private EditText edit_luru_product_name;
	private EditText edit_luru_product_price;
	private EditText edit__luru_purchase_quantity;
	private ListView listView_jinhuo;
	private AutoCompleteTextView  edit_caigou_luru_shangpinbianhao;
	private TextView textview_luru_jine;
	private TextView textview_jinhuohao;
	private AutoCompleteTextView  edit_buy_name;
	private EditText edit_caozuoyuan;
	private EditText edit_pay;
	/**************退单控件声明*************************/
	private AutoCompleteTextView edit_tuihuo_tuihuohao;
	private EditText edit_tuihuo_fukuan;
	
	private AutoCompleteTextView  edit_xinzeng_shangpinbianhao;
	private EditText edit_xinzeng_shangpinming;
	private EditText edit_xinzeng_jiage;
	private EditText edit_xinzeng_kuncunliang;
	private EditText edit_xinzeng_goumailiang;
	private TextView textview_tuihuo_jinhuohao;
	private TextView textview_tuihuo_jinhuoshang;
	private TextView textview_tuihuo_jinhuoshijian;
	private TextView textview_tuihuo_zonge;
	private TextView textview_tuihuo_qiankuan;
	private ListView listView_tuihuo;
	/**************查询控件声明*************************/
	private EditText edit_chaxun_jinhuohao;
	private EditText edit_chaxun_jinhuoshang;
	private EditText edit_chaxun_caozuoyuan;	
	private EditText edit_chaxun_jiaoyishijian;
	
	private ListView listView_chaxun;
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
	private TableLayout view_xinzeng;
	private Integer ID;
	public static boolean jine_flag=false;
	public static int  tabshow_flag=0;
	public static boolean xinzeng_flag=false;
	public static boolean show_flag=false;
	public static String tobuy_tuihuo;
	BuyService buyService;
	BuyServiceRegister buyService_register;
	GoodsService  goodsService;
	StorageService storageService;
	ActionBar actionBar;
	Spinner spinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		 setContentView(R.layout.caigou);  
	        // 获取TabHost对象  
	        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  
	        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
	        tabHost.setup();  
	        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("录入进货",  
	                getResources().getDrawable(R.drawable.banzi)).setContent(  
	                R.id.caigou_view1));  
	        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("退货处理")  
	                .setContent(R.id.caigou_view2));  
	        
	        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("进货查询")  
	                .setContent(R.id.caigou_view3));  
	  	       
	        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("欠款显示")  
	                .setContent(R.id.caigou_view4));  
	        	        
	     
	        edit_caigou_luru_shangpinbianhao= (AutoCompleteTextView)findViewById(R.id.edit_caigou_luru_shangpinbianhao);
	        
	        Button button1 = (Button)findViewById(R.id.button_caigou_luru_ok);  
	        Button button2 = (Button)findViewById(R.id.button_caigou_luru_tijiao);  
	        Button button3 = (Button)findViewById(R.id.button_caigou_tuihuo_ok);  
	        Button button4 = (Button)findViewById(R.id.button_caigou_tuihuo_shanchuhuodan);  
	        Button button5 = (Button)findViewById(R.id.button_caigou_tuihuo_xiugai);  
	        Button button6 = (Button)findViewById(R.id.button_caigou_chaxun_ok);
	        Button button7 = (Button)findViewById(R.id.button_caigou_qiankuan_shuaxin);	    	
	        Button button8 = (Button)findViewById(R.id.button_caiou_tuidan_xinzengshangpin);
	        
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
	     
	    
	    
	    	edit_luru_product_name = (EditText)findViewById(R.id.edit_caigou_luru_shangpinming);
	    	edit_luru_product_price = (EditText)findViewById(R.id.edit_caigou_luru_jinjia);
	    	edit__luru_purchase_quantity = (EditText)findViewById(R.id.edit_caigou_luru_shuliang);
	    	edit_buy_name= (AutoCompleteTextView)findViewById(R.id.edit_caigou_luru_jinhuoshang);
	    	edit_caozuoyuan = (EditText)findViewById(R.id.edit_caigou_luru_caozuoyuan);
	    	edit_pay = (EditText)findViewById(R.id.edit_caigou_luru_fukuan);   	
	    	textview_luru_jine=(TextView)findViewById(R.id.textview_caigou_luru_showjine);
	    	textview_jinhuohao=(TextView)findViewById(R.id.view_caigou_luru_showjinhuohao);
	    	
	    	
	    	
	    	edit_tuihuo_tuihuohao= (AutoCompleteTextView)findViewById(R.id.edit_caigou_tuihuo_shurutuihuohao);
	    	edit_tuihuo_fukuan= (EditText)findViewById(R.id.edit_caigou_tuidan_fukuan);
	    	
	    	
	    	
	    	textview_tuihuo_jinhuohao=(TextView)findViewById(R.id.textView_caigou_tuihuo_tuihuodan);
	    	textview_tuihuo_jinhuoshang=(TextView)findViewById(R.id.textView_caigou_tuidan_jinhuoshang);
	    	textview_tuihuo_jinhuoshijian=(TextView)findViewById(R.id.textview_caigou_tuihuo_jinhuoshijian);
	    	textview_tuihuo_zonge=(TextView)findViewById(R.id.textView_caigou_tuihuo__zongjia);
	    	textview_tuihuo_qiankuan=(TextView)findViewById(R.id.textView_caigou_tuihuo_qiankuan);
	    	
	    	
	    	edit_chaxun_jinhuohao= (EditText)findViewById(R.id.edit_caigou_chaxun_jinhuohao);
		    edit_chaxun_caozuoyuan= (EditText)findViewById(R.id.edit_caigou_chaxun_caozuoyuan);
		    edit_chaxun_jinhuoshang= (EditText)findViewById(R.id.edit_caigou_chaxun_jinhuoshang);
		    edit_chaxun_jiaoyishijian= (EditText)findViewById(R.id.edit_caigou_chaxun_riqi);
		   
		    textview_qiankuan_zonge=(TextView)findViewById(R.id.textView_caigou_qiankuan_zonge);
	    	
	    	 listView_jinhuo = (ListView)findViewById(R.id.listView_caigou_luru);
	    	 listView_chaxun = (ListView)findViewById(R.id.listView_caigou_chaxun);
	    	 listView_tuihuo = (ListView)findViewById(R.id.listView_caigou_tuihuo);
	    	 listView_qiankuan= (ListView)findViewById(R.id.listView_caigou_qiankuan);
	    	 
	    	buyService_register = new BuyServiceRegister(this);
	    	buyService = new BuyService(this);
	    	storageService= new StorageService(this);
	    	goodsService= new GoodsService(this);
	    	
	    	edit_pay.setText("");
	    	edit_chaxun_jinhuohao.setText("");
			edit_chaxun_caozuoyuan.setText("");
			edit_chaxun_jinhuoshang.setText("");
		    edit_chaxun_jiaoyishijian.setText("");
		    
		    
		     
		 	 
/*****************初始化操作***************************************************/			
   Cursor cursor = buyService_register.getCursorScrollData_chaxun(null,null,null,null,null, 0, 20);   //创建数据库
   
   if(show_flag)show_caigou_luru();
   
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
				Cursor cursor2 = buyService.getCursorScrollData_chaxun(edit_chaxun_jinhuohao.getText().toString(),edit_chaxun_jinhuoshang.getText().toString(),edit_chaxun_caozuoyuan.getText().toString(),edit_chaxun_jiaoyishijian.getText().toString(), 0, 20);
				  
				   if(cursor2.moveToFirst() == true)                 //当cursor为空时
				     
				   {
					   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, null,
					    		new String[] {"_id","buy_name", "operator_name", "time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
					    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
					            listView_chaxun.setAdapter(adapter); 
				   }
				break;
			case 3:
				
				tabHost.setCurrentTab(3);
				show_caigou_qiankuan();
				break;
			
			default:break;
			
			}
			
			
			
			AutoCompleteAdater_code cursorAdapter = new AutoCompleteAdater_code(this, android.R.layout.simple_dropdown_item_1line, null, "code", android.R.id.text1,1);
    		edit_caigou_luru_shangpinbianhao.setThreshold(1);
    		edit_caigou_luru_shangpinbianhao.setAdapter(cursorAdapter);
    		
    		AutoCompleteAdater_buyname cursorAdapter_name = new AutoCompleteAdater_buyname(this, android.R.layout.simple_dropdown_item_1line, null, "name", android.R.id.text1,1);
   		    edit_buy_name.setThreshold(1);
    		edit_buy_name.setAdapter(cursorAdapter_name);
    		
    		AutoCompleteAdater_buyid cursorAdapter_buyid = new AutoCompleteAdater_buyid(this, android.R.layout.simple_dropdown_item_1line, null, "_id", android.R.id.text1,1);
    		edit_tuihuo_tuihuohao.setThreshold(1);
    		edit_tuihuo_tuihuohao.setAdapter(cursorAdapter_buyid);
    		
   /********************************************************************/				
   listView_jinhuo.setOnItemClickListener(new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Bundle bundle_chuandi = new Bundle();
       	ListView lView = (ListView)parent;
			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
			
		   	String code = cursor.getString(cursor.getColumnIndex("goods_id"));		  
	    	 String tobuy= cursor.getString(cursor.getColumnIndex("tobuy"));
		
			bundle_chuandi.putString("code", code);
			bundle_chuandi.putString("tobuy", tobuy);
		
			Intent intent = new Intent(); 
			intent.putExtras(bundle_chuandi);
			intent.setClass(CaiGouActivity.this, LuRuHuoDan.class);
			startActivity(intent);
			CaiGouActivity.this.finish();
			cursor.close(); 
			tabshow_flag=0;
		}
		
						
	});
/********************************************************************/	
   listView_tuihuo.setOnItemClickListener(new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Bundle bundle_chuandi = new Bundle();
         	ListView lView = (ListView)parent;
			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
			
		   	String code = cursor.getString(cursor.getColumnIndex("goods_id"));	
		 	 String tobuy= cursor.getString(cursor.getColumnIndex("tobuy"));
	    	 String purchase_quantity= cursor.getString(cursor.getColumnIndex("purchase_quantity"));
		
			bundle_chuandi.putString("code", code);
			bundle_chuandi.putString("tobuy", tobuy);
			bundle_chuandi.putString("purchase_quantity", purchase_quantity);
			
			Intent intent = new Intent(); 
			intent.putExtras(bundle_chuandi);
			intent.setClass(CaiGouActivity.this, TuiHuoChuLi.class);
			tabshow_flag=1;
			startActivity(intent);
			CaiGouActivity.this.finish();
		
			
		}
		
						
	});	
/********************************************************************/
   listView_chaxun.setOnItemClickListener(new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Bundle bundle_chuandi_caigou = new Bundle();
       	    ListView lView = (ListView)parent;
			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
			
			ID = cursor.getInt(cursor.getColumnIndex("_id"));					    	   				    					
			String buy_name = cursor.getString(cursor.getColumnIndex("buy_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String time = cursor.getString(cursor.getColumnIndex("time"));				
			String pay = cursor.getString(cursor.getColumnIndex("pay"));
			String debt = cursor.getString(cursor.getColumnIndex("debt"));
			
			bundle_chuandi_caigou.putInt("id", ID);
			bundle_chuandi_caigou.putString("buy_name", buy_name);
			bundle_chuandi_caigou.putString("operator_name", operator_name);
			bundle_chuandi_caigou.putString("pay", pay);
			bundle_chuandi_caigou.putString("time", time);
			bundle_chuandi_caigou.putString("debt", debt);
			
			tabshow_flag=2;
			Intent intent = new Intent(); 
			intent.putExtras(bundle_chuandi_caigou);
			intent.setClass(CaiGouActivity.this, ChaXunHuoDan.class);
			startActivity(intent);
			CaiGouActivity.this.finish();
			
			
		}
		
						
	}); 
  
   
/********************************************************************/
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
			String buy_name = cursor.getString(cursor.getColumnIndex("buy_name"));
			String pay = cursor.getString(cursor.getColumnIndex("pay"));
			String debt = cursor.getString(cursor.getColumnIndex("debt"));
			
			bundle_chuandi.putInt("id", ID);	
			bundle_chuandi.putString("buy_name", buy_name);
			bundle_chuandi.putString("pay", pay);
			bundle_chuandi.putString("debt", debt);
			
			Intent intent = new Intent(); 
			intent.putExtras(bundle_chuandi);
			intent.setClass(CaiGouActivity.this, CaiGouQianKuanXianShi.class);	
			tabshow_flag=3;
			startActivity(intent);
			CaiGouActivity.this.finish();
			
			
		}
		
						
	});	
   
/**************************商品编号自动填充框光标移走时监听相应******************************************/
   edit_caigou_luru_shangpinbianhao.setOnFocusChangeListener(new OnFocusChangeListener() {  
       @Override  
       public void onFocusChange(View v, boolean hasFocus) {  
           AutoCompleteTextView view = (AutoCompleteTextView) v;  
           if (!hasFocus) {  
                   view.showDropDown();  
                Cursor cursor =goodsService.getGoodsCursorScrollData_code( edit_caigou_luru_shangpinbianhao.getText().toString(), 0, 20);   	   
      			if( cursor.moveToFirst()==false)
      			{
      				edit_luru_product_name.setText(null);
          			edit_luru_product_price.setText(null);
      				Toast.makeText(getApplicationContext(), "商品档案中没有该商品！",
        				     Toast.LENGTH_SHORT).show();
      			}
      			else if("".equals(edit_caigou_luru_shangpinbianhao.getText().toString().trim()))
            		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
         				     Toast.LENGTH_SHORT).show();
      			else
      			{
      			edit_luru_product_name.setText(cursor.getString(2));
      			edit_luru_product_price.setText(cursor.getString(7));
      			}
           }  
       }  
   });  
   /************************商品编号自动填充item选择时相应********************************************/
   edit_caigou_luru_shangpinbianhao.setOnItemClickListener(new OnItemClickListener() 
   { 

       @Override 
       public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, 
               long arg3) 
       { 
           
    	   Cursor cursor =goodsService.getGoodsCursorScrollData_code( edit_caigou_luru_shangpinbianhao.getText().toString(), 0, 20);   	   
			 cursor.moveToFirst();
			 if( cursor.moveToFirst()==false)
   			{
   				edit_luru_product_name.setText(null);
       			edit_luru_product_price.setText(null);
   				Toast.makeText(getApplicationContext(), "商品档案中没有该商品！",
     				     Toast.LENGTH_SHORT).show();
   			}
   			else if("".equals(edit_caigou_luru_shangpinbianhao.getText().toString().trim()))
          		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
       				     Toast.LENGTH_SHORT).show();
    			else
    			{
			edit_luru_product_name.setText(cursor.getString(2));
			edit_luru_product_price.setText(cursor.getString(7));
    			}
    	   
    	   
            
       } 
   }); 
   
 
 /********************************************************************/
   
	}
	
	
  
	public void onClick(View v){  
    	 
        int tag = (Integer) v.getTag();  
        switch(tag){  
        case 1: 
        	
       	Cursor cursor =buyService_register.getCursorScrollDataRegister( 0, 20);	
      		      	
        	if("".equals(edit_caigou_luru_shangpinbianhao.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "商品编号不能为空！",
   				     Toast.LENGTH_SHORT).show();
      	  else if("".equals(edit_luru_product_name.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "商品名称不能为空！",
    				     Toast.LENGTH_SHORT).show();
       	  else if("".equals(edit_luru_product_price.getText().toString().trim()))
      		  Toast.makeText(getApplicationContext(), "商品进价不能为空！",
     				     Toast.LENGTH_SHORT).show();
       	  else if("".equals(edit__luru_purchase_quantity.getText().toString().trim()))
     		  Toast.makeText(getApplicationContext(), "采购数量不能为空！",
    				     Toast.LENGTH_SHORT).show();
        	  else 
        	  {
      	   android.database.sqlite.SQLiteDatabase db;
			db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
			db.execSQL("insert into buy_register(goods_id,product_name, product_price, purchase_quantity,tobuy) values( ?, ?, ?, ?, ?)",
					new Object[] {edit_caigou_luru_shangpinbianhao.getText().toString(),edit_luru_product_name.getText().toString(), 
					edit_luru_product_price.getText().toString(),edit__luru_purchase_quantity.getText().toString(),String.valueOf(GetLastId())});
			
			show_flag=true;
			jine_flag=true;
			show_caigou_luru();
			
			
        	  }		
            break;  
        case 2:  
        	if("".equals( edit_buy_name.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "进货商不能为空！",
     				     Toast.LENGTH_SHORT).show();
        	  else if("".equals(edit_caozuoyuan.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "操作员不能为空！",
      				     Toast.LENGTH_SHORT).show();
         	  else if("".equals(edit_pay.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "本店付款不能为空！",
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
        	float b=stringToFloat(edit_pay.getText().toString());
        	float c=a-b;
        	android.database.sqlite.SQLiteDatabase db_buy;
 			db_buy = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
 			db_buy.execSQL("insert into buy(buy_name,operator_name,pay,time,debt) values( ?, ?, ?, ?, ?)",
 					new Object[] {  edit_buy_name.getText().toString(), edit_caozuoyuan.getText().toString(),
 					edit_pay.getText().toString(),  result_year+"年"+result_month+"月"+result_day+"日"+ result_hour+"时"+result_minute+"分",String.valueOf(c)});
 					
         			Toast.makeText(getApplicationContext(), "提交成功！",
         				     Toast.LENGTH_SHORT).show();
         			lurucangku();
         			show_caigou_luru_clear();
         	  }
            break;  
        case 3:
        	
        	if("".equals(edit_tuihuo_tuihuohao.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "输入不能为空！",
     				     Toast.LENGTH_SHORT).show();
        	  else
          	show_caigou_tuihuo();
        	
     
            break;  
        case 4:  
        	
        	
        	if(xinzeng_flag)
        		
        		delete_huodan();
        	
        	else
        	
        		Toast.makeText(getApplicationContext(), "无法删除货单！",
      				     Toast.LENGTH_SHORT).show();
        	
            break;  
        case 5:  
        	 
        	if("".equals(edit_tuihuo_tuihuohao.getText().toString().trim()))
        		  Toast.makeText(getApplicationContext(), "无法修改！",
     				     Toast.LENGTH_SHORT).show();
   		  else
        	  xiugaifukuan();
        	
          	
        	
            break; 
        
       case 6:  
    	   
		 
    	   show_caigou_chaxun();
    	   
    	  
    	      break; 
       case 7:  
       
    	   
    	   show_caigou_qiankuan();
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
    private void show_caigou_luru() {
    	show_caigou_luru_clear();  //清屏函数  
		Cursor cursor =buyService_register.getCursorScrollData_tobuy(GetLastId(), 0, 20);	
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview2, cursor,
				new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		textview_jinhuohao.setText(""+GetLastId());
		listView_jinhuo.setAdapter(adapter); 
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
/*****************************************************************/				
    }
/****************************************************************/	
    private void  show_caigou_tuihuo()
    {
        show_caigou_tuihuo_clear() ;   //清屏函数
    	
    	Float fukuan=(float) 0;
    	Float qiankuan=(float) 0;
		 Cursor cursor = buyService.getCursorScrollData_tuihuo(edit_tuihuo_tuihuohao.getText().toString(), 0, 20);		 
		 if(cursor.moveToFirst() == false)                                //当cursor为空时
				   Toast.makeText(getApplicationContext(), "不存在该订单",
						     Toast.LENGTH_SHORT).show();
		    else
		    {
		    	
			cursor.moveToFirst();
			textview_tuihuo_jinhuohao.setText(cursor.getString(0));
			tobuy_tuihuo=cursor.getString(0);
			textview_tuihuo_jinhuoshang.setText(cursor.getString(1));
			textview_tuihuo_jinhuoshijian.setText(cursor.getString(4));
			edit_tuihuo_fukuan.setText(cursor.getString(3));
			fukuan=stringToFloat(cursor.getString(3));
			cursor.close();
			
			cursor =buyService_register.getCursorScrollData_tobuy(Integer.parseInt(edit_tuihuo_tuihuohao.getText().toString()), 0, 20);				
		
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.caigoutuihuo_listview, cursor,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity}, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);							
			listView_tuihuo.setAdapter(adapter);
			 xinzeng_flag=true;
			cursor =buyService_register.getCursorScrollData_tobuy(Integer.parseInt(edit_tuihuo_tuihuohao.getText().toString()), 0, 20);	
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
			     
				     textview_tuihuo_zonge.setText(String.valueOf(k));
			         qiankuan=k-fukuan;
			         textview_tuihuo_qiankuan.setText(String.valueOf(qiankuan));
			    		     
			              }catch(Exception e){
			               return;
			              }			
		/*******************************************/
		       }
    }
/****************************************************************/
    private void show_caigou_chaxun() {
    	
    
    	
   Cursor cursor = buyService.getCursorScrollData_chaxun(edit_chaxun_jinhuohao.getText().toString(),edit_chaxun_jinhuoshang.getText().toString(),edit_chaxun_caozuoyuan.getText().toString(),edit_chaxun_jiaoyishijian.getText().toString(), 0, 20);
  
   if(cursor.moveToFirst() == false)                 //当cursor为空时
   {
	   SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, null,
	    		new String[] {"_id","buy_name", "operator_name", "time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
	    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	            listView_chaxun.setAdapter(adapter); 
	   Toast.makeText(getApplicationContext(), "不存在订单",
			     Toast.LENGTH_SHORT).show();
   }
 
   else
     
   {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview1, cursor,
    		new String[] {"_id","buy_name", "operator_name", "time"}, new int[] {R.id.xianshi1_dingdanhao,R.id.xianshi1_customer_name, R.id.xianshi1_operator_name, R.id.xianshi1_trade_time}, 
    		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            listView_chaxun.setAdapter(adapter); 
          
   }
    }
 /*****************************************************************/
    private void show_caigou_qiankuan()
    {
    	Float k=(float)0;
 		Float i=(float)0;
 		String qiankuan;
 		Cursor cursor = buyService.getCursorScrollData_qiankuan( 0, 20);
 		if(cursor.moveToFirst() == false)                                //当cursor为空时
			   Toast.makeText(getApplicationContext(), "没有客户欠款！",
					     Toast.LENGTH_SHORT).show();
		 
		   else
		   {
 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview4, cursor,
				new String[] {"_id", "buy_name", "debt", "operator_name", "time"}, new int[] {R.id.qiankuan_dingdanhao, R.id.qiankuan_kehuming, R.id.qiankuan_shangqian, R.id.qiankuan_caozuoyuan, R.id.qiankuan_tradetime}, 
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
/*****************************************************************/
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


/************************字符String转换成Float类型****************************************/
	  public static float stringToFloat(String floatstr) {
		  Float floatee;
		  floatee = Float.valueOf(floatstr);
		  return floatee.floatValue();
		  }
/****************************************************************/	
	  public int GetLastId() {
		  android.database.sqlite.SQLiteDatabase db;
		  db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
	       Cursor cursor = db.rawQuery("select * from buy",null);
	    //   cursor.moveToLast();
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
 /****************************************************************/
	  private void  xiugaifukuan()
	  {
		 
		  
		 		
		if("".equals(edit_tuihuo_fukuan.getText().toString().trim()))
     		  Toast.makeText(getApplicationContext(), "输入不能为空！",
  				     Toast.LENGTH_SHORT).show();
     	  else
			      
			   { 
     		 Cursor cursor = buyService.getCursorScrollData_tuihuo(edit_tuihuo_tuihuohao.getText().toString(), 0, 20);	
				   cursor.moveToFirst();
				   buyService.update_pay(edit_tuihuo_fukuan.getText().toString(), Integer.parseInt(cursor.getString(0)));
				   Toast.makeText(getApplicationContext(), "修改成功！",
		  				     Toast.LENGTH_SHORT).show();
				   show_caigou_tuihuo();
			   }
	  
		
	  }	  
/****************************************************************/
	  public void  show_caigou_luru_clear()
	  {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xianshi_listview2, null,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity }, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
					listView_jinhuo.setAdapter(adapter);
					
					edit_caigou_luru_shangpinbianhao.setText(null);
					edit_luru_product_name.setText(null);	
					edit_luru_product_price.setText(null);
				    edit__luru_purchase_quantity.setText(null);
				    textview_luru_jine.setText(null);
				    textview_jinhuohao.setText(null);
				    edit_buy_name.setText(null);
				    edit_pay.setText(null);
				    edit_caozuoyuan.setText(null);
				    
				    edit_caigou_luru_shangpinbianhao.setFocusable(true);   //将光标移到商品编号处
				    edit_caigou_luru_shangpinbianhao.setFocusableInTouchMode(true);   
				    edit_caigou_luru_shangpinbianhao.requestFocus(); 
				    
				  
	  }
/****************************************************************/
 public void  show_caigou_tuihuo_clear()
 {
 
  	 SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.xiaoshoutuidan_listview, null,
				new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity }, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
				listView_tuihuo.setAdapter(adapter);		
				textview_tuihuo_jinhuohao.setText(null);
				textview_tuihuo_jinhuoshang.setText(null);
				textview_tuihuo_jinhuoshijian.setText(null);
				textview_tuihuo_zonge.setText(null);
				textview_tuihuo_qiankuan.setText(null);
			    edit_tuihuo_fukuan.setText(null);
 }
/****************************************************************/
 public void lurucangku()
 {
	 /***************************得到系统时间*******************************/ 
	 system_time =Calendar.getInstance();        
   	 result_year=system_time.get(Calendar.YEAR);
	 result_month=system_time.get(Calendar.MONTH);
	 result_day=system_time.get(Calendar.DAY_OF_MONTH);
	 result_hour=system_time.get(Calendar.HOUR);
	 result_minute=system_time.get(Calendar.MINUTE);
    /*******************************************************************/  
	 
	 Cursor cursor =buyService_register.getCursorScrollData_tobuy(GetLastId()-1, 0, 20);	
		cursor.moveToFirst();
		String code;
		String purchase_quantity;
				
		code=cursor.getString(1);
		purchase_quantity=cursor.getString(4);
		cursor.close();
		cursor =storageService.getCursorScrollData_code(code, 0, 20);  //判断入库商品在库中是否存在
		if(cursor.moveToFirst()==false)   //当不存在时，插入新数据到数据库中
		{
			cursor.close();
		cursor=goodsService.getGoodsCursorScrollData_code(code,0,20);
		cursor.moveToFirst();
		storageService.save(cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), Integer.valueOf(purchase_quantity), edit_caozuoyuan.getText().toString(), result_year+"年"+result_month+"月"+result_day+"日"+ result_hour+"时"+result_minute+"分");
		
		}
		else                              //若存在时，找到该数据，更新该数据的库存量。
		{
			cursor.close();
			cursor =storageService.getCursorScrollData_code(code, 0, 20);
			cursor.moveToFirst();
			Integer  storage_quantity = Integer.valueOf(cursor.getString(9));
			storage_quantity=storage_quantity+Integer.valueOf(purchase_quantity);
			storageService.update_storage_quantity( Integer.valueOf(storage_quantity),code);
			
		}
		
      cursor =buyService_register.getCursorScrollData_tobuy(GetLastId()-1, 0, 20);
      cursor.moveToFirst();
		try{
			while(cursor.moveToNext()==true)
			{
				code=cursor.getString(1);
				purchase_quantity=cursor.getString(4);
				cursor.close();
				cursor =storageService.getCursorScrollData_code(code, 0, 20);  //判断入库商品在库中是否存在
				if(cursor.moveToNext()==false)   //当不存在时，插入新数据到数据库中
				{
					cursor.close();
				cursor=goodsService.getGoodsCursorScrollData_code(code,0,20);
				cursor.moveToFirst();
				storageService.save(cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),Integer.valueOf(purchase_quantity) , edit_caozuoyuan.getText().toString(), result_year+"年"+result_month+"月"+result_day+"日"+ result_hour+"时"+result_minute+"分");
				
				}
				else                              //若存在时，找到该数据，更新该数据的库存量。
				{
					cursor.close();
					cursor =storageService.getCursorScrollData_code(code, 0, 20);
					cursor.moveToFirst();
					Integer  storage_quantity = cursor.getInt(9);
					storage_quantity=(int) (storage_quantity+stringToFloat(purchase_quantity));
					storageService.update_storage_quantity( storage_quantity,code);
					
				}
			}
		     		    		     
		              }catch(Exception e){
		               return;
		              }			


 }
 /********************************************************************/ 
 public void delete_huodan()
 {
	Cursor cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy_tuihuo), 0, 20);
		cursor.moveToFirst();
		String code;
		String purchase_quantity;
		int i=2;
		code=cursor.getString(1);
		purchase_quantity=cursor.getString(4);
		
		cursor =storageService.getCursorScrollData_code( code, 0, 20);   	   
		cursor.moveToFirst();	
		Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
		Integer a= Integer.valueOf(purchase_quantity);
	   	storage_quantity=storage_quantity-a;
		storageService.update_storage_quantity(storage_quantity,code);
		
	    cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy_tuihuo), 0, 20);
		cursor.moveToFirst();
		try{
			while(cursor.moveToNext()==true)
			{
				code=cursor.getString(1);
				purchase_quantity=cursor.getString(4);
				
				cursor =storageService.getCursorScrollData_code( code, 0, 20);   	   
				cursor.moveToFirst();	
				 storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
				 a= Integer.valueOf(purchase_quantity);
			   	storage_quantity=storage_quantity-a;
				storageService.update_storage_quantity(storage_quantity,code);
				
			    cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy_tuihuo), 0, 20);				
			    cursor.moveToPosition(i++);
			}
		     		    		     
		              }catch(Exception e){
		               return;
		              }			
	 
	 android.database.sqlite.SQLiteDatabase db;
	 db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);	    			
     db.execSQL("delete from buy_register where tobuy=?", new Object[] {tobuy_tuihuo});
     db.execSQL("delete from buy where buyid=?", new Object[] {tobuy_tuihuo});  
     edit_tuihuo_tuihuohao.setText("");
     show_caigou_tuihuo_clear() ;
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
	       		  
	       	Cursor cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy_tuihuo), 0, 20);  //判断新增商品在原来货单中是否存在	       	
	       	if(cursor.moveToNext()==false)    //如果货单中没有，则插入
	       	{
	       	android.database.sqlite.SQLiteDatabase db1;
		   	db1 = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);		   
		   	db1.execSQL("insert into buy_register(goods_id,product_name, product_price, purchase_quantity,tobuy) values( ?, ?, ?, ?, ?)",
		   			new Object[] {edit_xinzeng_shangpinbianhao.getText().toString(),edit_xinzeng_shangpinming.getText().toString(), 
		   			edit_xinzeng_jiage.getText().toString(),edit_xinzeng_goumailiang.getText().toString(),tobuy_tuihuo});
	       	}
	       	else                              //若存在，则更新购买数量
	       	{
	       		cursor =buyService_register.getCursorScrollData_tobuy(Integer.valueOf(tobuy_tuihuo), 0, 20);
	       		cursor.moveToFirst();
	       		Integer a= Integer.valueOf(edit_xinzeng_goumailiang.getText().toString());
	       		Integer purchase_quantity= cursor.getInt(cursor.getColumnIndex("purchase_quantity"))+a;
	       		buyService_register.update_purchase_quantity(purchase_quantity,tobuy_tuihuo);
	       		
	       	}
	      //从仓库中加上相应数量
		    cursor =storageService.getCursorScrollData_code( edit_xinzeng_shangpinbianhao.getText().toString(), 0, 20);   	   
  			cursor.moveToFirst();	   	
  			Integer storage_quantity= cursor.getInt(cursor.getColumnIndex("storage_quantity")); 
  			Integer a= Integer.valueOf(edit_xinzeng_goumailiang.getText().toString());
		   	storage_quantity=storage_quantity+a;
    		storageService.update_storage_quantity(storage_quantity,edit_xinzeng_shangpinbianhao.getText().toString());
  		
		   	show_caigou_tuihuo();
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
 

 
}
