package com.example.paysa_madlab_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class homepage extends AppCompatActivity {

    TabLayout tab;
    ViewPager2 vpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        tab=findViewById(R.id.tab);
        vpager=findViewById(R.id.viewpager);

        vpager.setAdapter(new TabAdapter(this,tab.getTabCount()));
        new TabLayoutMediator(tab, vpager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0: tab.setText("Expenses");
                        break;
                    case 1: tab.setText("Income");
                        break;
                    case 2: tab.setText("Groups");
                        break;
                    case 3: tab.setText("My Profile");
                     break;
                }
            }
        }).attach();
    }
}