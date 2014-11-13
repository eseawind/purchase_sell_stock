package com.shixi.jxctest;

import com.shixi.service.SailService;
import com.shixi.jxctest. XiaoShouActivity;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public  class QianKuanXianShi extends Activity implements View.OnClickListener {
	

	private Intent intent_get;
	private Integer ID;

	private String customer_name;
	private String customer_pay;
	private String customer_debt;

	
	private Bundle bundle_getcinfo;
	private EditText edit_huankuan_huankuan;
	
	private TextView textview_huankuan_dingdanhao;
	private TextView textview_huankuan_kehuming;
	private TextView textview_huankuan_qiankuan;
	
	
	SailService sailService ;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.huankuan); 
	 /********************************************************************/	
			actionBar=getActionBar();
			actionBar.show();
	/********************************************************************/	
	        
	        intent_get = this.getIntent();
			bundle_getcinfo = intent_get.getExtras();
			ID = bundle_getcinfo.getInt("id");
			
			customer_name= bundle_getcinfo.getString("customer_name");
			customer_pay= bundle_getcinfo.getString("customer_pay");
			customer_debt= bundle_getcinfo.getString("customer_debt");
			
			sailService = new SailService(this);
			
			Button button1 = (Button)findViewById(R.id.button_huankuan_ok);  
	        Button button2 = (Button)findViewById(R.id.button_huankuan_cancel); 
	        button1.setOnClickListener(this);  
	        button1.setTag(1);  
	        button2.setOnClickListener(this);  
	        button2.setTag(2);  
			
			textview_huankuan_dingdanhao=(TextView)findViewById(R.id.textview_huankuan_dingdanhao);
			textview_huankuan_kehuming=(TextView)findViewById(R.id.textview_huankuan_kehuming);
			textview_huankuan_qiankuan=(TextView)findViewById(R.id.textview_huankuan_qiankuan);
			edit_huankuan_huankuan= (EditText)findViewById(R.id.edittext_huankuan_huankuan);
			edit_huankuan_huankuan.setText("");
			  
		textview_huankuan_dingdanhao.setText(String.valueOf(ID));
		textview_huankuan_kehuming.setText(customer_name);
		textview_huankuan_qiankuan.setText(customer_debt);	
		
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 int tag = (Integer) v.getTag();  
	        switch(tag){  
	        case 1: 
	        	if("".equals(edit_huankuan_huankuan.getText().toString().trim()))
	       		  Toast.makeText(getApplicationContext(), "输入不能为空！",
	    				     Toast.LENGTH_SHORT).show();
	       	  else
	  			      
	  			   { 
	       		     Float a= stringToFloat(customer_pay);
	       		     Float b= stringToFloat(customer_debt);
	       		     Float c= stringToFloat(edit_huankuan_huankuan.getText().toString());
	       		     Float d=a+c;
	       		     Float e=b-c;
	  			     sailService.update_pay(String.valueOf(d), Integer.parseInt(String.valueOf(ID)));
	  			     sailService.update_debt(String.valueOf(e),Integer.parseInt(String.valueOf(ID)));
	  			     Toast.makeText(getApplicationContext(), "还款成功！",
	  		  				     Toast.LENGTH_SHORT).show();
	  				    Intent intent = new Intent(); 
						intent.setClass( QianKuanXianShi.this,XiaoShouActivity.class);	
						startActivity(intent);
						QianKuanXianShi.this.finish();
	  			   }
	        	
	            break;      
	        case 2: 
	        	
	        	    Intent intent = new Intent(); 
					intent.setClass( QianKuanXianShi.this,XiaoShouActivity.class);	
					startActivity(intent);
					QianKuanXianShi.this.finish();
	            break;    
	        default :  
	            break;  
	        }  
	        
	}
/************************字符String转换成Float类型****************************************/
	  public static float stringToFloat(String floatstr) {
		  Float floatee;
		  floatee = Float.valueOf(floatstr);
		  return floatee.floatValue();
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
			intent.setClass( QianKuanXianShi.this,XiaoShouActivity.class);	
			startActivity(intent);
			QianKuanXianShi.this.finish();
	        return super.onOptionsItemSelected(item);
	    }	
}
