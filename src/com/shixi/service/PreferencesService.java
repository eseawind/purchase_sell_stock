package com.shixi.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	private Context context;
	
	public PreferencesService(Context context) {
		this.context = context;
	}

	public void save(Integer id, String name, String pwd) { 
		SharedPreferences preferences = context.getSharedPreferences("password", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("id", id);
		editor.putString("name", name);
		editor.putString("pwd", pwd);
		editor.commit();
	}
}

