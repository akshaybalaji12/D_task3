package com.example.admin.d_task3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SplashScreen extends Activity {

    private ProgressBar progressBar;
    private static String url,durl;
    private String TAG = MainActivity.class.getSimpleName();


    static ArrayList<PokeData> pokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pokedex = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new GetDetails().execute();
                doWork();
                startApp();
                finish();
            }
        }).start();

    }

    private void doWork(){
        for (int progress=0; progress<100; progress+=10){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void startApp(){
        Intent i = new Intent(SplashScreen.this,MainActivity.class);
        startActivity(i);
    }

    private class GetDetails extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            for (int k = 0; k<820; k+=20){
                url = "http://pokeapi.co/api/v2/pokemon/?offset="+ String.valueOf(k);
                HttpHandler sh = new HttpHandler();
                String jSonStr = sh.makeServiceCall(url);
                Log.e(TAG,"Response from url: " + jSonStr);

                if(jSonStr!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(jSonStr);

                        JSONArray results = jsonObject.getJSONArray("results");

                        for(int i=0;i<results.length();i++){
                            JSONObject res = results.getJSONObject(i);

                            String pokeURL = res.getString("url");
                            String pokeName = res.getString("name");

                            PokeData result = new PokeData();

                            result.setURL(pokeURL);
                            result.setName(pokeName);

                            pokedex.add(result);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG,"Parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Parsing error: " + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else{
                    Log.e(TAG,"Couldn't get JSON from server");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Couldn't get JSON from server",Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }

            }
            return null;
        }
    }

}
