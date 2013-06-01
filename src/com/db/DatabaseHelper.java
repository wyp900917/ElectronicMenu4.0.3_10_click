package com.db;

import com.EleMenu403_click.myApplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * ������Ҫ��ʵ���˻�ȡ���ݿ����Ĺ��ܣ�ͨ�������MyDBHelper�����õ�һ��SQLiteDatabase����ʵ��
 * 
 * @author ping
 * 
 */
public class DatabaseHelper {
	/*public static final String PATH = "/mnt/sdcard/electronicMenu/sql/";
	*//**
	 * ���ݿ�����
	 *//*
	public static final String DB_DBNAME = "electronicMenu.db";*/
	/**
	 * ���ݿ�İ汾��
	 */
	public static final int VERSION = 1;
	/**
	 * ���ݿ�ʵ������ʹ�õ���ģʽ
	 */
	public static SQLiteDatabase dbInstance;

	//private MyDBHelper myDBHelper;
	private Context context;
	
	private myApplication myapp;
	
	/**  
	 * DatabaseHelper��Ĺ��췽��
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		this.context = context;
		this.context.getPackageName();
	}

	/**
	 * �÷�����ʹ��MyDBHelper�������һ�����ݿ�ʵ������
	 * 
	 * @return dbInstance ����һ�����ݿ�ʵ������2
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
			// �õ�SQLDatabase����
			dbInstance = SQLiteDatabase.openOrCreateDatabase(
					myapp.getSqlpath() + myapp.getDbname(), null);
		}
		return dbInstance;
	}
}

/*class MyDBHelper extends SQLiteOpenHelper {
	*//**
	 * ���췽��
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
	 * ���췽����Ĭ�ϵ�CursorFactoryΪnull
	 * 
	 * @param context
	 * @param name
	 * @param version
	 *//*
	public MyDBHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	*//**
	 * ���췽����Ĭ�ϵ�CursorFactoryΪNull��Ĭ�ϵ����ݿ�汾��Ϊ1
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
		System.out.println("�������ݿ⣡");
		// String sql = "create table user(id int,name varchar(20))";
		// db.execSQL(sql);
	}

	*//**
	 * Called when the database needs to be upgraded. The implementation should
	 * use this method to drop tables, add tables, or do anything else it needs
	 * to upgrade to the new schema version.
	 *//*
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		System.out.println("�������ݿ⣡");
	}

}*/
