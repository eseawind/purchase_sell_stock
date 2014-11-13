package com.shixi.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.Sail;
import com.shixi.domain.SailRegister;

public class SailServiceRegister {
	private DBOpenHelper dbOpenHelper;
	
	public SailServiceRegister(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param Sail_register
	 */
	public void save_register(Sail sail,String edit_code, String edit_product_name,String  edit_product_price, String edit_purchase_quantity,String tosail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		

		db.execSQL("insert into sail(code,product_name, product_price, purchase_quantity,tosail) values( ?, ?, ?, ?, ?)",
				new Object[] {edit_code,edit_product_name, edit_product_price, edit_purchase_quantity,tosail});
		
		db.close();
	}

	
	/**
	 * 删除记录
	 * @param id
	 */
	
	public void delete_register(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from sail_register where sail_registerid=?", new Object[] {id});
	}
	public void delete_register_bytosail(String tosail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from sail_register where tosail=?", new Object[] {tosail});
	}
	/**
	* 清空数据库表
	* @param tablename
	*/
	public void clearFeedTable_register(){
		  String sql = "DELETE FROM sail_register;";
		        SQLiteDatabase db =  dbOpenHelper.getWritableDatabase();
		  db.execSQL(sql);
		  
		 }

	/**
	 * 更改记录
	 * @param Sail_Register
	 */
	public void update_register(Sail sail_register, String code,String product_name, String product_price, String purchase_quantity,String tosail, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update sail_register set code=?,product_name=?, product_price=?, purchase_quantity=? ,tosail=?, where sail_registerid=?",
				new Object[] {code,product_name, product_price, purchase_quantity,tosail, id});
	}
	
	public void update_purchase_quantity( Integer purchase_quantity,String code, String tosail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update sail_register set  purchase_quantity=?   where code=? and tosail=? ",
				new Object[] {purchase_quantity,code,tosail});
	}
	
	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public SailRegister find_register(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail_register where sail_registerid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int sail_registerid = cursor.getInt(cursor.getColumnIndex("sail_registerid"));
			String code = cursor.getString(cursor.getColumnIndex("code"));
			String product_name = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));			
			String tosail = cursor.getString(cursor.getColumnIndex("tosail"));
			return new SailRegister(sail_registerid,code, product_name, product_price, purchase_quantity,tosail);
		}
		cursor.close();
		return null;
	}
	
	
	public SailRegister find_byproduct_name(String product_name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail_register where product_name like ?", new String[] {"%" + product_name + "%"});
		if(cursor.moveToFirst()) {
			int sailid = cursor.getInt(cursor.getColumnIndex("sail_registerid"));
			String code = cursor.getString(cursor.getColumnIndex("code"));
			String product_name_real = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			String tosail = cursor.getString(cursor.getColumnIndex("tosail"));
		
			return new SailRegister(sailid,code, product_name_real, product_price, purchase_quantity,tosail);
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
	public List<SailRegister> getScrollData_register(int offset, int maxresult) {
		List<SailRegister> sails = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select * from sail_register order by sail_registerid asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		while(cursor.moveToNext()) {
			int sail_registerid = cursor.getInt(cursor.getColumnIndex("sail_registerid"));
			String code = cursor.getString(cursor.getColumnIndex("code"));
			String product_name = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			String tosail = cursor.getString(cursor.getColumnIndex("tosail"));

			
			sails.add(new SailRegister(sail_registerid, code,product_name, product_price, purchase_quantity,tosail));
		}
		cursor.close();
		return sails;
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
	
	public Cursor getCursorScrollData_register(int offset, int maxresult) {
		List<SailRegister> sails = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sail_registerid as _id,code,product_name,product_price,purchase_quantity,tosail from sail_register order by product_name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	public Cursor getCursorScrollDataRegister(int offset, int maxresult) {
		List<SailRegister> sails = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sail_registerid as _id,code,product_name,product_price,purchase_quantity,tosail from sail_register  order by product_name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	public Cursor getCursorScrollData_tosail(int tosail ,int offset, int maxresult) {
		List<SailRegister> customers = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sail_registerid as _id,code,product_name,product_price,purchase_quantity,tosail from sail_register where tosail like?  order by _id asc limit?, ?",
				new String[] {"%" + String.valueOf(tosail) + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_id(String id ,int offset, int maxresult) {
		List<SailRegister> customers = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sail_registerid as _id,code,product_name,product_price,purchase_quantity,tosail from sail_register where _id like?  order by _id asc limit?, ?",
				new String[] {"%" + id + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
	public Cursor getCursorScrollData_codeandtosail(String code ,String tosail ,int offset, int maxresult) {
		List<SailRegister> customers = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select sail_registerid as _id,code,product_name,product_price,purchase_quantity,tosail from sail_register where code like?  and  tosail like? order by _id asc limit?, ?",
				new String[] {"%" + code + "%","%" + tosail + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
}