package com.yukbicara;

import java.util.ArrayList;
import java.util.List;

import com.yukbicara.model.Daerah;
import com.yukbicara.sessionmanager.SessionManager;
import com.yukbicara.db.DaerahDAO;
import com.yukbicara.db.KosakataDAO;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;

public class KamusActivity extends Activity {
	
	private DaerahDAO dd;
	private KosakataDAO kd;
	private SessionManager sm;
	private List<Daerah> daerah;
	
	private Button bt_bahasa1;
	private Button bt_bahasa2;
	private Button bt_pilih;
	
	private Button bt_bahasaHasil1;
	private Button bt_bahasaHasil2;
	private Button bt_pilihHasil;
	
	private EditText input;
	private TextView output;
	
	private String bahasaInput;

	private String bahasaOutput;
	private String[] listDaerah;
	/*= {
			"Aceh",				//0
			"Ambon",			//1
			"Bali",				//2
			"Banjar",			//3
			"Bugis",			//4
			"Jawa",				//5
			"Madura",			//6
			"Orang Rimba",		//7
			"Papua",			//8
			"Poso",				//9
			"Sunda"	};			//10
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kamus);
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    sm = new SessionManager(KamusActivity.this);
	    //sm.clearSP();
	    sm.createSP(KamusActivity.this);
	    bahasaInput = sm.getBahasaPilih(KamusActivity.this);
	    bahasaOutput = sm.getBahasaHasil(KamusActivity.this);
	    
	    dd = new DaerahDAO(KamusActivity.this);
	    daerah = dd.getSemuaDaerah();
	    daerah.add(new Daerah("Indonesia"));
	    listDaerah = new String[daerah.size()];
	    for(int i =0;i<daerah.size();i++){
	    	listDaerah[i]= daerah.get(i).getNama_daerah();
	    }
	    
	    bt_bahasa1 = (Button)findViewById(R.id.bt_bahasa1);
	    bt_bahasa1.setPressed(true);
	    bt_bahasa1.setTextColor(Color.BLACK);
	    bt_bahasa1.setText(bahasaInput);
	    bt_bahasa2=(Button)findViewById(R.id.bt_bahasa2);
	    bt_pilih=(Button)findViewById(R.id.bt_pilih);
	    bt_bahasaHasil1=(Button)findViewById(R.id.bt_bahasaHasil1);
	    bt_bahasaHasil1.setPressed(true);
	    bt_bahasaHasil1.setTextColor(Color.BLACK);
	    bt_bahasaHasil1.setText(bahasaOutput);
	    bt_bahasaHasil2=(Button)findViewById(R.id.bt_bahasaHasil2);
	    bt_pilihHasil=(Button)findViewById(R.id.bt_pilihHasil);
	    
	    input=(EditText)findViewById(R.id.inputKamus);
	    output=(TextView)findViewById(R.id.hasilterjemahan);
	    
	    bt_bahasa1.setOnClickListener(bt_bahasa1_selected);
	    bt_bahasa1.setOnTouchListener(bt_bahasa1_touch);
	    bt_bahasa2.setOnClickListener(bt_bahasa2_selected);
	    bt_bahasa2.setOnTouchListener(bt_bahasa2_touch);
	    bt_pilih.setOnClickListener(bt_pilihInputListener);
	    bt_bahasaHasil1.setOnTouchListener(bt_bahasaHasil1_touch);
	    bt_bahasaHasil1.setOnClickListener(bt_bahasaHasil1_selected);
	    bt_bahasaHasil2.setOnTouchListener(bt_bahasaHasil2_touch);
	    bt_bahasaHasil2.setOnClickListener(bt_bahasaHasil2_selected);
	    bt_pilihHasil.setOnClickListener(bt_pilihHasilOutput);
	    
	    kd = new KosakataDAO(KamusActivity.this);
	    
	    input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				Log.d("debug query",arg0.toString()+" "+bahasaInput+" "+bahasaOutput);
				String arti=kd.translate(arg0.toString(), bahasaInput, bahasaOutput);
				output.setText(arti);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	OnTouchListener bt_bahasa1_touch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			bt_bahasa1.setPressed(true);
			bt_bahasa2.setPressed(false);
			bt_bahasa1.setTextColor(Color.BLACK);
			bt_bahasa2.setTextColor(Color.WHITE);
			arg0.performClick();
			if (!input.getText().equals("")){
				String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
				output.setText(arti);
			}
				
			return true;
		}
	};
	OnTouchListener bt_bahasa2_touch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			bt_bahasa2.setPressed(true);
			//bt_bahasa2.setTextColor(Color.parseColor("#FEFEFE"));
			bt_bahasa1.setPressed(false);
			bt_bahasa1.setTextColor(Color.WHITE);
			bt_bahasa2.setTextColor(Color.BLACK);
			//bt_bahasa2.setTextColor(Color.parseColor("#282829"));
			arg0.performClick();
			if (!input.getText().equals("")){
				String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
				output.setText(arti);
			}
			return true;
		}
	};
	OnTouchListener bt_bahasaHasil1_touch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			bt_bahasaHasil1.setPressed(true);
			bt_bahasaHasil2.setPressed(false);
			bt_bahasaHasil1.setTextColor(Color.BLACK);
			bt_bahasaHasil2.setTextColor(Color.WHITE);
			arg0.performClick();
			if (!input.getText().equals("")){
				String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
				output.setText(arti);
			}
			return true;
		}
	};
	OnTouchListener bt_bahasaHasil2_touch = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			bt_bahasaHasil2.setPressed(true);
			bt_bahasaHasil1.setPressed(false);
			bt_bahasaHasil1.setTextColor(Color.WHITE);
			bt_bahasaHasil2.setTextColor(Color.BLACK);
			arg0.performClick();
			if (!input.getText().equals("")){
				String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
				output.setText(arti);
			}
			return true;
		}
	};
	
	
	
	OnClickListener bt_pilihHasilOutput = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(KamusActivity.this);
			builder.setTitle("Pilih Bahasa");
			builder.setItems(listDaerah, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					String temp = listDaerah[arg1].toString();
					bt_bahasaHasil2.setText(temp);
					bt_bahasaHasil2.setPressed(true);
					bt_bahasaHasil1.setPressed(false);
					bt_bahasaHasil1.setTextColor(Color.WHITE);
					bt_bahasaHasil2.setTextColor(Color.BLACK);
					bahasaOutput = temp;
					if (!input.getText().equals("")){
						String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
						output.setText(arti);
					}
					sm.simpanBahasaHasil(bahasaOutput);
					//output.setText("");
					//input.setText("");
				}
			}); builder.show();
		}
	};
	OnClickListener bt_bahasaHasil2_selected = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			bahasaOutput = bt_bahasaHasil2.getText().toString();
			//output.setText("");
			//input.setText("");
			sm.simpanBahasaHasil(bahasaOutput);
		}
	};
	OnClickListener bt_bahasaHasil1_selected = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			bahasaOutput = bt_bahasaHasil1.getText().toString();
			//output.setText("");
			//input.setText("");
			sm.simpanBahasaHasil(bahasaOutput);
		}
	};
	OnClickListener bt_bahasa2_selected = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			bahasaInput = bt_bahasa2.getText().toString();
			//output.setText("");
			//input.setText("");
			sm.simpanBahasaPilih(bahasaInput);
		}
	};
	OnClickListener bt_bahasa1_selected = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			bahasaInput = bt_bahasa1.getText().toString();
			//output.setText("");
			//input.setText("");
			sm.simpanBahasaPilih(bahasaInput);
		}
	};
	
	OnClickListener bt_pilihInputListener = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(KamusActivity.this);
			builder.setTitle("Pilih Bahasa");
			//final CharSequence bahasa[] = daerah.toArray(new CharSequence[daerah.size()]);
			builder.setItems(listDaerah, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					String temp = listDaerah[arg1].toString();
					bt_bahasa2.setText(temp);
					bt_bahasa2.setPressed(true);
					bt_bahasa1.setPressed(false);
					bt_bahasa1.setTextColor(Color.WHITE);
					bt_bahasa2.setTextColor(Color.BLACK);
					bahasaInput = temp;
					if (!input.getText().equals("")){
						String arti=kd.translate(input.getText().toString(), bahasaInput, bahasaOutput);
						output.setText(arti);
					}
					sm.simpanBahasaPilih(bahasaInput);
					//output.setText("");
					//input.setText("");
				}
			}); builder.show();
	}
	};
	/*public void terjemahKata(View v){
		final TextView tvb1 = (TextView)findViewById(R.id.tvBahasa1);
	    final TextView tvb2 = (TextView)findViewById(R.id.tvBahasa2);
	    final TextView tvt = (TextView)findViewById(R.id.tvTerjemahan);
	    final EditText etk = (EditText)findViewById(R.id.etInputKata);
	    String bahasa1 = tvb1.getText().toString();
	    String bahasa2 = tvb2.getText().toString();
	    String kata = etk.getText().toString();
	    
	    if ( bahasa1.equals("Pilih")){
	    	createAlert("Pilih bahasa asal");
	    } else if ( bahasa2.equals("Pilih")){
	    	createAlert("Pilih bahasa tujuan");
	    } else if ( kata.equals("")){
	    	createAlert("Masukkan kata yang ingin di terjemahkan");
	    } else if ( !bahasa1.equals("Pilih") &&      
	    			!bahasa2.equals("Pilih") &&
    				!kata.equals("") ){
	    	kd = new KosakataDAO(KamusActivity.this);
	    	String arti = "";
	    	String kataTerjemahan = "";
	    	if (!bahasa1.equals("Indonesia")){
	    		arti = kd.getArtiKata(bahasa1, kata);
	    	}
	    	else {
	    		arti = kata;
	    	}
	    	if (!bahasa2.equals("Indonesia")){
	    		kataTerjemahan = kd.getKata(bahasa2, arti);
	    	}
	    	else {
	    		kataTerjemahan = arti;
	    	}
	    	
	    	tvt.setText("Arti :\n"+arti+"\n"+bahasa2+" :\n"+kataTerjemahan);
	    }
	}*/

	public void pilihBahasaOnClick(View v){
		final int idTextView =  v.getId();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(KamusActivity.this);
		builder.setTitle("Pilih Bahasa");
		builder.setItems(listDaerah, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TextView tvb = (TextView)findViewById(idTextView);
				tvb.setText(listDaerah[which]);
			}
		});
		builder.show();
	}
	
	public void createAlert(String s){
		AlertDialog.Builder builder = new AlertDialog.Builder(KamusActivity.this);
		builder.setMessage(s);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kamus, menu);
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
		} else if (id == R.id.action_clear){
			sm.clearSP();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
