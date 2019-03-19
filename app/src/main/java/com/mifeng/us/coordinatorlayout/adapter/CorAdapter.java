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
 * Created by night_slight on 2019/3/15.
 */

public class CorAdapter  extends RecyclerView.Adapter<CorAdapter.VHolder>{

    private ArrayList<String>mList;
    private LayoutInflater inflater;

    public CorAdapter(ArrayList<String> mList, Context context) {
        this.mList = mList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.cor_item,viewGroup,false);
        VHolder holder = new VHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vh, int i) {
        vh.mCorText.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VHolder extends RecyclerView.ViewHolder{
        TextView mCorText;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            mCorText = itemView.findViewById(R.id.mCorText);
        }
    }
}
