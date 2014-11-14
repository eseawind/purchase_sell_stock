package com.shixi.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.BuyRegister;
import com.shixi.domain.Sail;
import com.shixi.domain.SailRegister;

public class BuyServiceRegister {
	private DBOpenHelper dbOpenHelper;
	
	public BuyServiceRegister(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param Buy_register
	 */
	public void save_register(BuyRegister buy, String edit_goods_id, String edit_product_name,String  edit_product_price, String edit_purchase_quantity, String tobuy) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		
		db.execSQL("insert into buy(goods_id,product_name, product_price, purchase_quantity,tobuy) values( ?, ?, ?, ?, ?)",
				new Object[] {edit_goods_id,edit_product_name, edit_product_price, edit_purchase_quantity,tobuy});
		
		db.close();
	}

	/**
	 * 删除记录
	 * @param id
	 */
	public void delete_register(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from buy_register where buy_registerid=?", new Object[] {id});
	}
	public void delete_register_bytobuy(String tobuy) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from buy_register where tobuy=?", new Object[] {tobuy});
	}
	/**
	* 清空数据库表
	* @param tablename
	*/
	public void clearFeedTable_register(){
		  String sql = "DELETE FROM buy_register;";
		        SQLiteDatabase db =  dbOpenHelper.getWritableDatabase();
		  db.execSQL(sql);
		  
		 }

	/**
	 * 更改记录
	 * @param Buy_Register
	 */
	public void update_register(BuyRegister buy,String goods_id, String product_name, String product_price, String purchase_quantity,String tobuy, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update buy_register set goods_id=?,product_name=?, product_price=?, purchase_quantity=? ,tobuy=?, where buy_registerid=?",
				new Object[] {product_name, product_price, purchase_quantity,tobuy, id});
	}
	
	
	public void update_purchase_quantity( Integer purchase_quantity, String tobuy) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update buy_register set  purchase_quantity=?   where tobuy=?",
				new Object[] {purchase_quantity, tobuy});
	}
	
	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public BuyRegister find_register(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy_register where buy_registerid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int buy_registerid = cursor.getInt(cursor.getColumnIndex("buy_registerid"));
			String goods_id = cursor.getString(cursor.getColumnIndex("goods_id"));
			String product_name = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));			
			String tobuy = cursor.getString(cursor.getColumnIndex("tobuy"));
			return new BuyRegister(buy_registerid,goods_id, product_name, product_price, purchase_quantity,tobuy);
		}
		cursor.close();
		return null;
	}
	
	
	public BuyRegister find_byproduct_name(String product_name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy_register where product_name like ?", new String[] {"%" + product_name + "%"});
		if(cursor.moveToFirst()) {
			int buy_registerid = cursor.getInt(cursor.getColumnIndex("buy_registerid"));
			String goods_id = cursor.getString(cursor.getColumnIndex("goods_id"));
			String product_name_real = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			String tobuy = cursor.getString(cursor.getColumnIndex("tobuy"));
		
			return new BuyRegister(buy_registerid, goods_id,product_name_real, product_price, purchase_quantity,tobuy);
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
	public List<BuyRegister> getScrollData_register(int offset, int maxresult) {
		List<BuyRegister> buys = new ArrayList<BuyRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select * from buy_register order by buy_registerid asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		while(cursor.moveToNext()) {
			int buy_registerid = cursor.getInt(cursor.getColumnIndex("buy_registerid"));
			String goods_id = cursor.getString(cursor.getColumnIndex("goods_id"));
			String product_name = cursor.getString(cursor.getColumnIndex("product_name"));
			String product_price = cursor.getString(cursor.getColumnIndex("product_price"));
			String purchase_quantity = cursor.getString(cursor.getColumnIndex("purchase_quantity"));
			String tobuy = cursor.getString(cursor.getColumnIndex("tobuy"));
			
			buys.add(new BuyRegister(buy_registerid,goods_id, product_name, product_price, purchase_quantity,tobuy));
		}
		cursor.close();
		return buys;
	}
	
	/**
	 * 返回buy_register记录总数
	 * @return
	 */
	public long getCount_register() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from buy_register", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
	
	public Cursor getCursorScrollData_register(int offset, int maxresult) {
		List<BuyRegister> buys = new ArrayList<BuyRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buy_registerid as _id,goods_id,product_name,product_price,purchase_quantity,tobuy from buy_register order by goods_id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_chaxun(String _id,String goods_id,String product_name,String product_price,String purchase_quantity ,int offset, int maxresult) {
		List<Sail> customers = new ArrayList<Sail>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buy_registerid as _id,goods_id,product_name,product_price,purchase_quantity,tobuy from buy_register where _id like? and goods_id like? and product_name like? and product_price like ? and purchase_quantity like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%", "%" + goods_id+ "%", "%" + product_name+ "%",  "%" + product_price+ "%", "%" +  purchase_quantity + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollDataRegister(int offset, int maxresult) {
		List<BuyRegister> buys = new ArrayList<BuyRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buy_registerid as _id,goods_id,product_name,product_price,purchase_quantity,tobuy from buy_register  order by goods_id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
	public Cursor getCursorScrollData_tobuy(int tobuy ,int offset, int maxresult) {
		List<BuyRegister> customers = new ArrayList<BuyRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buy_registerid as _id,goods_id,product_name,product_price,purchase_quantity,tobuy from buy_register where tobuy like?  order by _id asc limit?, ?",
				new String[] {"%" + String.valueOf(tobuy) + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getCursorScrollData_code(String id ,int offset, int maxresult) {
		List<SailRegister> customers = new ArrayList<SailRegister>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select buy_registerid as _id,goods_id,product_name,product_price,purchase_quantity,tobuy from buy_register where goods_id like?  order by _id asc limit?, ?",
				new String[] { id , String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
}