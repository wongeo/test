package com.feng.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by feng on 2017/6/29.
 */

public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LayoutInflater mInflater;
    private String mTitle;

    public RecyclerViewFragment() {

    }

    @SuppressLint("ValidFragment")
    public RecyclerViewFragment(String title) {
        mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        mRecyclerView = new RecyclerView(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setAdapter(mRecycleAdapter);
        return mRecyclerView;
    }

    private RecyclerView.Adapter mRecycleAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String text = "Item:" + position;
            if (mTitle != null) {
                text = mTitle + " " + text;
            }
            ((MyViewHolder) holder).tv.setText(text);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
