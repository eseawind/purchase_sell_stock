package com.shixi.jxctest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 客户信息显示页面，用于信息的编辑和删除
 * @author whl
 *
 */

public class CustomerInfo extends Activity {
	
	private EditText nameinfo_c;
	private EditText phoneinfo_c;
	private EditText addressinfo_c;
	private EditText postcodeinfo_c;
	private EditText cmailinfo_c;
	private EditText companyinfo_c;
	
	private Button back_basicinfo;
	private Button delete_cinfo;
	private Button save_cinfo;
	
	private Bundle bundle_getcinfo;
	private Intent intent_get;
	
	ActionBar actionBar;
	
	private Integer ID;
	
	private String name;
	private String phone;
	private String address;
	private String postcode;
	private String cmail;
	private String company;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ncustomerinfo);
		
		actionBar=getActionBar();
		actionBar.show();
		
		nameinfo_c = (EditText)findViewById(R.id.nameinfo1);
		phoneinfo_c = (EditText)findViewById(R.id.phoneinfo1);
		addressinfo_c = (EditText)findViewById(R.id.addressinfo1);
		postcodeinfo_c = (EditText)findViewById(R.id.postcodeinfo1);
		cmailinfo_c = (EditText)findViewById(R.id.cmailinfo1);
		companyinfo_c = (EditText)findViewById(R.id.companyinfo1);
		
//		back_basicinfo = (Button)findViewById(R.id.back_basicinfo);
		delete_cinfo = (Button)findViewById(R.id.delete_cinfo);
		save_cinfo = (Button)findViewById(R.id.save_cinfo);
		
		intent_get = this.getIntent();
		bundle_getcinfo = intent_get.getExtras();
		ID = bundle_getcinfo.getInt("id");
		name = bundle_getcinfo.getString("name");
		phone = bundle_getcinfo.getString("phone");
		address = bundle_getcinfo.getString("address");
		postcode = bundle_getcinfo.getString("postcode");
		cmail = bundle_getcinfo.getString("cmail");
		company = bundle_getcinfo.getString("company");
		
		nameinfo_c.setText(name);
		phoneinfo_c.setText(phone);
		addressinfo_c.setText(address);
		postcodeinfo_c.setText(postcode);
		cmailinfo_c.setText(cmail);
		companyinfo_c.setText(company);
		
		/**
		 * 返回基本信息页面
		 */
		
//		back_basicinfo.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//				
//			}});
		
		/**
		 * 删除该条数据--------异常(NullPointerException)
		 */
		
		delete_cinfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(CustomerInfo.this)
				.setTitle("确定要删除客户信息吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {				
						android.database.sqlite.SQLiteDatabase db;
						db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);						
						db.execSQL("delete from customer where customerid=?", new Object[] {ID});						
						
					    nameinfo_c.setText("NULL");
						phoneinfo_c.setText("NULL");
						addressinfo_c.setText("NULL");
						postcodeinfo_c.setText("NULL");
						cmailinfo_c.setText("NULL");
						companyinfo_c.setText("NULL");
							
						Toast.makeText(getApplicationContext(), "删除成功！",
								     Toast.LENGTH_SHORT).show();
						
					}}).setNegativeButton("取消", null).create().show();
				
//				finish();
			}});
		
		/**
		 * 保存数据
		 */
		
		save_cinfo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update customer set name=?, phone=?, address=?, postcode=?, cmail=?, company=? where customerid=?",
						new Object[] {nameinfo_c.getText().toString(), phoneinfo_c.getText().toString(), addressinfo_c.getText().toString(), 
						postcodeinfo_c.getText().toString(), cmailinfo_c.getText().toString(), companyinfo_c.getText().toString(), ID});
				
				Toast.makeText(getApplicationContext(), "保存成功！",
					     Toast.LENGTH_SHORT).show();
				
				finish();
			}});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_nomenu, menu);
		actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg));
		
		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
