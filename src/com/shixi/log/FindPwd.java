package com.shixi.log;

import com.shixi.domain.Staff;
import com.shixi.jxctest.R;
import com.shixi.service.DBOpenHelper;
import com.shixi.service.TableService;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindPwd extends Activity {
	private DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
	private TableService tableService = new TableService(this);
	private Staff staff;
	private View v;
	
	ActionBar actionBar;
	
	private static String name;
	private static String ans;
	private static Integer question;
	
	private TextView tv_question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpassword);
		
		actionBar=getActionBar();
		actionBar.show();
		
	}
	
	public void onClick_showquestion(View v) {
		name = ((EditText)findViewById(R.id.fp_name)).getText().toString();
		question = tableService.find_byname_s_fp(name);
		
		tv_question = (TextView)findViewById(R.id.fp_question);
		
		if(question==-1) {
			Toast.makeText(this, "不存在该用户", Toast.LENGTH_SHORT).show();
		}
		
		else if(question == 0) {
			tv_question.setText("我妈妈的生日是？");
		}
		
		else if(question == 1) {
			tv_question.setText("我最喜欢的电影是？");
		}
		
		else if(question == 2) {
			tv_question.setText("我高中的名字是？");
		}
		
		else if(question == 3) {
			tv_question.setText("我最喜欢吃什么？");
		}
		
		else 
			tv_question.setText("我最喜欢的人是？");
		
	}
	
	public void onClick_showanswer(View v) {
		name = ((EditText)findViewById(R.id.fp_name)).getText().toString();
		question = tableService.find_byname_s_fp(name);
		ans = ((EditText)findViewById(R.id.fp_ans)).getText().toString();
		if(tableService.find_byname_s_pwd(name, question, ans)!=null) {
			new AlertDialog.Builder(this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("密码找回")
			.setMessage("您的密码为：" + tableService.find_byname_s_pwd(name, question, ans))
			.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		})
			.create().show();
		}
		else 
			Toast.makeText(this, "答案错误", Toast.LENGTH_SHORT).show();
		
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
