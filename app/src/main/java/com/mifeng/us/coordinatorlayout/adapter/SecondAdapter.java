package com.mifeng.us.coordinatorlayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mifeng.us.coordinatorlayout.R;

import java.util.ArrayList;

/**
 * Created by night_slight on 2019/3/18.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.VhHolder>{

    private ArrayList<String>mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    public SecondAdapter(ArrayList<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.second_item,viewGroup,false);
        VhHolder vhHolder = new VhHolder(view);
        return vhHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder vh, int i) {
        vh.mValue.setText(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class VhHolder extends RecyclerView.ViewHolder{

        TextView mValue;
        public VhHolder(@NonNull View itemView) {
            super(itemView);
            mValue = itemView.findViewById(R.id.mValue);
        }
    }
}
