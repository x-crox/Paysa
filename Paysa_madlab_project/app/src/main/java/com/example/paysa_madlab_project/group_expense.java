package com.example.paysa_madlab_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class group_expense extends AppCompatDialogFragment{
    NeumorphButton add_amount;
    Button cancel_add_amount;
    EditText amount_to_be_paid;
    static TextView amt_in_debt;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_group_expense, null);

        add_amount=view.findViewById(R.id.add_amount);
        cancel_add_amount=view.findViewById(R.id.cancel_add_amount);
        amount_to_be_paid=view.findViewById(R.id.amount_to_be_paid);

        add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount_to_be_paid.getText().toString().length()>0){
                    Float amt=Float.parseFloat(amt_in_debt.getText().toString())-Float.parseFloat(amount_to_be_paid.getText().toString());
                    amt_in_debt.setText(amt.toString());
                    Group_page.grp_expense.dismiss();
                }
            }
        });
        cancel_add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group_page.grp_expense.dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
