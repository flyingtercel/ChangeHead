package com.mifeng.us.coordinatorlayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mifeng.us.coordinatorlayout.adapter.SecondAdapter;
import com.mifeng.us.coordinatorlayout.base.BaseActivity;
import com.mifeng.us.coordinatorlayout.view.DesignScrollView;

import java.util.ArrayList;

public class SecondActivity extends BaseActivity {

    private RelativeLayout mLayout01;
    private TextView mTopLayout;
    private ImageView mHeadIcon;
    private RecyclerView mRecyclerView;
    private DesignScrollView mScrollView;

    private ArrayList<String>mDatas = new ArrayList<>();
    private int totalHeight;
    private View mIndicate;
    private RelativeLayout mBgIndicate;
    private LinearLayoutManager mCumanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setStatusBar();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int widthPixels = dm.widthPixels;

        initView();
    }

    private void initView() {

        mScrollView = findViewById(R.id.mScrollView);
        mLayout01 = findViewById(R.id.mConstraintLayout01);
        mTopLayout = findViewById(R.id.mTopLayout);
        mHeadIcon = findViewById(R.id.mHeadIcon);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mIndicate = findViewById(R.id.mIndicate);
        mBgIndicate = findViewById(R.id.mBgIndicate);
        for(int i=0;i<10;i++){
            mDatas.add("数据"+i);
        }
        mCumanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mCumanager);

        SecondAdapter mAdapter = new SecondAdapter(mDatas,this);
        mRecyclerView.setAdapter(mAdapter);

        onListener();
    }

    private void onListener() {

        changeColor();
        mScrollView.setOnScrollistener(new DesignScrollView.OnScrollistener() {
            @Override
            public void onScroll(int startY, int endY) {
                changeColor();
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int roateDx = 0; //滑动宽度
            private int allWidth = 0; //总宽度
            private int mRecyWidth = 0;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                roateDx += dx;
                allWidth = 0;
                mRecyWidth = mRecyclerView.getWidth();
                int itemCount = mCumanager.getItemCount(); //总数量
                int itemWidth = mCumanager.getChildAt(0).getWidth();
                for(int i=0;i<itemCount;i++){
                    allWidth +=itemWidth;
                }
                int width = mBgIndicate.getWidth();
                ViewPropertyAnimator animate = mIndicate.animate();
                float scaleX = (roateDx*1.0f) / (allWidth-mRecyWidth);
                animate.translationX(((width-mIndicate.getWidth())*scaleX));
                animate.start();

            }
        });

    }

    private void changeColor() {
        int[] location = new int[2];
        mHeadIcon.getLocationInWindow(location);
        int currentY = location[1];
        Log.i("TAG", "==============" + currentY);
        if (currentY >= 0) {
            mLayout01.setVisibility(View.VISIBLE);
            setTranslucentStatus(false);
        } else {
            mLayout01.setVisibility(View.INVISIBLE);
            setTranslucentStatus(true);
        }
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void setTranslucentStatus(boolean isBack) {

        if (isBack) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }


}
