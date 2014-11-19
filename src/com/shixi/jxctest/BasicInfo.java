package com.shixi.jxctest;

//import com.psiapp.tabhosttest.R;

import com.shixi.domain.Customer;
import com.shixi.domain.Goods;
import com.shixi.domain.Provider;
import com.shixi.domain.Staff;
import com.shixi.service.TableService;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;


/**
 * 基本信息显示页面，显示数据库中的各个表
 * @author whl
 *
 */

public class BasicInfo extends Activity {
	
	private ListView listView;		//Tab1表
	private ListView listView2;		//Tab2表
	private ListView listView3;		//Tab3表
	private ListView listView4;		//Tab4表
	
	public TableService tableService; 
	public Provider provider;
	public Customer customer;
	public Goods goods;
	public Staff staff;	
	
	private Button button_find2;
	private Button button_add2;
	private Button button_back1;
	private Button addgoods;
	private Button findgoods;
	private Button addprovider;
	private Button findprovider;
	private Button goodsback;
	private Button addstaff;
	private Button findstaff;
	private Button backbasicp;
	private Button backbasics;
	
	private EditText findcname;
	private EditText findgname;
	private EditText findpname;
	private EditText findsname;
	
	private Integer ID;
	private Integer ID_g;
	private Integer ID_p;
	private Integer ID_s;
	private Integer id_c;
	private Integer id_g;
	private Intent intent_cinfo;
	private Intent intent_ginfo;
	private Intent intent_sinfo;
	
	private LinearLayout linearLayout;
	private LinearLayout glinearLayout;
	private LinearLayout plinearLayout;
	private LinearLayout slinearLayout;
	
	public static int  tabshow_flag=0;
	
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basicinfo_tab);
		
		actionBar=getActionBar();
		actionBar.show();
		
		linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findcname, null);
		glinearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findgname, null);
		
		/**
		 * 添加TabHost
		 */
		TabHost tabHost = (TabHost)findViewById(R.id.tabs);
		
		tabHost.setup();
		
		TabSpec firstSpec = tabHost.newTabSpec("first");
		firstSpec.setIndicator("进货商档案", null);
		firstSpec.setContent(R.id.tab1);

		TabSpec secondSpec = tabHost.newTabSpec("second");
		secondSpec.setIndicator("客户档案", null);
		secondSpec.setContent(R.id.tab2);
		
		TabSpec thirdSpec = tabHost.newTabSpec("third");
		thirdSpec.setIndicator("货品档案", null);
		thirdSpec.setContent(R.id.tab3);
		
		TabSpec fourthSpec = tabHost.newTabSpec("fourth");
		fourthSpec.setIndicator("员工档案", null);
		fourthSpec.setContent(R.id.tab4);

		//添加选项卡到TabHost中
		tabHost.addTab(firstSpec);
		tabHost.addTab(secondSpec);
		tabHost.addTab(thirdSpec);
		tabHost.addTab(fourthSpec);
		
		button_add2 = (Button)findViewById(R.id.button_add2);
		button_find2 = (Button)findViewById(R.id.button_find2);
		addgoods = (Button)findViewById(R.id.addgoods);
		findgoods = (Button)findViewById(R.id.findgoods);
		addprovider = (Button)findViewById(R.id.button_addp);
		findprovider = (Button)findViewById(R.id.button_findp);
		addstaff = (Button)findViewById(R.id.button_adds);
		findstaff = (Button)findViewById(R.id.button_finds);
		
		new AlertDialog.Builder(this);
		
		/**
		 * 创建并显示数据库
		 */
		tableService = new TableService(this);
		listView = (ListView)findViewById(R.id.listView_p);
		listView2 = (ListView)findViewById(R.id.listView2);
		listView3 = (ListView)findViewById(R.id.listView3);
		listView4 = (ListView)findViewById(R.id.listView_s);
		listView.setOnItemClickListener(new ItemClickListener());
		listView2.setOnItemClickListener(new ItemClickListener2());
		listView3.setOnItemClickListener(new ItemClickListener3());
		listView4.setOnItemClickListener(new ItemClickListener4());
		
		switch(tabshow_flag)
		{
			case 0:
				tabHost.setCurrentTab(0);
				show();
			    break;
			case 1:
				tabHost.setCurrentTab(1);
				show2();
				break;
			case 2:
				tabHost.setCurrentTab(2);
				showgoods();
				break;
			case 3:				
				tabHost.setCurrentTab(3);
				show_staff();
				break;
			
			default:break;
		
		}
		
		show();
		show2();
		showgoods();
		show_staff();
		
		/**
		 * 未输入姓名时无法点击添加按钮
		 */	
//		if(name_edit.equals("")) {
//			button_add.setEnabled(false);
//		}								
		
		/**
		 * Provider数据添加功能，完成对数据库的修改
		 */
		addprovider.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tabshow_flag = 0;
				
				Intent intent = new Intent();
				intent.setClass(BasicInfo.this, AddProviderInfo.class);
				startActivity(intent);
				
			}});
		
		/**
		 * Staff数据添加功能，完成对数据库的修改
		 */
		addstaff.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				tabshow_flag = 3;
				Intent intent = new Intent();
				intent.setClass(BasicInfo.this, AddStaffInfo.class);
				startActivity(intent);
			}});
		
		
		/**
		 * Tab1数据查找功能，完成对数据库的查询
		 */		
		findprovider.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				plinearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findpname, null);
			    new  AlertDialog.Builder(BasicInfo.this)  
			    .setTitle("请输入要查找供货商的名称" )  
			    .setView(plinearLayout)  
			    .setPositiveButton("确定" , new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						findpname = (EditText)plinearLayout.findViewById(R.id.findpname);
						show_findp();
//						Toast.makeText(getApplicationContext(), findcname.getText().toString(),
//							     Toast.LENGTH_SHORT).show();
						
					}} )  
			    .setNegativeButton("取消" , null )  
			    .show();   
			}});
		
		/**
		 * Tab2数据查找功能
		 */
		button_find2.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findcname, null);
			    new  AlertDialog.Builder(BasicInfo.this)  
			    .setTitle("请输入要查找客户的姓名" )  
			    .setView(linearLayout)  
			    .setPositiveButton("确定" , new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						findcname = (EditText)linearLayout.findViewById(R.id.findcname);
						show3();
//						Toast.makeText(getApplicationContext(), findcname.getText().toString(),
//							     Toast.LENGTH_SHORT).show();
						
					}} )  
			    .setNegativeButton("取消" , null )  
			    .show();   
			}});
		
		/**
		 * Tab3数据查找功能
		 */
		findgoods.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				glinearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findgname, null);
			    new  AlertDialog.Builder(BasicInfo.this)  
			    .setTitle("请输入要查找的商品的名称：" )  
			    .setView(glinearLayout)  
			    .setPositiveButton("确定" , new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						findgname = (EditText)glinearLayout.findViewById(R.id.findgname);
						showfindgoods();
						
					}} )  
			    .setNegativeButton("取消" , null )  
			    .show();   
			}});
		
		/**
		 * Tab4数据查找功能，完成对数据库的查询
		 */		
		findstaff.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				slinearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.findsname, null);
			    new  AlertDialog.Builder(BasicInfo.this)  
			    .setTitle("请输入要查找职工的姓名" )  
			    .setView(slinearLayout)  
			    .setPositiveButton("确定" , new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						findsname = (EditText)slinearLayout.findViewById(R.id.findsname);
						show_finds();				
					}} )  
			    .setNegativeButton("取消" , null )  
			    .show();   
			}});
		
	
		/**
		 * Tab2返回功能选择页面
		 */		
//		button_back1.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();				
//			}});
		
		/**
		 * Tab2的添加功能
		 */
		button_add2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				tabshow_flag = 1;
				Intent intent = new Intent();
				intent.setClass(BasicInfo.this, AddCustomerInfo.class);
				startActivity(intent);
			}});
		
		/**
		 * Tab3的添加功能
		 */
		addgoods.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				tabshow_flag = 2;
				Intent intent = new Intent();
				intent.setClass(BasicInfo.this, AddGoodsInfo.class);
				startActivity(intent);		
			}});
		
		/**
		 * Tab3返回功能选择页面
		 */
//		goodsback.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();			
//			}});
		
		/**
		 * Tab4返回功能选择页面
		 */
//		backbasics.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();		
//			}});
		
		/**
		 * Tab1返回功能选择页面
		 */
//		backbasicp.setOnClickListener(new Button.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();		
//			}});	
	}

	/**
	 * Tab1点击数据库中的数据条目，将数据显示在编辑框中以便修改
	 */

	private final class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Bundle bundle_pinfo = new Bundle();
			ListView pView = (ListView)parent;
			Cursor cursor = (Cursor)pView.getItemAtPosition(position);
		    ID_p = cursor.getInt(cursor.getColumnIndex("_id"));
		    
		    Provider provider = tableService.find_p(ID_p);
		    
		    provider.getId();
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			String postcode = provider.getPostcode();
			String address = provider.getAddress();
			
			bundle_pinfo.putInt("id", ID_p);
			bundle_pinfo.putString("name", name);
			bundle_pinfo.putString("phone", phone);
			bundle_pinfo.putString("address", address);
			bundle_pinfo.putString("postcode", postcode);
			bundle_pinfo.putString("mail", mail);
			
			intent_cinfo = new Intent(); 
			intent_cinfo.putExtras(bundle_pinfo);
			intent_cinfo.setClass(BasicInfo.this, ProviderInfo.class);
			startActivity(intent_cinfo);			
		}	
	}
	
	/**
	 * Tab2点击数据库中的数据条目，弹出详细信息
	 */
	
	private final class ItemClickListener2 implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			Bundle bundle_cinfo = new Bundle();
			ListView lView = (ListView)parent;
			Cursor cursor = (Cursor)lView.getItemAtPosition(position);
		    ID = cursor.getInt(cursor.getColumnIndex("_id"));
		    
		    Customer customer = tableService.find(ID);
		    
		    id_c = customer.getId();
			String customername = cursor.getString(cursor.getColumnIndex("name"));
			String customerphone = cursor.getString(cursor.getColumnIndex("phone"));
			String customeraddress = cursor.getString(cursor.getColumnIndex("address"));
			String customerpostcode = customer.getPostcode();
			String customercmail = customer.getCmail();
			String customercompany = customer.getCompany();
			
			bundle_cinfo.putInt("id", id_c);
			bundle_cinfo.putString("name", customername);
			bundle_cinfo.putString("phone", customerphone);
			bundle_cinfo.putString("address", customeraddress);
			bundle_cinfo.putString("postcode", customerpostcode);
			bundle_cinfo.putString("cmail", customercmail);
			bundle_cinfo.putString("company", customercompany);
			
			intent_cinfo = new Intent(); 
			intent_cinfo.putExtras(bundle_cinfo);
			intent_cinfo.setClass(BasicInfo.this, CustomerInfo.class);
			startActivity(intent_cinfo);
		}
	}
		
		/**
		 * Tab3点击数据库中的数据条目，弹出商品详细信息
		 */
		private final class ItemClickListener3 implements OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Bundle bundle_ginfo = new Bundle();
				ListView lView = (ListView)parent;
				Cursor cursor = (Cursor)lView.getItemAtPosition(position);
			    ID_g = cursor.getInt(cursor.getColumnIndex("_id"));
			    
			    Goods goods = tableService.find_g(ID_g);
			    
			    id_g = goods.getId();
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String producter = cursor.getString(cursor.getColumnIndex("producter"));
				Integer quality = goods.getQuality();
				String area = goods.getArea();
				String date = goods.getDate();
				Integer bid = goods.getBid();
				Integer price = goods.getPrice();
				
				bundle_ginfo.putInt("id", id_g);
				bundle_ginfo.putString("name", name);
				bundle_ginfo.putString("area", area);
				bundle_ginfo.putString("producter", producter);
				bundle_ginfo.putString("date", date);
				bundle_ginfo.putInt("quality", quality);
				bundle_ginfo.putInt("bid", bid);
				bundle_ginfo.putInt("price", price);
				
				intent_ginfo = new Intent(); 
				intent_ginfo.putExtras(bundle_ginfo);
				intent_ginfo.setClass(BasicInfo.this, GoodsInfo.class);
				startActivity(intent_ginfo);			
			}
	}
		
		/**
		 * Tab4点击数据库中的数据条目，将数据显示在编辑框中以便修改
		 */
		private final class ItemClickListener4 implements OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Bundle bundle_sinfo = new Bundle();
				ListView sView = (ListView)parent;
				Cursor cursor = (Cursor)sView.getItemAtPosition(position);
			    ID_s = cursor.getInt(cursor.getColumnIndex("_id"));
			    
			    Staff staff = tableService.find_s(ID_s);
			    
			    staff.getId();
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String phone = cursor.getString(cursor.getColumnIndex("phone"));
				String mail = cursor.getString(cursor.getColumnIndex("mail"));
				String postcode = staff.getPostcode();
				String usrgroup = staff.getUsrgroup();
				
				bundle_sinfo.putInt("id", ID_s);
				bundle_sinfo.putString("name", name);
				bundle_sinfo.putString("phone", phone);
				bundle_sinfo.putString("address", usrgroup);
				bundle_sinfo.putString("postcode", postcode);
				bundle_sinfo.putString("mail", mail);
				
				intent_sinfo = new Intent(); 
				intent_sinfo.putExtras(bundle_sinfo);
				intent_sinfo.setClass(BasicInfo.this, StaffInfo.class);
				startActivity(intent_sinfo);			
			}		
		}
		

	/**
	 * 将数据库和ListView绑定并显示在Tab1中
	 */
	
	private void show() {
		Cursor cursor = tableService.getCursorScrollData_p(0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.provideritem, cursor,
				new String[] {"name", "phone", "mail"}, new int[] {R.id.pname, R.id.pphone, R.id.pmail}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
	}
	
	/**
	 * 将查询后结果显示在Tab1中
	 */
	private void show_findp() {
		Cursor cursor = tableService.getCursorScrollData2_p(findpname.getText().toString(), 0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.provideritem, cursor,
				new String[] {"name", "phone", "mail"}, new int[] {R.id.pname, R.id.pphone, R.id.pmail}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
	}
	
	
	/**
	 * 将数据库和ListView绑定并显示在Tab2中
	 */
	
	private void show2() {
		Cursor cursor = tableService.getCursorScrollData(0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor,
				new String[] {"name", "phone", "address"}, new int[] {R.id.name, R.id.phone, R.id.address}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView2.setAdapter(adapter);
	}
	
	/**
	 * 将查询后结果显示在Tab2中
	 */
	
	private void show3() {
		Cursor cursor = tableService.getCursorScrollData2(findcname.getText().toString(), 0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor,
				new String[] {"name", "phone", "address"}, new int[] {R.id.name, R.id.phone, R.id.address}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView2.setAdapter(adapter);
	}
	
	/**
	 * 将数据库和ListView绑定并显示在Tab3中
	 */
	private void showgoods() {
		Cursor cursor = tableService.getCursorScrollData_g(0,  20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.goodsitem, cursor, 
				new String[] {"name", "producter", "quality"}, new int[] {R.id.gname, R.id.gproducter, R.id.gquality}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView3.setAdapter(adapter);
	}
	
	/**
	 * 将查询后结果显示在Tab3中
	 */

	private void showfindgoods() {
		Cursor cursor = tableService.getGoodsCursorScrollData_Goods(findgname.getText().toString(), 0,  20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.goodsitem, cursor, 
				new String[] {"name", "producter", "quality"}, new int[] {R.id.gname, R.id.gproducter, R.id.gquality}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView3.setAdapter(adapter);
	}
	
	/**
	 * 将数据库和ListView绑定并显示在Tab4中
	 */
	
	private void show_staff() {
		Cursor cursor = tableService.getCursorScrollData_s(0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.staffitem, cursor,
				new String[] {"name", "phone", "mail"}, new int[] {R.id.sname, R.id.sphone, R.id.smail}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView4.setAdapter(adapter);
	}
	
	/**
	 * 将查询后结果显示在Tab4中
	 */
	private void show_finds() {
		Cursor cursor = tableService.getCursorScrollData2_s(findsname.getText().toString(), 0, 20);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.staffitem, cursor,
				new String[] {"name", "phone", "mail"}, new int[] {R.id.sname, R.id.sphone, R.id.smail}, 
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView4.setAdapter(adapter);
	}
	

	
	/**
	 * 添加按键响应，弹出系统设置
	 */
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		 if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   //获取 Menu键			
//					Intent i9 = new Intent(this, Settings.class);
//					this.startActivity(i9);			
//		 }
//		return super.onKeyDown(keyCode, event);
//	}
	
	
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
