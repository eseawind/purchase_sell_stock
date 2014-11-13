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
 * 商品信息显示页面，用于信息的编辑和删除
 * @author whl
 *
 */

public class GoodsInfo extends Activity {
	
	private EditText gnameinfo;
	private EditText gareainfo;
	private EditText gproductorinfo;
	private EditText gprodateinfo;
	private EditText gqualityinfo;
	private EditText gbidinfo;
	private EditText gpriceinfo;
	
	private Button gback_basicinfo;
	private Button delete_ginfo;
	private Button save_ginfo;
	
	private Bundle bundle_getginfo;
	private Intent intent_get;
	
	ActionBar actionBar;
	
	private Integer ID;
	
	private String name;
	private String area;
	private String producter;
	private String date;
	private Integer quality;
	private Integer bid;
	private Integer price;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodsinfo);
		
		actionBar=getActionBar();
		actionBar.show();
		
		gnameinfo = (EditText)findViewById(R.id.gnameinfo);
		gareainfo = (EditText)findViewById(R.id.gareainfo);
		gproductorinfo = (EditText)findViewById(R.id.gproductorinfo);
		gprodateinfo = (EditText)findViewById(R.id.gprodateinfo);
		gqualityinfo = (EditText)findViewById(R.id.gqualityinfo);
		gbidinfo = (EditText)findViewById(R.id.gbidinfo);
		gpriceinfo = (EditText)findViewById(R.id.gpriceinfo);
		
//		gback_basicinfo = (Button)findViewById(R.id.gback_basicinfo);
		delete_ginfo = (Button)findViewById(R.id.delete_ginfo);
		save_ginfo = (Button)findViewById(R.id.save_ginfo);
		
		intent_get = this.getIntent();
		bundle_getginfo = intent_get.getExtras();
		ID = bundle_getginfo.getInt("id");
		name = bundle_getginfo.getString("name");
		area = bundle_getginfo.getString("area");
		producter = bundle_getginfo.getString("producter");
		date = bundle_getginfo.getString("date");
		quality = bundle_getginfo.getInt("quality");
		bid = bundle_getginfo.getInt("bid");
		price = bundle_getginfo.getInt("price");
		
		gnameinfo.setText(name);
		gareainfo.setText(area);
		gproductorinfo.setText(producter);
		gprodateinfo.setText(date);
		gqualityinfo.setText(quality.toString());
		gbidinfo.setText(bid.toString());
		gpriceinfo.setText(price.toString());
		
		/**
		 * 返回基本信息页面
		 */
		
//		gback_basicinfo.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//				
//			}});
		
		/**
		 * 删除该条数据--------异常(NullPointerException)
		 */
		
		delete_ginfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(GoodsInfo.this)
				.setTitle("确定要删除商品信息吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {				
						android.database.sqlite.SQLiteDatabase db;
						db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);						
						db.execSQL("delete from goods where goodsid=?", new Object[] {ID});						
						
					    gnameinfo.setText("NULL");
					    gareainfo.setText("NULL");
					    gproductorinfo.setText("NULL");
					    gprodateinfo.setText("NULL");
					    gqualityinfo.setText("NULL");
					    gbidinfo.setText("NULL");
					    gpriceinfo.setText("NULL");
							
						Toast.makeText(getApplicationContext(), "删除成功！",
								     Toast.LENGTH_SHORT).show();
						
					}}).setNegativeButton("取消", null).create().show();
				
//				finish();
			}});
		
		/**
		 * 保存数据
		 */
		
		save_ginfo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				android.database.sqlite.SQLiteDatabase db;
				db = SQLiteDatabase.openOrCreateDatabase("data/data/com.shixi.jxctest/databases/basicInfo.db", null);
				db.execSQL("update goods set name=?, area=?, producter=?, date=?, quality=?, bid=?, price=? where goodsid=?",
						new Object[] {gnameinfo.getText().toString(), gareainfo.getText().toString(), gproductorinfo.getText().toString(), 
						gprodateinfo.getText().toString(), gqualityinfo.getText().toString(), gbidinfo.getText().toString(), gpriceinfo.getText().toString(),  ID});
				
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
