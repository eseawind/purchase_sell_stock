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

public class ProviderInfo extends Activity {
	
	private EditText nameinfo_p;
	private EditText phoneinfo_p;
	private EditText addressinfo_p;
	private EditText postcodeinfo_p;
	private EditText mailinfo_p;
	
	private Button back_basicinfop;
	private Button delete_pinfo;
	private Button save_pinfo;
	
	private Bundle bundle_getpinfo;
	private Intent intent_get;
	
	ActionBar actionBar;
	
	private Integer ID;
	
	private String name;
	private String phone;
	private String address;
	private String postcode;
	private String mail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.providerinfo);
		
		actionBar=getActionBar();
		actionBar.show();
		
		nameinfo_p = (EditText)findViewById(R.id.nameinfop);
		phoneinfo_p = (EditText)findViewById(R.id.phoneinfop);
		addressinfo_p = (EditText)findViewById(R.id.addressinfop);
		postcodeinfo_p = (EditText)findViewById(R.id.postcodeinfop);
		mailinfo_p = (EditText)findViewById(R.id.mailinfop);
		
//		back_basicinfop = (Button)findViewById(R.id.back_basicinfop);
		delete_pinfo = (Button)findViewById(R.id.delete_pinfo);
		save_pinfo = (Button)findViewById(R.id.save_pinfo);
		
		intent_get = this.getIntent();
		bundle_getpinfo = intent_get.getExtras();
		ID = bundle_getpinfo.getInt("id");
		name = bundle_getpinfo.getString("name");
		phone = bundle_getpinfo.getString("phone");
		address = bundle_getpinfo.getString("address");
		postcode = bundle_getpinfo.getString("postcode");
		mail = bundle_getpinfo.getString("mail");
		
		nameinfo_p.setText(name);
		phoneinfo_p.setText(phone);
		addressinfo_p.setText(address);
		postcodeinfo_p.setText(postcode);
		mailinfo_p.setText(mail);
		
//		Toast.makeText(getApplicationContext(), ID.toString(),
//			     Toast.LENGTH_SHORT).show();
		
		/**
		 * 返回基本信息页面
		 */
		
//		back_basicinfop.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//				
//			}});
		
		/**
		 * 删除该条数据
		 */
		delete_pinfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(ProviderInfo.this)
				.setTitle("确定要删除供货商信息吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {				
						android.database.sqlite.SQLiteDatabase db;
						db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);						
						db.execSQL("delete from provider where providerid=?", new Object[] {ID});						
						
					    nameinfo_p.setText("NULL");
						phoneinfo_p.setText("NULL");
						addressinfo_p.setText("NULL");
						postcodeinfo_p.setText("NULL");
						mailinfo_p.setText("NULL");						
							
						Toast.makeText(getApplicationContext(), "删除成功！",
								     Toast.LENGTH_SHORT).show();
						
					}}).setNegativeButton("取消", null).create().show();
				
//				finish();
			}});
		
		
		/**
		 * 保存数据
		 */
		
		save_pinfo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update provider set name=?, phone=?, address=?, postcode=?, mail=? where providerid=?",
						new Object[] {nameinfo_p.getText().toString(), phoneinfo_p.getText().toString(), addressinfo_p.getText().toString(), 
						postcodeinfo_p.getText().toString(), mailinfo_p.getText().toString(), ID});
				
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
