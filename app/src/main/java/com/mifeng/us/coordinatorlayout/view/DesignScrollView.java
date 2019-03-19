package com.mifeng.us.coordinatorlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by night_slight on 2019/3/15.
 */

public class DesignScrollView extends ScrollView{
    private OnScrollistener onScrollistener;

    public OnScrollistener getOnScrollistener() {
        return onScrollistener;
    }

    public void setOnScrollistener(OnScrollistener onScrollistener) {
        this.onScrollistener = onScrollistener;
    }

    public DesignScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DesignScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DesignScrollView(Context context) {
        super(context);
    }

    public interface OnScrollistener {

        void onScroll(int startY, int endY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        onScrollistener.onScroll(oldt, t);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
