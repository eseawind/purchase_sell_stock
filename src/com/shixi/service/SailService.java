package com.shixi.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.Sail;


public class SailService {
	private DBOpenHelper dbOpenHelper;
	
	public SailService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param Sail
	 */
	public void save(Sail sail,String edit_customer_name ,String edit_operator_name,String edit_customer_pay ,String edit_trade_time,String edit_customer_debt ) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		

		db.execSQL("insert into sail(customer_name,operator_name,customer_pay,trade_time,customer_debt) values(?, ?, ?, ?, ?)",
				new Object[] {edit_customer_name , edit_operator_name,edit_customer_pay , edit_trade_time,edit_customer_debt});
		
		db.close();
	}
	
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from sail where sailid=?", new Object[] {id});
	}
	
	public void delete_byoperator_name(String operator_name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from sail where operator_name=?", new Object[] {operator_name});
	}
	/**
	* 清空数据库表
	* @param tablename
	*/
	public void clearFeedTable(){
		  String sql = "DELETE FROM sail;";
		        SQLiteDatabase db =  dbOpenHelper.getWritableDatabase();
		  db.execSQL(sql);
		  
		 }
	/**
	 * 更改记录
	 * @param Sail
	 */
	public void update(Sail sail, String customer_name ,String operator_name,String customer_pay ,String trade_time ,String customer_debt, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update sail set customer_name=? ,operator_name=?,customer_pay=? ,trade_time=?,customer_debt=?, where sailid=?",
				new Object[] {customer_name,operator_name ,customer_pay,trade_time,customer_debt, id});
	}
	public void update_pay( String customer_pay , int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update sail set customer_pay=? where sailid=?",
				new Object[] {customer_pay, id});
	}
	public void update_debt( String customer_debt , int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update sail set customer_debt=? where sailid=?",
				new Object[] {customer_debt, id});
	}
	/**
	 * 查找记录sail
	 * @param id
	 * @return
	 */
	public Sail find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail where sailid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int sailid = cursor.getInt(cursor.getColumnIndex("sailid"));						
			String customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String customer_pay = cursor.getString(cursor.getColumnIndex("customer_pay"));
			String trade_time = cursor.getString(cursor.getColumnIndex("trade_time"));
			String customer_debt = cursor.getString(cursor.getColumnIndex("customer_debt"));
			return new Sail(sailid, customer_name,operator_name,customer_pay,trade_time,customer_debt);
		}
		cursor.close();
		return null;
	}
	
	
	public Sail find_byproduct_name(String product_name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail where product_name like ?", new String[] {"%" + product_name + "%"});
		if(cursor.moveToFirst()) {
			int sailid = cursor.getInt(cursor.getColumnIndex("sailid"));
						
			String customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String customer_pay = cursor.getString(cursor.getColumnIndex("customer_pay"));
			String trade_time = cursor.getString(cursor.getColumnIndex("trade_time"));
			String customer_debt = cursor.getString(cursor.getColumnIndex("customer_debt"));
			return new Sail(sailid, customer_name,operator_name,customer_pay,trade_time,customer_debt);
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
	public List<Sail> getScrollData(int offset, int maxresult) {
		List<Sail> sails = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail order by sailid asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		while(cursor.moveToNext()) {
			int sailid = cursor.getInt(cursor.getColumnIndex("sailid"));
			
			String customer_name = cursor.getString(cursor.getColumnIndex("customer_name"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String customer_pay = cursor.getString(cursor.getColumnIndex("customer_pay"));
			String trade_time = cursor.getString(cursor.getColumnIndex("trade_time"));
			String customer_debt = cursor.getString(cursor.getColumnIndex("customer_debt"));
			sails.add(new Sail(sailid, customer_name,operator_name,customer_pay,trade_time,customer_debt));
		}
		cursor.close();
		return sails;
	}
	
	/**
	 * 返回sail记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from sail", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
	/**
	 * 返回sail_register记录总数
	 * @return
	 */
	public long getCount_register() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from sail_register", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
	
	public Cursor getCursorScrollData(int offset, int maxresult) {
		List<Sail> sails = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail order by _id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	public Cursor getCursorScrollDataSail(int offset, int maxresult) {
		List<Sail> sails = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail order by product_name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData2(String product_name, int offset, int maxresult) {
		List<Sail> customers = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail where operator_name like ? order by _id asc limit?, ?",
				new String[] {"%" + product_name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_tuidan(String _id, int offset, int maxresult) {
		List<Sail> customers = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail where _id like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

	public Cursor getCursorScrollData_chaxun(String _id,String customer_name,String operator_name,String trade_time ,int offset, int maxresult) {
		List<Sail> customers = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail where _id like?  and customer_name like? and operator_name like ? and trade_time like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%",  "%" + customer_name+ "%",  "%" + operator_name+ "%", "%" +  trade_time + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_qiankuan( int offset, int maxresult) {
		List<Sail> customers = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sailid as _id,customer_name,operator_name,customer_pay,trade_time,customer_debt from sail where customer_debt not like ? order by _id asc limit?, ?",
				new String[] {"0.0", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
}