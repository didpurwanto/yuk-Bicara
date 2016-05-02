package com.yukbicara.db;

import java.util.ArrayList;
import java.util.List;
import com.yukbicara.dbhelper.DatabaseHelper;
import com.yukbicara.model.Daerah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DaerahDAO extends DAO {

	public DaerahDAO(Context context) {
		super(context);
	}

	public long save(Daerah daerah){

		ContentValues cv = new  ContentValues();
		cv.put(DatabaseHelper.NAMA_DAERAH_COLUMN, daerah.getNama_daerah());

		return database.insert(DatabaseHelper.DAERAH_TABLE, null, cv);
 	}
	public List<Daerah> getSemuaDaerah(){
		List<Daerah> ListDaerah = new ArrayList<Daerah>();
		Cursor cursor = database.query(DatabaseHelper.DAERAH_TABLE,new String[]{DatabaseHelper.NAMA_DAERAH_COLUMN}
		,null,null,null,null,null);
		while(cursor.moveToNext()){
			Daerah daerah = new Daerah(cursor.getString(0));
			ListDaerah.add(daerah);
		}
		return ListDaerah;
	}
	public void loadDaerah(){
		save(new Daerah("Batak"));
		save(new Daerah("Papua"));
		save(new Daerah("Bugis"));
		save(new Daerah("Aceh"));
		save(new Daerah("Bali"));
		save(new Daerah("Banjar"));
		save(new Daerah("Ambon"));
		save(new Daerah("Madura"));
		save(new Daerah("Orang Rimba"));
		save(new Daerah("Jawa"));
		save(new Daerah("Sunda"));
		save(new Daerah("Poso"));
		//Log.d("testing",percakapan2.getNama_daerah());
	}
}