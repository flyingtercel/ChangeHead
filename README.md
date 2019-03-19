# ChangeHead
滑动改变状态栏布局颜色及状态栏字体颜色

### 效果展示
![image](https://github.com/flyingtercel/ChangeHead/blob/master/img/slc.gif)
### 设置状态栏为透明的
```
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

```
### 自定义ScrollView重写onScrollChanged方法，监听滑动距离
```
public interface OnScrollistener {
        void onScroll(int startY, int endY);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onScrollistener != null){
            onScrollistener.onScroll(oldt, t);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }
    
```

### 根据滑动距离修改状态栏颜色和头部颜色
```
  private void changeColor() {
        //获取滑动布局头部到顶部的距离
        int[] location = new int[2];
        mHeadIcon.getLocationInWindow(location);
        int currentY = location[1];
        //如果滑动距离到顶部的距离为0则设置控件可见或者改变背景颜色
        if (currentY >= 0) {
            mLayout01.setBackgroundColor(Color.parseColor("#aa834a"));
            setTranslucentStatus(false);
        } else {
            mLayout01.setBackgroundColor(Color.parseColor("#FFFFFF"));
            setTranslucentStatus(true);
        }
    }

```
### 改变状态栏的字体颜色
```
  public void setTranslucentStatus(boolean isBack) {
        //只有安卓6.0之上才可以做状态栏字体颜色修改
        if (isBack) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
    
```
### 实现RecyclerVew的横向滑动,及适配器绑定
```
 for(int i=0;i<10;i++){
            mDatas.add("数据"+i);
        }
        mCumanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mCumanager);
        SecondAdapter mAdapter = new SecondAdapter(mDatas,this);
        mRecyclerView.setAdapter(mAdapter);

```
### 在RecyclerView横向滑动的时候实现监听，并且绑定底部自己定义的进度条，

```
  mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int roateDx = 0; //滑动宽度
            private int allWidth = 0; //总宽度
            private int mRecyWidth = 0;//RecyclerView的屏幕宽度
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

```
