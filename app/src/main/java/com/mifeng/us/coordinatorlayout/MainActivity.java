package com.mifeng.us.coordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mifeng.us.coordinatorlayout.adapter.CorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String>mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        TextView mDisignTxt = findViewById(R.id.mDesignTxt);
        for(int i=0;i<20;i++){
            mList.add("测试，测试，测试"+i);
        }
        CorAdapter mAdapter = new CorAdapter(mList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
