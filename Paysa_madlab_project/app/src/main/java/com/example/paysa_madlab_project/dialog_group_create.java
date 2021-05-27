package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import soup.neumorphism.NeumorphButton;

public class dialog_group_create extends AppCompatDialogFragment {
    NeumorphButton add_group;
    Button cancel_group_pop;
    EditText group_name;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_dialog_group_create,null);

        add_group=view.findViewById(R.id.add_group);
        cancel_group_pop=view.findViewById(R.id.cancel_group_pop);
        group_name=view.findViewById(R.id.group_name);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

        add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groups.gd.add(new Group_detail(group_name.getText().toString()));
                groups.group_grid.setAdapter(new GroupAdapter(getContext(),groups.gd));
                MainActivity.dgc.dismiss();
            }
        });

        cancel_group_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dgc.dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
