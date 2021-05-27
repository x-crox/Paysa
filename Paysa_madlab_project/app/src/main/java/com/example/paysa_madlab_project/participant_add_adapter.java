package com.example.paysa_madlab_project;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class participant_add_adapter extends ArrayAdapter<String> {
    private Context context;
    public participant_add_adapter(@NonNull Context context, ArrayList<String> participants) {
        super(context, 0,participants);
        //Group_page.view_to_participant_detail.clear();
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View grid_item=convertView;
        if(convertView==null){
            grid_item= LayoutInflater.from(context).inflate(R.layout.group_layout,parent,false);
        }
        String details=(String) getItem(position);
        Group_page.view_to_participant_detail.put(grid_item,details);
        ImageView group_dp =grid_item.findViewById(R.id.group_dp);
        group_dp.setImageResource(R.drawable.ic_baseline_account_circle_24);
        group_dp.setForegroundGravity(Gravity.CENTER);
        return grid_item;
    }
}
