package com.shixi.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	
	public DBOpenHelper(Context context) {
		super(context, "basicInfo.db", null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		/*修改数据库*/
		db.execSQL("CREATE TABLE customer (customerid integer primary key autoincrement, name varchar(20), phone varchar(14), address varchar(50), postcode varchar(16), cmail varchar(30), company varchar(40))");
		
		db.execSQL("CREATE TABLE provider (providerid integer primary key autoincrement, name varchar(20), phone varchar(20), address varchar(40), postcode varchar(20), mail varchar(20))");
		
		db.execSQL("CREATE TABLE goods (goodsid integer primary key autoincrement, code varchar(40), name varchar(20), area varchar(40), producter varchar(20), date Datatime, quality varchar(10), bid varchar(10), price varchar(10), recordtime varchar(20))");
		
		db.execSQL("CREATE TABLE staff (staffid integer primary key autoincrement, name varchar(20), usrgroup varchar(1),  password varchar(40), question integer(3), answer varchar(30), phone varchar(20), postcode varchar(20), mail varchar(20))");
		
		db.execSQL("CREATE TABLE sail (sailid integer primary key autoincrement, customer_name varchar(14),operator_name varchar(20),customer_pay varchar(14),trade_time varchar(30),customer_debt varchar(14))");
		
		db.execSQL("CREATE TABLE sail_register (sail_registerid integer primary key autoincrement, code varchar(20),product_name varchar(20), product_price varchar(14), purchase_quantity varchar(20),tosail varchar(20))");
		
		db.execSQL("CREATE TABLE buy_register (buy_registerid integer primary key autoincrement,goods_id varchar(20), product_name varchar(20), product_price varchar(14), purchase_quantity varchar(20),tobuy varchar(20))");
		
		db.execSQL("CREATE TABLE buy (buyid integer primary key autoincrement, buy_name varchar(20), operator_name varchar(20), pay varchar(14),time varchar(20),debt varchar(14))");
		
		db.execSQL("CREATE TABLE storage (storageid integer primary key autoincrement, goodsid varchar(20), name varchar(20), area varchar(40), producter varchar(20), date Datatime, quality varchar(10), bid varchar(10), price varchar(10), storage_quantity  integer  , operator_name varchar(20), storage_time varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	 /**
     * 根据输入内容模糊查询
     * @param name
     * @return
     */

    public Cursor query_code(String code) {

       SQLiteDatabase db = this.getReadableDatabase();
       
       return db.rawQuery("select goodsid as _id, code, name, area, producter, date, quality, bid, price, recordtime from goods where code  like '%" + code + "%' limit 10",null);

    }
    public Cursor query_buyname(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        
        return db.rawQuery("select providerid as _id, name, phone, address, postcode, mail from provider where name  like '%" + name + "%' limit 10",null);

     }
    public Cursor query_tuicang(String code) {

        SQLiteDatabase db = this.getReadableDatabase();
        
        return db.rawQuery("select storageid as _id,goodsid , name, area , producter, date , quality , bid , price, storage_quantity, operator_name, storage_time from storage where goodsid  like '%" + code + "%' limit 10",null);

     }
    public Cursor query_buyid(String buyid) {

        SQLiteDatabase db = this.getReadableDatabase();
        
        return db.rawQuery("select buyid as _id,buy_name ,operator_name, pay , time, debt  from buy where _id  like '%" + buyid + "%' limit 10",null);

     }
}
