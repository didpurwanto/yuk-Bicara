package com.yukbicara.db;

import com.yukbicara.dbhelper.DatabaseHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DAO {
	
	protected SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private Context mContext;
	
	public DAO(Context context) {
		this.mContext = context;
		dbHelper = DatabaseHelper.getHelper(mContext);
		open();
	}

	public void open() throws SQLException {
		if(dbHelper == null)
			dbHelper = DatabaseHelper.getHelper(mContext);
		database = dbHelper.getWritableDatabase();
	}
}
