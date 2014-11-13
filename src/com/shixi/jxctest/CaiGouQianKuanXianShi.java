package com.shixi.jxctest;

import com.shixi.service.BuyService;
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


public  class CaiGouQianKuanXianShi extends Activity implements View.OnClickListener {
	

	private Intent intent_get;
	private Integer ID;

	private String buy_name;
	private String pay;
	private String debt;

	
	private Bundle bundle_getcinfo;
	private EditText edit_caigou_huankuan_huankuan;
	
	private TextView textview_caigou_huankuan_jinhuohao;
	private TextView textview_caigou_huankuan_jinhuoshang;
	private TextView textview_caigou_huankuan_qiankuan;
	
	
	BuyService buyService ;
	ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.caigouhuankuan); 
    /********************************************************************/	
			actionBar=getActionBar();
			actionBar.show();
	/********************************************************************/		
	        
	        intent_get = this.getIntent();
			bundle_getcinfo = intent_get.getExtras();
			ID = bundle_getcinfo.getInt("id");
			
			buy_name= bundle_getcinfo.getString("buy_name");
			pay= bundle_getcinfo.getString("pay");
			debt= bundle_getcinfo.getString("debt");
			
			buyService = new BuyService(this);
			
			Button button1 = (Button)findViewById(R.id.button_caigou_huankuan_ok);  
	        Button button2 = (Button)findViewById(R.id.button_caigou_huankuan_cancel); 
	        button1.setOnClickListener(this);  
	        button1.setTag(1);  
	        button2.setOnClickListener(this);  
	        button2.setTag(2);  
	        
	    	textview_caigou_huankuan_jinhuohao=(TextView)findViewById(R.id.textview_caigou_huankuan_jinhuohao);
	    	textview_caigou_huankuan_jinhuoshang=(TextView)findViewById(R.id.textview_caigou_huankuan_jinhuoshang);
	    	textview_caigou_huankuan_qiankuan=(TextView)findViewById(R.id.textview_caigou_huankuan_qiankuan);
	    	edit_caigou_huankuan_huankuan= (EditText)findViewById(R.id.edittext_caigou_huankuan_huankuan);
	    	edit_caigou_huankuan_huankuan.setText("");
			  
	    	textview_caigou_huankuan_jinhuohao.setText(String.valueOf(ID));
	    	textview_caigou_huankuan_jinhuoshang.setText(buy_name);
	    	textview_caigou_huankuan_qiankuan.setText(debt);	
		
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 int tag = (Integer) v.getTag();  
	        switch(tag){  
	        case 1: 
	        	if("".equals(edit_caigou_huankuan_huankuan.getText().toString().trim()))
	       		  Toast.makeText(getApplicationContext(), "输入不能为空！",
	    				     Toast.LENGTH_SHORT).show();
	       	  else
	  			      
	  			   { 
	       		     Float a= stringToFloat(pay);
	       		     Float b= stringToFloat(debt);
	       		     Float c= stringToFloat(edit_caigou_huankuan_huankuan.getText().toString());
	       		     Float d=a+c;
	       		     Float e=b-c;
	  			     buyService.update_pay(String.valueOf(d), Integer.parseInt(String.valueOf(ID)));
	  			     buyService.update_debt(String.valueOf(e),Integer.parseInt(String.valueOf(ID)));
	  			     Toast.makeText(getApplicationContext(), "还款成功！",
	  		  				     Toast.LENGTH_SHORT).show();
	  				    Intent intent = new Intent(); 
						intent.setClass( CaiGouQianKuanXianShi.this,CaiGouActivity.class);	
						startActivity(intent);
						CaiGouQianKuanXianShi.this.finish();
	  			   }
	        	
	            break;      
	        case 2: 
	        	
	        	    Intent intent = new Intent(); 
					intent.setClass( CaiGouQianKuanXianShi.this,CaiGouActivity.class);	
					startActivity(intent);
					CaiGouQianKuanXianShi.this.finish();
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
			intent.setClass( CaiGouQianKuanXianShi.this,CaiGouActivity.class);	
			startActivity(intent);
			CaiGouQianKuanXianShi.this.finish();
	        return super.onOptionsItemSelected(item);
	    }
	
}
