package com.mifeng.us.coordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        initView();
    }

    private void initView() {

        TextView mChangeHead = findViewById(R.id.mChangeHead);
        TextView mCloseHead = findViewById(R.id.mCloseHead);
        TextView mHorital = findViewById(R.id.mHorital);

        mChangeHead.setOnClickListener(v->{
            startActivity(new Intent(HelloActivity.this,HomeActivity.class));
        });
        mCloseHead.setOnClickListener(v -> {
            startActivity(new Intent(HelloActivity.this,MainActivity.class));
        });
        mHorital.setOnClickListener(v -> {
            startActivity(new Intent(HelloActivity.this,SecondActivity.class));
        });

    }
}
