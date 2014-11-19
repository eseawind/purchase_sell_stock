/**
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

package com.shixi.jxctest;

import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomerInfo extends Activity {
	
	private EditText addname;
	private EditText addphone;
	private EditText addaddress;
	private EditText addpostcode;
	private EditText addcmail;
	private EditText addcompany;
	
	private Button save;
	private Button cancel;
	
	ActionBar actionBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.naddcustomer);
		
		actionBar=getActionBar();
		actionBar.show();
		
		addname = (EditText)findViewById(R.id.add_c_name);
		addphone = (EditText)findViewById(R.id.add_c_phone);
		addaddress = (EditText)findViewById(R.id.add_c_address);
		addpostcode = (EditText)findViewById(R.id.add_c_postcode);
		addcmail = (EditText)findViewById(R.id.add_c_cmail);
		addcompany = (EditText)findViewById(R.id.add_c_company);
		
		save = (Button)findViewById(R.id.finish_cinfo);
		cancel = (Button)findViewById(R.id.cancel_cinfo);
		
		save.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("insert into customer(name, phone, address, postcode, cmail, company) values(?, ?, ?, ?, ?, ?)",
						new Object[] {addname.getText().toString(), addphone.getText().toString(), addaddress.getText().toString(),
						addpostcode.getText().toString(), addcmail.getText().toString(), addcompany.getText().toString()});
				
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
