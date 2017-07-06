package com.example.admin.d_task3;


import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.admin.d_task3.SplashScreen.pokedex;
import static java.security.AccessController.getContext;

public class PokeTools {

    public static boolean re = false;
    public static String temp = "temp";
    static int i=0;

    public static boolean check(String pSearch,Context context){
        for(i=0; i< pokedex.size(); i++){
            if(pSearch.equalsIgnoreCase(pokedex.get(i).getPokeName())){
                temp = pokedex.get(i).getPokeName();
                break;
            }
            else{
                temp="temp";
            }
        }

        if(!temp.equals("temp")){
            re = true;
        }
        else {
            Toast.makeText(context,"POKEMON NOT FOUND",Toast.LENGTH_SHORT).show();
        }

        return re;
    }

    public static PokeDetails getDetails(){

        PokeDetails pd = new PokeDetails();
        HttpHandler hh = new HttpHandler();
        String jSonstr = hh.makeServiceCall(pokedex.get(i).getPokeURL());
        if(jSonstr != null)
            try {

                JSONObject poke = new JSONObject(jSonstr);

                JSONArray abilities = poke.getJSONArray("abilities");
                    JSONObject a = abilities.getJSONObject(0);
                    JSONObject ability = a.getJSONObject("ability");
                    pd.ab1 = ability.getString("name");
                    JSONObject a1 = abilities.getJSONObject(1);
                    JSONObject ability1 = a1.getJSONObject("ability");
                    pd.ab2 = ability1.getString("name");

                JSONArray types = poke.getJSONArray("types");
                    JSONObject t = types.getJSONObject(0);
                    JSONObject type = t.getJSONObject("type");
                    pd.type = type.getString("name");

                pd.ht = poke.getInt("height");
                pd.wt = poke.getInt("weight");
                pd.index = i;

                JSONArray stats = poke.getJSONArray("stats");
                    JSONObject stat = stats.getJSONObject(stats.length()-1);
                    pd.hp = stat.getInt("base_stat");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        return pd;
    }
}
