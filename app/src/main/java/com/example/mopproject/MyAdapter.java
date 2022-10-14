package com.example.mopproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    HomeFragment homeFragment;
    LayoutInflater layoutInflater = null;
    ArrayList<SamsungItems> item;

    public MyAdapter(HomeFragment homeFragment, ArrayList<SamsungItems> data) {
        homeFragment = homeFragment;
        item = data;
        layoutInflater = LayoutInflater.from(homeFragment.getContext());
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SamsungItems getItem(int position) {
        return item.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.homefragment_listview, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.samsungItem);
        TextView itemName = (TextView)view.findViewById(R.id.itemName);
        TextView itemPrice = (TextView)view.findViewById(R.id.itemPrice);

        imageView.setImageResource(item.get(position).getImage());
        itemName.setText(item.get(position).getName());
        itemPrice.setText(item.get(position).getPrice());

        return view;
    }
}