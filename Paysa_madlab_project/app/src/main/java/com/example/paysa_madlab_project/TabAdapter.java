package com.example.paysa_madlab_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {
    private Integer count;
    public TabAdapter(FragmentActivity frag_activity, int count)
    {
        super(frag_activity);
        this.count=count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment=null;
        switch(position)
        {
            case 0: fragment=new expenses();
                break;
            case 1: fragment=new income();
                break;
            case 2: fragment=new groups();
                    break;
            default: fragment=null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return count;
    }
}
