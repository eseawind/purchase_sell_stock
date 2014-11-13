package com.shixi.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.Goods;
import com.shixi.domain.SailRegister;

public class GoodsService {
	private DBOpenHelper dbOpenHelper;

	public GoodsService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param customer
	 */
	public void save_g(Goods goods, String name, String area, String produtor, String prodate, Integer quality, Integer bid, Integer price) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into goods(name, producingarea, manufacturer, productionsdate, qualitygp, bid, price) values(?, ?, ?, ?, ?, ?, ?)",
				new Object[] {name, area, produtor, prodate, quality, bid, price});
		db.close();
	}
	

	/**
	 * 删除记录
	 * @param id
	 */
	public void delete_g(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from goods where goodsid=?", new Object[] {id});
	}
	
	public void delete_byname_g(String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from goods where name=?", new Object[] {name});
	}
	
	public void delete_byname_g(Goods goods, String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from goods where name=?", new Object[] {name});
	}
	
	/**
	 * 更改记录
	 * @param customer
	 */
	public void update_g(Goods goods, String name, String area, String produtor, String prodate, String quality, String bid, String price, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update goods set name=?, producingarea=?, manufacturer=?, productionsdate=?, qualitygp=?, bid=?, price=? where goodsid=?",
				new Object[] {name, area, produtor, prodate, quality, bid, price, id});
	}
	
	/**
	 * 查找记录，通过ID
	 * @param id
	 * @return
	 */
	public Goods find_g(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from goods where goodsid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int goodsid = cursor.getInt(cursor.getColumnIndex("goodsid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String area = cursor.getString(cursor.getColumnIndex("producingarea"));
			String produtor = cursor.getString(cursor.getColumnIndex("manufacturer"));
			String prodate = cursor.getString(cursor.getColumnIndex("productionsdate"));
			Integer quality = cursor.getInt(cursor.getColumnIndex("qualitygp"));
			Integer bid = cursor.getInt(cursor.getColumnIndex("bid"));
			Integer price = cursor.getInt(cursor.getColumnIndex("price"));
			return new Goods(goodsid, name, area, produtor, prodate, quality, bid, price);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名------显示所有姓名尚未完成
	 * @param name
	 * @return
	 */
	public Goods find_byname_g(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from goods where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int goodsid = cursor.getInt(cursor.getColumnIndex("goodsid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String area = cursor.getString(cursor.getColumnIndex("producingarea"));
			String produtor = cursor.getString(cursor.getColumnIndex("manufacturer"));
			String prodate = cursor.getString(cursor.getColumnIndex("productionsdate"));
			Integer quality = cursor.getInt(cursor.getColumnIndex("qualitygp"));
			Integer bid = cursor.getInt(cursor.getColumnIndex("bid"));
			Integer price = cursor.getInt(cursor.getColumnIndex("price"));
			return new Goods(goodsid, name_real, area, produtor, prodate, quality, bid, price);
		}
		cursor.close();
		return null;
	}
	
	
	/**
	 * 返回记录总数
	 * @return
	 */
	public long getCount_g() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from goods", null);
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
	public Cursor getCursorScrollData_g(int offset, int maxresult) {
		List<Goods> goods = new ArrayList<Goods>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select goodsid as _id,name,producingarea,qualitygp from goods order by name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getGoodsCursorScrollData_Goods(String name, int offset, int maxresult) {
		List<Goods> goods = new ArrayList<Goods>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select goodsid as _id,name,producingarea,qualitygp from goods where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	public Cursor getGoodsCursorScrollData_code(String code, int offset, int maxresult) {
		List<Goods> goods = new ArrayList<Goods>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select goodsid as _id,code, name, area, producter, date, quality, bid, price, recordtime from goods where code like ? order by name asc limit?, ?",
				new String[] {"%" + code + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
}
