package com.shixi.jxctest;

import com.shixi.service.BuyService;
import com.shixi.service.BuyServiceRegister;
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
import android.widget.ListView;
import android.widget.TextView;


public  class ChaXunHuoDan extends Activity {
	
	//implements View.OnClickListener
	private Intent intent_get;
	private int ID;
	
	private String buy_name;
	private String operator_name;
	private String time;
	private String pay;
	private String debt;
	private Bundle bundle_getcinfo;
	
	private TextView view_caigou_chaxuntanchu_jinhuohao;
	private TextView view_caigou_chaxuntanchu_jinhuoshang;
	private TextView view_caigou_chaxuntanchu_caozuoyuan;
	private TextView view_caigou_chaxuntanchu_tradetime;
	private TextView view_caigou_chaxuntanchu_jine;
	private TextView view_caigou_chaxuntanchu_fukuan;
	private TextView view_caigou_chaxuntanchu_qiankuan;
	
	private ListView listView_chaxun_tanchu;
	
	BuyService buyService ;
	private BuyServiceRegister buyService_register;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/********************************************************************/	
		actionBar=getActionBar();
		actionBar.show();
/********************************************************************/
		setContentView(R.layout.chaxunhuodantanchu);
	
		
	        
	        intent_get = this.getIntent();
			bundle_getcinfo = intent_get.getExtras();
			ID = bundle_getcinfo.getInt("id");
			buy_name = bundle_getcinfo.getString("buy_name");
			operator_name = bundle_getcinfo.getString("operator_name");
			time = bundle_getcinfo.getString("time");
			pay= bundle_getcinfo.getString("pay");
			debt= bundle_getcinfo.getString("debt");
	        
			
			view_caigou_chaxuntanchu_jinhuohao=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_jinhuohao);
			view_caigou_chaxuntanchu_jinhuoshang=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_jinhuoshang);
			view_caigou_chaxuntanchu_caozuoyuan=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_caozuoyuan);
			view_caigou_chaxuntanchu_tradetime=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_tradetime);
			view_caigou_chaxuntanchu_jine=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_jine);
			view_caigou_chaxuntanchu_fukuan=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_fukuan);
			view_caigou_chaxuntanchu_qiankuan=(TextView)findViewById(R.id.view_caigou_chaxuntanchu_qiankuan);
			listView_chaxun_tanchu = (ListView)findViewById(R.id.listView_caigou_chaxun_tanchu);
			
									
			buyService = new BuyService(this);
	    	buyService_register = new BuyServiceRegister(this);
	    	view_caigou_chaxuntanchu_jinhuohao.setText(String.valueOf(ID));
	    	view_caigou_chaxuntanchu_jinhuoshang.setText(buy_name);
	    	view_caigou_chaxuntanchu_caozuoyuan.setText(operator_name);
	    	view_caigou_chaxuntanchu_tradetime.setText(time);			
	    	view_caigou_chaxuntanchu_fukuan.setText(pay);
		
	    	Cursor cursor =buyService_register.getCursorScrollData_tobuy(ID, 0, 20);				
			
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
			view_caigou_chaxuntanchu_jine.setText(trade_jine);
			view_caigou_chaxuntanchu_qiankuan.setText(debt);
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
			intent.setClass( ChaXunHuoDan.this,CaiGouActivity.class);	
			startActivity(intent);
			ChaXunHuoDan.this.finish();
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
