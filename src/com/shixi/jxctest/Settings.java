/**
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

package com.shixi.jxctest;

import com.shixi.domain.Staff;
import com.shixi.domain.StaffAccount;
import com.shixi.log.Login;
import com.shixi.service.BackupAndRestore;
import com.shixi.service.PreferencesService;
import com.shixi.service.TableService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends Activity {

	private BackupAndRestore backuprestore = new BackupAndRestore(this);
	
	private LinearLayout linearLayout;
	
	private RadioButton back;
	private RadioButton res;
	private RadioGroup radiogroup;
	
	private EditText oldpwd;
	private EditText newpwd;
	private EditText confirmpwd;
	
	private LinearLayout brlinearLayout;
	
	private Integer id = 1;
	private String name = "王二";
	private String prepwd;
	
	private TableService tableService = new TableService(this);
	
	private StaffAccount staffaccount;
	
	private Staff staff;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);		
	}
	
	
	public void onClick_changedate(View v){
		String name;
		String phone;
		String usrg;
		String postcode;
		String mail;
		
		Bundle bundle_sinfo = new Bundle();
		
		staff = tableService.find_s(staffaccount.id);	
		
		name = staff.getName();
		phone = staff.getPhone();
		usrg = staff.getUsrgroup();
		postcode = staff.getPostcode();
		mail = staff.getMail();
		
		bundle_sinfo.putString("name", name);
		bundle_sinfo.putString("phone", phone);
		bundle_sinfo.putString("address", usrg);
		bundle_sinfo.putString("postcode", postcode);
		bundle_sinfo.putString("mail", mail);
		
		Intent intent = new Intent();
		intent.putExtras(bundle_sinfo);
		intent.setClass(this, StaffInfo.class);
		startActivity(intent);
	}
	
	public void onClick_changepwd(View v){
		linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.changepwd, null);
	
		staff = tableService.find_s(staffaccount.id);
		
		prepwd = staff.getPassword();
		
	    new  AlertDialog.Builder(this)  
	    .setTitle("修改密码" )  
	    .setView(linearLayout)  
	    .setPositiveButton("确定" , new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				oldpwd = (EditText)linearLayout.findViewById(R.id.oldpwd);
				newpwd = (EditText)linearLayout.findViewById(R.id.newpwd);
				confirmpwd = (EditText)linearLayout.findViewById(R.id.confirmpwd);
				
				String pwd = oldpwd.getText().toString();
				String newpwds = newpwd.getText().toString();
				String confirm = confirmpwd.getText().toString();
				
				if(!(prepwd.equals(pwd))) {
					Toast.makeText(getApplicationContext(), "密码错误",
						     Toast.LENGTH_SHORT).show();
				}
				
				else if(!(newpwds.equals(confirm))) {
					Toast.makeText(getApplicationContext(), "密码输入不一致",
						     Toast.LENGTH_SHORT).show();
				}
				else {			
					tableService.update_pwd(staff, newpwds, staffaccount.id);
					Toast.makeText(getApplicationContext(), "保存成功！",
						     Toast.LENGTH_SHORT).show();
				}						
			}} )  
	    .setNegativeButton("取消" , null )  
	    .show();   
	}
	 
	
    
//    public void onClick_Exit(View v){
//		new AlertDialog.Builder(this)
//		.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
//		.setTitle("退出")
//		.setMessage("确定退出？！")
//		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//			    android.os.Process.killProcess(android.os.Process.myPid());
//			}
//		})
//		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.cancel();
//			}
//		})
//		.create().show();
//	}
    
    
	public void onClick_About(View v){
		new AlertDialog.Builder(this)
		.setIcon(null)
		.setTitle("帮助")
		.setMessage("本软件由whlpsi制作")
		.setPositiveButton("联系我们", new DialogInterface.OnClickListener() {
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uricontact= Uri.parse("cosmio.w@gmail.com");
				Intent intentme = new Intent(Intent.ACTION_VIEW, uricontact);
				startActivity(intentme);
				finish();
			}
		})
		.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		})
		.create().show();
	}
	
	public void onClick_backup(View v){
		backuprestore.backupDB();	
	}
	
	public void onClick_restore(View v) {
		backuprestore.restoreDB();
	}
	
	public void onClick_deleteaccount(View v){
		new AlertDialog.Builder(this)
		.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
		.setTitle("退出")
		.setMessage("确定退出？！")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				tableService.delete_s(staffaccount.id);
				Toast.makeText(getApplicationContext(), "删除账户成功！",
					     Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent();
				intent.setClass(Settings.this, Login.class);
				startActivity(intent);
				
				finish();
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		})
		.create().show();
	}
	
}
