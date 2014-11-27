package cl.telematica.tareamultimedios;

import cl.telematica.tareamultimedios.asyncJson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

	  TextView titulors;
	  TextView puntors;
	  TextView linksors;
	  ImageView imageners;
	  //URL to get JSON Array
	  private static String url = "http://www.mocky.io/v2/5440667984d353f103f697c0";
	  //JSON Node Names
	  private static final String TAG_OBJETO ="in";
	  private static final String TAG_TITULO = "title";
	  private static final String TAG_PUNTO = "point";
	  private static final String TAG_LINK = "link";
	  private static final String TAG_IMAGEN = "image";
	  Button Btngetdata;
	  JSONArray post = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
             new JSONParse().execute();
      }
    });
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {
	       private ProgressDialog pDialog;
	      @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
//		         Toast.makeText(getApplicationContext()," Guardado!", Toast.LENGTH_SHORT).show();

	             titulors = (TextView)findViewById(R.id.titulo);
	             puntors = (TextView)findViewById(R.id.puntos);
	             imageners = (ImageView)findViewById(R.id.imagen); 
	             
	            pDialog = new ProgressDialog(MainActivity.this);
	            pDialog.setMessage("Getting Data ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	      }
	      @Override
	        protected JSONObject doInBackground(String... args) {
	        JSONParser jParser = new JSONParser();
	        // Getting JSON from URL
	        JSONObject json = jParser.getJSONFromUrl(url);
	        return json;
	      }
	       @Override
	         protected void onPostExecute(JSONObject json) {
	         pDialog.dismiss();
	         //Toast.makeText(getApplicationContext()," Guardado!", Toast.LENGTH_SHORT).show();
	        
	         try {
	            // Getting JSON Array
	            post = json.getJSONArray(TAG_OBJETO);
	            JSONObject c = post.getJSONObject(0);
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
	    }



}
