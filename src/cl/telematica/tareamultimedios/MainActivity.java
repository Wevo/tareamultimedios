package cl.telematica.tareamultimedios;

import cl.telematica.tareamultimedios.asyncJson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements DownloadListener {

	TextView titulors;
	  TextView puntors;
	  TextView linksors;
	  ImageView imageners;
	  //URL to get JSON Array
	  private static String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
	  //JSON Node Names
	  private static final String TAG_OBJETO ="in";
	  private static final String TAG_TITULO = "title";
	  private static final String TAG_PUNTO = "points";
	  private static final String TAG_LINK = "link";
	  private static final String TAG_IMAGEN = "image";
	  Button Btngetdata;
	  JSONArray post = null;
	  
	  private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		titulors = (TextView)findViewById(R.id.titulo);
      puntors = (TextView)findViewById(R.id.puntos);
      imageners = (ImageView)findViewById(R.id.imagen); 
      
      pDialog = new ProgressDialog(MainActivity.this);
      pDialog.setMessage("Getting Data ...");
      pDialog.setIndeterminate(false);
      pDialog.setCancelable(true);
		
		Btngetdata = (Button)findViewById(R.id.getdata);
      Btngetdata.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View view) {
	             new DownloadManager(MainActivity.this, 2000, 7000, "GET").execute(url);
	      }
	    });
	}

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

	@Override
	public void onRequestStart() {
		// TODO Auto-generated method stub
		if(pDialog != null && !pDialog.isShowing()){
			pDialog.show();
		}
	}

	@Override
	public void onRequestComplete(String data) {
		// TODO Auto-generated method stub
		if(pDialog != null && pDialog.isShowing()){
			pDialog.dismiss();
		}
		try {
		  JSONArray json = new JSONArray(data);
          // Getting JSON Array
          JSONObject c = json.getJSONObject(0);
          // Storing  JSON item in a Variable
          String titulo = c.getString(TAG_TITULO);
          String punto = c.getString(TAG_PUNTO);
          String link = c.getString(TAG_LINK);
          String imagen = c.getString(TAG_IMAGEN); //quiza no??
          
          //Set JSON Data in TextView
          titulors.setText(titulo);
          puntors.setText(punto);
         // linksors.setText(link);
          //imageners.setIma??
      } catch (JSONException e) {
        e.printStackTrace();
      }
	}

	@Override
	public void onError(String error, int code) {
		// TODO Auto-generated method stub
		
	}  
	
}
