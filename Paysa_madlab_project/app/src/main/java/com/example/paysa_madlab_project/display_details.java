package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class display_details  extends AppCompatDialogFragment {
    static String details=null;
    TextView text_id;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_display_details,null);
        text_id=view.findViewById(R.id.participant_id);
        text_id.setText(details);
        System.out.println("check string: "+details);
        builder.setView(view);
        return builder.create();
    }

    public static void setDetail(String strdetails){
        details=strdetails;
    }
}
