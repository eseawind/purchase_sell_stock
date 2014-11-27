package com.shixi.autocompleteadater;

import com.shixi.service.DBOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

/**
 * 
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */
public class AutoCompleteAdater_buyid extends SimpleCursorAdapter {

	private DBOpenHelper dbHelper = null;
	private Context context;
	// 查询字段
	private String queryField;

	public AutoCompleteAdater_buyid(Context context, int layout, Cursor c, String from, int to,int flags) {
		super(context, layout, c, new String[] { from }, new int[] { to },flags);
		this.context = context;
		this.queryField = from;
	}

	/**
	 * 动态查询数据库
	 */
	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		if (constraint != null) {
			return getDbHelper().query_buyid((String) constraint);
		} else {
			return null;
		}
	}

	/**
	 * 这里设置在弹出的提示列表中点击某一项后的返回值,返回值将被显示在文本框中
	 */
	@Override
	public CharSequence convertToString(Cursor cursor) {
		return cursor.getString(cursor.getColumnIndex(queryField));
	}

	public DBOpenHelper getDbHelper() {
		if (dbHelper == null) {
			dbHelper = new DBOpenHelper(this.context);
		}
		return dbHelper;
	}
}
