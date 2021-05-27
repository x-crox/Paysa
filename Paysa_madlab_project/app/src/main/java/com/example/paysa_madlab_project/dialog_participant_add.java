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

public class dialog_participant_add extends AppCompatDialogFragment {
    NeumorphButton participant_add;
    Button cancel_add_participant;
    EditText participant_email;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_dialog_participant_add, null);
        participant_add=view.findViewById(R.id.participant_add);
        cancel_add_participant=view.findViewById(R.id.cancel_add_participant);
        participant_email=view.findViewById(R.id.participant_email);

        participant_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(participant_email.getText().toString().length()>0){
                    try{
                        Statement st = MainActivity.conn.createStatement();
                        int count = st.executeUpdate("INSERT INTO GroupMembers VALUES ('" + Group_detail.getGroup_name(Group_page.prev_view) + "', '" + participant_email.getText().toString() + "')");
                        if (count > 0) {
                            Toast.makeText(getContext(), "New group member added!",Toast.LENGTH_SHORT).show();
                            Group_detail.getParticipants(Group_page.prev_view).add(participant_email.getText().toString());
                        } else {
                            Toast.makeText(getContext(), "Failed to add!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception sqlException){
                        sqlException.printStackTrace();
                    }
                    Group_page.participant_grid.setAdapter(new participant_add_adapter(getContext(),Group_detail.getParticipants(Group_page.prev_view)));
                    Group_page.dpa.dismiss();
                }
            }
        });

        cancel_add_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group_page.dpa.dismiss();
            }
        });
        builder.setView(view);
        return builder.create();


    }
}
