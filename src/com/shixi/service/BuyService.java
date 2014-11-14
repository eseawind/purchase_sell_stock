package com.shixi.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.Buy;
import com.shixi.domain.Sail;

public class BuyService {
	private DBOpenHelper dbOpenHelper;
	
	public BuyService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param Buy_register
	 */
	public void save(Buy buy, String edit_buy_name, String edit_operator_name,String  edit_pay, String edit_time, String edit_debt) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例

		db.execSQL("insert into buy(buy_name,operator_name, pay, time, debt) values( ?, ?, ?, ?, ?)",
				new Object[] {edit_buy_name,edit_operator_name,edit_pay, edit_time,edit_debt});
		
		db.close();
	}
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from buy where buyid=?", new Object[] {id});
	}
	/**
	* 清空数据库表
	* @param tablename
	*/
	public void clearFeedTable(){
		String sql = "DELETE FROM buy;";
		SQLiteDatabase db =  dbOpenHelper.getWritableDatabase();
		db.execSQL(sql);
		  
	}

	/**
	 * 更改记录
	 * @param Buy_Register
	 */
	public void update_register(Buy buy,String buy_name,String operator_name, String pay,String time,String debt, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update buy set buy_name=?,operator_name=?, pay=?, time=? ,debt=?, where buyid=?",
				new Object[] {buy_name,operator_name, pay, time, debt, id});
	}
	public void update_pay( String pay , int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update buy set pay=? where buyid=?",
				new Object[] {pay, id});
	}
	public void update_debt( String debt , int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update buy set debt=? where buyid=?",
				new Object[] {debt, id});
	}
	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public Buy find_register(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy where buyid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int buyid = cursor.getInt(cursor.getColumnIndex("buyid"));
			String buy_name = cursor.getString(cursor.getColumnIndex("buy_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String pay = cursor.getString(cursor.getColumnIndex("pay"));
			String time = cursor.getString(cursor.getColumnIndex("time"));			
			String debt = cursor.getString(cursor.getColumnIndex("debt"));
			return new Buy(buyid,buy_name, operator_name, pay, time,debt);
		}
		cursor.close();
		return null;
	}
	
	
	public Buy find_bybuy_name(String buy_name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy where buy_name like ?", new String[] {"%" + buy_name + "%"});
		if(cursor.moveToFirst()) {
			int buyid = cursor.getInt(cursor.getColumnIndex("buyid"));
			String buy_name_real = cursor.getString(cursor.getColumnIndex("buy_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String pay = cursor.getString(cursor.getColumnIndex("pay"));
			String time = cursor.getString(cursor.getColumnIndex("time"));			
			String debt = cursor.getString(cursor.getColumnIndex("debt"));
		
			return new Buy(buyid,buy_name_real, operator_name, pay, time,debt);
		}
		cursor.close();
		return null;
	}
	
	
	/**
	 * 返回记录
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public List<Buy> getScrollData(int offset, int maxresult) {
		List<Buy> buys = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy order by buyid asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		while(cursor.moveToNext()) {
			int buyid = cursor.getInt(cursor.getColumnIndex("buyid"));
			String buy_name = cursor.getString(cursor.getColumnIndex("buy_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String pay = cursor.getString(cursor.getColumnIndex("pay"));
			String time = cursor.getString(cursor.getColumnIndex("time"));			
			String debt = cursor.getString(cursor.getColumnIndex("debt"));

			
			buys.add(new Buy(buyid,buy_name, operator_name, pay, time,debt));
		}
		cursor.close();
		return buys;
	}
	
	
	/**
	 * 返回buy记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from buy", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
	
	public Cursor getCursorScrollData(int offset, int maxresult) {
		List<Buy> buys = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buyid as _id,buy_name, operator_name, pay, time,debt from buy order by _id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	public Cursor getCursorScrollData_chaxun(String _id,String buy_name,String operator_name,String time,int offset, int maxresult) {
		List<Buy> buys = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buyid as _id,buy_name, operator_name, pay, time, debt from buy where _id like? and buy_name like? and operator_name like? and time like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%", "%" + buy_name+ "%", "%" + operator_name+ "%",  "%" + time+ "%",  String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData2(int offset, int maxresult) {
		List<Buy> buys = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buyid as _id,buy_name, operator_name, pay, time,debt from buy order by _id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	public Cursor getCursorScrollData_tuihuo(String _id, int offset, int maxresult) {
		List<Buy> customers = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buyid as _id,buy_name, operator_name, pay, time,debt from buy where _id like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_qiankuan( int offset, int maxresult) {
		List<Buy> customers = new ArrayList<Buy>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buyid as _id,buy_name,operator_name,pay,time,debt from buy where debt not like ? order by _id asc limit?, ?",
				new String[] {"0.0", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}	
}