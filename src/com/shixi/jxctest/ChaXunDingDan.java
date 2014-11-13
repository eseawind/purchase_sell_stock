package com.shixi.jxctest;

import com.shixi.service.SailService;
import com.shixi.service.SailServiceRegister;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public  class ChaXunDingDan extends Activity {
	
	//implements View.OnClickListener
	private Intent intent_get;
	private Integer ID;
	
	private String customer_name;
	private String operator_name;
	private String trade_time;
	private String customer_pay;
	private String customer_debt;
	private Bundle bundle_getcinfo;
	
	
	private TextView xianshi3_view_diangdanhao;
	private TextView xianshi3_view_kehuming;
	private TextView xianshi3_view_caozuoyuan;
	private TextView xianshi3_view_tradetime;
	private TextView view_chaxuntanchu_jine;
	private TextView view_chaxuntanchu_fukuan;
	private TextView view_chaxuntanchu_qiankuan;
	
	private ListView listView_chaxun_tanchu;
	
	SailService sailService ;
	private SailServiceRegister sailService_register;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.chaxundingdantanchu);
	
	       
	        
	        intent_get = this.getIntent();
			bundle_getcinfo = intent_get.getExtras();
			ID = bundle_getcinfo.getInt("id");
			customer_name = bundle_getcinfo.getString("customer_name");
			operator_name = bundle_getcinfo.getString("operator_name");
			trade_time = bundle_getcinfo.getString("trade_time");
			customer_pay= bundle_getcinfo.getString("customer_pay");
			customer_debt= bundle_getcinfo.getString("customer_debt");
	        
			
		    xianshi3_view_diangdanhao=(TextView)findViewById(R.id.xianshi3_view_diangdanhao);
			xianshi3_view_kehuming=(TextView)findViewById(R.id.xianshi3_view_kehuming);
			xianshi3_view_tradetime=(TextView)findViewById(R.id.xianshi3_view_tradetime);
			xianshi3_view_caozuoyuan=(TextView)findViewById(R.id.xianshi3_view_caozuoyuan);
			view_chaxuntanchu_jine=(TextView)findViewById(R.id.view_chaxuntanchu_jine);
			view_chaxuntanchu_fukuan=(TextView)findViewById(R.id.view_chaxuntanchu_fukuan);
			view_chaxuntanchu_qiankuan=(TextView)findViewById(R.id.view_chaxuntanchu_qiankuan);
			listView_chaxun_tanchu = (ListView)findViewById(R.id.listView_chaxun_tanchu);
			
									
			sailService = new SailService(this);
	    	sailService_register = new SailServiceRegister(this);
	    	    xianshi3_view_diangdanhao.setText(String.valueOf(ID));
				xianshi3_view_kehuming.setText(customer_name);
				xianshi3_view_caozuoyuan.setText(operator_name);
				xianshi3_view_tradetime.setText(trade_time);			
				view_chaxuntanchu_fukuan.setText(customer_pay);
		
		  Cursor cursor =sailService_register.getCursorScrollData_tosail(ID, 0, 20);
		  SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.chaxundingdantantanchu_listview, cursor,
					new String[] {"product_name", "product_price", "purchase_quantity"}, new int[] {R.id.product_name, R.id.product_price, R.id.purchase_quantity}, 
					CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);							
		  listView_chaxun_tanchu.setAdapter(adapter);
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
			String	trade_jine  =String.valueOf(k) ;
			view_chaxuntanchu_jine.setText(trade_jine);
			view_chaxuntanchu_qiankuan.setText(customer_debt);
			              }catch(Exception e){
			               return;
			              }			
		/*******************************************/
		  
		   
			
	}

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
			intent.setClass( ChaXunDingDan.this,XiaoShouActivity.class);	
			startActivity(intent);
			ChaXunDingDan.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
 
/****************************************************************/	
/************************字符String转换成Float类型****************************************/
	  public static float stringToFloat(String floatstr) {
		  Float floatee;
		  floatee = Float.valueOf(floatstr);
		  return floatee.floatValue();
		  }
/****************************************************************/
}
