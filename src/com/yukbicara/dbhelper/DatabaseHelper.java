package com.yukbicara.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	//inisialiasi nama db dan versinya
	private static final String DATABASE_NAME = "yukbicara.db";
	private static final int DATABASE_VERSION = 1;

	//inisialiasi penaamaan table
	public static final String DAERAH_TABLE = "daerah";
	public static final String PERCAKAPAN_TABLE = "percakapan";
	public static final String KOSAKATA_TABLE = "kosakata";

	//inisialisasi primary key setiap kolom
	public static final String ID_COLUMN = "id";
	
	//inisialiasi kolom-kolom dari table daerah
	public static final String NAMA_DAERAH_COLUMN = "nama_daerah";
	
	//inisialiasi kolom-kolom percakapan
	public static final String PERCAKAPAN_ISI = "isi";
	public static final String PERCAKAPAN_TERJEMAHAN = "terjemahan";
	public static final String PERCAKAPAN_SUARA = "suara";
	
	//inisialisasi kolom-kolom kosakata
	public static final String KOSAKATA_ISI = "isi";
	public static final String KOSAKATA_TERJEMAHAN = "terjemahan";

	private static DatabaseHelper instance;
	
	public static final String CREATE_DAERAH_TABLE = "CREATE TABLE "
			+ DAERAH_TABLE + "("
			+ NAMA_DAERAH_COLUMN + " TEXT PRIMARY KEY) ";
	
	public static final String CREATE_PERCAKAPAN_TABLE = "CREATE TABLE "
			+ PERCAKAPAN_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NAMA_DAERAH_COLUMN + " TEXT, " + PERCAKAPAN_ISI + " TEXT, "
			+ PERCAKAPAN_TERJEMAHAN + " TEXT, "
			+ PERCAKAPAN_SUARA + " INTEGER, "
			+ "FOREIGN KEY(" + NAMA_DAERAH_COLUMN + ") REFERENCES "
			+ DAERAH_TABLE + "("+NAMA_DAERAH_COLUMN + "))";
	
	public static final String CREATE_KOSAKATA_TABLE = "CREATE TABLE "
			+ KOSAKATA_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NAMA_DAERAH_COLUMN + " TEXT, " + KOSAKATA_ISI + " TEXT, "
			+ KOSAKATA_TERJEMAHAN + " TEXT, "
			+ "FOREIGN KEY(" + NAMA_DAERAH_COLUMN + ") REFERENCES "
			+ DAERAH_TABLE + "("+NAMA_DAERAH_COLUMN + "))";
	
	DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public static synchronized DatabaseHelper getHelper(Context context) {
		if (instance == null)
			instance = new DatabaseHelper(context);
		return instance;
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DAERAH_TABLE);
		db.execSQL(CREATE_PERCAKAPAN_TABLE);
		db.execSQL(CREATE_KOSAKATA_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
