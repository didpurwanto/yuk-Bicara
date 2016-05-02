package com.yukbicara;

import com.yukbicara.db.DaerahDAO;
import com.yukbicara.db.KosakataDAO;
import com.yukbicara.db.PercakapanDAO;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {
	
	private DaerahDAO dd;
	private KosakataDAO kd;
	private PercakapanDAO pd;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageButton ibk = (ImageButton)findViewById(R.id.iBtnKamus);
        ImageButton ibp = (ImageButton)findViewById(R.id.iBtnPercakapan);
        
        ibk.setOnClickListener( ibkListener );
        ibp.setOnClickListener( ibpListener );
        
        dd = new DaerahDAO(MainActivity.this);
        kd = new KosakataDAO(MainActivity.this);
        pd = new PercakapanDAO(MainActivity.this);
        
        if(dd.getSemuaDaerah().size() == 0){
			dd.loadDaerah();
		}
        if(kd.getAllKosakata().size() == 0){
        	kd.loadKosaKataAceh();
        	Log.d("debug","Aceh selesai");        	
       		kd.loadKosaKataBugis();
        	Log.d("debug","Bugis selesai");
        	kd.loadKosaKataJawa();
        	Log.d("debug","Jawa selesai");
        	kd.loadKosaKataPapua();
        	Log.d("debug","Papua Selesai");
        	kd.loadKosaKataPoso();
        	Log.d("debug","Poso selesai");
        	kd.loadKosaKataSunda();
        	Log.d("debug","Sunda selesai");
        	kd.loadKosaKataOrangRimba();        	
        	Log.d("debug","Orang Rimba selesai");
        	kd.loadKosaKataBugis();
        	Log.d("debug","Bugis selesai");
        	kd.loadKosaKataAmbon();
        	Log.d("debug","Ambon selesai");
        	kd.loadKosaKataBali();
        	Log.d("debug","Baliselesai");        	
        	kd.loadKosaKataMadura();
        	Log.d("debug","Madura selesai");
        	
        	
        }
        if(pd.getAllPercakapan().size() == 0){
        	pd.addPercakapan();
        }
      
    }
    
    OnClickListener ibkListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, KamusActivity.class);
			startActivity(intent);
		}
	};
	
	OnClickListener ibpListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, AwalActivity.class);
			startActivity(intent);
		}
	};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
