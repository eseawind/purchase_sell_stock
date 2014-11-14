package com.shixi.log;

import com.shixi.domain.Staff;
import com.shixi.domain.StaffAccount;
import com.shixi.jxctest.MainActivity;
import com.shixi.jxctest.R;
import com.shixi.service.DBOpenHelper;
import com.shixi.service.TableService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
	private TableService tableService = new TableService(this);
	
	private TextView reg;

	private String logname;
	private String logpwd;
	
	private StaffAccount staffaccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		logname = ((EditText)findViewById(R.id.log_name)).getText().toString();
		logpwd = ((EditText)findViewById(R.id.log_pwd)).getText().toString();
		
		reg = (TextView)findViewById(R.id.tv_reg);
		
		if(tableService.getCount_s()!=0) {
			reg.setTextColor(android.graphics.Color.GRAY);
			reg.setClickable(false);
		}
	}
	
	public void onClick_login(View v) {
		logname = ((EditText)findViewById(R.id.log_name)).getText().toString();
		logpwd = ((EditText)findViewById(R.id.log_pwd)).getText().toString();
		
		if(logpwd.equals(tableService.find_byname_s_pwd(logname))) {
			Toast.makeText(getApplicationContext(), "登录成功！",
				     Toast.LENGTH_SHORT).show();
			
			//保存账户ID以便修改
			staffaccount.id = tableService.find_byname_s_id(logname);
			
			Intent intent = new Intent();
			intent.setClass(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		else {
			Toast.makeText(getApplicationContext(), "用户名或密码不正确",
				     Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onClick_register(View v) {
		Intent intent = new Intent();
		intent.setClass(this, Register.class);
		startActivity(intent);
	}
	
	public void onClick_getsercet(View v) {
		Intent intent = new Intent();
		intent.setClass(this, FindPwd.class);
		startActivity(intent);
	}
}
