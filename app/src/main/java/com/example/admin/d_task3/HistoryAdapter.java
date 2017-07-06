package com.example.admin.d_task3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.load;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    static ArrayList<HistoryData> pHistory;
    int lastPos = 0;



    public HistoryAdapter(ArrayList<HistoryData> pHistory){
        this.pHistory = pHistory;
    }

    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, int position) {
        Log.d("onBindViewHolder", pHistory.size() + "");
        holder.pNameHis.setText(pHistory.get(position).getName());
        lastPos = position;
    }

    @Override
    public int getItemCount() {
        return (null != pHistory?pHistory.size():0);
    }

    public void notifyData(ArrayList<HistoryData> pHistory){
        Log.d("notifyData", pHistory.size() + "");
        this.pHistory = pHistory;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView pNameHis;


        public MyViewHolder(View itemView) {
            super(itemView);
            pNameHis = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
}
