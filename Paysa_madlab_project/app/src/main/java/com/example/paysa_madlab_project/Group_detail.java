package com.example.paysa_madlab_project;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class Group_detail {
    private String group_id;
    private Integer img_src;
    private String group_name;
    private static HashMap <View, ArrayList<String>> view_to_participant=new HashMap<View,ArrayList<String>>();
    public Group_detail(String group_id,Integer img_src){
        this.group_id=group_id;
        this.img_src=img_src;
    }
    public Group_detail(String group_name){
        this.group_name=group_name;
        this.img_src=R.drawable.ic_participant_add_icon;
    }
    public void enter_details(String group_id,Integer img_src){
        this.group_id=group_id;
        this.img_src=img_src;
    }
    public static ArrayList<String> getParticipants(View v){
        return view_to_participant.get(v);
    }
    public static void add_item(View view,ArrayList<String> participants){
        view_to_participant.put(view,participants);
    }

    public String getGroup_id(){
        return this.group_id;
    }

    public int getImg_src(){
        return this.img_src;
    }
}
