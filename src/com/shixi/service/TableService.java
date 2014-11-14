package com.shixi.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shixi.domain.Customer;
import com.shixi.domain.Goods;
import com.shixi.domain.Provider;
import com.shixi.domain.Staff;

public class TableService {
	private DBOpenHelper dbOpenHelper;

	public TableService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param customer
	 */
	public void save(Customer customer, String edit_name, String edit_phone, String edit_address, String edit_postcode, 
			String edit_cmail, String edit_company) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into customer(name, phone, address, postcode, cmail, company) values(?, ?, ?, ?, ?, ?)",
				new Object[] {edit_name, edit_phone, edit_address, edit_postcode, edit_cmail, edit_company});
		db.close();
	}
	
	public void save2(Customer customer, String name, String phone, String address, String postcode, String cmail, String company) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into customer(name, phone, address, postcode, cmail, company) values(?, ?, ?, ?, ?, ?)",
				new Object[] {name, phone, address, postcode, cmail, company});						
		db.close();
	}
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from customer where customerid=?", new Object[] {id});
	}
	
	public void delete_byname(String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from customer where name=?", new Object[] {name});
	}
	
	public void delete_byname_c(Customer customer, String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from customer where name=?", new Object[] {name});
	}
	
	/**
	 * 更改记录
	 * @param customer
	 */
	public void update(Customer customer, String name, String phone, String address, String postcode, String cmail, String company, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update customer set name=?, phone=?, address=?, postcode=?, cmail=?, company=? where customerid=?",
				new Object[] {name, phone, address, postcode, cmail, company, id});
	}
	
	/**
	 * 查找记录，通过ID
	 * @param id
	 * @return
	 */
	public Customer find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from customer where customerid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int customerid = cursor.getInt(cursor.getColumnIndex("customerid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String cmail = cursor.getString(cursor.getColumnIndex("cmail"));
			String company = cursor.getString(cursor.getColumnIndex("company"));
			return new Customer(customerid, name, phone, address, postcode, cmail, company);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名------显示所有姓名尚未完成
	 * @param name
	 * @return
	 */
	public Customer find_byname(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from customer where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int customerid = cursor.getInt(cursor.getColumnIndex("customerid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String cmail = cursor.getString(cursor.getColumnIndex("cmail"));
			String company = cursor.getString(cursor.getColumnIndex("company"));
			return new Customer(customerid, name_real, phone, address, postcode, cmail, company);
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
		Cursor cursor = db.rawQuery("select count (*) from customer", null);
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
		List<Customer> customers = new ArrayList<Customer>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select customerid as _id,name,phone,address from customer order by name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
	
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData2(String name, int offset, int maxresult) {
		List<Customer> customers = new ArrayList<Customer>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select customerid as _id,name,phone,address from customer where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

//***********************Goods********************************//
	/**
	 * 添加记录
	 * @param goods
	 */
	public void save_g(Goods goods, String name, String area, String producter, String date, Integer quality, Integer bid, Integer price) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into goods(name, area, producter, date, quality, bid, price) values(?, ?, ?, ?, ?, ?, ?)",
				new Object[] {name, area, producter, date, quality, bid, price});
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
	public void update_g(Goods goods, String name, String area, String producter, String date, String quality, String bid, String price, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update goods set name=?, area=?, producter=?, date=?, quality=?, bid=?, price=? where goodsid=?",
				new Object[] {name, area, producter, date, quality, bid, price, id});
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
			String area = cursor.getString(cursor.getColumnIndex("area"));
			String producter = cursor.getString(cursor.getColumnIndex("producter"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			Integer quality = cursor.getInt(cursor.getColumnIndex("quality"));
			Integer bid = cursor.getInt(cursor.getColumnIndex("bid"));
			Integer price = cursor.getInt(cursor.getColumnIndex("price"));
			return new Goods(goodsid, name, area, producter, date, quality, bid, price);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录Goods，通过姓名
	 * @param name
	 * @return
	 */
	public Goods find_byname_g(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from goods where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int goodsid = cursor.getInt(cursor.getColumnIndex("goodsid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String area = cursor.getString(cursor.getColumnIndex("area"));
			String producter = cursor.getString(cursor.getColumnIndex("producter"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			Integer quality = cursor.getInt(cursor.getColumnIndex("quality"));
			Integer bid = cursor.getInt(cursor.getColumnIndex("bid"));
			Integer price = cursor.getInt(cursor.getColumnIndex("price"));
			return new Goods(goodsid, name_real, area, producter, date, quality, bid, price);
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
		Cursor cursor = db.rawQuery("select goodsid as _id,name,producter,quality from goods order by name asc limit?, ?",
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
		Cursor cursor = db.rawQuery("select goodsid as _id,name,producter,quality from goods where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

//*************************Provider*******************************//

	/**
	 * 添加记录
	 * @param customer
	 */
	public void save_p(Provider provider, String edit_name, String edit_phone, String edit_address, String edit_postcode, 
			String edit_mail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into provider(name, phone, address, postcode, mail) values(?, ?, ?, ?, ?)",
				new Object[] {edit_name, edit_phone, edit_address, edit_postcode, edit_mail});
		db.close();
	}
	
	public void save2_p(Provider provider, String name, String phone, String address, String postcode, String mail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into provider(name, phone, address, postcode, mail) values(?, ?, ?, ?, ?)",
				new Object[] {name, phone, address, postcode, mail});
				
		
		db.close();
	}
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delete_p(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from provider where providerid=?", new Object[] {id});
	}
	
	public void delete_byname_p(String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from provider where name=?", new Object[] {name});
	}
	
	public void delete_byname_p(Provider provider, String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from provider where name=?", new Object[] {name});
	}
	
	/**
	 * 更改记录
	 * @param customer
	 */
	public void update_p(Provider provider, String name, String phone, String address, String postcode, String mail, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update provider set name=?, phone=?, address=?, postcode=?, mail=? where customerid=?",
				new Object[] {name, phone, address, postcode, mail, id});
	}
	
	/**
	 * 查找记录Provider，通过ID
	 * @param id
	 * @return
	 */
	public Provider find_p(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from provider where providerid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int providerid = cursor.getInt(cursor.getColumnIndex("providerid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			return new Provider(providerid, name, phone, address, postcode, mail);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名
	 * @param name
	 * @return
	 */
	public Provider find_byname_p(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from provider where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int providerid = cursor.getInt(cursor.getColumnIndex("providerid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			return new Provider(providerid, name_real, phone, address, postcode, mail);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 返回记录总数
	 * @return
	 */
	public long getCount_p() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from provider", null);
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
	public Cursor getCursorScrollData_p(int offset, int maxresult) {
		List<Provider> providers = new ArrayList<Provider>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select providerid as _id,name,phone,mail from provider order by name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData2_p(String name, int offset, int maxresult) {
		List<Provider> providers = new ArrayList<Provider>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select providerid as _id,name,phone,mail from provider where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}

//*****************************Staff********************************//

	/**
	 * 添加记录
	 * @param staff
	 */
	public void save_s(Staff staff, String name, String phone, String address, String postcode, String mail) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into staff(name, phone, address, postcode, mail) values(?, ?, ?, ?, ?)",
				new Object[] {name, phone, address, postcode, mail});
		db.close();
	}
	
	/**
	 * 添加注册信息
	 * @param staff
	 */
	public void save_regs(Staff staff, String name, Integer usrgroup, String pwd, Integer question, String ans) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	//获得数据库的操作实例
		db.execSQL("insert into staff(name, usrgroup, password, question, answer) values(?, ?, ?, ?, ?)",
				new Object[] {name, usrgroup, pwd, question, ans});
		db.close();
	}
	
	/**
	 * 删除记录
	 * @param id
	 */
	public void delete_s(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from staff where staffid=?", new Object[] {id});
	}
	
	public void delete_byname_s(String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from staff where name=?", new Object[] {name});
	}
	
	public void delete_byname_s(Staff staff, String name) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		db.execSQL("delete from staff where name=?", new Object[] {name});
	}
	
	/**
	 * 更改记录
	 * @param staff
	 */
	public void update_s(Staff staff, String name, String phone, String address, String postcode, String mail, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update staff set name=?, phone=?, address=?, postcode=?, mail=? where staffid=?",
				new Object[] {name, phone, address, postcode, mail, id});
	}
	
	/**
	 * 更改账户密码
	 * @param staff
	 */
	public void update_pwd(Staff staff,String pwd, int id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update staff set password=? where staffid=?",
				new Object[] {pwd, id});
	}
	
	/**
	 * 查找记录Staff，通过ID
	 * @param id
	 * @return
	 */
	public Staff find_s(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where staffid=?", new String[] {id.toString()});
		if(cursor.moveToFirst()) {
			int staffid = cursor.getInt(cursor.getColumnIndex("staffid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String usrgroup = cursor.getString(cursor.getColumnIndex("usrgroup"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			Integer question = cursor.getInt(cursor.getColumnIndex("question"));
			String answer = cursor.getString(cursor.getColumnIndex("answer"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			return new Staff(staffid, name, usrgroup, password, question, answer, phone, postcode, mail);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名，模糊查找
	 * @param name
	 * @return
	 */
	public Staff find_byname_s(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where name like ?", new String[] {"%" + name + "%"});
		if(cursor.moveToFirst()) {
			int staffid = cursor.getInt(cursor.getColumnIndex("staffid"));
			String name_real = cursor.getString(cursor.getColumnIndex("name"));
			String usrgroup = cursor.getString(cursor.getColumnIndex("usrgroup"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			Integer question = cursor.getInt(cursor.getColumnIndex("question"));
			String answer = cursor.getString(cursor.getColumnIndex("answer"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
			String mail = cursor.getString(cursor.getColumnIndex("mail"));
			return new Staff(staffid, name_real, usrgroup, password, question, answer, phone, postcode, mail);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 查找记录，通过姓名，返回密保问题
	 * @param name
	 * @return
	 */
	public Integer find_byname_s_fp(String name) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where name like ?", new String[] {name});
		if(cursor.moveToFirst()) {
			Integer question = cursor.getInt(cursor.getColumnIndex("question"));
			return question;
		}
		cursor.close();
		return -1;
	}
	
	/**
	 * 查找记录，返回密码
	 * @param name
	 * @return
	 */
	public String find_byname_s_pwd(String name, Integer question, String answer) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where name=?", new String[] {name});
		if(cursor.moveToFirst()) {
			String answ = cursor.getString(cursor.getColumnIndex("answer"));
			Integer ques = cursor.getInt(cursor.getColumnIndex("question"));
			
			if(answer.equals(answ)&&question.equals(ques)) {
				return cursor.getString(cursor.getColumnIndex("password"));
			}
			
		}
		return null;
	}
	
	/**
	 * 查找记录，通过姓名
	 * @param name
	 * @return
	 */
	public String find_byname_s_pwd(String name) {
		String pwd = "x";
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where name like ?", new String[] {name});
		if(cursor.moveToFirst()) {
			pwd = cursor.getString(cursor.getColumnIndex("password"));
			return pwd;
		}
		cursor.close();
		return "x";
	}
	
	/**
	 * 查找记录，通过姓名，返回staffid
	 * @param name
	 * @return
	 */
	public Integer find_byname_s_id(String name) {
		Integer ids;
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select * from staff where name=?", new String[] {name});
		if(cursor.moveToFirst()) {
			ids = cursor.getInt(cursor.getColumnIndex("staffid"));
			return ids;
		}
		cursor.close();
		return -1;
	}
	
	/**
	 * 返回记录总数
	 * @return
	 */
	public long getCount_s() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();	
		Cursor cursor = db.rawQuery("select count (*) from staff", null);
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
	public Cursor getCursorScrollData_s(int offset, int maxresult) {
		List<Staff> staffs = new ArrayList<Staff>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select staffid as _id,name,phone,mail from staff order by name asc limit?, ?",
				new String[] {String.valueOf(offset), String.valueOf(maxresult)});
		
		return cursor;
	}
	
	/**
	 * 返回查找记录Cursor
	 * @param offset
	 * @param maxresult
	 * @return
	 */
	public Cursor getCursorScrollData2_s(String name, int offset, int maxresult) {
		List<Staff> staffs = new ArrayList<Staff>();
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();	
		Cursor cursor = db.rawQuery("select staffid as _id,name,phone,mail from staff where name like ? order by name asc limit?, ?",
				new String[] {"%" + name + "%", String.valueOf(offset), String.valueOf(maxresult)});
		return cursor;
	}
}

