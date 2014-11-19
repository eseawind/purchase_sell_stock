package com.shixi.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shixi.domain.Sail;
import com.shixi.domain.Storage;

public class StorageService {
	private DBOpenHelper dbOpenHelper;

	public StorageService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param storage
	 */
	public void save(String goodsid,String name, String area, String producter,
			String date, String quality, String bid, String price,Integer storage_quantity,
			String operator_name,String storage_time) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into storage(goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time});
		db.close();
	}

	/**
	 * 删除记录
	 * @param id
	 */
	public void delete_storageid(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from storage where storageid=?", new Object[] {id});
	}
	
	public void delete_byname(String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from storage where name=?", new Object[] {name});
	}
	
	/**
	 * 更改记录
	 * @param storage
	 */
	public void update( String goodsid,String name, String area, String producter,
			String date, String quality, String bid, String price,Integer storage_quantity,
			String operator_name,String storage_time, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update storage set goodsid=?,set name=?, area=?, producter=?, date=?, quality=?, bid=?, price=?, storage_quantity=? , operator_name=? , storage_time=?  where storageid=?",
				new Object[] {goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time, id});
	}
	public void update_storage_quantity( Integer storage_quantity, String code) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update storage set  storage_quantity=?   where goodsid=?",
				new Object[] {storage_quantity, code});
	}
	
	/**
	 * 查找记录，通过ID
	 * @param id
	 * @return
	 */
	public Storage find_g(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from storage where storageid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int storageid = cursor.getInt(cursor.getColumnIndex("storageid"));
			String goodsid = cursor.getString(cursor.getColumnIndex("goodsid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));	
			String area = cursor.getString(cursor.getColumnIndex("area"));
			String producter = cursor.getString(cursor.getColumnIndex("producter"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String quality = cursor.getString(cursor.getColumnIndex("quality"));
			String bid = cursor.getString(cursor.getColumnIndex("bid"));
			String price = cursor.getString(cursor.getColumnIndex("price"));
			Integer storage_quantity = cursor.getInt(cursor.getColumnIndex("storage_quantity"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String storage_time = cursor.getString(cursor.getColumnIndex("storage_time"));
			
			return new Storage(storageid, goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名------显示所有姓名尚未完成
	 * @param name
	 * @return
	 */
	public Storage find_byname_g(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from storage where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int storageid = cursor.getInt(cursor.getColumnIndex("storageid"));
			String goodsid = cursor.getString(cursor.getColumnIndex("goodsid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String area = cursor.getString(cursor.getColumnIndex("area"));
			String producter = cursor.getString(cursor.getColumnIndex("producter"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String quality = cursor.getString(cursor.getColumnIndex("quality"));
			String bid = cursor.getString(cursor.getColumnIndex("bid"));
			String price = cursor.getString(cursor.getColumnIndex("price"));
			Integer storage_quantity = cursor.getInt(cursor.getColumnIndex("storage_quantity"));
			String operator_name = cursor.getString(cursor.getColumnIndex("operator_name"));
			String storage_time = cursor.getString(cursor.getColumnIndex("storage_time"));
			return new Storage(storageid, goodsid,name_real,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 返回记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from storage", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
	
	/**
	 * 返回记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData(int offset, int maxresult) {
		List<Storage> storage = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id, goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time from storage order by _id asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData_Storage(String name, int offset, int maxresult) {
		List<Storage> storage = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id,goodsid,name,area,producter,date, quality,bid,price,storage_quantity,operator_name,storage_time from storage where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

	public Cursor getCursorScrollData_chaxun(String _id,String name,String operator_name,String storage_time ,int offset, int maxresult) {
		List<Storage> customers = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id,goodsid, name, area, producter, date, quality, bid, price,storage_quantity, operator_name, storage_time from storage where _id like?  and name like? and operator_name like ? and storage_time like ? order by _id asc limit?, ?",
				new String[] {"%" + _id + "%",  "%" + name+ "%",  "%" + operator_name+ "%", "%" +  storage_time + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

	public Cursor getCursorScrollData_code(String code ,int offset, int maxresult) {
		List<Storage> customers = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id,goodsid, name, area, producter, date, quality, bid, price,storage_quantity, operator_name, storage_time from storage where goodsid =?  order by _id asc limit?, ?",
				new String[] { code , String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
	public Cursor getCursorScrollData_dicang(int offset, int maxresult) {
		List<Storage> customers = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id,goodsid, name, area, producter, date, quality, bid, price,storage_quantity, operator_name, storage_time from storage where  storage_quantity  < 30 order by _id asc limit?, ?",
				new String[] { String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
	public Cursor getCursorScrollData_dibaozhiqi(int offset, int maxresult) {
		List<Storage> customers = new ArrayList<Storage>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select storageid as _id,goodsid, name, area, producter, date, quality, bid, price,storage_quantity, operator_name, storage_time from storage where  (  (quality-(strftime('%s',date('now'))-strftime('%s',date))/86400) between 0 and 300  )  order by _id asc limit?, ?",
				new String[] { String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
}
