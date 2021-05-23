package com.example.paysa_madlab_project;

import android.graphics.drawable.Drawable;

public class Group_detail {
    private String group_id;
    private Integer img_src;

    public Group_detail(String group_id,Integer img_src){
        this.group_id=group_id;
        this.img_src=img_src;
    }
    public void enter_details(String group_id,Integer img_src){
        this.group_id=group_id;
        this.img_src=img_src;
    }

    public String getGroup_id(){
        return this.group_id;
    }

    public int getImg_src(){
        return this.img_src;
    }
}
