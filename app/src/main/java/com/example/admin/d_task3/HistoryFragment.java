package com.example.admin.d_task3;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class HistoryFragment extends android.support.v4.app.Fragment {

    private FloatingActionButton deleteAll;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HistoryAdapter historyAdapter;
    static ArrayList<HistoryData> pHistory = new ArrayList<>();




    public HistoryFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savesInstanceState){
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        deleteAll = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) view.findViewById(R.id.history);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        historyAdapter = new HistoryAdapter(pHistory);
        recyclerView.setAdapter(historyAdapter);


        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pHistory.clear();
                historyAdapter.notifyDataSetChanged();

                Snackbar snackbar = Snackbar
                        .make(getView(),"HISTORY CLEARED",Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(Color.WHITE);
                View sview = snackbar.getView();
                TextView t = (TextView) sview.findViewById(android.support.design.R.id.snackbar_text);
                t.setTextColor(Color.CYAN);
                snackbar.show();
            }
        });




        ItemTouchHelper.SimpleCallback itemCallBack =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        final int position = viewHolder.getAdapterPosition();
                        pHistory.remove(position);
                        historyAdapter.notifyItemRemoved(position);
                        Snackbar sb = Snackbar
                                .make(getView(),"ITEM REMOVED",Snackbar.LENGTH_SHORT);
                        View sbview = sb.getView();
                        TextView te = (TextView) sbview.findViewById(android.support.design.R.id.snackbar_text);
                        te.setTextColor(Color.CYAN);
                        sb.show();
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;

    }

    public void getPHis(String pName){
        HistoryData his = new HistoryData();
        his.setName(pName);
        pHistory.add(his);
    }
}
