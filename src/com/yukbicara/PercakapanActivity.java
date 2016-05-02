package com.yukbicara;


import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yukbicara.db.PercakapanDAO;
import com.yukbicara.model.Percakapan;

public class PercakapanActivity extends Activity {

	private String[] listPercakapanz = {"Sapaan", "Mencari Orang", "Sapaan di Sawah"};
	private String[] percakapan = {
			"A :\tselamat pagi\n"+
	    	"\t\tsugeng enjing\n"+
		    "B :\tselamat pagi juga\n"+
		    "\t\tsami-sami, sugeng enjing\n"+
		    "A :\tBagaimana kabarmu?\n"+
		    "\t\tpripun kabare?\n"+
    		"B :\tBaik, bagaimana dengan kamu?\n"+
		    "\t\tsae, panjenengan?\n"+
    		"A :\tSaya baik juga\n"+
		    "\t\tSami, Sae mawon\n"+
	    	"B :\tMau pergi kemana?\n"+
		    "\t\tbadhe tindhak pundi?\n"+
    		"A :\tMau ke pasar\n"+
		    "\t\tBadhe teng peken.\n",
		    
		    "A :\tselamat malam\n"+
		    "\t\tsugeng dalu\n"+
		    "B :\tada perlu apa?\n"+
		    "\t\twonten betah nopo?\n"+
    		"A :\tMau ketemu ibumu\n"+
		    "\t\tBadhe ketemu mbok sampeyan\n"+
		    "B :\tSilahkan, ibu ada di rumah\n"+
		    "\t\tMonggo, mbok e wonten  dalem\n"+
		    "A :\tTerima kasih\n"+
		    "\t\tMatur suwun\n"+
		    "B :\tSama -sama\n"+
		    "\t\tsami-sami",
		    
		    "A :\tMau kemana?\n"+
		    "\t\tBadhe tindak pundi?\n"+
			"B :\tMau pergi ke sawah\n"+
		    "\t\tBadhe teng saben\n"+
			"A :\tSudah mulai panen ya?\n"+
		    "\t\tsampun panen tha?\n"+
			"B :\tSudah, kemarin sudah mulai memanen\n"+
		    "\t\tsampun, kolo wingi sampun mulai manen\n"+
			"A :\tOh begitu, baik, silahkan ke sawah\n"+
		    "\t\tOh ngoten, njeeh, monggo ten saben\n"
		    
	};
	
	private List<Percakapan> listPercakapan;
	private PercakapanDAO pd;
	private String[] judulPercakapan;
	private String percakapanPilihan;
	private String pilihanBahasa;
	private int indexPilihan;
	private boolean isPlaying;
	private MediaPlayer mp2;
	private ImageButton ibms;
	//private MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_percakapan);
		
		Bundle bundle = new Bundle(getIntent().getExtras());
		pilihanBahasa = bundle.getString("Bahasa");
		
		Log.d("pilihan bahasa", pilihanBahasa);
		
		pd = new PercakapanDAO(PercakapanActivity.this);
		listPercakapan = pd.getPercakapan(pilihanBahasa);
		judulPercakapan = new String[listPercakapan.size()];
		for (int i=0; i<listPercakapan.size(); i++){
			judulPercakapan[i] = listPercakapan.get(i).getTerjemahan();
		}
		
		
		EditText etb = (EditText)findViewById(R.id.editTextPilihanPercakapan);
		etb.setOnClickListener(etbListener);
		
		ibms = (ImageButton)findViewById(R.id.iBtnMainkanSuara);
		ibms.setOnClickListener(ibmsListener);
		
		isPlaying = false;
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	OnClickListener etbListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(PercakapanActivity.this);
			builder.setTitle("Pilih Percakapan");
			builder.setItems(judulPercakapan, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					percakapanPilihan = listPercakapan.get(which).getIsi();
					indexPilihan = which;
					
					TableLayout tl = (TableLayout)findViewById(R.id.table_text_percakapan);
					//tl.getWidth()
					
					EditText etb = (EditText)findViewById(R.id.editTextPilihanPercakapan);
					etb.setText(judulPercakapan[which]);
					
					TextView tvp = (TextView)findViewById(R.id.textViewPercakapan);
					tvp.setMaxWidth(tl.getWidth());
					tvp.setText(percakapanPilihan);
					
				}
			}); 
			
			builder.show();
		}
	};

	OnClickListener ibmsListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MediaPlayer mp = null;
			mp = MediaPlayer.create(PercakapanActivity.this, listPercakapan.get(indexPilihan).getSuara());
			/*switch(indexPilihan){
			case 0:
				mp = MediaPlayer.create(PercakapanActivity.this, R.raw.percakapan1);
				break;
			case 1:
				mp = MediaPlayer.create(PercakapanActivity.this, R.raw.percakapan2);
				break;
			case 2:
				mp = MediaPlayer.create(PercakapanActivity.this, R.raw.percakapan3);
				break;
			default
			}*/
			
			if (mp != null && isPlaying){
				mp2.stop(); mp2 = mp; // stop percakapan
				isPlaying = false;
				ibms.setImageResource(R.drawable.bt_mainkan1); // tombol mainkan
				
			} else if (mp != null && !isPlaying){
				mp2 = mp; mp2.start(); // mulai mainkan
				isPlaying = true;
				ibms.setImageResource(R.drawable.bt_hentikan); // tombol hentikan
				
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.percakapan, menu);
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
