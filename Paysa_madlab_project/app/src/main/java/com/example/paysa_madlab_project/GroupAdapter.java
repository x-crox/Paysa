package com.example.paysa_madlab_project;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import soup.neumorphism.NeumorphImageButton;

public class GroupAdapter extends BaseAdapter {
    ArrayList<Group_detail> group_details;
    Context context;
    public GroupAdapter(@NonNull Context context, ArrayList<Group_detail> group_info) {
        this.group_details=group_info;
        this.context=context;
    }

    @Override
    public int getCount() {
        return group_details.size();
    }

    @Override
    public Object getItem(int position) {
        return group_details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View grid_item=convertView;
        if(convertView==null){
            grid_item= LayoutInflater.from(context).inflate(R.layout.group_layout,parent,false);
        }
        Group_detail imgMap=(Group_detail) getItem(position);
        ImageView group_dp =grid_item.findViewById(R.id.group_dp);
        group_dp.setImageResource(imgMap.getImg_src());
        group_dp.setForegroundGravity(Gravity.CENTER);
        return grid_item;
    }
}
