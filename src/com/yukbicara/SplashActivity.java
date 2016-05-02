package com.yukbicara;

import com.yukbicara.db.DaerahDAO;
import com.yukbicara.db.KosakataDAO;
import com.yukbicara.db.PercakapanDAO;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {
	
	/*private DaerahDAO dd;
	private KosakataDAO kk;
	private PercakapanDAO pk;*/
	private static int SPLASH_TIME_OUT = 500; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);		
		
		/*dd = new DaerahDAO(SplashActivity.this);
		kk = new KosakataDAO(SplashActivity.this);
		pk = new PercakapanDAO(SplashActivity.this);
		
		if(dd.getSemuaDaerah().size()==0){
			dd.loadDaerah();
		}
		if(kk.getAllKosakata().size()==0){
			kk.loadKosaKataAceh();
        	Log.d("debug","Aceh selesai");        	
       		kk.loadKosaKataBugis();
        	Log.d("debug","Bugis selesai");
        	kk.loadKosaKataJawa();
        	Log.d("debug","Jawa selesai");
        	kk.loadKosaKataPapua();
        	Log.d("debug","Papua Selesai");
			
		}
		if(pk.getAllPercakapan().size()==0){
			pk.addPercakapan();
		}
		
		Intent i = new Intent(SplashActivity.this,MainActivity.class);*/
		new Handler().postDelayed(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				finish();
				
			}
		}, SPLASH_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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
