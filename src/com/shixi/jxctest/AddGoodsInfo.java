package com.shixi.jxctest;

import com.shixi.domain.Goods;
import com.shixi.service.TableService;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGoodsInfo extends Activity {
	
//	private DBOpenHelper dbOpenHelper;
	private EditText addgname;
	private EditText addgarea;
	private EditText addgproducter;
	private EditText addgdate;
	private EditText addgquality;
	private EditText addgbid;
	private EditText addgprice;
	private EditText addgcode;
	
	private Button save;
	private Button cancel;
	
	private TableService tbservice;
	private Goods goods;
	
	ActionBar actionBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addgoods);
		
		actionBar=getActionBar();
		actionBar.show();
		
		addgname = (EditText)findViewById(R.id.addgnameinfo);
		addgarea = (EditText)findViewById(R.id.addgareainfo);
		addgproducter = (EditText)findViewById(R.id.addgproductorinfo);
		addgdate = (EditText)findViewById(R.id.addgprodateinfo);
		addgquality = (EditText)findViewById(R.id.addgqualityinfo);
		addgbid = (EditText)findViewById(R.id.addgbidinfo);
		addgprice = (EditText)findViewById(R.id.addgpriceinfo);
		addgcode = (EditText)findViewById(R.id.addgcode);	
		
		//捕获焦点，但不弹出键盘
//		addgcode.setInputType(InputType.TYPE_NULL);
		
		save = (Button)findViewById(R.id.finish_ginfo);
		cancel = (Button)findViewById(R.id.cancel_ginfo);
		
		save.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("insert into goods(code, name, area, producter, date, quality, bid, price) values(?, ?, ?, ?, ?, ?, ?, ?)",
						new Object[] {addgcode.getText().toString(), addgname.getText().toString(), addgarea.getText().toString(), addgproducter.getText().toString(),
						addgdate.getText().toString(), addgquality.getText().toString(), addgbid.getText().toString(), addgprice.getText().toString()});
				
				Toast.makeText(getApplicationContext(), "保存成功！",
					     Toast.LENGTH_SHORT).show();
				
				finish();
			}});
		
		cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_nomenu, menu);
		actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg));
		return true;
	}
}
