package com.example.admin.d_task3;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    private SectionPageAdapter sectionPageAdapter;
    private ViewPager mviewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_search_white_18dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_history_white_18dp);


    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(),"Search");
        adapter.addFragment(new HistoryFragment(),"History");
        viewPager.setAdapter(adapter);
    }


}
