package com.mifeng.us.coordinatorlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mifeng.us.coordinatorlayout.view.DesignScrollView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout title;
    private LinearLayout top;
    private View titleLine1;
    private View titleLine;
    private DesignScrollView scrollView;
    private boolean isTransparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        setTranslucentStatus(this, true);

        title = findViewById(R.id.ll_title);
        title.getBackground().mutate().setAlpha(0);
        top = findViewById(R.id.ll_title_top);
        titleLine1 = findViewById(R.id.v_title_line_1);
        titleLine = findViewById(R.id.v_title_line);
        scrollView = findViewById(R.id.sv_content);

        // 设置状态栏高度
        int statusBarHeight = this.getResources().getDimensionPixelSize(this.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        titleLine.setLayoutParams(params);
        titleLine1.setLayoutParams(params);

        // 设置滑动
        scrollView.setOnScrollistener(new DesignScrollView.OnScrollistener() {

            @Override
            public void onScroll(int startY, int endY) {
                //根据scrollview滑动更改标题栏透明度
                changeAphla(startY, endY);
            }
        });
    }

    /**
     * 根据内容窗体的移动改变标题栏背景透明度
     *
     * @param startY scrollview开始滑动的y坐标（相对值）
     * @param endY   scrollview结束滑动的y坐标（相对值）
     */
    private void changeAphla(int startY, int endY) {
        //获取标题高度
        int titleHeight = title.getMeasuredHeight();
        //获取背景高度
        int backHeight = top.getMeasuredHeight();

        //获取控件的绝对位置坐标
        int[] location = new int[2];
        top.getLocationInWindow(location);
        //从屏幕顶部到控件顶部的坐标位置Y
        int currentY = location[1];
        //表示回到原位（滑动到顶部）
        if (currentY >= 0) {
            title.getBackground().mutate().setAlpha(0);
        }


        Log.e("zpan", "=titleHeight=" + titleHeight + "=backHeight=" + backHeight + "=currentY=" + currentY);
        //颜色透明度改变
        if (currentY < titleHeight && currentY >= -(backHeight - titleHeight)) {
            //计算出滚动过程中改变的透明值
            double y = Math.abs(currentY) * 1.0;
            double height = (backHeight - titleHeight) * 1.0;
            int changeValue = (int) (y / height * 255);

            Log.e("zpan", "changeValue=" + changeValue);
            //判断是向上还是向下
            if (endY > startY) {    //向上;透明度值增加，实际透明度减小
                title.getBackground().mutate().setAlpha(changeValue);
            } else if (endY < startY) {     //向下；透明度值减小，实际透明度增加
                title.getBackground().mutate().setAlpha(changeValue);
            }
        }
        //红色背景移除屏幕
        if (currentY < -(backHeight - titleHeight)) {
            title.getBackground().mutate().setAlpha(255);
        }
    }

    /**
     * 设置状态栏透明及字体颜色
     *
     * @param activity
     * @param isBack 状态栏字体颜色是否设置为黑色
     */
    public void setTranslucentStatus(Activity activity, boolean isBack) {
        Window win = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //保证华为虚拟键盘能显示
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(Color.TRANSPARENT);
//            win.setNavigationBarColor(Color.TRANSPARENT); //保证华为虚拟键盘是系统色
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (isBack){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else{
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }


}
