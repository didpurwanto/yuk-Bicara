package com.yukbicara.db;

import java.util.ArrayList;
import java.util.List;

import com.yukbicara.R;
import com.yukbicara.dbhelper.DatabaseHelper;
import com.yukbicara.model.Percakapan;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class PercakapanDAO extends DAO {

	public PercakapanDAO(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public long save(Percakapan percakapan) {
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.NAMA_DAERAH_COLUMN, percakapan.getNama_daerah());
		cv.put(DatabaseHelper.PERCAKAPAN_ISI, percakapan.getIsi());
		cv.put(DatabaseHelper.PERCAKAPAN_TERJEMAHAN, percakapan.getTerjemahan());
		cv.put(DatabaseHelper.PERCAKAPAN_SUARA, percakapan.getSuara());

		return database.insert(DatabaseHelper.PERCAKAPAN_TABLE, null, cv);
	}

	public List<Percakapan> getAllPercakapan() {
		List<Percakapan> percakapan = new ArrayList<Percakapan>();
		Cursor cursor = database.query(DatabaseHelper.PERCAKAPAN_TABLE,
				new String[] { DatabaseHelper.ID_COLUMN,
						DatabaseHelper.NAMA_DAERAH_COLUMN,
						DatabaseHelper.PERCAKAPAN_ISI,
						DatabaseHelper.PERCAKAPAN_TERJEMAHAN,
						DatabaseHelper.PERCAKAPAN_SUARA }, null, null,
				null, null, null);
		while (cursor.moveToNext()) {
			Percakapan temp = new Percakapan(cursor.getInt(0),
					cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getInt(4));
			percakapan.add(temp);
		}
		return percakapan;
	}
	
	public List<Percakapan> getPercakapan(String bahasa){
		List<Percakapan> percakapan = new ArrayList<Percakapan>();
		Cursor cursor = database.query(DatabaseHelper.PERCAKAPAN_TABLE,
				new String[] { DatabaseHelper.ID_COLUMN,
						DatabaseHelper.NAMA_DAERAH_COLUMN,
						DatabaseHelper.PERCAKAPAN_ISI,
						DatabaseHelper.PERCAKAPAN_TERJEMAHAN,
						DatabaseHelper.PERCAKAPAN_SUARA },
						DatabaseHelper.NAMA_DAERAH_COLUMN +" = ?", 
				new String[] { bahasa },
				null, null, null);
		while (cursor.moveToNext()) {
			Percakapan temp = new Percakapan(cursor.getInt(0),
					cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getInt(4));
			Log.d("percakapan~", temp.getTerjemahan());
			percakapan.add(temp);
		}
		return percakapan;
	}

	public void addPercakapan() {
		/*save(new Percakapan(
				0,
				"Aceh",
				"A\t:\n" +
				"Kamu sudah makan?\n" +
				"Droëneuh kaleuh pajôh bu?\n" +
				"B\t:\n" +
				"Belum, apakah kamu sudah?\n" +
				"goh lom, Droëneuh ka lheh?\n" +
				"A\t:\n" +
				"Belum juga, bagaimana kalau kita makan bersama?\n" +
				"Goh lom cit, meunyo ta pajoh bu ngon lon kiban?\n" +
				"B\t:\n" +
				"Boleh, mari berangkat bersama ke kantin\n" +
				"jet, yak ta jak bak kantin\n",
				"Ke Kantin",
				R.raw.jawa_percakapan1));*/
				
		save(new Percakapan(0, "Jawa", 
				"A\t:\n" +
				"selamat pagi\n"+
		    	"sugeng enjing\n"+
			    "B\t:\n" +
			    "selamat pagi juga\n"+
			    "sami-sami, sugeng enjing\n"+
			    "A\t:\n" +
			    "Bagaimana kabarmu?\n"+
			    "pripun kabare?\n"+
	    		"B\t:\n" +
	    		"Baik, bagaimana dengan kamu?\n"+
			    "sae, panjenengan?\n"+
	    		"A\t:\n" +
	    		"Saya baik juga\n"+
			    "Sami, Sae mawon\n"+
		    	"B\t:\n" +
		    	"Mau pergi kemana?\n"+
			    "badhe tindhak pundi?\n"+
	    		"A\t:\n" +
	    		"Mau ke pasar\n"+
			    "Badhe teng peken\n",
			    "Sapaan",
			    R.raw.jawa_percakapan1));
		
		save(new Percakapan(0, "Jawa", 
			"A\t:\n" +
			"selamat malam\n"+
		    "sugeng dalu\n"+
		    "B\t:\n" +
		    "ada perlu apa?\n"+
		    "wonten betah nopo?\n"+
    		"A\t:\n" +
    		"Mau ketemu ibumu\n"+		    
		    "Badhe ketemu mbok sampeyan\n"+
		    "B\t:\n" +
		    "Silahkan, ibu ada di rumah\n"+
		    "Monggo, mbok e wonten  dalem\n"+
		    "A\t:\n" +
		    "Terima kasih\n"+
		    "Matur suwun\n"+
		    "B\t:\n" +
		    "Sama -sama\n"+
		    "sami-sami\n",
		    "Mencari Orang",
		    R.raw.jawa_percakapan2));
		
		save(new Percakapan(0, "Jawa", 
			"A\t:\n" +
			"Mau kemana?\n"+
		    "Badhe tindak pundi?\n"+
			"B\t:\n" +
			"Mau pergi ke sawah\n"+
		    "Badhe teng saben\n"+
			"A\t:\n" +
			"Sudah mulai panen ya?\n"+
		    "sampun panen tha?\n"+
			"B\t:\n" +
			"Sudah, kemarin sudah mulai memanen\n"+
		    "sampun, kolo wingi sampun mulai manen\n"+
			"A\t:\n" +
			"Oh begitu, baik, silahkan ke sawah\n"+
		    "Oh ngoten, njeeh, monggo ten saben\n",
		    "Sapaan di Sawah",
		    R.raw.jawa_percakapan3));
		
		save(new Percakapan(0, "Sunda", 
			"A\t:\n" +
			"selamat siang\n" +
			"wilujeung siang\n" +
			"B\t:\n" +
			"selamat siang juga\n" +
			"wilujeung siang oge" +
			"A\t:\n" +
			"Bagaimana kabarmu?\n" +
			"kumaha damang?\n" +
			"B\t:\n" +
			"Baik, bagaimana dengan kamu?\n" +
			"Alhamdulillah pangestu, kumaha sawangsulna?\n" +
			"A\t:\n" +
			"Saya baik juga. Kamu mau pergi kemana?\n" +
			"Alhamdulillah pangestu oge, bade angkat kamana?\n" +
			"B\t:\n" +
			"Saya ingin pergi ke pasar\n" +
			"abdi bade angkat ka pasar\n" +
			"A\t:\n" +
			"Oh, boleh saya ikut?\n" +
			"oh, wios abdi ngiring?\n" +
			"B\t:\n" +
			"Iya, silahkan\n" +
			"muhun mangga",
			"Sapaan", 
			R.raw.sunda_percakapan1));
		
		save(new Percakapan(0, "Sunda", 
				"A\t:\n" +
				"Selamat pagi\n" +
				"wilujeung enjing\n" +
				"B\t:\n" +
				"Selamat pagi juga\n" +
				"wilujeung enjing oge\n" +
				"A\t:\n" +
				"Selamat pagi juga\n" +
				"parantos tuang?\n" +
				"B\t:\n" +
				"Belum, apakah kamu sudah?\n" +
				"teu acan, ai anjeun atos teu acan?\n" +
				"A\t:\n" +
				"Belum juga, bagaimana kalau kita makan bersama?\n" +
				"teu acan oge. Kumaha upami urang tuang sasarengan?\n" +
				"B\t:\n" +
				"Boleh, mari berangkat bersama ke kantin\n" +
				"muhun mangga, hayu urang sareng ka kantin\n" +
				"A\t:\n" +
				"Iya baik\n" +
				"muhun hayu\n" +
				"B\t:\n" +
				"Iya\n" +
				"muhun\n" ,
				"Ke Kantin",
				R.raw.sunda_percakapan2));
		
		save(new Percakapan(0, "Sunda", 
				"A\t:\n" +
				"Hai\n" +
				"Hai\n" +
				"B\t:\n" +
				"Hai juga\n" +
				"Hai juga\n" +
				"A\t:\n" +
				"Boleh kenalan?\n" +
				"wios nepangan?\n" +
				"B\t:\n" +
				"Iya, silahkan\n" +
				"muhun mangga\n" +
				"A\t:\n" +
				"Nama saya Dita, kamu siapa?\n" +
				"nami abdi dita, nami anjeun saha?\n" +
				"B\t:\n" +
				"Saya Ammy Rahmatika, bisa dipanggil Ammy\n" +
				"abdi Ammy Rahmatika, tiasa di panggil Ammy\n" +
				"A\t:\n" +
				"Salam kenal ya\n" +
				"wilujeung uninga\n" +
				"B\t:\n" +
				"Iya salam kenal juga\n" +
				"muhun, wilujeung uninga oge\n" ,
				"Perkenalan", 
				R.raw.sunda_percakapan3));
		
		save(new Percakapan(0, "Batak", 
				"A\t:\n" +
				"selamat siang\n"+
		    	"horas arian\n"+
			    "B\t:\n" +
			    "selamat siang juga\n"+
			    "horas arian\n"+
			    "A\t:\n" +
			    "kamu sudah makan?\n"+
			    "nunga mangan ho ?\n"+
	    		"B\t:\n" +
	    		"sudah, apakah kamu sudah?\n"+
			    "nunga, nunga ho ?\n"+
	    		"A\t:\n" +
	    		"belum\n"+
			    "daong dope\n"+
		    	"B\t:\n" +
		    	"baik, silahkan makan duluan\n"+
			    "olo, parjolo ma ho\n",
			    "Sapaan",
			    R.raw.batak_percakapan1));

		save(new Percakapan(0, "Batak", 
				"A\t:\n" +
				"halo, siapa namamu?\n"+
		    	"horas, ise goarmu?\n"+
			    "B\t:\n" +
			    "kenalkan, nama saya Andri\n"+
			    "horas, goarhu andri\n"+
			    "A\t:\n" +
			    "kenalkan juga nama saya welderson\n"+
			    "goarhu welderson\n"+
	    		"B\t:\n" +
	    		"dari mana asalmu?\n"+
			    "sian dia do ho\n"+
	    		"A\t:\n" +
	    		"saya dari batak\n"+
			    "ahu sian batak\n",		    	
			    "Perkenalan",
			    R.raw.batak_percakapan2));


	}
	
	
}
