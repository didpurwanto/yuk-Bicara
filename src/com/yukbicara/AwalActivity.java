package com.yukbicara;

import java.util.ArrayList;
import java.util.List;

import com.yukbicara.db.DaerahDAO;
import com.yukbicara.model.Daerah;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class AwalActivity extends Activity {

	private String pilihanBahasa;
	private String[] bahasa = {"Jawa"};
	private String[] listDaerah={"Batak","Jawa","Sunda"};;
	private DaerahDAO dd;
	private List<Daerah> daerah;
	
	List<String> didik = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_awal);
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		
		ImageButton ibpp = (ImageButton)findViewById(R.id.iBtnPilihPercakapan);

		dd = new DaerahDAO(AwalActivity.this);
		daerah = dd.getSemuaDaerah();
		/*listDaerah = new String[daerah.size()];
		for (int i=0; i<daerah.size(); i++){
			listDaerah[i] = daerah.get(i).getNama_daerah();
		}*/
		ibpp.setOnClickListener(ibppListener);
		// pilihanBahasa = (TextBox)findViewById(R.id.textBoxBahasa).getText().ToString(); ambil bahasa yang dipilih
		pilihanBahasa = "Jawa";
		
		
		
		EditText etb = (EditText)findViewById(R.id.editTextBahasa);
		etb.setOnClickListener(etbListener);
		
	}
	
	/*
	 * public Dialog createDialog(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(AwalActivity.this);
		builder.setTitle("Pilih Bahasa");
		builder.setItems(bahasa, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				pilihanBahasa = bahasa[which];
			}
		}); 
		
		return builder.create();
	}
	*/
	
	OnClickListener etbListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(AwalActivity.this);
			builder.setTitle("Pilih Bahasa");
			//CharSequence[] cs = didik.toArray(new CharSequence[didik.size()]);
			builder.setItems(listDaerah, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					pilihanBahasa = listDaerah[which];
					EditText etb = (EditText)findViewById(R.id.editTextBahasa);
					etb.setText(pilihanBahasa);
				}
			}); 
			
			builder.show();
		}
	};
	
	
	OnClickListener ibppListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(AwalActivity.this, PercakapanActivity.class);
			intent.putExtra("Bahasa", pilihanBahasa);
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.awal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
