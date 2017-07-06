package com.example.admin.d_task3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.example.admin.d_task3.SplashScreen.pokedex;


public class SearchFragment extends android.support.v4.app.Fragment {

    EditText pName;
    Button search;
    ImageView pokeImg;
    TextView  abi1,wt,ht,hp,pn,typ;
    CardView cv;
    FrameLayout fl;
    GetPD gpd;
    String pSearch;
    PokeDetails pokeDetails;
    boolean check = false;
    HistoryFragment hf = new HistoryFragment();



    public SearchFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savesInstanceState){
        final View view = inflater.inflate(R.layout.fragment_search,container,false);
        pName = (EditText) view.findViewById(R.id.editText);
        search = (Button) view.findViewById(R.id.button);
        pokeImg = (ImageView) view.findViewById(R.id.imageView2);
        abi1 = (TextView) view.findViewById(R.id.textView11);
        pn = (TextView) view.findViewById(R.id.textView3);
        wt = (TextView) view.findViewById(R.id.textView9);
        ht = (TextView) view.findViewById(R.id.textView7);
        typ = (TextView) view.findViewById(R.id.textView8);
        hp = (TextView) view.findViewById(R.id.textView13);
        cv = (CardView) view.findViewById(R.id.cardView);
        fl = (FrameLayout) view.findViewById(R.id.fl);
        fl.setVisibility(View.GONE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(pName.getText())){
                    gpd = new GetPD();
                    gpd.execute();
                } else {
                    Toast.makeText(getContext(),"ENTER TEXT IN FIELD", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public class GetPD extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pSearch = pName.getText().toString();
            pName.setText("");
            fl.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            check = PokeTools.check(pSearch,getContext());
            if(check){
                pokeDetails = PokeTools.getDetails();
            }
            else if(!check){
                Toast.makeText(getContext(),"POKEMON NOT FOUND!",Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if(check){
                setDetails(pokeDetails);
            }
        }
    }

    private void setDetails(PokeDetails p){
        hf.getPHis(pokedex.get(p.index).getPokeName());
        setPokeImage(p.index+1);
        pn.setText(pokedex.get(p.index).getPokeName());
        ht.setText(String.valueOf(p.ht));
        wt.setText(String.valueOf(p.wt));
        typ.setText(p.type);
        hp.setText(String.valueOf(p.hp));
        abi1.setText(p.ab1+""+" , "+""+p.ab2);
        cv.setVisibility(View.VISIBLE);
        fl.setVisibility(View.GONE);
    }

    private void setPokeImage(int index) {

        String iURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+
                String.valueOf(index)+".png";
        Glide
                .with(this)
                .load(iURL)
                .override(1000,800)
                .into(pokeImg);
    }


}