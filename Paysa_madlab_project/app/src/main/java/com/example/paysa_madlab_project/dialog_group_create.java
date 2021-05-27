package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.sql.ResultSet;
import java.sql.Statement;

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
                try{
                    Statement st = MainActivity.conn.createStatement();
                    int count = st.executeUpdate("INSERT INTO Groups VALUES ('" +  group_name.getText().toString() + "')");
                    System.out.println("INSERT INTO Groups VALUES ('" +  group_name.getText().toString() + "')");
                    if (count > 0) {
                        Toast.makeText(getContext(), "Group created!",Toast.LENGTH_SHORT).show();
                        System.out.println("INSERT INTO GroupMembers VALUES ('" + group_name.getText().toString() + "', '" + MainActivity.PaysaEmail + "')");
                        count = st.executeUpdate("INSERT INTO GroupMembers VALUES ('" + group_name.getText().toString() + "', '" + MainActivity.PaysaEmail + "')");
                        if (count > 0) {
                            Toast.makeText(getContext(), "Added you to group!",Toast.LENGTH_SHORT).show();
                            groups.gd.add(new Group_detail(group_name.getText().toString()));
                        } else {
                            Toast.makeText(getContext(), "Failed to add you to group!",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Failed create group!",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception sqlException){
                    sqlException.printStackTrace();
                }
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
