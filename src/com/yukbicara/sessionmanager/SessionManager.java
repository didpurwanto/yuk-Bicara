package com.yukbicara.sessionmanager;

import java.util.List;

import com.yukbicara.KamusActivity;
import com.yukbicara.db.DaerahDAO;
import com.yukbicara.model.Daerah;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

/*	private String papua = "Papua";
	private String bugis = "Bugis";
	private String aceh = "Aceh";
	private String bali = "Bali";
	private String banjar = "Banjar";
	private String ambon = "Ambon";
	private String madura = "Madura";
	private String orangRimba = "Orang Rimba";
	private String jawa = "Jawa";
	private String sunda = "Sunda";
	private String poso = "Poso";
	private String indonesia = "Indonesia";*/
	
	private DaerahDAO dd;
	private List<Daerah> daerah;
	private String prefPilih = "prefPilih";
	private String prefHasil = "prefHasil";
	SharedPreferences sp1;
	SharedPreferences sp2;
	
	public SessionManager(Context context){
		sp1 = context.getSharedPreferences(prefPilih, context.MODE_PRIVATE);
		sp2 = context.getSharedPreferences(prefHasil, context.MODE_PRIVATE);
	}
	
	public void setAwal(Context context){
		dd = new DaerahDAO(context);
	    daerah = dd.getSemuaDaerah();
	    daerah.add(new Daerah("Indonesia"));
	    SharedPreferences.Editor editor1 = sp1.edit();
	    SharedPreferences.Editor editor2 = sp2.edit();
	    for (Daerah daerah1 : daerah) {
			editor1.putInt(daerah1.getNama_daerah(), 1 );
			editor2.putInt(daerah1.getNama_daerah(), 1 );
		}
	    editor1.commit();
	    editor2.commit();
	}
	
	public void createSP(Context context){
		if (sp1.getInt("Indonesia", 0) == 0){
			setAwal(context);
		}
	}
	
	public void clearSP(){
		SharedPreferences.Editor editor1 = sp1.edit();
		SharedPreferences.Editor editor2 = sp2.edit();
		editor1.clear();
		editor2.clear();
		editor1.commit();
		editor2.commit();
		
	}
	
	public void simpanBahasaPilih(String bahasa){
		SharedPreferences.Editor editor = sp1.edit();
		editor.putInt(bahasa, sp1.getInt(bahasa, 0)+1 );
		editor.commit();
	}
	
	public void simpanBahasaHasil(String bahasa){
		SharedPreferences.Editor editor = sp2.edit();
		editor.putInt(bahasa, sp2.getInt(bahasa, 0)+1 );
		editor.commit();
	}
	
	public String getBahasaPilih(Context context){
		String bahasa = "";
		dd = new DaerahDAO(context);
	    daerah = dd.getSemuaDaerah();
	    daerah.add(new Daerah("Indonesia"));
	    int max = 0;
	    for (Daerah daerah1 : daerah) {
			if (max < sp1.getInt(daerah1.getNama_daerah(), 0)){
				max = sp1.getInt(daerah1.getNama_daerah(), 0);
				bahasa = daerah1.getNama_daerah();
			}
		}
		
		return bahasa;
	}
	public String getBahasaHasil(Context context){
		String bahasa = "";
		dd = new DaerahDAO(context);
	    daerah = dd.getSemuaDaerah();
	    daerah.add(new Daerah("Indonesia"));
	    int max = 0;
	    for (Daerah daerah1 : daerah) {
			if (max < sp2.getInt(daerah1.getNama_daerah(), 0)){
				max = sp2.getInt(daerah1.getNama_daerah(), 0);
				bahasa = daerah1.getNama_daerah();
			}
		}
		return bahasa;
	}
}
