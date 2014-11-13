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

public class StaffInfo extends Activity {
	
	private EditText nameinfo_s;
	private EditText phoneinfo_s;
	private EditText addressinfo_s;
	private EditText postcodeinfo_s;
	private EditText mailinfo_s;
	
	private Button back_basicinfos;
	private Button delete_sinfo;
	private Button save_sinfo;
	
	private Bundle bundle_getsinfo;
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
		setContentView(R.layout.staffinfo);
		
		actionBar=getActionBar();
		actionBar.show();
		
		nameinfo_s = (EditText)findViewById(R.id.nameinfos);
		phoneinfo_s = (EditText)findViewById(R.id.phoneinfos);
		addressinfo_s = (EditText)findViewById(R.id.addressinfos);
		postcodeinfo_s = (EditText)findViewById(R.id.postcodeinfos);
		mailinfo_s = (EditText)findViewById(R.id.mailinfos);
		
//		back_basicinfos = (Button)findViewById(R.id.back_basicinfos);
		delete_sinfo = (Button)findViewById(R.id.delete_sinfo);
		save_sinfo = (Button)findViewById(R.id.save_sinfo);
		
		intent_get = this.getIntent();
		bundle_getsinfo = intent_get.getExtras();
		ID = bundle_getsinfo.getInt("id");
		name = bundle_getsinfo.getString("name");
		phone = bundle_getsinfo.getString("phone");
		address = bundle_getsinfo.getString("address");
		postcode = bundle_getsinfo.getString("postcode");
		mail = bundle_getsinfo.getString("mail");
		
		nameinfo_s.setText(name);
		phoneinfo_s.setText(phone);
		addressinfo_s.setText(address);
		postcodeinfo_s.setText(postcode);
		mailinfo_s.setText(mail);
		
//		Toast.makeText(getApplicationContext(), ID.toString(),
//			     Toast.LENGTH_SHORT).show();
		
		/**
		 * 返回基本信息页面
		 */
		
//		back_basicinfos.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//				
//			}});
		
		/**
		 * 删除该条数据
		 */
		delete_sinfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(StaffInfo.this)
				.setTitle("确定要删除供货商信息吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {				
						android.database.sqlite.SQLiteDatabase db;
						db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);						
						db.execSQL("delete from staff where staffid=?", new Object[] {ID});						
						
					    nameinfo_s.setText("NULL");
						phoneinfo_s.setText("NULL");
						addressinfo_s.setText("NULL");
						postcodeinfo_s.setText("NULL");
						mailinfo_s.setText("NULL");						
							
						Toast.makeText(getApplicationContext(), "删除成功！",
								     Toast.LENGTH_SHORT).show();
						
					}}).setNegativeButton("取消", null).create().show();
				
//				finish();
			}});
		
		
		/**
		 * 保存数据
		 */
		
		save_sinfo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update staff set name=?, phone=?, usrgroup=?, postcode=?, mail=? where staffid=?",
						new Object[] {nameinfo_s.getText().toString(), phoneinfo_s.getText().toString(), addressinfo_s.getText().toString(), 
						postcodeinfo_s.getText().toString(), mailinfo_s.getText().toString(), ID});
				
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
