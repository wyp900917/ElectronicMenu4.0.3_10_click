package com.db;

import com.EleMenu403_click.myApplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * 该类主要是实现了获取数据库对象的功能，通过定义的MyDBHelper类来得到一个SQLiteDatabase对象实例
 * 
 * @author ping
 * 
 */
public class DatabaseHelper {
	/*public static final String PATH = "/mnt/sdcard/electronicMenu/sql/";
	*//**
	 * 数据库名称
	 *//*
	public static final String DB_DBNAME = "electronicMenu.db";*/
	/**
	 * 数据库的版本号
	 */
	public static final int VERSION = 1;
	/**
	 * 数据库实例对象，使用单例模式
	 */
	public static SQLiteDatabase dbInstance;

	//private MyDBHelper myDBHelper;
	private Context context;
	
	private myApplication myapp;
	
	/**  
	 * DatabaseHelper类的构造方法
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		this.context = context;
		this.context.getPackageName();
	}

	/**
	 * 该方法是使用MyDBHelper类来获得一个数据库实例对象
	 * 
	 * @return dbInstance 返回一个数据库实例对象2
	 */
	public SQLiteDatabase openDatabase() {
		myapp = new myApplication();
		if (dbInstance == null) {
			/*
			 * myDBHelper = new MyDBHelper(context,DB_DBNAME,VERSION); //Create
			 * and/or open a database that will be used for reading and writing.
			 * //The first time this is called, the database will be opened and
			 * onCreate(SQLiteDatabase), onUpgrade(SQLiteDatabase, int, int)
			 * and/or onOpen(SQLiteDatabase) will be called. dbInstance =
			 * myDBHelper.getWritableDatabase();
			 */
			// 得到SQLDatabase对象
			dbInstance = SQLiteDatabase.openOrCreateDatabase(
					myapp.getSqlpath() + myapp.getDbname(), null);
		}
		return dbInstance;
	}
}

/*class MyDBHelper extends SQLiteOpenHelper {
	*//**
	 * 构造方法
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 *//*
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	*//**
	 * 构造方法，默认的CursorFactory为null
	 * 
	 * @param context
	 * @param name
	 * @param version
	 *//*
	public MyDBHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	*//**
	 * 构造方法，默认的CursorFactory为Null，默认的数据库版本号为1
	 * 
	 * @param context
	 * @param name
	 *//*
	public MyDBHelper(Context context, String name) {
		this(context, name, 1);
	}

	*//**
	 * Called when the database is created for the first time. This is where the
	 * creation of tables and the initial population of the tables should
	 * happen.
	 *//*
	public void onCreate(SQLiteDatabase db) {
		System.out.println("创建数据库！");
		// String sql = "create table user(id int,name varchar(20))";
		// db.execSQL(sql);
	}

	*//**
	 * Called when the database needs to be upgraded. The implementation should
	 * use this method to drop tables, add tables, or do anything else it needs
	 * to upgrade to the new schema version.
	 *//*
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		System.out.println("更新数据库！");
	}

}*/
